package com.hpbu.lacs;

public class ChromaObj {
	private String P_DATE;
	private String TIME;
	private String MODEL_NO;
	private String PRODUCT_SN;
	private String ANSI_LUMEN;

	public ChromaObj() {

	}

	public String getP_DATE() {
		return P_DATE;
	}



	public void setP_DATE(String p_DATE) {
		P_DATE = p_DATE;
	}



	public String getTIME() {
		return TIME;
	}



	public void setTIME(String tIME) {
		TIME = tIME;
	}



	public String getMODEL_NO() {
		return MODEL_NO;
	}



	public void setMODEL_NO(String i) {
		MODEL_NO = i;
	}



	public String getPRODUCT_SN() {
		return PRODUCT_SN;
	}



	public void setPRODUCT_SN(String pRODUCT_SN) {
		PRODUCT_SN = pRODUCT_SN;
	}



	public String getANSI_LUMEN() {
		return ANSI_LUMEN;
	}



	public void setANSI_LUMEN(String aNSI_LUMEN) {
		ANSI_LUMEN = aNSI_LUMEN;
	}



	@Override
	public String toString() {
		return "Chroma [MODEL_NO=" + MODEL_NO + ", PRODUCT_SN=" + PRODUCT_SN + ", ANSI_LUMEN=" + ANSI_LUMEN + "]";
	}

}