package items;

public class Book extends Item {

  public Book(Long id, String title, String author, String publishingHouse, int numberOfPages) {
    super(id, title, author, publishingHouse, null, numberOfPages, null);
  }

  @Override
  public String toString() {
    return "Book{" +
      "title='" + title + '\'' +
      ", author='" + author + '\'' +
      ", publishingHouse='" + publishingHouse + '\'' +
      ", numberOfPages=" + numberOfPages +
      '}';
  }
}
