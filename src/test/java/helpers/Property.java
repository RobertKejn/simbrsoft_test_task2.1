package helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Property {
    private static Properties properties;
    static {
        try {
            properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream("src/test/resources/config.properties");
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
    public static int getPropertyInt(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }
}
