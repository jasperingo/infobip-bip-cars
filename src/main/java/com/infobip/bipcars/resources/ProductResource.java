package com.infobip.bipcars.resources;

import com.infobip.bipcars.entities.Product;
import com.infobip.bipcars.repositories.ProductRepository;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {
    @Inject
    private ProductRepository productRepository;

    @POST
    public Response create(@NotNull @Valid final Product product) {
        productRepository.create(product);

        return Response.created(URI.create(""))
                .entity(product)
                .build();
    }

    @GET
    public Response findMany() {
        return Response.ok(productRepository.findMany()).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") final Long id, @NotNull @Valid final Product product) {
        final Product existingProduct = productRepository.find(id);

        if (existingProduct == null) {
            throw new NotFoundException("Product not found");
        }

        product.setId(existingProduct.getId());

        productRepository.update(product);

        return Response.ok(product).build();
    }

    @GET
    @Path("/{id}")
    public Response findOne(@PathParam("id") final Long id) {
        final Product product = productRepository.find(id);

        if (product == null) {
            throw new NotFoundException("Product not found");
        }

        return Response.ok(product).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") final Long id) {
        final Product product = productRepository.find(id);

        if (product == null) {
            throw new NotFoundException("Product not found");
        }

        productRepository.delete(product);

        return Response.noContent().build();
    }
}
