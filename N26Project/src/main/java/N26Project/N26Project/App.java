package N26Project.N26Project;


import java.util.*;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Hello world!
 *
 */
public class App extends FunctionalTest
{	
	  
	@Test
	public void highlyPricedProduct() {
		RequestSpecification httpRequest = RestAssured.given().header("Cache-Control", "no-Cache");
		httpRequest.baseUri("http://localhost:3030");
		httpRequest.basePath("/products"); 
		httpRequest.queryParam("$sort[price]", "-1");
		Response response = httpRequest.request(Method.GET);
		Assert.assertEquals(200, response.getStatusCode());
		List<Float> listOfAllProduct = response.getBody().jsonPath().getList("data.price");
		List<Float> clonedList = new ArrayList<Float>();
		clonedList.addAll(listOfAllProduct);
		Collections.sort(clonedList, Collections.reverseOrder());
		Assert.assertEquals(clonedList, listOfAllProduct);
		
	}
	
	@Test
	public void onlyProductAndDescription() {
		RequestSpecification httpRequest = RestAssured.given().header("Cache-Control", "no-Cache");
		httpRequest.baseUri("http://localhost:3030");
		httpRequest.basePath("/products"); 
		httpRequest.queryParam("$select[]", "name");
		httpRequest.queryParam("$select[]", "description");
		Response response = httpRequest.request(Method.GET);
		Assert.assertEquals(200, response.getStatusCode());
		List<Object> listOfAllProduct = response.getBody().jsonPath().getList("data");
		Set<String> keys = new HashSet<String>();
		for (Object product : listOfAllProduct) {
		    Map<String,String> productMap = (Map<String,String>)(product);
		    keys.addAll(productMap.keySet());
		}
		Assert.assertTrue(keys.size() == 2);
		Assert.assertTrue(keys.contains("name"));
		Assert.assertTrue(keys.contains("description"));
	}
	
	@Test
	public void tvWithFreeShippingAndPriceBetween500And800() {
		RequestSpecification httpRequest = RestAssured.given().header("Cache-Control", "no-Cache");
		httpRequest.baseUri("http://localhost:3030");
		httpRequest.basePath("/products"); 
		httpRequest.queryParam("category.name", "TVs");
		httpRequest.queryParam("price[$gt]", "500");
		httpRequest.queryParam("price[$lt]", "800");
		httpRequest.queryParam("shipping[$eq]", "0");
		Response response = httpRequest.request(Method.GET);
		Assert.assertEquals(200, response.getStatusCode());
		List<String> nameOfProducts = response.getBody().jsonPath().getList("data.name");
		Boolean isTVInProductName = true;
		for(String nameOfProduct: nameOfProducts) {
			if(!nameOfProduct.contains("TV")) 
				isTVInProductName =  false;
		}
		
		Assert.assertTrue(isTVInProductName);
		
		
		List<Float> priceOfProducts = response.getBody().jsonPath().getList("data.price");
		Boolean isPriceBW500To800 = true;
		for(Float priceOfProduct : priceOfProducts) {
			if(!(priceOfProduct > 500 && priceOfProduct < 800)) 
				isPriceBW500To800 =  false;
		}
		
		Assert.assertTrue(isPriceBW500To800);
		
	}
	
	@Test
	public void onlyCatagoryName() {
		RequestSpecification httpRequest = RestAssured.given().header("Cache-Control", "no-Cache");
		httpRequest.baseUri("http://localhost:3030");
		httpRequest.basePath("/categories"); 
		httpRequest.queryParam("$select[]", "name");
		Response response = httpRequest.request(Method.GET);
		Assert.assertEquals(200,response.getStatusCode());
		List<Object> listOfAllCatagory = response.getBody().jsonPath().getList("data");
		Set<String> keys = new HashSet<String>();
		for (Object catagory : listOfAllCatagory) {
		    Map<String,String> catagoryMap = (Map<String,String>)(catagory);
		    keys.addAll(catagoryMap.keySet());
		}
		Assert.assertTrue(keys.size() == 1);
		Assert.assertTrue(keys.contains("name"));
		
	}
	
	@Test
	public void catogoryWithTVInTheName() {
		RequestSpecification httpRequest = RestAssured.given().header("Cache-Control", "no-Cache");
		httpRequest.baseUri("http://localhost:3030");
		httpRequest.basePath("/categories"); 
		httpRequest.queryParam("name[$like]", "*TV*");
		Response response = httpRequest.request(Method.GET);
		Assert.assertEquals(200, response.getStatusCode());
		List<String> nameOfCategories = response.getBody().jsonPath().getList("data.name");
		Boolean isTVInCategoryName = true;
		for(String nameOfCategory: nameOfCategories) {
			if(!nameOfCategory.contains("TV")) 
				isTVInCategoryName =  false;
		}
		
		Assert.assertTrue(isTVInCategoryName);
		
	}
	 
