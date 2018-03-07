package com.lab.sockettest.socket.receive;

public class RestartResponse extends BaseReceivePack {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3139249574149389057L;
	
	private int result;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	@Override
	public void setBody(byte[] body) {
		result = body[0];
	}

}
