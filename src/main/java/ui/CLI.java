package ui;

import command.*;
import items.Item;
import repository.Repository;
import repository.RepositoryInMemory;
import services.CLIAdapters.RepositoryCLIAdapter;
import services.RepositoryAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CLI implements UI {

  private final Repository<Item> repository = RepositoryInMemory.getInstance();
  private final RepositoryAdapter adapter = new RepositoryCLIAdapter(repository);
  private final List<Command> commands = new ArrayList<>(
    Arrays.asList(
      new AcceptItemsCommand(adapter),
      new SellItemCommand(adapter),
      new ViewCatalogCommand(adapter),
      new ChangeItemsDataCommand(adapter),
      new ExitCommand()
    )
  );

  @Override
  public void run() {
    System.out.println("Program is running.");
    Scanner scn = new Scanner(System.in);
    while (true) {
      printMenu();
      try {
        int choose = Integer.parseInt(scn.nextLine()) - 1;
        if (choose >= 0 && choose < commands.size()) commands.get(choose).execute();
      } catch (NumberFormatException e) {
        System.out.println("--> Error!!! Incorrect input. <--");
      }
    }
  }

  private void printMenu() {
    System.out.println("------------Menu-----------");
    for (int i = 0; i < commands.size(); i++) {
      System.out.println(i + 1 + " -> " + commands.get(i));
    }
  }
}
