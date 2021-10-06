package services;

import items.Item;
import repository.Repository;
import repository.RepositoryInMemory;

import java.util.*;

public class RepositoryCLIAdapter {

  private final Repository repository = RepositoryInMemory.getInstance();
  private final Scanner scn = new Scanner(System.in);

  public void acceptItems() {
    System.out.println("""
      Select the item you want to add:
      1. Book
      2. Magazine
      3. Newspaper""");
    Item item = chooseItem();
    if (item == null) return;

    System.out.println("How much?");
    int choose = Integer.parseInt(scn.nextLine());
    if (choose > 0) {
      for (int i = 0; i < choose; i++) repository.add(item);
    }
  }

  public void sellAnItem() {
    List<Item> items = new ArrayList<>(getItems());

    if (items.size() == 0) return;

    System.out.println("Select the item you want to sell:");
    for (int i = 0; i < items.size(); i++) System.out.println((i + 1) + ") " + items.get(i));

    int choose = Integer.parseInt(scn.nextLine()) - 1;
    if (choose >= 0 && choose < items.size()){
      repository.deleteByIndex(repository.getAll().indexOf(items.get(choose)));
    } else {
      System.out.println("Out of range!");
    }
  }

  public void viewCatalog() {
    System.out.println("------------Catalog-------------");
    Map<Item, Integer> catalog = getCatalog();
    for (Map.Entry<Item, Integer> entry : catalog.entrySet()) {
      System.out.println(entry.getKey() + " Amount in repository: " + entry.getValue());
    }
  }

  public void changeItemsData() {
    List<Item> items = new ArrayList<>(getItems());

    if (items.size() == 0) return;

    System.out.println("Enter the number of the item you would like to change:");
    for (int i = 0; i < items.size(); i++) System.out.println((i + 1) + ") " + items.get(i));

    int choose = Integer.parseInt(scn.nextLine()) - 1;
    if (choose >= 0 && choose < items.size()){
      Item item = ItemFromCLIBuilder.createItemFromCLI(items.get(choose).getClass());
      if (item != null) {
        repository.replaceAll(repository.getAll().get(repository.getAll().indexOf(items.get(choose))), item);
      }
    } else {
      System.out.println("Out of range!");
    }
  }

  private Set<Item> getItems(){
    return new HashSet<>(repository.getAll());
  }

  private Map<Item, Integer> getCatalog(){
    Map<Item, Integer> catalog = new HashMap<>();
    List<Item> items = repository.getAll();

    for (Item item : items) {
      if (!catalog.containsKey(item)) {
        catalog.put(item, 1);
      } else {
        catalog.put(item, catalog.get(item) + 1);
      }
    }

    return catalog;
  }

  private Item chooseItem(){
    Item item = null;
    switch (Integer.parseInt(scn.nextLine())) {
      case 1 -> item = ItemFromCLIBuilder.createBookFromCLI();
      case 2 -> item = ItemFromCLIBuilder.createMagazineFromCLI();
      case 3 -> item = ItemFromCLIBuilder.createNewspaperFromCLI();
      default -> System.out.println("Out of range!");
    }
    return item;
  }
}
