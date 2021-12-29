package com.footsalon.common.code;

public enum Config {
	
	//DOMAIN("http://www.pclass.com"),
	DOMAIN("http://localhost:9292"),
	SMTP_AUTHENTICATION_ID("futsalon2021@gmail.com"),
	SMTP_AUTHENTICATION_PASSWORD("cxjguuxgisrswugo"),
	COMPANY_EMAIL("futsalon2021@gmail.com"),
	//UPLOAD_PATH("C:\\CODE\\upload") 운영서버
	UPLOAD_PATH("C:\\CODE\\upload\\futsalon\\");//개발서버
	
	public final String DESC;
	
	private Config(String desc) {
		this.DESC = desc;
	}
}
