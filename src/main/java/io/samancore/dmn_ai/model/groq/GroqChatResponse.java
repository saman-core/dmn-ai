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
public class GroqChatResponse {
    String id;
    String object;
    Long created;
    String model;
    List<Choices> choices;
    Object usage;
    String system_fingerprint;
    Object x_groq;

    @Value
    @Jacksonized
    @Builder(
            setterPrefix = "set",
            builderMethodName = "newBuilder",
            toBuilder = true
    )
    public static class Choices {
        Integer index;
        GroqMessages message;
        Object logprobs;
        String finish_reason;
    }
}
