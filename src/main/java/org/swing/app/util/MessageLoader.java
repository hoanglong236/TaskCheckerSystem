package org.swing.app.util;

import org.swing.app.common.Constant;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MessageLoader {

    private static final MessageLoader UNIQUE_INSTANCE = new MessageLoader();
    private final Properties properties;

    private MessageLoader() {
        this.properties = new Properties();
        try {
            final ClassLoader currentClassLoader = getClass().getClassLoader();
            final InputStream inputStream = currentClassLoader.getResourceAsStream(Constant.MESSAGE_FILE_PATH);
            this.properties.load(inputStream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static MessageLoader getInstance() {
        return UNIQUE_INSTANCE;
    }

    public String getMessage(String key) {
        return this.properties.getProperty(key);
    }
}
