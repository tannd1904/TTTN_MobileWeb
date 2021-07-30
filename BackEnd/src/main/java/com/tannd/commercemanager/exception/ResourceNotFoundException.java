package com.tannd.commercemanager.exception;

public class ResourceNotFoundException extends BaseException {

  private static final long serialVersionUID = 1L;

  private static final int ERROR_STATUS_DEFAULT = 404;

  public ResourceNotFoundException(String message, Throwable e) {
    super(ERROR_STATUS_DEFAULT, message, e);
  }

  public ResourceNotFoundException(String message) {
    super(ERROR_STATUS_DEFAULT, message);
  }

}
