package entity.items;

import entity.Item;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Magazine extends Item {

  public Magazine(){
    super(null, null, null, null, null, null, null);
  }

  @Override
  public String toString() {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    return "Magazine{" +
      "title='" + title + '\'' +
      ", number=" + number +
      ", numberOfPages=" + numberOfPages +
      ", releaseDate=" + format.format(releaseDate.getTime()) +
      '}';
  }
}
