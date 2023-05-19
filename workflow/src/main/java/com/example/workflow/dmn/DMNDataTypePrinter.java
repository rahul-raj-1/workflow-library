package com.example.workflow.dmn;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.namespace.QName;

import org.kie.dmn.api.marshalling.DMNMarshaller;
import org.kie.dmn.backend.marshalling.v1x.DMNMarshallerFactory;
import org.kie.dmn.model.api.Definitions;
import org.kie.dmn.model.api.InputData;
import org.kie.dmn.model.api.ItemDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

@Service
public class DMNDataTypePrinter {

	@Autowired
	private ResourceLoader resourceLoader;
	
	public void executeDMN() throws IOException {
	    ClassPathResource resource = new ClassPathResource("text.dmn");
	    String dmnContent = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);

	    DMNMarshaller marshaller = DMNMarshallerFactory.newDefaultMarshaller();
	    Definitions definitions = marshaller.unmarshal(dmnContent);

	    List<InputData> inputDataList = definitions.getDrgElement().stream()
	            .filter(element -> element instanceof InputData)
	            .map(element -> (InputData) element)
	            .collect(Collectors.toList());

	    List<ItemDefinition> inputItemDefinitions = new ArrayList<>();

	    for (InputData inputData : inputDataList) {
	        ItemDefinition itemDefinition = findItemDefinition(definitions, inputData.getVariable().getTypeRef());
	        if (itemDefinition != null) {
	            inputItemDefinitions.add(itemDefinition);
	        }
	    }

	    for (ItemDefinition inputItemDefinition : inputItemDefinitions) {
	        iterateItemDefinitionComponents(inputItemDefinition, 0);
	    }
	}




	// Recursive function to iterate over the components of an ItemDefinition
	private static void iterateItemDefinitionComponents(ItemDefinition itemDefinition, int level) {
	    String indentation = getIndentation(level);

	    System.out.println(indentation + "ItemComponent Name: " + itemDefinition.getName());
	    System.out.println(indentation + "ItemComponent TypeRef: " + itemDefinition.getTypeRef());
	    System.out.println(indentation + "---------------------------------------");

	    List<ItemDefinition> nestedItemDefinitions = itemDefinition.getItemComponent();
	    for (ItemDefinition nestedItemDefinition : nestedItemDefinitions) {
	        iterateItemDefinitionComponents(nestedItemDefinition, level + 1);
	    }
	}
	
	// Utility function to generate indentation based on the level
	private static String getIndentation(int level) {
	    StringBuilder indentation = new StringBuilder();
	    for (int i = 0; i < level; i++) {
	        indentation.append("\t");
	    }
	    return indentation.toString();
	}
	
	

	private ItemDefinition findItemDefinition(Definitions definitions, QName typeRef) {
	    return definitions.getItemDefinition().stream()
	            .filter(itemDefinition -> itemDefinition.getName().equals(typeRef.getLocalPart()))
	            .findFirst()
	            .orElse(null);
	}
}
	    