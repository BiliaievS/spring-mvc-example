package com.springframework.commands.validators;

import com.springframework.commands.CustomerForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by sbiliaiev on 1/28/2018.
 */
@Component
public class CustomerFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return CustomerForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CustomerForm customerForm = (CustomerForm) o;

        if (!customerForm.getPasswordText().equals(customerForm.getPasswordTextConf())) {
            errors.rejectValue("passwordText", "PasswordsDontMatch.customerForm.passwordText", "Passwords Don't Match O_o");
            errors.rejectValue("passwordTextConf", "PasswordsDontMatch.customerForm.passwordTextConf", "Passwords Don't Match");
        }
    }
}
