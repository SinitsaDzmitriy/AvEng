package edu.sam.aveng.base.model.transfer.user;

public abstract class AbstractUserRegCredentials extends AbstractUserCredentials {

    protected String retypedPassword;

    public String getRetypedPassword() {
        return retypedPassword;
    }

    public void setRetypedPassword(String retypedPassword) {
        this.retypedPassword = retypedPassword;
    }
}
