
@tag
Feature: Purchase the order
  I want to use this template for my feature file

Background:
Given I landed on webshop

  @Regression
  Scenario Outline: Positive test of submitting the order
    Given User logged in with email <userEmail> and password <userPassword>
    When I add product <productName> to cart
    And Checkout <productName> and submit order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmationPage

    Examples: 
      | userEmail 							 | userPassword | productName 		|
      | villanostra@freemail.hu	 | Negen1234 		| ZARA COAT 3 		|
      | gybeger@freemail.hu			 | Testabcd			| ADIDAS ORIGINAL |
