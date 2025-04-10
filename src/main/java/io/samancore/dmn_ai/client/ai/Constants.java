package io.samancore.dmn_ai.client.ai;

public class Constants {
    public static final String AI_MODEL = "deepseek-chat";
    public static final String USER_NAME = "user";
    public static final String SYSTEM_NAME = "system";
    public static final String ASSISTANT_NAME = "assistant";
    public static final String CONTENT_SYSTEM = "You are a Java developer and business analyst, you have to model DMN decision diagram in version 1.5 " +
            "for import at https://sandbox.kie.org/#/8db5e6e2-2b7d-4e8a-93ba-67e78ba4d5b3/file/Untitled.dmn. " +
            "Respond only with the xml diagram file followed by an explanation of what was created. The final decision element has to be called 'result'";
    public static final String INITIAL_CONTENT_USER = "give me an example of a dmn diagram";
    public static final String INITIAL_CONTENT_ASSISTANT = """
            ```xml
            <?xml version="1.0" encoding="UTF-8" ?>
            <definitions xmlns="https://www.omg.org/spec/DMN/20230324/MODEL/" expressionLanguage="https://www.omg.org/spec/DMN/20230324/FEEL/" namespace="https://kie.org/dmn/_4D5DBD9B-AD6B-4DFB-9EA4-41C632BC5C26" id="_37D038A8-BB85-4F4D-B723-63CDA4457A31" name="DMN_D08C12D9-25ED-48C4-8D5D-EA77EDFA1303" xmlns:dmndi="https://www.omg.org/spec/DMN/20230324/DMNDI/" xmlns:dc="http://www.omg.org/spec/DMN/20180521/DC/" xmlns:di="http://www.omg.org/spec/DMN/20180521/DI/" xmlns:kie="https://kie.org/dmn/extensions/1.0">
              <inputData name="New Input Data" id="_7FF563B6-57C9-4F17-BEC6-6CCF86079647">
                <variable name="New Input Data" id="_62D1FA9F-E17B-4C8C-B5EB-C2235D2EBE3E" />
              </inputData>
              <decision name="result" id="_4A678661-5455-4E97-9681-EC37DF6FAE5B">
                <variable id="_367D8E89-E034-49EC-A05A-156428D51AFB" name="result" />
                <informationRequirement id="_BE205D50-A14B-454C-A2A6-276F222B22E8">
                  <requiredInput href="#_7FF563B6-57C9-4F17-BEC6-6CCF86079647" />
                </informationRequirement>
              </decision>
              <dmndi:DMNDI>
                <dmndi:DMNDiagram id="_D6F29E65-EE22-4441-933F-34760BA4F0CF" name="Default DRD" useAlternativeInputDataShape="false">
                  <di:extension>
                    <kie:ComponentsWidthsExtension>
                      <kie:ComponentWidths />
                    </kie:ComponentsWidthsExtension>
                  </di:extension>
                  <dmndi:DMNShape id="_BA4724E5-EB77-4014-954F-D5848640D127" dmnElementRef="_7FF563B6-57C9-4F17-BEC6-6CCF86079647" isCollapsed="false" isListedInputData="false">
                    <dc:Bounds x="100" y="300" width="160" height="80" />
                  </dmndi:DMNShape>
                  <dmndi:DMNShape id="_E7DB116D-9E95-4B03-8A86-2286E2E968FB" dmnElementRef="_4A678661-5455-4E97-9681-EC37DF6FAE5B" isCollapsed="false" isListedInputData="false">
                    <dc:Bounds x="100" y="100" width="160" height="80" />
                  </dmndi:DMNShape>
                  <dmndi:DMNEdge id="_9E30FC3A-E90F-45A6-893F-E7E51EA5797C-AUTO-TARGET" dmnElementRef="_BE205D50-A14B-454C-A2A6-276F222B22E8" sourceElement="_BA4724E5-EB77-4014-954F-D5848640D127" targetElement="_E7DB116D-9E95-4B03-8A86-2286E2E968FB">
                    <di:waypoint x="180" y="340" />
                    <di:waypoint x="180" y="140" />
                  </dmndi:DMNEdge>
                </dmndi:DMNDiagram>
              </dmndi:DMNDI>
            </definitions>
            ```
            This DMN model:
            1. Defines one input data element (New Input Data)
            2. Contains one decision (result)
            4. Includes diagram information (DMNDI) for visualization
            """;

    private Constants() {
    }
}
