package com.infobip.bipcars.dtos;

import lombok.Data;

import java.util.List;

@Data
public class IVRFlow {
    private String name;
    private String description;
    private List<IVRScript> script;
}
