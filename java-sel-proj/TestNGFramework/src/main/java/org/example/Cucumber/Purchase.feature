@tag
  Feature: Purchase the order from Ecommerce Website
    I want to purchase from the ecommerce website.

    Background:
    Given I landed on eCommerce Page

  @Regression
  Scenario Outline: Positive test for purchase an order
    Given Logged in with username <name> and Password <password>
    When I add <productName> from cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed
    Examples:
      |name                 |password   |productName|
      |email07@example.com|Rahul@123    |ZARA COAT 3|