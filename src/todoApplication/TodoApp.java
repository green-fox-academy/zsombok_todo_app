package todoApplication;

import todoApplication.models.ErrorHandler;
import todoApplication.models.TodoList;

public class TodoApp {

  public static void main(String[] args) {

    executeOperation(args);

  }

  private static void executeOperation(String[] args) {

    ErrorHandler errorHandler = new ErrorHandler();

    TodoList todoList = new TodoList();

    if (args.length == 0) {
      todoList.printUsageInfo();
      return;
    }

    switch (args[0]) {
      case "-l":
        todoList.print();
        break;
      case "-a":
        if (args.length == 1) {
          System.out.println("Unable to add: no task provided");
          return;
        }
        todoList.addTodo(args[1]);
        break;
      case "-r":
        todoList.removeTodo(args[1]);
        return;
      case "-t":
        todoList.toggleTodoStatus(args[1]);
        break;
      default:
        System.out.println("hello");
    }
  }
}
