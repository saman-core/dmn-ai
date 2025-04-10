package io.samancore.dmn_ai.model.ai;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Value
@Jacksonized
@Builder(
        setterPrefix = "set",
        builderMethodName = "newBuilder",
        toBuilder = true
)
public class ChatRequest {
    String model;
    Boolean stream;
    List<Message> messages;
}
