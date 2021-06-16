package hospitalRun.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

@Slf4j
public class PropertyReader {
    private static Properties properties;

    private PropertyReader() {
        properties = new Properties();
        String rootPath = Objects.requireNonNull(getClass().getClassLoader().getResource("application.properties")).getPath();
        try {
            properties.load(new FileInputStream(rootPath));
        } catch (FileNotFoundException e) {
            log.error("Property file not found", e);
            throw new RuntimeException("Property file not found");
        } catch (IOException e) {
            log.error("File cannot be read", e);
            throw new RuntimeException("File cannot be read");
        }
    }

    public static Properties applicationProperties() {
        if (properties == null) {
            new PropertyReader();
        }
        return properties;
    }
}
