package com.conference.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@Schema(description = "Data Transfer Object for User Role")
public class UserRoleDTO {
    @Schema(description = "Role ID", example = "1")
    private Long id;

    @Schema(description = "Role type", example = "EDITEUR")
    private String role;

    @Schema(description = "User ID associated with this role", example = "3")
    private Long utilisateurId;

    @Schema(description = "Conference ID associated with this role", example = "5")
    private Long conferenceId;

    // Getters and Setters
}
