package com.rhb.backendassessment._shared.annotation.validation.startrating;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class StarRatingValidator
    implements ConstraintValidator<StarRatingValidation, Optional<Double>> {
  @Override
  public boolean isValid(Optional<Double> starRating, ConstraintValidatorContext constraintValidatorContext) {
    if (starRating.isPresent() && starRating != null) {
      if (starRating.get() < 0.5 || starRating.get() > 5) {
        return false;
      }
    }
    return true;
  }
}
