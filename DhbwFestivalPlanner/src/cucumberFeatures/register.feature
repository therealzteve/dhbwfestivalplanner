Feature: Registering for FestivalPlanner

Scenario: Successful Registration
Given I am not registered yet
And i am on the Homepage
When I click Register
And I fill out the required Information
And click submit
Then I return to the Login form
And my Account is unlocked
And I am able to Login now