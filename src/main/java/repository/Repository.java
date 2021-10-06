package repository;

import items.Item;

import java.util.List;

public interface Repository {

  List<Item> getAll();

  void add(Item item);

  void deleteByIndex(int index);

  void replaceAll(Item existing, Item toReplace);

}
