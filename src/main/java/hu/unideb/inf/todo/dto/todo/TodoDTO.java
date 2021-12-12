package hu.unideb.inf.todo.dto.todo;

public class TodoDTO {

    private String content;

    public TodoDTO() {
    }

    public TodoDTO(String content, Long userid) {
        this.content = content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

}
