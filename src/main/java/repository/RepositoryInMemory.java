package repository;

import items.Item;

import java.util.ArrayList;
import java.util.List;

public class RepositoryInMemory implements Repository<Item> {

  private static RepositoryInMemory INSTANCE;
  private final List<Item> items;
  private Long id = 1L;

  private RepositoryInMemory() {
    items = new ArrayList<>();
  }

  public static RepositoryInMemory getInstance() {
    if (INSTANCE == null) INSTANCE = new RepositoryInMemory();
    return INSTANCE;
  }

  @Override
  public List<Item> getAll() {
    return items;
  }

  @Override
  public void add(Item item) {
    item.setId(id);
    id++;
    items.add(item);
  }

  @Override
  public void delete(Item item) {
    items.remove(item);
  }

  /**
   * All existing items will be replaced by itemToReplace
   */
  @Override
  public void replaceAll(Item existing, Item itemToReplace) {
    for (Item item : items) {
      if (item.sameTo(existing)) {
        item.setTitle(itemToReplace.getTitle());
        item.setAuthor(itemToReplace.getAuthor());
        item.setNumber(itemToReplace.getNumber());
        item.setNumberOfPages(itemToReplace.getNumberOfPages());
        item.setPublishingHouse(itemToReplace.getPublishingHouse());
        item.setReleaseDate(itemToReplace.getReleaseDate());
      }
    }
  }


}
