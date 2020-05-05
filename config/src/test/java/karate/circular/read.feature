Feature: Read Circular Queue

  Background:
    * def circularSchema = { id: '#uuid', name: '##string', description: '##string' }
    * url baseUrl

  Scenario: Create a Circular Queue with Name and Description
    Given path '/circular'
    And header Accept = 'application/json'
    When method get
    Then status 200
    And match response == '#[] circularSchema'
