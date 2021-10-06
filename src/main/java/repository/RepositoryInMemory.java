package repository;

import items.Item;

import java.util.ArrayList;
import java.util.List;

public class RepositoryInMemory implements Repository{

  private static RepositoryInMemory INSTANCE;
  private final List<Item> items;

  private RepositoryInMemory(){
    items = new ArrayList<>();
  }

  public static RepositoryInMemory getInstance(){
    if (INSTANCE == null) INSTANCE = new RepositoryInMemory();
    return INSTANCE;
  }

  @Override
  public List<Item> getAll() {
    return items;
  }

  @Override
  public void add(Item item) {
    items.add(item);
  }

  @Override
  public void deleteByIndex(int index) {
    items.remove(index);
  }

  @Override
  public void replaceAll(Item existing, Item toReplace) {
    for (int i = 0; i < items.size(); i++) {
      if (items.get(i) == existing) items.set(i, toReplace);
    }
  }
}
