package items;

import java.util.Calendar;
import java.util.Objects;

public abstract class Item {

  protected String title, author, publishingHouse;
  protected Integer number, numberOfPages;
  protected Calendar releaseDate;

  public Item(String title, String author, String publishingHouse, Integer number, Integer numberOfPages, Calendar releaseDate) {
    this.title = title;
    this.author = author;
    this.publishingHouse = publishingHouse;
    this.number = number;
    this.numberOfPages = numberOfPages;
    this.releaseDate = releaseDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Item item = (Item) o;
    return Objects.equals(title, item.title) && Objects.equals(author, item.author) && Objects.equals(publishingHouse, item.publishingHouse) && Objects.equals(number, item.number) && Objects.equals(numberOfPages, item.numberOfPages) && Objects.equals(releaseDate, item.releaseDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, author, publishingHouse, number, numberOfPages, releaseDate);
  }
}
