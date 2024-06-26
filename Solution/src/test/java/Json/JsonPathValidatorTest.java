package Json;

import Interfaces.PathValidator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
/*
    YOU HAVE TO CHANGE PATH !
*/
public class JsonPathValidatorTest {
    private PathValidator pathValidator;
    private String invalidPath;
    private String validPath;
    @Before
    public void setUp(){
        pathValidator = new JsonPathValidator();
        invalidPath = "///";
        validPath = "D:\\AWSIAM_Internship_2024\\Solution\\src\\main\\resources\\validJson.json"; // <-- You have to change Path!
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