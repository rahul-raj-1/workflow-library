package com.example.workflow.step.impl;

import org.springframework.stereotype.Component;

import com.example.workflow.base.Step;
import com.example.workflow.context.Context;

@Component
public class LoggingStep implements Step {

    @Override
    public void execute(Context context) {
        boolean isValid = (boolean) context.get("isValid");
        if (isValid) {
            System.out.println("Data is valid. Logging successful.");
        } else {
            System.out.println("Data is invalid. Logging failed.");
        }
    }


}
