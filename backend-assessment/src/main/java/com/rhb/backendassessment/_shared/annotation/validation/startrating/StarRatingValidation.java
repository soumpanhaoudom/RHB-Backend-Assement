package com.rhb.backendassessment._shared.annotation.validation.startrating;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * StarRating Validation
 * @author panhoudom
 */
@Target( { ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = StarRatingValidator.class)
public @interface StarRatingValidation {
    //error message
    String message() default "invalid starRating";

    //represents group of constraints
    public Class<?>[] groups() default {};

    //represents additional information about annotation
    public Class<? extends Payload>[] payload() default {};
}
