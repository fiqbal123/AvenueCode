Feature: Create Sub Task
  As a ToDo App user I should be able to create a subtask So I can break down my tasks in smaller pieces

  Scenario: ‘Manage Subtasks’ button shows up in task list for a task
    Given User is already logged in
    And User is on home page
    And I click on My Tasks link in navigation bar
    And a task is already created
    When I view the column of subTasks for the task created
    Then I should see the "Manage Subtasks" button

  Scenario: ‘Manage Subtasks’ button shows up the number of subtasks
    Given a task is already created
    When I view the column of subTasks for the task created
    Then I should see "0" for the number of subtasks in the button

  Scenario: click on ‘Manage Subtasks’ button opens up a popup window for Subtasks
    Given a task is already created
    When I click on the Manage Subtasks button
    Then I should see pop window

  Scenario: Ensure task description is read only field
    Given user is on subtask popup page
    When I enter "change value" in task description textbox
    Then I should not see any changes to the text of task "test task"

  Scenario: Ensure sub task can be created for description less than max length
    Given user is on subtask popup page
    When I enter "test Sub task" in Sub Task description field
    Then I should see the subtask of "test Sub task" successfully created

  Scenario: Ensure sub task can not be created for description greater than max length
    Given user is on subtask popup page
    When I enter greater than the allowed limit in Sub Task description field
    Then I should NOT see the subtask created

  Scenario: Ensure correct format in due Date field
    Given user is on subtask popup page
    When I enter correct date format in due Date field and create a subtask
    Then I should see the subtask created successfully

  Scenario: Ensure incorrect format in due Date field is not allowed
    Given user is on subtask popup page
    When I enter incorrect date format in due Date field and create a subtask
    Then I should Not see the subtask created successfully

  Scenario: Ensure sub task description field is a required field
    Given user is on subtask popup page
    When I create a subtask without entering any value for task description field
    Then I should Not see the subtask created successfully for task empty

  Scenario: Ensure due date field is a required field
    Given user is on subtask popup page
    When I create a subtask without entering any value for Due date field
    Then I should Not see the subtask created successfully for task empty

  Scenario: Ensure all subtasks that are done remain as done after closing and reopening the popup
    Given user is on subtask popup page
    When I create a subtask "Done1" and "Done2"
    And click on the Done column for subtasks of "Done1" and "Done2"
    And close the popup window
    And open the popup window again for task of "change value"
    Then I should still see both the subtasks done checkbox as selected
    
   Scenario: Ensure user is able enable and disable the done checkbox for a subtask
    Given user is on subtask popup page
    When I create a subtask "TestUncheck1"
    And click on the Done column for subtasks of "TestUncheck1"
    And disable the checkbox for the subtasks of "TestUncheck1"
    Then I should see the checkbox for the subtasks of "TestUncheck1"

