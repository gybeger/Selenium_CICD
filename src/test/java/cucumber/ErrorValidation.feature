
@tag
Feature: Title of your feature
  I want to use this template for my feature file


  @ErrorValidation
  Scenario Outline: login failed
    Given I landed on webshop
    When User logged in with email <userEmail> and password <userPassword>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | userEmail 							 | userPassword |
      | villanostra@freemail.hu	 | Negen1234mm 		|
      | gybeger@freemail.hu			 | Testabcdm			|