package com.example.workflow.dmn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyCommandLineRunner implements CommandLineRunner {
	
	
	
	@Autowired
	private DMNDataTypePrinter d;

    @Override
    public void run(String... args) throws Exception {
        // This code will run when the application starts up
    	
    	
    	d.executeDMN();
        
    }
}
