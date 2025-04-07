package io.samancore.dmn_ai.model.groq;

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
public class GroqChatRequest {
    String model;
    List<GroqMessages> messages;
}
