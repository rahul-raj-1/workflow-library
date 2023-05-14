package com.example.workflow.base;

import java.util.ArrayList;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.workflow.context.Context;
@Component
public class MyWorkflow implements Workflow {
   
    
    private List<Step> steps = new ArrayList<>();
    
   
    
    @Override
    public void addStep(Step step) {
        steps.add(step);
    }

    @Override
    public void execute() {
        Context context = new Context();
        try {
            for (Step step : steps) {
                step.execute(context);
               
            }
        } catch (Exception e) {
            handleError(e);
        }
    }

    @Override
    public void handleError(Exception e) {
        // Handle the error here
    }
}
