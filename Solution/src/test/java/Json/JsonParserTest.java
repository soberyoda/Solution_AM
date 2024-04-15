package Json;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class JsonParserTest {
    private String invalidPath;
    private String validPath;
    private String validJson;

    /*
     YOU HAVE TO CHANGE PATH !
    */
    @Before
    public void setUp(){
        invalidPath = "///";
        validPath = "D:\\AWSIAM_Internship_2024\\Solution\\src\\test\\java\\resources\\validJson.json"; // <-- Yo have to change path!
        validJson =  "{\n" +
                "    \"PolicyName\": \"root\",\n" +
                "    \"PolicyDocument\": {\n" +
                "        \"Version\": \"2012-10-17\",\n" +
                "        \"Statement\": [\n" +
                "            {\n" +
                "                \"Sid\": \"IamListAccess\",\n" +
                "                \"Effect\": \"Allow\",\n" +
                "                \"Action\": [\n" +
                "                    \"iam:ListRoles\",\n" +
                "                    \"iam:ListUsers\"\n" +
                "                ],\n" +
                "                \"Resource\": \"*\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}\n";
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReadFile_invalidPath() {
        JsonParser.readFile(invalidPath);
    }

    @Test
    public void testReadFile_validPath(){
        assertEquals(validJson.trim(), JsonParser.readFile(validPath).trim());
    }

    @Test
    public void testIsJsonNotEmpty_jsonIsEmpty() {
        assertFalse(JsonParser.isJsonNotEmpty(""));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParse_pathIsNull() {
        JsonParser.parse("");
    }
}