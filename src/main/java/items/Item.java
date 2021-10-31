package items;

import java.util.Calendar;
import java.util.Objects;

public abstract class Item {

  protected Long id;
  protected String title, author, publishingHouse;
  protected Integer number, numberOfPages;
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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getPublishingHouse() {
    return publishingHouse;
  }

  public void setPublishingHouse(String publishingHouse) {
    this.publishingHouse = publishingHouse;
  }

  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  public Integer getNumberOfPages() {
    return numberOfPages;
  }

  public void setNumberOfPages(Integer numberOfPages) {
    this.numberOfPages = numberOfPages;
  }

  public Calendar getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(Calendar releaseDate) {
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
