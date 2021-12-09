package entity.items;

import entity.Item;

import java.util.Calendar;

public class Book extends Item {

  public Book(){
    super(null, null, null, null, null, null, null);
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
