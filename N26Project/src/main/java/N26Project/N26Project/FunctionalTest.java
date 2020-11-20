package N26Project.N26Project;

import org.junit.BeforeClass;

import io.restassured.RestAssured;

public class FunctionalTest {

    @BeforeClass
    public static void setup() {
        String port = System.getProperty("server.port");
        if (port == null) {
            RestAssured.port = Integer.valueOf(3030);
        }
        else{
            RestAssured.port = Integer.valueOf(port);
        }


        String baseHost = System.getProperty("server.host");
        if(baseHost==null){
            baseHost = "http://localhost";
        }
        RestAssured.baseURI = baseHost;

    }

}
