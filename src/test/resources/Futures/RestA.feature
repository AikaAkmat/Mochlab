Feature: Testing the Mocklab
  Scenario: Validation Of Customer
    When the user sends GET request
    Then the status code should  be 200
    And the user validates response body values
