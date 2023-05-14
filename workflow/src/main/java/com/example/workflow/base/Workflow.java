package com.example.workflow.base;

public interface Workflow {
    // Method to add a new step to the workflow
    void addStep(Step step);

    // Method to execute the workflow
    void execute();

    // Method to handle errors or exceptions during workflow execution
    void handleError(Exception e);
}
