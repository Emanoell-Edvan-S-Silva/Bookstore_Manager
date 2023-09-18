package wdabookstore.bookstoremanager.notes;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.Year;

public class YearConstraintValidator implements ConstraintValidator<YearConstraint, Integer> {
    @Override
    public void initialize(YearConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(Integer year, ConstraintValidatorContext context) {
        if (year == null) {
            return true;
        }

        int currentYear = Year.now().getValue();
        return year <= currentYear;
    }
}
