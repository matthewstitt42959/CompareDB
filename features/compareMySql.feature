#Testcases for Comparing MySQL
#Author : Matthew Stitt

Feature: Attempt to compare MySQL databases

  Scenario Outline: Connect to two databases and compare results
    Given Connection to Database <first>, <second>, <username>, <password>
	When <table> is compared to <table>
	Then Results should match

	Examples: 
      | first   |second| username |password|table
      | orgchart_api |orgchart_api_copy|mstitt|root|DEPARTMENT


		
	  	
