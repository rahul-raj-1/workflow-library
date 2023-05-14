package com.example.workflow.base;

import com.example.workflow.context.Context;

public interface Step {

    void execute(Context context) throws Exception;

}
