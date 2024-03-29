package com.astroitsolutions.clienttracker.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.astroitsolutions.clienttracker.Entity.Company;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface CompanyController {

    @Operation(
        summary = "",
        description = "",
        responses = {
            @ApiResponse(responseCode = "200", description = "Created/Updated"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = String.class)))
        })
    public ResponseEntity<Company> addOrUpdateCompany(Company newCompany);

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
    public ResponseEntity<Company> getCompanyById(int id);

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
    public ResponseEntity<Company> getCompanyByName(String name);

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
    public ResponseEntity<HttpStatus> addClientToCompany(int clientId, int companyId);

    // @Operation(
    //     summary = "",
    //     description = "",
    //     responses = {
    //         @ApiResponse(responseCode = "200", description = "Successful Operation"),
    //         @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = String.class))),
    //         @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = String.class))),
    //         @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = String.class))),
    //         @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = String.class)))
    //     })
    // public ResponseEntity<HttpStatus> deleteCompanyById(int id);

    // @Operation(
    //     summary = "",
    //     description = "",
    //     responses = {
    //         @ApiResponse(responseCode = "200", description = "Successful Operation"),
    //         @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = String.class))),
    //         @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = String.class))),
    //         @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = String.class))),
    //         @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = String.class)))
    //     })
    // public ResponseEntity<HttpStatus> deleteCompanyByName(String name);
}
