package com.huuu.base.validation.validator;

import com.huuu.base.validation.constraints.Mobile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * 手机号验证器
 * @author chenzhenhu
 */
public class MobileValidator implements ConstraintValidator<Mobile, String> {

    private Pattern pattern = Pattern.compile("/^(?:(?:\\+|00)86)?1(?:(?:3[\\d])|(?:4[5-7|9])|(?:5[0-3|5-9])|(?:6[5-7])|(?:7[0-8])|(?:8[\\d])|(?:9[1|8|9]))\\d{8}$/");

    @Override
    public void initialize(Mobile constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (null != value && !value.trim().equals("")) {
            return pattern.matcher(value).matches();
        }
        return true;
    }
}
