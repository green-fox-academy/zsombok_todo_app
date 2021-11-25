package todoApplication;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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
        break;
      case "-a":
        if (args.length == 1) {
          System.out.println("Unable to add: no task provided");
          return;
        }
        addTodo(args[1]);
        break;
      case "-r":
        removeTodo(args[1]);
        return;
      case "-c":
        checkTodo(args[1]);
        break;
      default:
        System.out.println("hello");
    }
  }

  private static void checkTodo(String arg) {
  }

  private static void removeTodo(String arg) {
  }

  private static void addTodo(String description) {
    try {
      Files.write(todoFilePath(), (description + System.lineSeparator()).getBytes(StandardCharsets.UTF_8),StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void listAllTodo() {
    System.out.println("TODOS:");
    List<String> fileContent = new ArrayList<>();
    try {
      fileContent = getTodoList();
    } catch (IOException e) {
      printNoTodo();
    }
    int todoCount = fileContent.size();
    if (todoCount == 0) {
      printNoTodo();
      return;
    }
    for (int i = 0; i < todoCount; i++) {
      System.out.printf("\t%d - %s", i + 1, fileContent.get(i));
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

  private static Path todoFilePath() {
    return Paths.get("todoList.txt");
  }

  private static List<String> getTodoList() throws IOException {
    return Files.readAllLines(todoFilePath());
  }

}
