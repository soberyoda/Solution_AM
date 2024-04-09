package Json;

import Interfaces.PathValidator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class JsonPathValidatorTest {
    private PathValidator pathValidator;
    private String invalidPath;
    private String validPath;
    @Before
    public void setUp(){
        pathValidator = new JsonPathValidator();
        invalidPath = "///";
        validPath = "D:\\AWSIAM_Internship_2024\\Solution\\src\\test\\java\\resources\\validJson.json";
    }
    @Test(expected = IllegalArgumentException.class)
    public void testIsPathValid_invalidPath() {
        pathValidator.isPathValid(invalidPath);
    }

    @Test
    public void testIsPathValid_validPath(){
        assertTrue(pathValidator.isPathValid(validPath));
    }
}