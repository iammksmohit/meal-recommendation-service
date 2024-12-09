package com.micro.mealrecommendservice.controller;

import com.micro.mealrecommendservice.dto.MealRecommendationRequest;
import com.micro.mealrecommendservice.dto.MealRecommendationResponse;
import com.micro.mealrecommendservice.services.RecommendationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MealRecommendationController {

    private final RecommendationService recommendationService;

    public MealRecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping("/recommend")
    @Operation(summary = "Get meal recommendations", description = "Fetch top healthy recipes and restaurants for a given cuisine and city")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Successful retrieval of recommendations"))
    public ResponseEntity<MealRecommendationResponse> getRecommendations(@RequestParam(required = false, defaultValue = "Bangalore") String city,
                                                                         @RequestParam(required = false, defaultValue = "Indian") String cuisine,
                                                                         @RequestParam(defaultValue = "1") int page,
                                                                         @RequestParam(defaultValue = "true", required = false) boolean isOffline) {
        return ResponseEntity.ok(recommendationService.getRecommendations(city, cuisine, page, isOffline));
    }
}
