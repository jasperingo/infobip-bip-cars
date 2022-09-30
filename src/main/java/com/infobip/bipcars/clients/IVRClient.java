package com.infobip.bipcars.clients;

import com.infobip.bipcars.dtos.IVRFlow;

import javax.annotation.PostConstruct;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class IVRClient {
    private String IVR_ID;

    private String API_KEY;

    private String API_END_POINT;

    @PostConstruct
    public void init() throws IOException {
        final InputStream inputStream  = IVRClient.class
                .getClassLoader()
                .getResourceAsStream("/application.local.properties");

        final Properties prop = new Properties();

        prop.load(inputStream);

        IVR_ID = prop.getProperty("IVR_ID");
        API_KEY = prop.getProperty("API_KEY");
        API_END_POINT = prop.getProperty("API_END_POINT");
    }

    public IVRFlow getIVRFlow() {
        return ClientBuilder.newClient()
                .target(String.format("%s/%s", API_END_POINT, IVR_ID))
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", API_KEY)
                .get(IVRFlow.class);
    }

    public IVRFlow updateIVRFlow(IVRFlow body) {
        return ClientBuilder.newClient()
                .target(String.format("%s/%s", API_END_POINT, IVR_ID))
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", API_KEY)
                .put(Entity.entity(body, MediaType.APPLICATION_JSON_TYPE), IVRFlow.class);
    }
}
