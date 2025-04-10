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
public class ChatResponse {
    String id;
    String object;
    Long created;
    String model;
    List<Choices> choices;
    Object usage;
    String system_fingerprint;

    @Value
    @Jacksonized
    @Builder(
            setterPrefix = "set",
            builderMethodName = "newBuilder",
            toBuilder = true
    )
    public static class Choices {
        Integer index;
        Message message;
        Object logprobs;
        String finish_reason;
    }
}
