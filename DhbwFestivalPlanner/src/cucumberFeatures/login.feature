Feature: Basic Flow for the login

Scenario: Login with correct data
Given I am not logged in
When I filled the required fields
And submit the formular
Then the system should validate the data
and show me the Übersichtseite

Scenario: Login with wrong data
Given I am not logged in 
And I didn’t fill in the correct data/ didn’t fill all required fields
When I submit the formular
Then the system should validate the data
and show me an error message
