Feature: Inviting Guests

Scenario: Inviting Guests
Given that the User is logged in
And the Event is created
When the user clicks on the Events title
And he clicks on the Guest Panel
And he fills the form
Then the Guest appears on the Guest List

Scenario: Require Name
Given that the User is logged in
And the Event is created
When the user clicks on the Events title
And he clicks on the Guest Panel
And the user forgets to fill the name
Then he gets an alert that the name is required

Scenario: Require Name
Given that the User is logged in
And the Event is created
When the user clicks on the Events title
And he clicks on the Guest Panel
And the user enters an invalid email address
Then he gets an alert that the email address is invalid

Scenario: Uninviting Guests
Given that the User is logged in
And the Event is created
And a certain Guest is invited
When he removes the guest
Then the Guest disappears from the Guest List