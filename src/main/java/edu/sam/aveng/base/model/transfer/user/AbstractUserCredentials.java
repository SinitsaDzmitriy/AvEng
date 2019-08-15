package edu.sam.aveng.base.model.transfer.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public abstract class AbstractUserCredentials {

    @NotBlank(message = "{validation.email.blank}")
    @Email(message = "{validation.email.pattern}")
    protected String email;

    @NotBlank(message = "{validation.password.blank}")
    @Size(min = 8, max = 64, message = "{validation.password.size}")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]*$", message = "{validation.password.pattern}")
    protected String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
