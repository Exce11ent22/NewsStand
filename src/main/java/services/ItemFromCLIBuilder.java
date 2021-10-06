package services;

import items.Book;
import items.Item;
import items.Magazine;
import items.Newspaper;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class ItemFromCLIBuilder {

  private static final Scanner scn = new Scanner(System.in);

  public static Item createItemFromCLI(Class<? extends Item> c){
    if (Book.class.equals(c)) return createBookFromCLI();
    if (Magazine.class.equals(c)) return createMagazineFromCLI();
    if (Newspaper.class.equals(c)) return createNewspaperFromCLI();
    return null;
  }

  public static Book createBookFromCLI() {
    return new Book(getTitle(), getAuthor(), getPublishingHouse(), getNumberOfPages());
  }

  public static Magazine createMagazineFromCLI() {
    return new Magazine(getTitle(), getNumber(), getReleaseDate(), getNumberOfPages());
  }

  public static Newspaper createNewspaperFromCLI() {
    return new Newspaper(getTitle(), getNumber(), getReleaseDate());
  }

  private static String getTitle() {
    System.out.print("Title: ");
    return scn.nextLine();
  }

  private static String getAuthor() {
    System.out.print("Author: ");
    return scn.nextLine();
  }

  private static String getPublishingHouse() {
    System.out.print("Publishing house: ");
    return scn.nextLine();
  }

  private static Integer getNumber() {
    System.out.print("Number: ");
    return Integer.parseInt(scn.nextLine());
  }

  private static Integer getNumberOfPages() {
    System.out.print("Number of pages: ");
    return Integer.parseInt(scn.nextLine());
  }

  private static Calendar getReleaseDate() {
    System.out.print("Release Year: ");
    int year = Integer.parseInt(scn.nextLine());

    System.out.print("Release Month: ");
    int month = Integer.parseInt(scn.nextLine());

    System.out.print("Release Day: ");
    int day = Integer.parseInt(scn.nextLine());

    return new GregorianCalendar(year, month, day);
  }

}
