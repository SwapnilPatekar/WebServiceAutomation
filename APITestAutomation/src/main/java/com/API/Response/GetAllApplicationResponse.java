package com.API.Response;

import java.util.List;

public class GetAllApplicationResponse {

	
	private String arnListCount;
	private List <arnList> arnList;
	public String getArnListCount() {
		return arnListCount;
	}
	public void setArnListCount(String arnListCount) {
		this.arnListCount = arnListCount;
	}
	public List<arnList> getGetArnListResponses() {
		return arnList;
	}
	public void setGetArnListResponses(List<arnList> arnLists) {
		this.arnList = arnLists;
	}
	
	

}
