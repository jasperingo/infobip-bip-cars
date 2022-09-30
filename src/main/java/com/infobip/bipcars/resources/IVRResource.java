package com.infobip.bipcars.resources;

import com.infobip.bipcars.clients.IVRClient;
import com.infobip.bipcars.dtos.IVRProducts;
import com.infobip.bipcars.dtos.IVRFlow;
import com.infobip.bipcars.dtos.IVRWorkingHours;
import com.infobip.bipcars.dtos.UpdateIVR;
import com.infobip.bipcars.entities.Product;
import com.infobip.bipcars.entities.WorkingHour;
import com.infobip.bipcars.repositories.ProductRepository;
import com.infobip.bipcars.repositories.WorkingHourRepository;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Objects;

@Path("/ivr")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class IVRResource {
    @Inject
    private ProductRepository productRepository;

    @Inject
    private WorkingHourRepository workingHourRepository;

    @Inject
    private IVRClient ivrClient;

    @GET
    @Path("/flow")
    public Response findIVRFlow() {
        return Response.ok(ivrClient.getIVRFlow()).build();
    }

    @PUT
    @Path("/flow")
    public Response updateIVRFlow(@NotNull @Valid final UpdateIVR updateIVR) {
        final IVRFlow ivrFlow = ivrClient.getIVRFlow();

        ivrFlow.getScript().get(0).setSay(updateIVR.getWelcomeMessage());

        ivrFlow.getScript().get(3).getCaseEl().get("1").get(0).setRequest(updateIVR.getProductsUrl());

        ivrFlow.getScript().get(3).getCaseEl().get("2").get(0).setRequest(updateIVR.getWorkingHoursUrl());

        ivrFlow.getScript().get(3).getCaseEl().get("3").get(1).setDial(updateIVR.getCustomerCarePhoneNumber());

        return Response.ok(ivrClient.updateIVRFlow(ivrFlow)).build();
    }

    @GET
    @Path("/products")
    public Response findProducts() {
        final List<Product> products = productRepository.findMany();

        final Product lastProduct = products.isEmpty() ? null : products.get(products.size() - 1);

        String ivrResponse = products.isEmpty()
            ? "There are no products"
            : products.stream()
                .map(product -> String.format(
                        "%s%s for %.2f Naira%s",
                        Objects.equals(product.getId(), lastProduct.getId()) ? "and " : "",
                        product.getName(),
                        product.getPrice(),
                        !Objects.equals(product.getId(), lastProduct.getId()) ? "," : ""
                ))
                .reduce("Our products includes:", ((previous, current) -> String.format("%s %s", previous, current)));

        return Response.ok(new IVRProducts(ivrResponse)).build();
    }

    @GET
    @Path("/working-hours")
    public Response findWorkingHours() {
        final List<WorkingHour> workingHours = workingHourRepository.findMany();

        final WorkingHour lastWorkingHour = workingHours.isEmpty() ? null : workingHours.get(workingHours.size() - 1);

        String ivrResponse = workingHours.isEmpty()
                ? "There are no working hours"
                : workingHours.stream()
                    .map(workingHour -> String.format(
                            "%s%s from %s to %s%s",
                            Objects.equals(workingHour.getId(), lastWorkingHour.getId()) ? "and " : "",
                            workingHour.getDay(),
                            workingHour.getOpen(),
                            workingHour.getClose(),
                            !Objects.equals(workingHour.getId(), lastWorkingHour.getId()) ? "," : ""
                    ))
                    .reduce("We work on:", ((previous, current) -> String.format("%s %s", previous, current)));

        return Response.ok(new IVRWorkingHours(ivrResponse)).build();
    }
}