	@Test
	public void storesInMinnesota() {
		RestAssured.baseURI = "http://localhost:3030";
		RestAssured.basePath = "/stores";
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.queryParam("state", "MN");
		Response response = httpRequest.request(Method.GET);
		Assert.assertEquals(200, response.getStatusCode());
		List<String> storesInMinnesota = response.getBody().jsonPath().getList("data.state");
		Boolean isStateMN = true;
		for(String storeInMinnesota: storesInMinnesota) {
			if(!storeInMinnesota.contentEquals("MN")) 
				isStateMN =  false;
		}
		
		Assert.assertTrue(isStateMN);
		
	}
	
	@Test
	public void storesThatSellAppleProducts() {
		RestAssured.baseURI = "http://localhost:3030";
		RestAssured.basePath = "/stores";
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.queryParam("service.name", "Apple Shop");
		Response response = httpRequest.request(Method.GET);
		Assert.assertEquals(200, response.getStatusCode());
		List<List<String>> storesSellingApple = response.getBody().jsonPath().getList("data.services.name");
		Boolean isAppleStore = true;
		for(List<String> servicesWithApple: storesSellingApple) {
			if(!servicesWithApple.contains("Apple Shop")) 
				isAppleStore =  false;
		}
		
		Assert.assertTrue("Not Everything Listed has AppleStore", isAppleStore);
	}
	
	@Test
	public void storesWithin10Miles() {
		RestAssured.baseURI = "http://localhost:3030";
		RestAssured.basePath = "/stores";
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.queryParam("near", "90210");
		httpRequest.queryParam("service.name", "Windows Store");
		Response response = httpRequest.request(Method.GET);
		Assert.assertEquals(200, response.getStatusCode());
		
		List<List<String>> windowStores = response.getBody().jsonPath().getList("data.services.name");
		
		Boolean isWindowStore = true;
		for(List<String> windowStore: windowStores) {
			if(!windowStore.contains("Windows Store")) 
				isWindowStore =  false;
		}
		
		Assert.assertTrue("Not Everything Listed has Window Store", isWindowStore);
	}
	
	@Test
	public void createNewProduct() {
		RestAssured.baseURI ="http://localhost:3030";
		RestAssured.basePath = "/products";
		RequestSpecification request = RestAssured.given();
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", "New Product"); 
		requestParams.put("type", "Hard Good");
		 
		requestParams.put("upc", "12345676");
		requestParams.put("price", 99.99);
		requestParams.put("description",  "This is a placeholder request for creating a new product.");
		requestParams.put("model", "NP12345");
		
		request.header("Content-Type", "application/json");
		request.body(requestParams.toJSONString());
		 
		// Post the request and check the response
		Response response = request.post();
		
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 201);
	}
	
	@Test
	public void createNewStore() {
		RestAssured.baseURI ="http://localhost:3030";
		RestAssured.basePath = "/stores";
		RequestSpecification request = RestAssured.given();
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", "New Store"); 
		requestParams.put("type", "BigBox");
		 
		requestParams.put("address", "123 Fake St");
		requestParams.put("address2", "	");
		requestParams.put("city", "Springfield");
		requestParams.put("state", "MN");
		requestParams.put("zip", "55123");
		requestParams.put("lat", 44.969658);
		requestParams.put("lng", -93.449539);
		requestParams.put("hours", "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8");
		
		request.header("Content-Type", "application/json");
		request.body(requestParams.toJSONString());
		 
		// Post the request and check the response
		Response response = request.post();
		
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 201);
	}
	
	@Test
	public void createNewService() {
		RestAssured.baseURI ="http://localhost:3030";
		RestAssured.basePath = "/services";
		RequestSpecification request = RestAssured.given();
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", "New Service"); 
	
		request.header("Content-Type", "application/json");
		request.body(requestParams.toJSONString());
		 
		// Post the request and check the response
		Response response = request.post();
		
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 201);
	}
	
	@Test
	public void createNewCategory() {
		RestAssured.baseURI ="http://localhost:3030";
		RestAssured.basePath = "/categories";
		RequestSpecification request = RestAssured.given();
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("id", "pcmcat12345" + UUID.randomUUID().toString()); 
		requestParams.put("name", "New Category"); 
	
		request.header("Content-Type", "application/json");
		request.body(requestParams.toJSONString());
		 
		// Post the request and check the response
		Response response = request.post();
		
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 201);
	}
	
	
}
