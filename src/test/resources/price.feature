Feature: Price Retrieval

  Scenario: Test 1: Retrieve price on date 2020-06-14T10:00:00
    Given I have the productId 35455, brandId 1, and date "2020-06-14T10:00:00"
    When I request the price from the price service
    Then I should receive a status code of 200
    And the response should contain productId 35455, brandId 1, and price 35.50

  Scenario: Test 2: Retrieve price on date 2020-06-14T16:00:00
    Given I have the productId 35455, brandId 1, and date "2020-06-14T16:00:00"
    When I request the price from the price service
    Then I should receive a status code of 200
    And the response should contain productId 35455, brandId 1, and price 25.45

  Scenario: Test 3: Retrieve price on date 2020-06-14T21:00:00
    Given I have the productId 35455, brandId 1, and date "2020-06-14T21:00:00"
    When I request the price from the price service
    Then I should receive a status code of 200
    And the response should contain productId 35455, brandId 1, and price 35.50

  Scenario: Test 4: Retrieve price on date 2020-06-15T10:00:00
    Given I have the productId 35455, brandId 1, and date "2020-06-15T10:00:00"
    When I request the price from the price service
    Then I should receive a status code of 200
    And the response should contain productId 35455, brandId 1, and price 30.50

  Scenario: Test 5: Retrieve price on date 2020-06-16T21:00:00
    Given I have the productId 35455, brandId 1, and date "2020-06-16T21:00:00"
    When I request the price from the price service
    Then I should receive a status code of 200
    And the response should contain productId 35455, brandId 1, and price 38.95