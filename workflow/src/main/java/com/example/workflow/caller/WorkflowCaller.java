package com.example.workflow.caller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.workflow.base.Workflow;
import com.example.workflow.step.impl.LoggingStep;
import com.example.workflow.step.impl.ProcessingStep;
import com.example.workflow.step.impl.ValidationStep;

@Component
public class WorkflowCaller {
    
    @Autowired
    private Workflow workflow;
    
    
    @Autowired
    private ValidationStep validationStep;
    
    @Autowired
    private ProcessingStep processingStep;
    
    @Autowired
    private LoggingStep loggingStep;
    
    public void startWorkflow() {
        // Add steps to the workflow
        workflow.addStep(validationStep);
        workflow.addStep(processingStep);
        workflow.addStep(loggingStep);
        
        // Execute the workflow
        workflow.execute();
    }
}

