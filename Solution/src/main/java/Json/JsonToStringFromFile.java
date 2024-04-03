package Json;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonToStringFromFile {
    private static final Logger logger = LoggerFactory.getLogger(JsonToStringFromFile.class);
    public static String readFile(String jsonFilePath) {
        if(jsonFilePath == null || jsonFilePath.isEmpty()){
            throw new IllegalArgumentException("File path cannot be null or empty.");
        }
        Path path = Paths.get(jsonFilePath);
        if(!Files.exists(path) || !Files.isRegularFile(path)){
            throw new IllegalArgumentException("File does not exist or is not a regular file.");
        }
        try {
            return new String(Files.readAllBytes(path));
        } catch (IOException e) {
            logger.error("Error reading file: " + e.getMessage());
            throw new RuntimeException();
        }
    }
}
