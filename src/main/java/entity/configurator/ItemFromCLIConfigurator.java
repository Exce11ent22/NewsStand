package entity.configurator;

import entity.Item;
import entity.items.Book;
import entity.items.Magazine;
import entity.items.Newspaper;
import tools.ItemParametersFromCLI;

public class ItemFromCLIConfigurator implements ItemConfigurator{

  @Override
  public void configure(Item item) {
    if (item.getClass().equals(Book.class)) configureBook(item);
    if (item.getClass().equals(Magazine.class)) configureMagazine(item);
    if (item.getClass().equals(Newspaper.class)) configureNewspaper(item);
  }

  private void configureBook(Item item){
    item.setTitle(ItemParametersFromCLI.getTitle());
    item.setAuthor(ItemParametersFromCLI.getAuthor());
    item.setPublishingHouse(ItemParametersFromCLI.getPublishingHouse());
    item.setNumberOfPages(ItemParametersFromCLI.getNumberOfPages());
  }

  private void configureMagazine(Item item){
    item.setTitle(ItemParametersFromCLI.getTitle());
    item.setNumber(ItemParametersFromCLI.getNumber());
    item.setNumberOfPages(ItemParametersFromCLI.getNumberOfPages());
    item.setReleaseDate(ItemParametersFromCLI.getReleaseDate());
  }

  private void configureNewspaper(Item item){
    item.setTitle(ItemParametersFromCLI.getTitle());
    item.setNumber(ItemParametersFromCLI.getNumber());
    item.setReleaseDate(ItemParametersFromCLI.getReleaseDate());
  }

}
