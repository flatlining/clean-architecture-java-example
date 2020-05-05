Feature: Create Circular Queue

  Background:
    * url baseUrl

  Scenario: Create a Circular Queue with Name and Description
    Given path '/circular'
    And request { name: 'This is a name', description: 'This is a description' }
    And header Accept = 'application/json'
    When method post
    Then status 201
    And match response == { id: '#uuid', name: 'This is a name', description: 'This is a description' }
