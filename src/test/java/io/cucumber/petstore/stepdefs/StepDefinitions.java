package io.cucumber.petstore.stepdefs;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.petstore.configuration.TestContext;
import io.cucumber.petstore.models.*;
import io.cucumber.petstore.serviceimpl.PetApi;
import io.restassured.response.Response;
import org.junit.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static io.cucumber.petstore.configuration.Context.PET_OBJECT;

public class StepDefinitions {

    TestContext testContext;

    public StepDefinitions(TestContext testContext) {
        this.testContext = testContext;
    }

    private PetDto.PetDetails createdPetData;

    private PetDto.PetDetails petTestObject = PetDto.PetDetails.builder()
            .id(Math.abs(new Random().nextInt()))
            .category(new CategoryDto.CategoryDetails(1, "dogs"))
            .name("Tina")
            .photoUrls(new ArrayList<>(
                    Collections.singletonList("")))
            .tags(new ArrayList<>(
                    Collections.singletonList(new TagDto.TagDetails(1, "dachshund"))))
            .status(Status.available)
            .build();

    @When("I search for {string} pets")
    public void getPetsByStatus(String status) {
        Response response = PetApi.getPets(Status.valueOf(status));
        setResponseContext(response);
    }

    @Then("Pets are shown")
    public void showPets() {
        Response responseContext = getResponseContext();
        Assert.assertEquals(200, responseContext.getStatusCode());
    }

    @When("I added pet to the store")
    public void addPetToStore() throws IOException {
        Response response = PetApi.postPet(petTestObject);
        setResponseContext(response);
        createdPetData = convertResponseToObject(response);
    }

    @Then("Pet successfully added")
    public void petIsAdded() throws IOException {
        Response responseContext = getResponseContext();

        Assert.assertEquals(200, responseContext.getStatusCode());
        Response getPetResp = PetApi.getPet(createdPetData.getId());
        PetDto.PetDetails petDetails = convertResponseToObject(getPetResp);
        Assert.assertEquals(200, getPetResp.getStatusCode());
        Assert.assertEquals("Tina", petDetails.getName());
    }

    @When("I update status to {string}")
    public void updatePetStatus(String status) throws Throwable {
        petTestObject.setStatus(Status.valueOf(status));
        Response response = PetApi.updatePet(petTestObject);
        Assert.assertEquals(200, response.getStatusCode());
        setResponseContext(response);
    }

    @Then("Pet status is updated to {string}")
    public void verifyStatusIsUpdated(String status) throws Throwable {
        PetDto.PetDetails petDetails = convertResponseToObject(getResponseContext());
        Assert.assertEquals(Status.valueOf(status), petDetails.getStatus());
    }

    @When("I delete pet")
    public void deletePet() {
        Response deletePetResp = PetApi.deletePet(createdPetData.getId());
        Assert.assertEquals(200, deletePetResp.getStatusCode());
    }

    @Then("Pet is deleted")
    public void petIsDeleted() {
        Response getPetResp = PetApi.getPet(createdPetData.getId());
        Assert.assertEquals(404, getPetResp.getStatusCode());
    }

    private void setResponseContext(Response lastResponse) {
        testContext.scenarioContext.setContext(PET_OBJECT, lastResponse);
    }

    private PetDto.PetDetails convertResponseToObject(Response lastResponse) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(lastResponse.getBody().print(), PetDto.PetDetails.class);
    }

    private Response getResponseContext() {
        return (Response) testContext.getScenarioContext().getContext(PET_OBJECT);
    }
}
