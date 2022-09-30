package com.infobip.bipcars.dtos;

import lombok.Data;

@Data
public class IVRScriptOption {
    private Integer maxInputLength;

    private Integer timeout;

    private String sendToReports;

    private String senderId;

    private Integer maxCallDuration;

    private String method;

    private Boolean collectResponse;
}
