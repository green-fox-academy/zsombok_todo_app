package todoApplication.models;

public class Todo {

  private String description;
  private boolean isDone;

  public Todo(String description) {
    isDone = false;
    this.description = description;
  }

  public Todo(String description, String isDone) {
    this.description = description;
    this.isDone = Boolean.parseBoolean(isDone);
  }

  public void toggleStatus() {
    isDone ^= true;
  }

  @Override
  public String toString() {
    return String.format("[%s] %s", isDone ? "X" : " ", description);
  }

  public String stringify() {
    return String.format("%s|%s", description, isDone);
  }
}
