package com.API.TestManagementPOJO;

public class TestScenariosPOJO {
	private String reference;
	private String prerequisiteTestSuiteReference;
	private String prerequisiteTestScenarioReference;
	private String description;
	private String automationScriptReference;
	private String serialNumber;
	private String executeTestScenario;
	private String executionStatus;
	private String automationScripterName;
	private String logFile;
	private String startTime;
	private String endTime;
	private String remarks;
	
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getPrerequisiteTestSuiteReference() {
		return prerequisiteTestSuiteReference;
	}
	public void setPrerequisiteTestSuiteReference(String prerequisiteTestSuiteReference) {
		this.prerequisiteTestSuiteReference = prerequisiteTestSuiteReference;
	}
	public String getPrerequisiteTestScenarioReference() {
		return prerequisiteTestScenarioReference;
	}
	public void setPrerequisiteTestScenarioReference(String prerequisiteTestScenarioReference) {
		this.prerequisiteTestScenarioReference = prerequisiteTestScenarioReference;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAutomationScriptReference() {
		return automationScriptReference;
	}
	public void setAutomationScriptReference(String automationScriptReference) {
		this.automationScriptReference = automationScriptReference;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getExecuteTestScenario() {
		return executeTestScenario;
	}
	public void setExecuteTestScenario(String executeTestScenario) {
		this.executeTestScenario = executeTestScenario;
	}
	public String getExecutionStatus() {
		return executionStatus;
	}
	public void setExecutionStatus(String executionStatus) {
		this.executionStatus = executionStatus;
	}
	public String getAutomationScripterName() {
		return automationScripterName;
	}
	public void setAutomationScripterName(String automationScripterName) {
		this.automationScripterName = automationScripterName;
	}
	public String getLogFile() {
		return logFile;
	}
	public void setLogFile(String logFile) {
		this.logFile = logFile;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
