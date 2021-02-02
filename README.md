[![Build Status](https://www.travis-ci.com/jane-gerashchenko/petstoreTest.svg?branch=main)](https://travis-ci.com/github/jane-gerashchenko/petstoreTest)

# Pet Store API Test
In order to run test you can use IDE, or you can call `mvn cleat test` inside the project folder.

## Initial technical task:
Implement the following API automated checks over DEMO PET STORE: https://petstore.swagger.io/

- Get "available" pets. Assert expected result
- Post a new available pet to the store. Assert new pet added.
- Update this pet status to "sold". Assert status updated.
- Delete this pet. Assert deletion.

## What was achieved:
1. Maven project with series of API tests.
2. Positive tests for above requirements
3. Travis CI
4. Cucumber reporting
