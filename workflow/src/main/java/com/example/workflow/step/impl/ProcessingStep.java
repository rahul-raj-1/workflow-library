package com.example.workflow.step.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

import com.example.workflow.base.Step;
import com.example.workflow.context.Context;

@Component
public class ProcessingStep implements Step {
	


    @Override
    public void execute(Context context) {
    	System.out.println(" In Processing step");

        boolean isValid = (boolean) context.get("isValid");
        if (isValid) {
        //    Object data = context.get("get_obj_from_context");
        	List<Objects> processedData = processData(isValid);
            context.put("processedData",processedData);
            System.out.println("Data processed successfully.");
        } else {
            System.out.println("Data was not processed due to validation errors.");
        }
    }

    private List<Objects> processData(Object data) {
        // calls to DB 
    	List<Objects> ans = new ArrayList<Objects>();
        return ans;
    }
}
