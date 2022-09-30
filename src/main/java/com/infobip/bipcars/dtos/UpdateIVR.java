package com.infobip.bipcars.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateIVR {
    @NotBlank
    private String welcomeMessage;

    @NotBlank
    private String productsUrl;

    @NotBlank
    private String workingHoursUrl;

    @NotBlank
    private String customerCarePhoneNumber;
}
