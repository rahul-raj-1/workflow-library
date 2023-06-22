package com.example.workflow.dmn;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XMLParser {
    public static void main(String[] args) {
        try {
            // Create a ResourceLoader instance
            
            ClassPathResource resource = new ClassPathResource("text.dmn");

            File xmlFile = resource.getFile();

            // Load and parse the XML document
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);

            // Create a map to store the values
            Map<String, String> itemDefinitions = new HashMap<>();
            Map<String, String> itemComponents = new HashMap<>();

            // Get all <dmn:itemDefinition> elements
            NodeList itemDefinitionList = document.getElementsByTagName("dmn:itemDefinition");
            for (int i = 0; i < itemDefinitionList.getLength(); i++) {
                Element itemDefinition = (Element) itemDefinitionList.item(i);
                String id = itemDefinition.getAttribute("id");
                String typeRef = itemDefinition.getElementsByTagName("dmn:typeRef").item(0).getTextContent();
                itemDefinitions.put(id, typeRef);
            }

            // Get all <dmn:itemComponent> elements
            NodeList itemComponentList = document.getElementsByTagName("dmn:itemComponent");
            for (int i = 0; i < itemComponentList.getLength(); i++) {
                Element itemComponent = (Element) itemComponentList.item(i);
                String id = itemComponent.getAttribute("id");
                String typeRef = itemComponent.getElementsByTagName("dmn:typeRef").item(0).getTextContent();
                itemComponents.put(id, typeRef);
            }

            // Print the maps
            printItemDefinitions(itemDefinitions);
            printItemComponents(itemComponents);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printItemDefinitions(Map<String, String> itemDefinitions) {
        System.out.println("Item Definitions:");
        for (Map.Entry<String, String> entry : itemDefinitions.entrySet()) {
            System.out.println("ID: " + entry.getKey() + ", TypeRef: " + entry.getValue());
        }
    }

    private static void printItemComponents(Map<String, String> itemComponents) {
        System.out.println("Item Components:");
        for (Map.Entry<String, String> entry : itemComponents.entrySet()) {
            System.out.println("ID: " + entry.getKey() + ", TypeRef: " + entry.getValue());
        }
    }
}

