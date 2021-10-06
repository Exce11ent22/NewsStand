package ui;

import services.RepositoryCLIAdapter;

import java.util.Scanner;

public class CLI implements UI{

  private boolean runnable = true;
  private final RepositoryCLIAdapter repositoryCLIAdapter = new RepositoryCLIAdapter();

  @Override
  public void run() {
    System.out.println("Program is running.");
    Scanner scn = new Scanner(System.in);
    while (runnable) {
      System.out.println("""
        ----------------Menu----------------
        1. Accept items
        2. Sell item
        3. View catalog
        4. Change items data
        5. Exit""");
      try {
        runnable = execute(Integer.parseInt(scn.nextLine()));
      } catch (NumberFormatException e) {
        System.out.println("--> Error!!! Incorrect input. <--");
      }
    }
  }

  private boolean execute(int n) {
    switch (n) {
      case 1 -> repositoryCLIAdapter.acceptItems();
      case 2 -> repositoryCLIAdapter.sellAnItem();
      case 3 -> repositoryCLIAdapter.viewCatalog();
      case 4 -> repositoryCLIAdapter.changeItemsData();
      case 5 -> {
        return false;
      }
      default -> System.out.println("Incorrect operation");
    }
    return true;
  }
}
