package com.spring.pms.dataSource;

public class DataSource {
	private String HOST;
	private String USER;
	private String PORT;
	private String PASS;
	private String DATABASE;
	public String getHOST() {
		return HOST;
	}
	public void setHOST(String hOST) {
		HOST = hOST;
	}
	public String getUSER() {
		return USER;
	}
	public void setUSER(String uSER) {
		USER = uSER;
	}
	public String getPORT() {
		return PORT;
	}
	public void setPORT(String pORT) {
		PORT = pORT;
	}
	public String getPASS() {
		return PASS;
	}
	public void setPASS(String pASS) {
		PASS = pASS;
	}
	public String getDATABASE() {
		return DATABASE;
	}
	public void setDATABASE(String dATABASE) {
		DATABASE = dATABASE;
	}
}
