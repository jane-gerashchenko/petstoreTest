package io.cucumber.petstore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum Status {
    available,
    pending,
    sold
}
