package hu.unideb.inf.todo.model;

public class Todo {

    private final long id;
    private final long userId;
    private final String content;

    public Todo(long id, long userId, String content) {
        this.id = id;
        this.userId = userId;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }
}
