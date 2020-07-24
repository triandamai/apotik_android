package com.tdn.domain.serialize.res;

import com.tdn.domain.model.PenjualanModel;

import java.util.ArrayList;
import java.util.List;

public class ResponseGetPenjualan{
	private int responseCode;
	private String responseMessage;
	private List<PenjualanModel> data;
	private boolean status;

	public void setResponseCode(int responseCode){
		this.responseCode = responseCode;
	}

	public int getResponseCode(){
		return responseCode;
	}

	public void setResponseMessage(String responseMessage){
		this.responseMessage = responseMessage;
	}

	public String getResponseMessage(){
		return responseMessage;
	}

	public void setData(List<PenjualanModel> data){
		this.data = data;
	}

	public List<PenjualanModel> getData(){
		if(data == null){
			data = new ArrayList<>();
		}
		return data;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"ResponseGetPenjualan{" + 
			"response_code = '" + responseCode + '\'' + 
			",response_message = '" + responseMessage + '\'' + 
			",data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}