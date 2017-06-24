package com.leador.gcloud.monitor.exception;

public class GCloudException extends Exception {
  /**
   * 
   */
  private static final long serialVersionUID = 13069662772895749L;
  private int errorCode;
  private String msg;

  public GCloudException() {
    super();
  }

  public GCloudException(int errorCode, String msg) {
    super();
    this.errorCode = errorCode;
    this.msg = msg;
  }

  public int getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(int errorCode) {
    this.errorCode = errorCode;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }


}
