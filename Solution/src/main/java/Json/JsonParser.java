package Json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JsonParser {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static String readFile(String jsonFilePath) {
        if(jsonFilePath == null || jsonFilePath.isEmpty()){
            throw new IllegalArgumentException("File path cannot be null or empty.");
        }
        Path path = Paths.get(jsonFilePath);
        if(!Files.exists(path) || !Files.isRegularFile(path)){
            throw new IllegalArgumentException("File does not exist or is not a regular file.");
        }
        try {
            Stream<String> lines = Files.lines(path);
            String data = lines.collect(Collectors.joining("\n"));
            lines.close();
            return data;
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
    public static boolean isJsonNotEmpty(String jsonData) {
        return jsonData != null && !jsonData.isEmpty();
    }
    public static JsonNode parse(String src){
        String content = readFile(src);
        if(!isJsonNotEmpty(content)) throw new IllegalArgumentException("JSON data is empty or null");
        try {
            return objectMapper.readTree(content);
        }catch (JsonProcessingException e){
            e.printStackTrace();
            return null;
        }
    }
}
