package io.samancore.dmn_ai.client.groq;

import io.samancore.dmn_ai.client.AiClient;
import io.samancore.dmn_ai.model.DmnAiModel;
import io.samancore.dmn_ai.model.DmnAiRequestModel;
import io.samancore.dmn_ai.model.groq.GroqChatRequest;
import io.samancore.dmn_ai.model.groq.GroqMessages;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ApplicationScoped
public class GroqClient implements AiClient {
    private static final String AI_MODEL = "meta-llama/llama-4-scout-17b-16e-instruct";
    private static final String USER_NAME = "user";

    @ConfigProperty(name = "api.key.groq")
    String apiKey;

    @Inject
    @RestClient
    GroqApi api;

    @Override
    public DmnAiModel generate(DmnAiRequestModel model) {
        String aiModel = (model.getModel() == null || model.getModel().isEmpty()) ? AI_MODEL : model.getModel();

        var request = GroqChatRequest.newBuilder()
                .setModel(aiModel)
                .setMessages(List.of(GroqMessages.newBuilder().setRole(USER_NAME).setContent(model.getMessage()).build()))
                .build();
        var response = api.chat(request, apiKey);
        var data = response.getChoices().getFirst().getMessage().getContent();

        var message = extractTextBeforeXmlContent(data);
        if (message.isEmpty()) {
            message = extractTextBetweenXmlPatterns(data);
        }

        return DmnAiModel.newBuilder()
                .setMessage(message)
                .setData(extractXmlContent(data))
                .build();
    }

    @Override
    public DmnAiModel explain(DmnAiRequestModel model) {
        return null;
    }

    public String extractXmlContent(String text) {
        Pattern patron = Pattern.compile("(?s)```xml\\s*(.*?)\\s*```");
        Matcher matcher = patron.matcher(text);
        if (matcher.find()) {
            return matcher.group(1).trim();
        } else {
            return "Not Content";
        }
    }

    public String extractTextBeforeXmlContent(String text) {
        Pattern patron = Pattern.compile("(?s)(.*?)```xml\\s*(.*?)\\s*```");
        Matcher matcher = patron.matcher(text);
        if (matcher.find()) {
            return matcher.group(1).trim();
        } else {
            return "";
        }
    }

    public String extractTextBetweenXmlPatterns(String text) {
        Pattern patron = Pattern.compile("(?s)```xml\\s*(.*?)\\s*```");
        Matcher matcher = patron.matcher(text);
        if (matcher.find()) {
            int firstEnd = matcher.end();
            if (matcher.find(firstEnd)) {
                return text.substring(firstEnd, matcher.start()).trim();
            } else {
                return text.substring(firstEnd).trim();
            }
        } else {
            return "";
        }
    }
}
