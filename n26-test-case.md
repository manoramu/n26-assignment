# Test Case Development
--------

## Monefy Testcases Based on Priority (_Andriod_)

Monefy Testcases Based on Priority (Andriod)

1. Verify expense is getting added properly via expense button. This has to be tested for all 12 category of expense.
2. Verify income is getting added properly via income button. This has to be tested for all 3 categories of income.
3. Click on the line button at the right or left side of the balance and verify all the transaction has been displayed with the proper amount.
4. Proceed with the below steps
    * Make Transactions on different dates of the year. 
    * Click the menu icon. 
    * Choose between the options Day, Month, year, week, all, interval(Choose Different date range)
    * Verify the balance is shown accordingly.
5. Make expense and income transaction in USD cash and USD card payment. Verify the balance on toggling between All Accounts, Cash, Payment Card.
6. Proceed with the below steps
    * Transfer Amount between USD Cash to USD payment.
    * Add say 10 USD from USD Cash to USD payment
    * Verify 10 USD has been added to expense in the cash.
    * Verify 10 USD has been added to income in the payment card
7. Repeat the test case 8 from USD payment to USD Cash.
8. Click on Accounts. Verify Balance in cash and Payment card is getting displayed properly.
9. Proceed with the below steps
    * Click on account -> add and try to add a new account
	* Provide Initial account balance.
	* Click on category and add.
	* Verify new category has been added successfully with the provided initial account balance.
10. Click on Merge button and verify newly created account is merged to the cash or payment card and verify the balance.
11. Create another account as in step 11. And try to delete the account. Verify account is deleted successfully.
	
12. Proceed with the below steps
    * Click on Accounts -> Transfer. 
	* Move money from payment card to cash 
	* Verify the balance after transfer.	
13. Click on Main Menu -> Currencies.  It should be enabled only for pro users.
14. Proceed with the below steps
    * Click on settings -> Balance and check budget mode with USD 50.
	* Verify in Balance USD 50 is only shown.
	* Add an income and verify still the main income account is USD 50. 
	* Also verify while clicking transaction icon, All the actual transactions should be displayed.
15.	Click on the search button and search for any transaction by providing some keyword relating to the transaction. Verify transaction is getting retrieved.
16. Click export to file, the file with all transaction should be exported to mentioned format.
17. Try unlocking the Pro version.
18. Try to change the currency and verify the currency has been changed in all places(Main menu and balance)
19. Try to change the language and verify the language has been changed.
20. Click on the dark theme and it should be available for pro users.
21. Click on password protection and it should work only for pro users
22. Verify adding a new category to expense and income should work only for pro members.