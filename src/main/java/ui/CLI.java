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

  private final RepositoryAdapter adapter;
  private final List<Command> commands;

  public CLI(RepositoryAdapter adapter, List<Command> commands) {
    this.adapter = adapter;
    this.commands = commands;
  }

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
