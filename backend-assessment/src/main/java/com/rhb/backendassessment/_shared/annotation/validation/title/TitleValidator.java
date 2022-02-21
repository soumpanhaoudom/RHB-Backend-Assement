package com.rhb.backendassessment._shared.annotation.validation.title;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class TitleValidator
    implements ConstraintValidator<TitleValidation, Optional<String>> {
  @Override
  public boolean isValid(Optional<String> title, ConstraintValidatorContext constraintValidatorContext) {
    if (title.isPresent() && title != null) {
      if (title.get().length() < 1 || title.get().length() > 128) {
        return false;
      }
    }
    return true;
  }
}
