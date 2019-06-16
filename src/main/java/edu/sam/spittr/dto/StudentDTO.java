package edu.sam.spittr.dto;

/**
 * @author dzmitry.marudau
 * @since 2019.4
 */
public class StudentDTO {
    private String login;
    private String firsName;
    private String lastName;


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirsName() {
        return firsName;
    }

    public void setFirsName(String firsName) {
        this.firsName = firsName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
