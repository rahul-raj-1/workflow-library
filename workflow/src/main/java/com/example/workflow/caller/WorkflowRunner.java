package com.example.workflow.caller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class WorkflowRunner implements CommandLineRunner {
   
	@Autowired
    private  WorkflowCaller workflowCaller;

  

    @Override
    public void run(String... args) throws Exception {
        System.out.println("New step added to the workflow!");
        
      //  workflowCaller.startWorkflow();

    }
}
