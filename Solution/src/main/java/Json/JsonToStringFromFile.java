package Json;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonToStringFromFile {
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
            throw new RuntimeException();
        }
    }
}
