package com.API.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class arnList {
	
	private String aplty;
	private String arn;
	private String ismig;
	public String getAplty() {
		return aplty;
	}
	public void setAplty(String aplty) {
		this.aplty = aplty;
	}
	public String getArn() {
		return arn;
	}
	public void setArn(String arn) {
		this.arn = arn;
	}
	public String getIsmig() {
		return ismig;
	}
	public void setIsmig(String ismig) {
		this.ismig = ismig;
	}



}
