package services.CLIAdapters;

import entity.Item;
import entity.ItemFactory;
import entity.ItemType;
import entity.configurator.ItemFromCLIConfigurator;
import repository.Repository;
import services.RepositoryAdapter;

import java.util.*;

public class RepositoryCLIAdapter implements RepositoryAdapter {

  private final Repository<Item> repository;
  private final Scanner scn = new Scanner(System.in);

  public RepositoryCLIAdapter(Repository<Item> repository) {
    this.repository = repository;
  }

  private static boolean containItem(List<Item> items, Item toCheck) {
    for (Item item : items) {
      if (item.sameTo(toCheck)) return true;
    }
    return false;
  }

  private static Item containItem(Map<Item, Integer> items, Item toCheck) {
    for (Map.Entry<Item, Integer> entry : items.entrySet()) {
      if (entry.getKey().sameTo(toCheck)) return entry.getKey();
    }
    return null;
  }

  @Override
  public void acceptItems() {
    System.out.println("Select the item you want to add:\n");
    Item item = chooseAndCreateItem();
    if (item == null){
      System.out.println("Error!!!");
      return;
    }
    System.out.println("How much?");
    int choose = Integer.parseInt(scn.nextLine());
    if (choose > 0) {
      for (int i = 0; i < choose; i++) repository.add(item);
    } else {
      System.out.println("Incorrect input!");
    }
  }

  @Override
  public void sellItem() {
    List<Item> items = getDifferentItems();

    if (items.size() == 0) return;

    System.out.println("Select the item you want to sell:");
    for (int i = 0; i < items.size(); i++) System.out.println((i + 1) + ") " + items.get(i));

    int choose = Integer.parseInt(scn.nextLine()) - 1;
    if (choose >= 0 && choose < items.size()) {
      repository.delete(repository.getAll().get(choose));
    } else {
      System.out.println("Out of range!");
    }
  }

  @Override
  public void viewCatalog() {
    System.out.println("----------Catalog----------");
    Map<Item, Integer> catalog = getCatalog();
    for (Map.Entry<Item, Integer> entry : catalog.entrySet()) {
      System.out.println(entry.getKey() + "; Amount in repository: " + entry.getValue());
    }
  }

  private Item chooseAndCreateItem() {
    for (ItemType itemType : ItemType.values()) {
      System.out.println(itemType.getTypeNumber() + " -> " + itemType.getItemClass().getSimpleName());
    }
    int choose = Integer.parseInt(scn.nextLine());
    return ItemFactory.create(ItemType.getTypeFromTypeNumber(choose), new ItemFromCLIConfigurator());
  }

  /**
   * returns only items with unique fields
   */
  private List<Item> getDifferentItems() {
    List<Item> items = repository.getAll();
    List<Item> result = new ArrayList<>();
    for (Item item : items) {
      if (!containItem(result, item)) result.add(item);
    }
    return result;
  }

  /**
   * returns items only with unique fields and counts similar in repo
   */
  private Map<Item, Integer> getCatalog() {
    Map<Item, Integer> catalog = new HashMap<>();
    List<Item> items = repository.getAll();
    for (Item item : items) {
      Item itemToPut = containItem(catalog, item);
      if (itemToPut != null) {
        catalog.put(itemToPut, catalog.get(itemToPut) + 1);
      } else {
        catalog.put(item, 1);
      }
    }
    return catalog;
  }
}
