package items;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Magazine extends Item{

  public Magazine(String title, Integer number, Calendar releaseDate, Integer numberOfPages){
    super(title, null, null, number, numberOfPages, releaseDate);
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