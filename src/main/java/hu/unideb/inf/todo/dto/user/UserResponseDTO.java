package hu.unideb.inf.todo.dto.user;

public class UserResponseDTO {

    private Long id;
    private String username;
    private String birthDate;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

}
