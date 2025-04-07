package io.samancore.dmn_ai.service.impl;

import io.samancore.dmn_ai.client.AiClient;
import io.samancore.dmn_ai.model.*;
import io.samancore.dmn_ai.service.DmnAiService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DmnAiServiceImpl implements DmnAiService {

    @Inject
    AiClient aiClient;

    @Override
    public DmnAiModel generate(DmnAiRequestModel request) {
        return aiClient.generate(request);
    }

    @Override
    public DmnAiModel explain(DmnAiRequestModel request) {
        return aiClient.explain(request);
    }
}
