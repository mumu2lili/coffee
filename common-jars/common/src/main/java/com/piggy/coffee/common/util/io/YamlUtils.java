package com.piggy.coffee.common.util.io;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;

public class YamlUtils {
    private static ObjectMapper mapper;

    static {
        YAMLFactory yamlFactory = new YAMLFactory();
        yamlFactory.enable(YAMLGenerator.Feature.SPLIT_LINES);
        yamlFactory.disable(YAMLGenerator.Feature.ALWAYS_QUOTE_NUMBERS_AS_STRINGS);
        yamlFactory.enable(YAMLGenerator.Feature.MINIMIZE_QUOTES);
        yamlFactory.disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER);
        mapper = new ObjectMapper(yamlFactory);
    }


    public static void writeYaml(File resultFile,
                                 Object value) {
        try {
            mapper.writeValue(resultFile, value);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
