package com.astroitsolutions.clienttracker.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.astroitsolutions.clienttracker.Entity.Product;
import com.astroitsolutions.clienttracker.Entity.Review;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface ProductController {
    
    @Operation(
        summary = "",
        description = "",
        responses = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = String.class)))
        })
    public ResponseEntity<Product> addProduct(Product product);

    @Operation(
        summary = "",
        description = "",
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = String.class)))
        })
    public ResponseEntity<Product> findProductById(int id);

    @Operation(
        summary = "",
        description = "",
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = String.class)))
        })
    public ResponseEntity<Product> findProductByName(String name);

    @Operation(
        summary = "",
        description = "",
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = String.class)))
        })
    public ResponseEntity<List<Review>> retrieveAllReviewsForProductById(int id);

    @Operation(
        summary = "",
        description = "",
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = String.class)))
        })
    public ResponseEntity<List<Review>> retrieveAllReviewsForProductByName(String name );

    @Operation(
        summary = "",
        description = "",
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = String.class)))
        })
    public ResponseEntity<Product> activateProductById(int productId);

    @Operation(
        summary = "",
        description = "",
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = String.class)))
        })
    public ResponseEntity<Product> deactivateProductById(int productId);

    @Operation(
        summary = "",
        description = "",
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = String.class)))
        })
    public ResponseEntity<Product> activateProductByName(String name);

    @Operation(
        summary = "",
        description = "",
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = String.class)))
        })
    public ResponseEntity<Product> deactivateProductByName(String name);

//     @Operation(
//         summary = "",
//         description = "",
//         responses = {
//             @ApiResponse(responseCode = "200", description = "Successful Operation"),
//             @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = String.class))),
//             @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = String.class))),
//             @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = String.class))),
//             @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = String.class)))
//         })
//     public ResponseEntity<HttpStatus> deleteProductById(int id);

//     @Operation(
//         summary = "",
//         description = "",
//         responses = {
//             @ApiResponse(responseCode = "200", description = "Successful Operation"),
//             @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = String.class))),
//             @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = String.class))),
//             @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = String.class))),
//             @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = String.class)))
//         })
//     public ResponseEntity<HttpStatus> deleteProductByName(String name);
}
