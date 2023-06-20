package com.example.workflow.dmn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyCommandLineRunner implements CommandLineRunner {
	
	
	
	@Autowired
	private DmnDecisionTableInput d;

    @Override
    public void run(String... args) throws Exception {
        // This code will run when the application starts up
    	
    	
    	 List<FieldDetails> fieldDetailsList = d.parseDmnFile();
    	 
          
          System.out.println(fieldDetailsList.toString());

          for (FieldDetails fieldDetails : fieldDetailsList) {
              System.out.println(fieldDetails);
          }
    	 
    	
    	 
    	
    	 
    	 
        
    }
}
