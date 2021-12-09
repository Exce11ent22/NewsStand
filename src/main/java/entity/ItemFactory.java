package entity;

import entity.configurator.ItemConfigurator;
import lombok.SneakyThrows;

public class ItemFactory {

  @SneakyThrows
  public static Item create(ItemType type, ItemConfigurator configurator) {
    if (type == null) return null;
    Item item = type.getItemClass().getDeclaredConstructor().newInstance();
    configurator.configure(item);
    return item;
  }

  @SneakyThrows
  public static Item createEmptyItem(ItemType type) {
    if (type == null) return null;
    return type.getItemClass().getDeclaredConstructor().newInstance();
  }

}
