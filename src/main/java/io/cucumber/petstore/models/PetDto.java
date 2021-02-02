package io.cucumber.petstore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


public class PetDto {

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PetDetails {
        private Integer id;
        private CategoryDto.CategoryDetails category;
        private String name;
        private List<String> photoUrls;
        private List<TagDto.TagDetails> tags;
        private Status status;
    }
}
