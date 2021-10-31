package items;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Newspaper extends Item {

  public Newspaper(Long id, String title, Integer number, Calendar releaseDate) {
    super(id, title, null, null, number, null, releaseDate);
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
