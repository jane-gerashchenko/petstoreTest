Feature: Search for pets

  Scenario: Get pets by status
    When I search for "available" pets
    Then Pets are shown
