package io.samancore.dmn_ai.client.ai;

public class Constants {
    public static final String AI_MODEL = "deepseek-chat";
    public static final String USER_NAME = "user";
    public static final String SYSTEM_NAME = "system";
    public static final String CONTENT_SYSTEM_INIT = """
            You are a Java developer and business analyst, you have to modified DMN 1.2+ decision diagram for import in IBM BAMOE.
            Respond only with the xml diagram followed by an explanation of what was created. The final decision element has to be named 'result'.
            The initial diagram to modified is:
            ```xml
            """;
    public static final String CONTENT_SYSTEM_END = """
            
            ```
            """;

    private Constants() {
    }
}
