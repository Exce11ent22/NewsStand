package ui;

import command.*;

import java.util.List;
import java.util.Scanner;

public class CLI implements UI {

  private final List<Command> commands;

  public CLI(List<Command> commands) {
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
