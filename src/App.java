import entities.Employee;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class App {

  public static void main(String[] args) throws Exception {
    System.out.println(
      "============================================================"
    );
    Scanner sc = new Scanner(System.in);

    // Caminho do arquivo:   C:\Users\Guest\Documents\projetos\curso java\ARQUIVOS\fixacao.txt
    System.out.print("Insira o caminho completo do arquivo: ");
    String path = sc.nextLine();
    System.out.println("==============================================");
    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
      List<Employee> list = new ArrayList<>();

      String line = br.readLine();
      while (line != null) {
        String[] fields = line.split(",");
        list.add(
          new Employee(fields[0], fields[1], Double.parseDouble(fields[2]))
        );
        line = br.readLine();
      }
      System.out.print("Insira o salário: ");
      double salary = sc.nextDouble();

      List<String> emails = list
        .stream()
        .filter(x -> x.getSalary() > salary)
        .map(x -> x.getEmail())
        .sorted()
        .collect(Collectors.toList());
      System.out.println("=========================================");
      System.out.println(
        "E-mail de pessoas cujo salário é superior a " +
        String.format("%.2f", salary) +
        ":"
      );
      emails.forEach(System.out::println);

      double sum = list
        .stream()
        .filter(x -> x.getName().charAt(0) == 'M')
        .map(x -> x.getSalary())
        .reduce(0.0, (x, y) -> x + y);
      System.out.println("===================================");
      System.out.println(
        "Soma do salário de pessoas cujo nome começa com 'M':" +
        String.format("%.2f", sum)
      );
    } catch (IOException e) {
      System.out.println("Error: " + e.getMessage());
    }

    System.out.println("===================================");
    sc.close();
  }
}
