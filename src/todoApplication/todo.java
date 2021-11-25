package todoApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class todo {

  public static void main(String[] args) {

    sortArguments(args);

  }

  private static void sortArguments(String[] args) {
    if (args.length == 0) {
      printUsageInfo();
      return;
    }
    switch (args[0]) {
      case "-l":
        listAllTodo();
      case "-a":
        addTodo(args[1]);
      case "-r":
        removeTodo(args[1]);
      case "-c":
        checkTodo(args[1]);
      default:
        System.out.println("hello");
    }
  }

  private static void checkTodo(String arg) {
  }

  private static void removeTodo(String arg) {
  }

  private static void addTodo(String desctiption) {
  }

  private static void listAllTodo() {
    System.out.println("TODOS:\n");
    Path filePath = Paths.get("todoList.txt");
    List<String> fileContent = new ArrayList<>();
    try {
      fileContent = Files.readAllLines(filePath);
    } catch (IOException e) {
      printNoTodo();
    }
    int todoCount = fileContent.size();
    if (todoCount == 0) {
      printNoTodo();
      return;
    }
    for (int i = 0; i < todoCount; i++) {
      System.out.printf("%d - %s", i + 1, fileContent.get(i));
    }
  }

  private static void printNoTodo() {
    System.out.println("No todos for today! :)");
  }

  private static void printUsageInfo() {
    System.out.println("\nCommand Line Todo Application");
    System.out.println("=============================\n");
    System.out.println("Command line arguments:");
    System.out.println("\t-l\tlists all todos");
    System.out.println("\t-a\tadds a todo");
    System.out.println("\t-r\tremoves a todo");
    System.out.println("\t-c\tcompletes a todo");
  }

}
