package com.example.workflow.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.example.workflow.base.Step;
import com.example.workflow.config.StepConfig;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

//WorkflowUtil.java
public class WorkflowUtil {
 public static List<Step> loadConfig(String configFile) {
     List<Step> steps = new ArrayList<>();

     try {
         ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
         mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

         InputStream inputStream = new FileInputStream(new File(configFile));
         List<StepConfig> configs = mapper.readValue(inputStream, new TypeReference<List<StepConfig>>() {});

         for (StepConfig config : configs) {
             if (config.isEnabled()) {
                 Step step = (Step) Class.forName(config.getClassName()).getDeclaredConstructor().newInstance();
                 steps.add(step);
             }
         }
     } catch (Exception e) {
         e.printStackTrace();
     }

     return steps;
 }
}

