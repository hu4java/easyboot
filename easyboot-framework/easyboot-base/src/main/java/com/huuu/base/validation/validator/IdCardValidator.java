package com.huuu.base.validation.validator;

import com.huuu.base.validation.constraints.IdCard;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * 身份证号验证器
 * @author chenzhenhu
 */
public class IdCardValidator implements ConstraintValidator<IdCard, String> {

    private Pattern pattern = Pattern.compile("/^[1-9]\\d{5}(?:18|19|20)\\d{2}(?:0[1-9]|10|11|12)(?:0[1-9]|[1-2]\\d|30|31)\\d{3}[\\dXx]$/");

    @Override
    public void initialize(IdCard constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (null != value && !value.trim().equals("")) {
            return pattern.matcher(value).matches();
        }
        return true;
    }
}
