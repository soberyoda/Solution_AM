package Json;

import Interfaces.PathValidator;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonPathValidator implements PathValidator {

    @Override
    public boolean isPathValid(String jsonFilePath) {
        if(jsonFilePath == null || jsonFilePath.isEmpty()){
            throw new IllegalArgumentException("File path cannot be null or empty.");
        }
        Path path = Paths.get(jsonFilePath);
        if(!Files.exists(path) || !Files.isRegularFile(path)){
            throw new IllegalArgumentException("File does not exist or is not a regular file.");
        }
        return true;
    }
}
