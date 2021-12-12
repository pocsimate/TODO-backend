package hu.unideb.inf.todo.model;

import javax.persistence.*;

@Entity
@Table(name = "todos")
public class Todo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "userid", referencedColumnName = "id")
    private UserModel user;

    private String content;

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
