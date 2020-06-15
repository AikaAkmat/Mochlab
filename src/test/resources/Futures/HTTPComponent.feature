Feature: Testing the Mocklab

  Background:
    When user sends GET request to mocklab.io

  Scenario: Validation Of Status Code
    Then the status code should be 200
    And content type should be "application/json;charset=utf-8"
    Then response time must be less than "500ms"

  Scenario: Validation Users information
    Then user id should be greater than 0
    Then user name can't be longer than 10 alpha characters
    Then user lastName can't be longer than 10 alpha characters
    Then user age must be integer and 0 > age < 120
    Then user gender must only be "F" or "M"

    #1.  Status code
    # 2. Content - Response body values
    # 3. Template (object data types) - POJO validation (create your own class to validate this, DO NOT use any of the libraries template validation methods)
    #The acceptance criteria for this API is as follows:
    # 1. id [int] greater than zero
    # 2. name [String] can’t be longer than 10 alpha characters
    # 3. last [String] can’t be longer than 10 alpha characters
    # 4. age [int] must be integer and 0 > age < 120
    # 5. gender [String] must only be F or M
    # 6. response time must be less than 500ms
    # Expectation:
    # 1. Language = Java
    # 2. You can use any Java open source Restful library (HTTPComponent, RestAssured, RestTemplate, Intuit Karate, etc.) to complete exercise
    # 3. You must use Cucumber and/or TestNG as your unit testing framework
    # 4. Your code will be measured using the following criteria:
    # o Coverage - Is your automation covering all acceptance criteria?
    # o Code Maturity - Are you organizing your code effectively? This will allow for easy refactoring.
    # o Effectiveness - How to communicate your solutions to others
    # 5.Upload code to Github and provide link