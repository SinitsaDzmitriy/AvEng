package edu.sam.aveng.base.model.transfer.dto;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

// ToDo: Add appropriate validation messages

public class UserDto {

    @NotBlank(message = "{validation.email.blank}")
    @Email(message = "{validation.email.pattern}")
    private String email;

    @NotBlank(message = "validation.password.blank")
    @Size(min = 8, max = 64, message = "{validation.password.size}")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]*$", message = "{validation.password.pattern}")
    private String password;

    private String retypedPassword;

    public UserDto() {
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRetypedPassword(String retypedPassword) {
        this.retypedPassword = retypedPassword;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRetypedPassword() {
        return retypedPassword;
    }

    @AssertTrue(message = "{validation.password.mismatch}")
    public boolean isValid() {
        return password.equals(retypedPassword);
    }
}
