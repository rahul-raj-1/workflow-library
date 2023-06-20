package com.example.workflow.dmn;

import java.util.List;
import java.util.Set;

public class FieldDetails {
    private String fieldName;
    private String dataType;
    private Set<String> feelExpressions;
    private List<Object> generatedBoundaryData;
    
    public FieldDetails(String fieldName, String dataType, Set<String> boundaryConditions) {
        this.fieldName = fieldName;
        this.dataType = dataType;
        this.feelExpressions = boundaryConditions;
    }

    public FieldDetails(String fieldName, String dataType, Set<String> boundaryConditions, List<Object> generatedBoundaryData) {
        this.fieldName = fieldName;
        this.dataType = dataType;
        this.feelExpressions = boundaryConditions;
        this.generatedBoundaryData = generatedBoundaryData;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getDataType() {
        return dataType;
    }

   
    

    public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	
	public Set<String> getFeelExpressions() {
		return feelExpressions;
	}

	public void setFeelExpressions(Set<String> feelExpressions) {
		this.feelExpressions = feelExpressions;
	}

	

	public List<Object> getGeneratedBoundaryData() {
		return generatedBoundaryData;
	}

	public void setGeneratedBoundaryData(List<Object> generatedBoundaryData) {
		this.generatedBoundaryData = generatedBoundaryData;
	}

	@Override
    public String toString() {
        return fieldName + ": " + dataType + ": - " + feelExpressions.toString() + " Generated Numbers: " + generatedBoundaryData.toString();
    }
}
