package io.samancore.dmn_ai.service;

import io.samancore.dmn_ai.model.*;

public interface DmnAiService {

    DmnAiModel generate(DmnAiRequestModel request);

    DmnAiModel explain(DmnAiRequestModel request);
}
