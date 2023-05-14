package com.example.workflow.step.impl;

import org.springframework.stereotype.Component;

import com.example.workflow.base.Step;
import com.example.workflow.context.Context;

@Component
public class ValidationStep implements Step {
	
     //private final Validator validator;

   
    @Override
    public void execute(Context context) {
    	
    	System.out.println(" In validation step");
     //   Object data = context.get("incoming_request");
      //  boolean isValid = validator.validate(data);
        boolean isValid=false;
        if (!isValid) {
            context.put("isValid", true);

        }
    }
}
