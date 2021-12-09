package tools;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class ItemParametersFromCLI {

  private static final Scanner scn = new Scanner(System.in);

  public static String getTitle() {
    System.out.print("Title: ");
    return scn.nextLine();
  }

  public static String getAuthor() {
    System.out.print("Author: ");
    return scn.nextLine();
  }

  public static String getPublishingHouse() {
    System.out.print("Publishing house: ");
    return scn.nextLine();
  }

  public static Integer getNumber() {
    System.out.print("Number: ");
    return Integer.parseInt(scn.nextLine());
  }

  public static Integer getNumberOfPages() {
    System.out.print("Number of pages: ");
    return Integer.parseInt(scn.nextLine());
  }

  public static Calendar getReleaseDate() {
    System.out.print("Release Year: ");
    int year = Integer.parseInt(scn.nextLine());

    System.out.print("Release Month: ");
    int month = Integer.parseInt(scn.nextLine());

    System.out.print("Release Day: ");
    int day = Integer.parseInt(scn.nextLine());

    return new GregorianCalendar(year, month, day);
  }

}
