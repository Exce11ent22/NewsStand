package services.CLIAdapters;

import items.Item;
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
    System.out.println(
      "Select the item you want to add:\n" +
      "1. Book\n" +
      "2. Magazine\n" +
      "3. Newspaper\n");
    Item item = chooseItem();
    boolean accepted = false;
    while(!accepted){
      System.out.println("Are you sure you want to add\n" + item + "\nin your repo?\n[Y/N]");
      String answer = scn.nextLine().toLowerCase();
      if (answer.equals("y")) accepted = true;
      else if (!answer.equals("n")) return;
      item = accepted ? item : chooseItem();
    }
    if (item == null) return;

    System.out.println("How much?");
    int choose = Integer.parseInt(scn.nextLine());
    if (choose > 0) {
      for (int i = 0; i < choose; i++) repository.add(item);
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

  private Item chooseItem() {
    Item item = null;
    switch (Integer.parseInt(scn.nextLine())) {
      case 1: item = ItemFromCLIBuilder.createBookFromCLI(); break;
      case 2: item = ItemFromCLIBuilder.createMagazineFromCLI(); break;
      case 3: item = ItemFromCLIBuilder.createNewspaperFromCLI(); break;
      default: System.out.println("Out of range!");
    }
    return item;
  }

  /**
   * returns items only with unique fields
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
