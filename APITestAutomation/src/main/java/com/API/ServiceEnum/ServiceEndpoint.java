package com.API.ServiceEnum;

public enum ServiceEndpoint {
	AUTHENTICATE("/v0.2/authenticate"),
	Registration_DOWNLOAD_DOCUMENT("/v0.2/document"),
	Registration_GET_ALL_APPLICATION_REQUEST("/v0.2/application"),
	Registration_GETAPPREQ_NEW_REG_SUBMIT("/v0.2/application"),
	Registration_GETALERT("/v0.2/alert"),
	Registration_GetEntityRequests("/v0.2/entity"),
	LOGOUT("/v0.2/logout");
	private String url;

	ServiceEndpoint(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String data) {
		this.url = data+url;
	}

}
