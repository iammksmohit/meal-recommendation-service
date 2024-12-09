package com.micro.mealrecommendservice.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MealRecommendationRequest {

    private String city;

    private String cuisine;

    private Integer page;

    public MealRecommendationRequest(String city, String cuisine, Integer page) {

        this.city = city;
        this.cuisine = cuisine;
        this.page = page;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
