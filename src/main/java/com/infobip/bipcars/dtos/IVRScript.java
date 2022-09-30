package com.infobip.bipcars.dtos;

import lombok.Data;

import javax.json.bind.annotation.JsonbProperty;
import java.util.List;
import java.util.Map;

@Data
public class IVRScript {
    private String say;

    private String collectInto;

    @JsonbProperty("switch")
    private String switchEl;

    @JsonbProperty("case")
    private Map<String, List<IVRScript>> caseEl;

    private String request;

    private String dial;

    private IVRScriptOption options;
}
