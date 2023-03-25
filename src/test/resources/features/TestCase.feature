@Execution
Feature: AID for AID automation to search the information that google loads.

  Scenario: Search information about AID
    Given I enter on the google page
    When I search "Aid for Aids"
    Then I validate the result is greater than zero with the domain
    And I validate the title and enter on menu options

