package io.samancore.dmn_ai.client.ai;

import io.quarkus.rest.client.reactive.NotBody;
import io.samancore.dmn_ai.model.ai.ChatRequest;
import io.samancore.dmn_ai.model.ai.ChatResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/chat/completions")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterRestClient(configKey = "ai-api")
public interface AiApi {

    @POST
    @ClientHeaderParam(name = "Authorization", value = "Bearer {token}")
    ChatResponse chat(ChatRequest request,
                      @NotBody String token);
}
