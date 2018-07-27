package com.aia.coast.entity;

public class assignTask {
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getProcessStep() {
		return processStep;
	}
	public void setProcessStep(String processStep) {
		this.processStep = processStep;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	private String id;
	private String userId;
	private String caseId;
	private String processStep;
	private String activityId;
	
	@Override
	public String toString()
	{
		return "assignTask: [id="+id+", userId="+userId+",caseId="+caseId+",processStep="+processStep+",activityId="+activityId+"]";
	}
}
