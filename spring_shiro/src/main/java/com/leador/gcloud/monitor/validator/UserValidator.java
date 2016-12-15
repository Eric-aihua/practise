package com.leador.gcloud.monitor.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.leador.gcloud.monitor.po.User;

public class UserValidator implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    return User.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    User user = (User) target;
    if (!user.getPwd().equals(user.getRetryPwd())) {
      errors.rejectValue("retryPwd", "error.pwd.notmath");
    }
  }

}
