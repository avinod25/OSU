package com.osu.demo.controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.osu.demo.api.model.BaseResponse;
import com.osu.demo.api.model.LobsterInfo;
import com.osu.demo.api.model.LobsterRequest;
import com.osu.demo.api.model.LobsterResponse;
import com.osu.demo.api.model.LobstersResponse;
import com.osu.demo.api.model.Status;
import com.osu.demo.common.DemoException;
import com.osu.demo.common.ErrorCode;
import com.osu.demo.service.LobstersService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * The Class LobsterController.
 * 
 * @author Vinod Arroju
 */
@RestController
@RequestMapping("/api/lobsters")
@Tag(name = "Lobsters", description = "Lobsters data manage API's")
public class LobsterController {

	/** The log. */
	private Logger log = LogManager.getLogger(LobsterController.class);

	/** The lobsters service. */
	@Autowired
	LobstersService lobstersService;

	/** The Constant CONTENT_TYPE_JSON. */
	private final static String CONTENT_TYPE_JSON = "application/json";
	
	/** The Constant HTTP_STATUS_200. */
	private final static String HTTP_STATUS_200 = "200";
	
	/** The Constant HTTP_STATUS_404. */
	private final static String HTTP_STATUS_404 = "404";
	
	/** The Constant HTTP_STATUS_500. */
	private final static String HTTP_STATUS_500 = "500";
	
	/** The Constant HTTP_STATUS_401. */
	private final static String HTTP_STATUS_401 = "401";
	
	/** The Constant HTTP_STATUS_204. */
	private final static String HTTP_STATUS_204 = "204";

