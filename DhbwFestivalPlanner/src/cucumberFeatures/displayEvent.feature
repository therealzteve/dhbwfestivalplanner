Feature: Basic Flow for viewing Event Pages

Scenario: Users View
Given that the event is created
And the Guest is not logged in
When he follows on the link
Then he should be displayed the event page