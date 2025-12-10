Feature: purchase the order from exommerce website


  Background: I landed on Rahulshetty ecommerce page

  Scenario Outline:Positive testcase of submiting order
    Given login using username<username> and passowrd<passowrd>
    When I add product <productname> to cart
    And Checkout <productname> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page
    Examples:
      | username                | password      | productname |
      |   maddy08@gmail.com     | Universe1234@ |   ZARA COAT 3|