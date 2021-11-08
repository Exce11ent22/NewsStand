package repository.persistence;

import items.Book;
import items.Item;
import items.Magazine;
import items.Newspaper;
import repository.Repository;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class RepositoryInDB implements Repository<Item> {

  private final ConnectionManager connectionManager;

  public RepositoryInDB(ConnectionManager connectionManager) {
    this.connectionManager = connectionManager;
  }

  @Override
  public List<Item> getAll() {
    try {
      Connection connection = connectionManager.getConnection();
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT * FROM items");
      return extractor.extract(resultSet);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Collections.emptyList();
  }

  @Override
  public void add(Item item) {
    try {
      Connection connection = connectionManager.getConnection();
      Statement statement = connection.createStatement();
      statement.execute(String.format("INSERT INTO " +
          "ITEMS(TITLE, AUTHOR, PUBLISHING_HOUSE, NUMBER, NUMBER_OF_PAGES, RELEASE_DATE, CLASS)" +
          "VALUES( %s , %s , %s , %s , %s , %s , %s );",
        getTitleAsString(item), getAuthorAsString(item), getPublishingHouseAsStirng(item), getNumberAsString(item),
        getNumberOfPagesAsString(item), getReleaseDateAsString(item), getItemClassAsString(item)));
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void delete(Item item) {
    Long id = item.getId();
    if (id == null) return;
    try {
      Connection connection = connectionManager.getConnection();
      Statement statement = connection.createStatement();
      statement.execute(String.format("DELETE ITEMS " +
        "WHERE ID = %s;", id));
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private final Extractor<Item> extractor = rs -> {
    List<Item> items = new ArrayList<>();
    while (rs.next()) {
      // получить все поля
      Long id = rs.getLong("id");
      String title = rs.getString("title");
      String author = rs.getString("author");
      String publishingHouse = rs.getString("publishing_house");
      Integer number = rs.getInt("number");
      Integer numberOfPages = rs.getInt("number_of_pages");
      Calendar releaseDate = toCalendar(rs.getDate("release_date"));
      Class<? extends Item> itemClass = null;
      try {
        itemClass = (Class<? extends Item>) Class.forName(rs.getString("class"));
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }

      // в зависимости от поля class создать экземпляр нужного класса и наполнить полями
      Item item = null;
      assert itemClass != null;
      if (itemClass.equals(Book.class)) item = new Book(id, title, author, publishingHouse, numberOfPages);
      if (itemClass.equals(Magazine.class)) item = new Magazine(id, title, number, releaseDate, numberOfPages);
      if (itemClass.equals(Newspaper.class)) item = new Newspaper(id, title, number, releaseDate);

      // проверить объект
      if (item != null) items.add(item);
    }
    return items;
  };

  private static Calendar toCalendar(Date date) {
    if (date == null) return null;
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return cal;
  }

  private String getTitleAsString(Item item) {
    return item.getTitle() != null ? "'" + item.getTitle() + "'" : "null";
  }

  private String getAuthorAsString(Item item) {
    return item.getAuthor() != null ? "'" + item.getAuthor() + "'" : "null";
  }

  private String getPublishingHouseAsStirng(Item item) {
    return item.getPublishingHouse() != null ? "'" + item.getPublishingHouse() + "'" : "null";
  }

  private String getNumberAsString(Item item) {
    return item.getNumber() != null ? item.getNumber().toString() : "null";
  }

  private String getNumberOfPagesAsString(Item item) {
    return item.getNumberOfPages() != null ? item.getNumberOfPages().toString() : "null";
  }

  private String getReleaseDateAsString(Item item) {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    return item.getReleaseDate() != null ? "'" + format.format(item.getReleaseDate().getTime()) + "'" : "null";
  }

  private String getItemClassAsString(Item item) {
    return "'" + item.getClass().toString().split(" ")[1] + "'";
  }

}
