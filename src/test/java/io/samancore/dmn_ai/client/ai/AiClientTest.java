package io.samancore.dmn_ai.client.ai;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class AiClientTest {

    @Inject
    AiClient aiClient;

    @Test
    public void testExtractXmlContent() {
        String text = "Some text before ```xml <tag>content</tag> ``` some text after";
        String expected = "<tag>content</tag>";
        String result = aiClient.extractXmlContent(text);
        assertEquals(expected, result);
    }

    @Test
    public void testExtractXmlContent_AtInit() {
        String text = "```xml <tag>content</tag> ``` some text after";
        String expected = "<tag>content</tag>";
        String result = aiClient.extractXmlContent(text);
        assertEquals(expected, result);
    }

    @Test
    public void testExtractXmlContent_AtEnd() {
        String text = "Some text before ```xml <tag>content</tag> ```";
        String expected = "<tag>content</tag>";
        String result = aiClient.extractXmlContent(text);
        assertEquals(expected, result);
    }

    @Test
    public void testExtractXmlContent_NotContent() {
        String text = "Some text";
        String expected = "";
        String result = aiClient.extractXmlContent(text);
        assertEquals(expected, result);
    }

    @Test
    public void testExtractXmlContent_First() {
        String text = "Some text before ```xml <tag>content</tag> ``` some text between ```xml <tag>content2</tag> ``` ok";
        String expected = "<tag>content</tag>";
        String result = aiClient.extractXmlContent(text);
        assertEquals(expected, result);
    }

    @Test
    public void testExtractTextBeforeXmlContent() {
        String text = "Some text before ```xml <tag>content</tag> ``` some text after";
        String expected = "Some text before";
        String result = aiClient.extractTextBeforeXmlContent(text);
        assertEquals(expected, result);
    }

    @Test
    public void testExtractTextBeforeXmlContent_NotContent() {
        String text = "```xml <tag>content</tag> ``` some text after";
        String expected = "";
        String result = aiClient.extractTextBeforeXmlContent(text);
        assertEquals(expected, result);
    }

    @Test
    public void testExtractTextBetweenXmlPatterns() {
        String text = "Some text before ```xml <tag>content1</tag> ``` some text between ```xml <tag>content2</tag> ``` some text after";
        String expected = "some text between";
        String result = aiClient.extractTextBetweenXmlPatterns(text);
        assertEquals(expected, result);
    }

    @Test
    public void testExtractTextBetweenXmlPatterns_NoSecondPattern() {
        String text = "Some text before ```xml <tag>content1</tag> ``` some text after";
        String expected = "some text after";
        String result = aiClient.extractTextBetweenXmlPatterns(text);
        assertEquals(expected, result);
    }

    @Test
    public void testExtractTextBetweenXmlPatterns_NotContent() {
        String text = "Some text before ```xml <tag>content1</tag> ```";
        String expected = "";
        String result = aiClient.extractTextBetweenXmlPatterns(text);
        assertEquals(expected, result);
    }
}