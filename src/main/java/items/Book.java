package items;

public class Book extends Item {

  public Book(String title, String author, String publishingHouse, int numberOfPages){
    super(title, author, publishingHouse, null, numberOfPages, null);
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
