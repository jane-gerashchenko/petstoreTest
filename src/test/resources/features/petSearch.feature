Feature: Search for pets

  Scenario: Get "available" pets
    When I search for available pets
    Then Available pets are shown
