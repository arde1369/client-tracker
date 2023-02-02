package com.astroitsolutions.clienttracker.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.astroitsolutions.clienttracker.Entity.Client;
import com.astroitsolutions.clienttracker.Entity.Review;
import com.astroitsolutions.clienttracker.Entity.Transaction;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface ClientController {

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
    public ResponseEntity<Client> addClient(Client client);

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
    public ResponseEntity<Client> retrieveClientById(int id);

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
    public ResponseEntity<Client> retrieveClientByFirstnameAndLastname(String firstname, String lastname);

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
    public ResponseEntity<HttpStatus> addReviewForProductByClientId(int clientId, Review review);

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
    public ResponseEntity<List<Review>> getReviewsAddedByClientById(int id);

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
    public ResponseEntity<List<Review>> getReviewsAddedByClientByFirstnameAndLastname(String firstname, String lastname);

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
    public ResponseEntity<HttpStatus> addTransactionForClientById(int id, Transaction transaction);

    // @Operation(
    //     summary = "",
    //     description = "",
    //     responses = {
    //         @ApiResponse(responseCode = "201", description = "Created"),
    //         @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = String.class))),
    //         @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = String.class))),
    //         @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = String.class))),
    //         @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = String.class)))
    //     })
    // public void addTransactionForClientByFirstnameAndLastname(String firstname, String lastname, Transaction transaction);

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
    public ResponseEntity<List<Transaction>> getTransactionsByClientById(int id);

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
    public ResponseEntity<List<Transaction>> getTransactionsAddedByClientByFirstnameAndLastname(String firstname, String lastname);

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
    public ResponseEntity<HttpStatus> updateRatingForClientById(int id, int rating);

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
    public ResponseEntity<HttpStatus> updateRatingForClientByFirstnameAndLastname(String firstname, String lastname, int rating);

    // @Operation(
    //     summary = "Delete client specified by the given course ID",
    //     description = "Delete client specified by the given course ID",
    //     responses = {
    //         @ApiResponse(responseCode = "200", description = "Successful Operation"),
    //         @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = String.class))),
    //         @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = String.class))),
    //         @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = String.class))),
    //         @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = String.class)))
    //     })
    // public ResponseEntity<HttpStatus> deleteClientById(int id);

    // @Operation(
    //     summary = "Delete client specified by the given course ID",
    //     description = "Delete client specified by the given course ID",
    //     responses = {
    //         @ApiResponse(responseCode = "200", description = "Successful Operation"),
    //         @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = String.class))),
    //         @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = String.class))),
    //         @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = String.class))),
    //         @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = String.class)))
    //     })
    // public ResponseEntity<HttpStatus> deleteClientByFirstAndLastname(String firstname, String lastname);
}
