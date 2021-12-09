package entity;

import entity.items.Book;
import entity.items.Magazine;
import entity.items.Newspaper;

public enum ItemType {

  BOOK(1, Book.class),
  MAGAZINE(2, Magazine.class),
  NEWSPAPER(3, Newspaper.class);

  private final int typeNumber;
  private final Class<? extends Item> itemClass;

  ItemType(int typeNumber, Class<? extends Item> itemClass) {
    this.typeNumber = typeNumber;
    this.itemClass = itemClass;
  }

  public int getTypeNumber() {
    return typeNumber;
  }

  public Class<? extends Item> getItemClass() {
    return itemClass;
  }

  public static ItemType getTypeFromTypeNumber(int typeNumber){
    for (ItemType itemType : ItemType.values()) {
      if (itemType.getTypeNumber() == typeNumber) return itemType;
    }
    return null;
  }

  public static ItemType getTypeFromClass(Class<? extends Item> itemClass) {
    for (ItemType itemType : ItemType.values()) {
      if (itemType.getItemClass() == itemClass) return itemType;
    }
    return null;
  }

}
