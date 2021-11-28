package todoApplication.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TodoList {

  List<Todo> todos;
  FileHandler fileHandler;

  public TodoList(String cacheFileLocations) {
    fileHandler = new FileHandler(cacheFileLocations);
  }

  public TodoList() {
    fileHandler = new FileHandler("todoList.txt");
    todos = new ArrayList<>();
    fetchTodos();
  }

  public void toggleTodoStatus(String nthTodo) {
    todos.get(Integer.parseInt(nthTodo) - 1).toggleStatus();
    save();
  }

  public void removeTodo(String nthTodo) {
    todos.remove(Integer.parseInt(nthTodo) - 1);
    save();
  }

  public void addTodo(String description) {
    todos.add(new Todo(description));
    save();
  }

  public void print() {
    System.out.println("\nTODOS:");
    if (todos.size() == 0) {
      printNoTodo();
      return;
    }
    for (int i = 0; i < todos.size(); i++) {
      System.out.printf("\t%d - %s\n", i + 1, todos.get(i));
    }
  }

  public void printNoTodo() {
    System.out.println("\tNo todos for today! :)\n");
  }

  public void printUsageInfo() {
    System.out.println("\nCommand Line Todo Application");
    System.out.println("=============================\n");
    System.out.println("Command line arguments:");
    System.out.println("\t-l\t\tlists all todos");
    System.out.println("\t-a \"My todo\"\tadds a todo with description 'My todo'");
    System.out.println("\t-r nth\t\tremoves the nth todo");
    System.out.println("\t-t nth\t\ttoggles the status of the nth todo");
  }

  private void fetchTodos() {
    List<String> fileContent;
    try {
      fileContent = fileHandler.readObj();
    } catch (IOException | ClassNotFoundException e) {
      return;
    }
    for (String s : fileContent) {
      String[] rowContent = s.split("\\|");
      todos.add(new Todo(rowContent[0], rowContent[1]));
    }
  }

  private void save() {
    List<String> fileContent = new ArrayList<>(todos.size());
    for (Todo todo : todos) {
      fileContent.add(todo.stringify());
    }
    try {
      fileHandler.writeObj(fileContent);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
