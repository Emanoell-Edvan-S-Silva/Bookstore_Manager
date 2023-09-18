package wdabookstore.bookstoremanager.notes;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = YearConstraintValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface YearConstraint {
    String message() default "O ano de lançamento não pode ser no futuro";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