	/**
	 * Creates the lobster.
	 *
	 * @param request the request
	 * @param principal the principal
	 * @return the response entity
	 */
	@Operation(summary = "Add lobster details", security = {
			@SecurityRequirement(name = "bearer-token") }, responses = {
					@ApiResponse(responseCode = HTTP_STATUS_200, description = "Successful Operation", content = @Content(mediaType = CONTENT_TYPE_JSON, schema = @Schema(implementation = LobsterResponse.class))),
					@ApiResponse(responseCode = HTTP_STATUS_404, description = "Not found", content = @Content(mediaType = CONTENT_TYPE_JSON, schema = @Schema(implementation = BaseResponse.class))),
					@ApiResponse(responseCode = HTTP_STATUS_401, description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true))),
					@ApiResponse(responseCode = HTTP_STATUS_500, description = "Internal Server Error", content = @Content(mediaType = CONTENT_TYPE_JSON, schema = @Schema(implementation = BaseResponse.class))) })
	@PostMapping(produces = CONTENT_TYPE_JSON, consumes = CONTENT_TYPE_JSON)
	public ResponseEntity<?> createLobster(@Valid @RequestBody(required = true) LobsterRequest request,
			Authentication principal) {
		log.debug("In createLobster::" + principal.getName());
		LobsterResponse response = new LobsterResponse();
		HttpStatus status = HttpStatus.OK;
		try {
			LobsterInfo lobster = lobstersService.createLobster(request, (UserDetails) principal.getPrincipal());
			response.setStatus(Status.SUCCESS);
			response.setData(lobster);
		} catch (DemoException e) {
			status = handleError(e, response);
		}
		return ResponseEntity.status(status).body(response);
	}

	/**
	 * Update lobster.
	 *
	 * @param lobsterId the lobster id
	 * @param request the request
	 * @param principal the principal
	 * @return the response entity
	 */
	@Operation(summary = "Update lobster details by Id", security = {
			@SecurityRequirement(name = "bearer-token") }, responses = {
					@ApiResponse(responseCode = HTTP_STATUS_200, description = "Successful Operation", content = @Content(mediaType = CONTENT_TYPE_JSON, schema = @Schema(implementation = LobsterResponse.class))),
					@ApiResponse(responseCode = HTTP_STATUS_404, description = "Not found", content = @Content(mediaType = CONTENT_TYPE_JSON, schema = @Schema(implementation = BaseResponse.class))),
					@ApiResponse(responseCode = HTTP_STATUS_401, description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true))),
					@ApiResponse(responseCode = HTTP_STATUS_500, description = "Internal Server Error", content = @Content(mediaType = CONTENT_TYPE_JSON, schema = @Schema(implementation = BaseResponse.class))) })
	@PutMapping(path = "{lobsterId}", produces = CONTENT_TYPE_JSON, consumes = CONTENT_TYPE_JSON)
	public ResponseEntity<?> updateLobster(@Valid @PathVariable(name = "lobsterId", required = true) Integer lobsterId,
			@Valid @RequestBody(required = true) LobsterRequest request, Authentication principal) {
		log.debug("In updateLobster::" + lobsterId);
		LobsterResponse response = new LobsterResponse();
		HttpStatus status = HttpStatus.OK;
		try {
			LobsterInfo updatedLobster = lobstersService.updateLobster(lobsterId, request,
					(UserDetails) principal.getPrincipal());
			response.setStatus(Status.SUCCESS);
			response.setData(updatedLobster);
		} catch (DemoException e) {
			status = handleError(e, response);
		}
		return ResponseEntity.status(status).body(response);
	}

	/**
	 * Delete lobster.
	 *
	 * @param lobsterId the lobster id
	 * @return the response entity
	 */
	@Operation(summary = "Delete lobster details by Id", security = {
			@SecurityRequirement(name = "bearer-token") }, responses = {
					@ApiResponse(responseCode = HTTP_STATUS_204, description = "Successful Operation"),
					@ApiResponse(responseCode = HTTP_STATUS_404, description = "Not found", content = @Content(mediaType = CONTENT_TYPE_JSON, schema = @Schema(implementation = BaseResponse.class))),
					@ApiResponse(responseCode = HTTP_STATUS_401, description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true))),
					@ApiResponse(responseCode = HTTP_STATUS_500, description = "Internal Server Error", content = @Content(mediaType = CONTENT_TYPE_JSON, schema = @Schema(implementation = BaseResponse.class))) })
	@DeleteMapping(path = "{lobsterId}", produces = CONTENT_TYPE_JSON)
	public ResponseEntity<?> deleteLobster(@PathVariable("lobsterId") Integer lobsterId) {
		log.debug("In deleteLobster::" + lobsterId);
		BaseResponse response = new BaseResponse();
		HttpStatus status = HttpStatus.NO_CONTENT;
		try {
			Boolean deleted = lobstersService.deleteLobster(lobsterId);
			if (!deleted) {
				status = HttpStatus.NOT_FOUND;
				response.setStatus(Status.INVALID_INPUT);
				response.setMessage("INVALID INPUT");
			}
		} catch (DemoException e) {
			status = handleError(e, response);
		}
		return ResponseEntity.status(status).body(response);
	}

	/**
	 * List lobsters.
	 *
	 * @return the lobsters response
	 */
	@Operation(summary = "Get all lobsters details", responses = {
			@ApiResponse(description = "Successful Operation", responseCode = HTTP_STATUS_200, content = @Content(mediaType = CONTENT_TYPE_JSON, schema = @Schema(implementation = LobstersResponse.class))) })
	@GetMapping(produces = CONTENT_TYPE_JSON)
	public LobstersResponse listLobsters() {
		log.debug("In listLobsters::");
		LobstersResponse response = new LobstersResponse();
		response.setStatus(Status.SUCCESS);
		response.setData(lobstersService.listLobsters());
		return response;
	}

	/**
	 * Gets the lobster.
	 *
	 * @param lobsterId the lobster id
	 * @return the lobster
	 */
	@Operation(summary = "Get lobster details by Id", responses = {
			@ApiResponse(responseCode = HTTP_STATUS_200, description = "Successful Operation", content = @Content(mediaType = CONTENT_TYPE_JSON, schema = @Schema(implementation = LobsterResponse.class))),
			@ApiResponse(responseCode = HTTP_STATUS_404, description = "Not found", content = @Content(mediaType = CONTENT_TYPE_JSON, schema = @Schema(implementation = BaseResponse.class))),
			@ApiResponse(responseCode = HTTP_STATUS_500, description = "Internal Server Error", content = @Content(mediaType = CONTENT_TYPE_JSON, schema = @Schema(implementation = BaseResponse.class))) })
	@GetMapping(path = "{lobsterId}", produces = CONTENT_TYPE_JSON)
	public ResponseEntity<?> getLobster(@PathVariable(name = "lobsterId", required = true) Integer lobsterId) {
		log.debug("In getLobster::" + lobsterId);
		LobsterResponse response = new LobsterResponse();
		HttpStatus status = HttpStatus.OK;
		try {
			LobsterInfo lobster = lobstersService.getLobster(lobsterId);
			if (lobster != null) {
				response.setStatus(Status.SUCCESS);
				response.setData(lobster);
			} else {
				status = HttpStatus.NOT_FOUND;
				response.setStatus(Status.INVALID_INPUT);
				response.setMessage("No data found");
			}
		} catch (DemoException e) {
			status = handleError(e, response);
		}
		return ResponseEntity.status(status).body(response);
	}

	/**
	 * Handle error.
	 *
	 * @param e the e
	 * @param response the response
	 * @return the http status
	 */
	private HttpStatus handleError(DemoException e, BaseResponse response) {
		log.error("ERROR::", e);
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		if (ErrorCode.InputError == e.getCode()) {
			status = HttpStatus.BAD_REQUEST;
		}
		response.setStatus(Status.ERROR);
		response.setMessage(e.getMessage());
		return status;
	}
}
