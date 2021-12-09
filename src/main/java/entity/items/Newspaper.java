package entity.items;

import entity.Item;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Newspaper extends Item {

  public Newspaper(){
    super(null, null, null, null, null, null, null);
  }

  @Override
  public String toString() {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    return "Newspaper{" +
      "title='" + title + '\'' +
      ", number=" + number +
      ", releaseDate=" + format.format(releaseDate.getTime()) +
      '}';
  }
}
