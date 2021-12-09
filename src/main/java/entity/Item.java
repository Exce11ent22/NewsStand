package entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;
import java.util.Scanner;

public abstract class Item {

  @Getter @Setter
  protected Long id;
  @Getter @Setter
  protected String title, author, publishingHouse;
  @Getter @Setter
  protected Integer number, numberOfPages;
  @Getter @Setter
  protected Calendar releaseDate;

  public Item(Long id, String title, String author, String publishingHouse, Integer number, Integer numberOfPages, Calendar releaseDate) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.publishingHouse = publishingHouse;
    this.number = number;
    this.numberOfPages = numberOfPages;
    this.releaseDate = releaseDate;
  }

  /**
   * Checks field equality without regard to id
   */
  public boolean sameTo(Item item) {
    return Objects.equals(title, item.title) &&
      Objects.equals(author, item.author) &&
      Objects.equals(publishingHouse, item.publishingHouse) &&
      Objects.equals(number, item.number) &&
      Objects.equals(numberOfPages, item.numberOfPages) &&
      Objects.equals(releaseDate, item.releaseDate);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Item item = (Item) o;
    return Objects.equals(id, item.id) &&
      Objects.equals(title, item.title) &&
      Objects.equals(author, item.author) &&
      Objects.equals(publishingHouse, item.publishingHouse) &&
      Objects.equals(number, item.number) &&
      Objects.equals(numberOfPages, item.numberOfPages) &&
      Objects.equals(releaseDate, item.releaseDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, author, publishingHouse, number, numberOfPages, releaseDate);
  }
}
