package com.aia.cs.ipos.entity;

public class Tasker {
	private String POLICY_NUM;
	public String getPOLICY_NUM() {
		return POLICY_NUM;
	}
	public void setPOLICY_NUM(String pOLICY_NUM) {
		POLICY_NUM = pOLICY_NUM;
	}
	public String getPOLICY_NAME() {
		return POLICY_NAME;
	}
	public void setPOLICY_NAME(String pOLICY_NAME) {
		POLICY_NAME = pOLICY_NAME;
	}
	public String getWFWORKSTEPNAME() {
		return WFWORKSTEPNAME;
	}
	public void setWFWORKSTEPNAME(String wFWORKSTEPNAME) {
		WFWORKSTEPNAME = wFWORKSTEPNAME;
	}
	public String getLOGINID_USER() {
		return LOGINID_USER;
	}
	public void setLOGINID_USER(String lOGINID_USER) {
		LOGINID_USER = lOGINID_USER;
	}
	public int getPAGE_NUM() {
		return PAGE_NUM;
	}
	public void setPAGE_NUM(int pAGE_NUM) {
		PAGE_NUM = pAGE_NUM;
	}
	public int getROW_TOTAL() {
		return ROW_TOTAL;
	}
	public void setROW_TOTAL(int rOW_TOTAL) {
		ROW_TOTAL = rOW_TOTAL;
	}
	private String POLICY_NAME;
	private String WFWORKSTEPNAME;
	private String LOGINID_USER;
	private int PAGE_NUM;
	private int ROW_TOTAL;
	
	
}
