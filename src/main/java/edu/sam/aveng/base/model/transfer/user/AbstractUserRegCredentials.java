package edu.sam.aveng.base.model.transfer.user;

import javax.validation.constraints.AssertTrue;

public abstract class AbstractUserRegCredentials extends AbstractUserCredentials {

    protected String retypedPassword;

    public String getRetypedPassword() {
        return retypedPassword;
    }

    public void setRetypedPassword(String retypedPassword) {
        this.retypedPassword = retypedPassword;
    }

    @AssertTrue(message = "{validation.password.mismatch}")
    public boolean isValid() {
        return password.equals(retypedPassword);
    }
}
