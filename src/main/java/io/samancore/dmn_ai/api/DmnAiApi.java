package io.samancore.dmn_ai.api;

import io.samancore.dmn_ai.model.DmnAiModel;
import io.samancore.dmn_ai.model.DmnAiRequestModel;
import io.samancore.dmn_ai.service.DmnAiService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("")
@Produces(MediaType.APPLICATION_JSON)
public class DmnAiApi {

    @Inject
    DmnAiService service;

    @POST
    @Path("/generate")
    @RolesAllowed({"admin"})
    public DmnAiModel generate(DmnAiRequestModel request) {
        return service.generate(request);
    }

    @POST
    @Path("/explain")
    @RolesAllowed({"admin"})
    public DmnAiModel explain(DmnAiRequestModel request) {
        return service.explain(request);
    }
}
