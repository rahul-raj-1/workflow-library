package com.example.workflow.dmn;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class DmnDecisionTableInput {

    public static void main(String[] args) {
        List<FieldDetails> fieldDetailsList = parseDmnFile();
        
        System.out.println(fieldDetailsList.toString());

        for (FieldDetails fieldDetails : fieldDetailsList) {
            System.out.println(fieldDetails);
        }
    }

    public static List<FieldDetails> parseDmnFile() {
        List<FieldDetails> fieldDetailsList = new ArrayList<>();

        try {
            ClassPathResource resource = new ClassPathResource("text.dmn");
            InputStream inputStream = resource.getInputStream();
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(inputStream);
            document.getDocumentElement().normalize();

            // Get the list of 'dmn:decisionTable' elements
            NodeList decisionTableList = document.getElementsByTagName("dmn:decisionTable");

            // Iterate through the 'dmn:decisionTable' elements
            for (int i = 0; i < decisionTableList.getLength(); i++) {
                Node decisionTableNode = decisionTableList.item(i);
                if (decisionTableNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element decisionTableElement = (Element) decisionTableNode;

                    // Get the list of 'dmn:inputExpression' elements within 'dmn:decisionTable'
                    NodeList inputExpressionList = decisionTableElement.getElementsByTagName("dmn:inputExpression");

                    // Iterate through the 'dmn:inputExpression' elements
                    for (int j = 0; j < inputExpressionList.getLength(); j++) {
                        Node inputExpressionNode = inputExpressionList.item(j);
                        if (inputExpressionNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element inputExpressionElement = (Element) inputExpressionNode;

                            // Get the column name from the 'dmn:text' element
                            NodeList textNodes = inputExpressionElement.getElementsByTagName("dmn:text");
                            if (textNodes.getLength() > 0) {
                                String columnName = textNodes.item(0).getTextContent().trim();
                                String dataType = inputExpressionElement.getAttribute("typeRef");
                                Set<String> boundaryConditions = new HashSet<>();

                                // Get the list of 'dmn:rule' elements within 'dmn:decisionTable'
                                NodeList ruleList = decisionTableElement.getElementsByTagName("dmn:rule");

                                // Iterate through the 'dmn:rule' elements
                                for (int k = 0; k < ruleList.getLength(); k++) {
                                    Node ruleNode = ruleList.item(k);
                                    if (ruleNode.getNodeType() == Node.ELEMENT_NODE) {
                                        Element ruleElement = (Element) ruleNode;

                                        // Get the corresponding value from the 'dmn:text' element if available
                                        NodeList ruleTextNodes = ruleElement.getElementsByTagName("dmn:text");
                                        if (j < ruleTextNodes.getLength()) {
                                            String value = ruleTextNodes.item(j).getTextContent().trim();
                                            boundaryConditions.add(value);
                                        }
                                    }
                                }

                                FieldDetails fieldDetails = new FieldDetails(columnName, dataType, boundaryConditions);
                                fieldDetailsList.add(fieldDetails);
                            }
                        }
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return fieldDetailsList;
    }
}
