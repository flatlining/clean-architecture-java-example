Feature: Circular Queue resource must support CRUD operations

  Background:
    * def circularSchema = { id: '#uuid', name: '##string', description: '##string' }
    * url baseUrl

  Scenario: Perform multiple CRUD operations toCircular Queue resource
    Given path '/circular/'
    And header Accept = 'application/json'
    When method get
    Then status 200
    And match response == '#[0] circularSchema'

    Given path '/circular/'
    And request { name: 'This is a name', description: 'This is a description' }
    And header Accept = 'application/json'
    When method post
    Then status 201
    And match response == { id: '#uuid', name: 'This is a name', description: 'This is a description' }

    Given path '/circular/'
    And request { name: 'This is a name' }
    And header Accept = 'application/json'
    When method post
    Then status 201
    And match response == { id: '#uuid', name: 'This is a name', description: '#notpresent' }
    And def itemWithNameOnlyId = response.id

    Given path '/circular/'
    And request { description: 'This is a description' }
    And header Accept = 'application/json'
    When method post
    Then status 201
    And match response == { id: '#uuid', name: '#notpresent', description: 'This is a description' }
    And def itemWithDescriptionOnlyId = response.id

    Given path '/circular/'
    And header Accept = 'application/json'
    When method get
    Then status 200
    And match response == '#[3] circularSchema'

    Given path '/circular/', itemWithNameOnlyId
    When method delete
    Then status 204
    And match response == ''

    Given path '/circular/'
    And header Accept = 'application/json'
    When method get
    Then status 200
    And match response == '#[2] circularSchema'

    Given path '/circular/', itemWithDescriptionOnlyId
    When method delete
    Then status 204
    And match response == ''

    Given path '/circular/'
    And header Accept = 'application/json'
    When method get
    Then status 200
    And match response == '#[1] circularSchema'

    Given path '/circular/'
    When method delete
    Then status 204
    And match response == ''

    Given path '/circular/'
    And header Accept = 'application/json'
    When method get
    Then status 200
    And match response == '#[0] circularSchema'
