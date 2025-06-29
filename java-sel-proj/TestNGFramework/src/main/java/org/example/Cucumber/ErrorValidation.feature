@tag
Feature: Error validation
  I want to purchase from the ecommerce website.

  @ErrorValidation
  Scenario Outline: Positive test for purchase an order
    Given I landed on eCommerce Page
    When Logged in with username <name> and Password <password>
    Then "Incorrect email or password" message was displayed.
    Examples:
      |name                 |password   |
      |email07@example.com|Rahul@12    |