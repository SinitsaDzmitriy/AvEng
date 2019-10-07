package edu.sam.aveng.base.model.transfer;

import edu.sam.aveng.base.model.transfer.IUserCredentials;
import edu.sam.aveng.temp.validation.constraint.UniqueEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

// ToDo: Add appropriate validation messages

public class UserCredentials implements IUserCredentials {

    @NotBlank(message = "{validation.back.email.blank}")
    @Size(max = 254, message = "{validation.back.email.size}")
    @Email(message = "{validation.back.email.pattern}")
    @UniqueEmail(message = "{validation.back.email.duplicate}")
    private String email;

    @NotBlank(message = "validation.back.password.blank")
    @Size(min = 8, max = 64, message = "{validation.password.size}")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]*$", message = "{validation.back.password.pattern}")
    private String password;

    public UserCredentials() {}

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
