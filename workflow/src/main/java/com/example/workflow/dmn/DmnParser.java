package com.example.workflow.dmn;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.kie.dmn.api.marshalling.DMNMarshaller;
import org.kie.dmn.backend.marshalling.v1x.DMNMarshallerFactory;
import org.kie.dmn.model.api.Definitions;
import org.kie.dmn.model.api.InputData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;



@Service
public class DmnParser {

	@Autowired
	private ResourceLoader resourceLoader;

	public void executeDMN() throws IOException {


		 ClassPathResource resource = new ClassPathResource("text.dmn");
		    String dmnContent = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
		    

		DMNMarshaller marshaller = DMNMarshallerFactory.newDefaultMarshaller();
		Definitions definitions = marshaller.unmarshal(dmnContent);

		List<InputData> inputDataList = definitions.getDrgElement().stream()
				.filter(element -> element instanceof InputData).map(element -> (InputData) element)
				.collect(Collectors.toList());
		
		System.out.println(inputDataList.size());

		for (InputData inputData : inputDataList) {
			System.out.print(" InputData id: " + inputData.getId());
			System.out.print(" InputData name: " + inputData.getName());
			System.out.print(" InputData type: " + inputData.getVariable().getTypeRef());
			System.out.println(" InputData att : " + inputData.getVariable().getAdditionalAttributes());
			
		}

	}
	
	

	    
}
