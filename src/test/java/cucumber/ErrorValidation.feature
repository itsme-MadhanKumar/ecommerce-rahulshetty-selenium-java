@tag
Feature: Validating the error

  @Error
  Scenario Outline: Error Validation
    Given I landed on Rahulshetty ecommerce page
    When login using username <username> and password <password>
    Then "Incorrect email or password." message is displayed on login page

    Examples:
      | username                | password      |
      |   maddy08@gmail.com     | Universe1234 |
