package todoApplication.models;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;

public class FileHandler {

  Path filePath;
  String fileName;

  public FileHandler(String fileLocation) {
    fileName = fileLocation;
    filePath = Paths.get(fileLocation);
  }

  public List<String> readObj() throws IOException, ClassNotFoundException {
    FileInputStream fis = new FileInputStream(fileName);
    ObjectInputStream ois = new ObjectInputStream(fis);

    List<String> list = (List<String>) ois.readObject();
    ois.close();
    return list;
  }

  public void writeObj(List<String> todos) throws IOException {
    FileOutputStream fos = new FileOutputStream(fileName);

    ObjectOutputStream oos = new ObjectOutputStream(fos);
    oos.writeObject(todos);
    oos.close();
  }

  public List<String> read() {
    try {
      return Files.readAllLines(filePath);
    } catch (IOException e) {
      return Collections.emptyList();
    }
  }

  public void write(List<String> fileContent) throws IOException {
    Files.write(filePath, fileContent, StandardOpenOption.CREATE);
  }

}
