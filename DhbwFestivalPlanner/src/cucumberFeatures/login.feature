Feature: Basic Flow for the login

Scenario: Login with correct data
Given I am not logged in 
When I filled the required fields
And I submit the formular
Then the system should validate the data and show me the main overview

Scenario: Login with wrong data
Given I am not logged in 
And I did not fill in the correct data or did not fill all required fields
When I submit the formular
Then the system should validate the data and show me an error message
