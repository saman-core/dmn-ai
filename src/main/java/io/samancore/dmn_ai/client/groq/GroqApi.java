package io.samancore.dmn_ai.client.groq;

import io.quarkus.rest.client.reactive.NotBody;
import io.samancore.dmn_ai.model.groq.GroqChatRequest;
import io.samancore.dmn_ai.model.groq.GroqChatResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/chat/completions")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterRestClient(configKey = "groq-api")
public interface GroqApi {

    @POST
    @ClientHeaderParam(name = "Authorization", value = "Bearer {token}")
    GroqChatResponse chat(GroqChatRequest request,
                          @NotBody String token);
}
