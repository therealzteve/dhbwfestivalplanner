Feature: Basic Flow for viewing Event Pages

Scenario: Users View
Given that the User is logged in
And the Event is created
When the user clicks on the Events title
Then he should be displayed the Events Information

Scenario: Guests View
Given the Guest is not logged in
When he follows on the link
Then he should be displayed the event page