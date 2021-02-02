Feature: Create, update and delete pet

  Background:
    Given I added pet to the store
    And Pet successfully added

  Scenario: Update pet
    When I update status to sold
    Then Pet status is updated

  Scenario: Delete pet
    When I delete pet
    Then Pet is deleted
