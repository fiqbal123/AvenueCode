@tag
Feature: Create Task
  I should be able to create a task so I can manage my tasks

  @tag1
  Scenario: My tasks link on home page
    Given User is already logged in
    And on home page
    When I view the My Tasks link in navigation bar
    Then I should see the My Tasks link in the navigation bar

  
  Scenario: click on My Tasks link
  Given User is on home page
  When I click on My Tasks link in navigation bar
  Then I should be redirected to My Tasks page
  
  Scenario: check message displayed on My Tasks page at the top part
  Given User is on My Tasks page
  When I check the message at the top part
  Then I should see the message "Hey Faraz, this is your todo list for today:"
  
  Scenario: check user is unable to create a task without entering anything
  Given User is on My Tasks page
  When I dont enter anything in the Task description field and click on Add task button
  Then I shouldnt see any empty task being created in the list.
  
   Scenario: check user is unable to create a task with less than 3 characters
  Given User is on My Tasks page
  When I enter a value "ab" in the task description textbox which is less than 3 characters.
  Then I shouldnt see the task "ab" created in the list
  
  Scenario: check user is able to add a task by hitting the enter key of keyboard
  Given User is on My Tasks page
  When I enter a value "abhgd" in the task description textbox and hit the enter key.
  Then I should see the task "abhgd" created in the list
  
  Scenario: check user is unable to add the task if the description is greater than 250 characters
  Given User is on My Tasks page
  When I enter a value greater than 250 characters in the task description textbox and hit the enter key.
  Then I should NOT see the task created as the description is greater than 250 characters
  