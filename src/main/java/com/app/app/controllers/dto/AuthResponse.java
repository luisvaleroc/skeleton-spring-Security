package com.app.app.controllers.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"username", "message", "jwt", "statys"})
public record AuthResponse(String username, String message, String jwt, boolean status) {

}
