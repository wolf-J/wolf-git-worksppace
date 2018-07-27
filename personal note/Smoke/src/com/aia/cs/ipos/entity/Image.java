package com.aia.cs.ipos.entity;

public class Image {
	private String doccontent;
	public String getDoccontent() {
		return doccontent;
	}
	public void setDoccontent(String doccontent) {
		this.doccontent = doccontent;
	}
	public String getImagetype() {
		return imagetype;
	}
	public void setImagetype(String imagetype) {
		this.imagetype = imagetype;
	}
	public String getEappInfoid() {
		return eappInfoid;
	}
	public void setEappInfoid(String eappInfoid) {
		this.eappInfoid = eappInfoid;
	}
	public String getImageid() {
		return imageid;
	}
	public void setImageid(String imageid) {
		this.imageid = imageid;
	}
	private String imagetype;
	private String eappInfoid;
	private String imageid;
}
