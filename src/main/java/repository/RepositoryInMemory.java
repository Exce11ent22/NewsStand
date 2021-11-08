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

}
