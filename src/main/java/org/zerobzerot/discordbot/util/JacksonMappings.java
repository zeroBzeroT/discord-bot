package org.zerobzerot.discordbot.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class JacksonMappings {

    public static final ObjectMapper YAML = new ObjectMapper(new YAMLFactory());

    static {
        YAML.enable(SerializationFeature.INDENT_OUTPUT);
    }

}
