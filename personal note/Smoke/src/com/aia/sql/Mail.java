package com.aia.sql;

import java.util.Date;

public class Mail {
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public int getWebId() {
		return webId;
	}

	public void setWebId(int webId) {
		this.webId = webId;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getUseFor() {
		return useFor;
	}

	public void setUseFor(String useFor) {
		this.useFor = useFor;
	}

	private long id;
	private Date createTime;
	private Date modifyTime;
	
	private int webId;
	private String mail;
	
	private String useFor;
	
	public Mail(){}
	
	public Mail(int webId, String mail, String useFor)
	{
		this.webId=webId;
		this.mail=mail;
		this.useFor=useFor;
	}
	
	
	public String toString()
	{
		return "MailDO [id=" + id + ", createTime=" + createTime + ", modifyTime=" + modifyTime + ", webId=" + webId + ", mail=" + mail + ", useFor=" + useFor + "]";
	}
	
}
