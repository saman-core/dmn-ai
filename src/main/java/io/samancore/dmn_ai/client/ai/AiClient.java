package io.samancore.dmn_ai.client.ai;

import io.samancore.dmn_ai.model.DmnAiModel;
import io.samancore.dmn_ai.model.DmnAiRequestModel;
import io.samancore.dmn_ai.model.ai.ChatRequest;
import io.samancore.dmn_ai.model.ai.Message;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.samancore.dmn_ai.client.ai.Constants.*;

@ApplicationScoped
public class AiClient implements io.samancore.dmn_ai.client.AiClient {

    @ConfigProperty(name = "api.key")
    String apiKey;

    @Inject
    @RestClient
    AiApi api;

    @Override
    public DmnAiModel generate(DmnAiRequestModel model) {
        var request = ChatRequest.newBuilder()
                .setStream(false)
                .setModel(AI_MODEL)
                .setMessages(
                        List.of(Message.newBuilder()
                                        .setRole(SYSTEM_NAME)
                                        .setContent(CONTENT_SYSTEM_INIT + model.getInitialModel() + CONTENT_SYSTEM_END)
                                        .build(),
                                Message.newBuilder()
                                        .setRole(USER_NAME)
                                        .setContent(model.getMessage())
                                        .build()
                        )
                )
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
            return "";
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
