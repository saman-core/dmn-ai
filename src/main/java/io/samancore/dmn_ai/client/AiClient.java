package io.samancore.dmn_ai.client;

import io.samancore.dmn_ai.model.DmnAiModel;
import io.samancore.dmn_ai.model.DmnAiRequestModel;

public interface AiClient {

    DmnAiModel generate(DmnAiRequestModel model);

    DmnAiModel explain(DmnAiRequestModel model);
}
