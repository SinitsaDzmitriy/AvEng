package edu.sam.aveng.temp.validation.validator;

import edu.sam.aveng.base.service.user.IUserService;
import edu.sam.aveng.temp.validation.constraint.UniqueEmail;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class UniqueEmailValidator implements
        ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private IUserService userService;

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(UniqueEmail email) {
    }

    @Override
    public boolean isValid(String email,
                           ConstraintValidatorContext cxt) {

        return !userService.isUserRegistered(email);
}

}
