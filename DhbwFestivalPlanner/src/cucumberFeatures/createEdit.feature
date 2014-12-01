Feature: Basic Flow for creating or editing an event

Scenario: Creating an event start
Given I am logged in
When I create a new event
Then a form with the required data should be displayed

Scenario: Editing an Event
Given I am logged in
When I klick on the pen icon underneath an Event
Then a form with the event data should be displayed
When I changed the data
Then The event should be displayed again with the updated Information

Scenario: Submit the event formular with required fields filled in
Given I am logged in
And I filled all required fields
When I submit the formular
Then the system should create the event
And I should see the main menu of the event

Scenario: Submit the event formular with required not fields filled in
Given I am logged in
And I did not fill all required fields
When I submit the formular
Then I should see the formular again
And I should get a hint which fields are not valid
