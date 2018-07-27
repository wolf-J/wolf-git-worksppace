package com.aia.cs.ipos.entity;

public class ProposalInfo {
	private String createtime;
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getContactid() {
		return contactid;
	}
	public void setContactid(String contactid) {
		this.contactid = contactid;
	}
	public String getPdfid() {
		return pdfid;
	}
	public void setPdfid(String pdfid) {
		this.pdfid = pdfid;
	}
	public String getProposaltotal() {
		return proposaltotal;
	}
	public void setProposaltotal(String proposaltotal) {
		this.proposaltotal = proposaltotal;
	}
	public String getProposalinfoid() {
		return proposalinfoid;
	}
	public void setProposalinfoid(String proposalinfoid) {
		this.proposalinfoid = proposalinfoid;
	}
	public int getMembers() {
		return members;
	}
	public void setMembers(int members) {
		this.members = members;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public int getDependents() {
		return dependents;
	}
	public void setDependents(int dependents) {
		this.dependents = dependents;
	}
	public SubProposalInfo[] getSubProposalInfo() {
		return SubProposalInfo;
	}
	public void setSubProposalInfo(SubProposalInfo[] subProposalInfo) {
		SubProposalInfo = subProposalInfo;
	}
	private String contactid;
	private String pdfid;
	private String proposaltotal;
	private String proposalinfoid;
	private int members;
	private String updatetime;
	private String productname;
	private int dependents;
	private SubProposalInfo[] SubProposalInfo;
}
