package com.rhb.backendassessment._shared.annotation.validation.title;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Title Validation
 * @author panhoudom
 */
@Target( { ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = TitleValidator.class)
public @interface TitleValidation {
    //error message
    String message() default "invalid title";

    //represents group of constraints
    public Class<?>[] groups() default {};

    //represents additional information about annotation
    public Class<? extends Payload>[] payload() default {};
}
