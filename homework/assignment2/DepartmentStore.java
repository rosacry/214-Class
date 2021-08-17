
/**
 * This class represents the main for running the ItemList, ItemInfoNode, and the ItemInfo classes 
 * @author Christopher Rosales
 * @ID #114328928
 * @Assignment #2, Department Store
 */
import java.util.InputMismatchException;
import java.util.Scanner;

public class DepartmentStore {
  private static Scanner input = new Scanner(System.in);
  private static ItemList list = new ItemList();
  private static boolean flag = false;

  /**
   * Description: Prints the menu for the main
   */
  public static void menu() {
    System.out.println(
        "\n\nC) - Clean Store\nI) - Insert an item into the list\nL) - List by location\nM) - Move an item in the store\nO) - Checkout\nP) - Print all items in the store\nR) - Print by RFID tag number\nU) - Update inventory system\nQ) - Quit\n");
    System.out.print("Select a menu option: ");
  }

  /**
   * Description: Method to insert a ItemInfo object into a ItemInfoNode and adds
   * that to the Item List
   *
   * @throws IllegalArgumentException Checks if the values for RFID, original
   *                                  location, and price are correctly formatted
   *
   */
  public static void optionI() {
    try {
      System.out.print("\nEnter the name: ");
      String name = input.nextLine();
      System.out.print("\nEnter the RFID: ");
      String rfid = input.nextLine();
      if (rfid.length() != 9 || exclusionHex(rfid)) {
        flag = true;
        System.out.println("RFID must be 9 characters long");
        throw new IllegalArgumentException();
      }
      System.out.print("\nEnter the original Location: ");
      String origLocation = input.nextLine();
      if (origLocation.length() != 6 || origLocation.charAt(0) != 's' || !(isNumeric(origLocation.substring(1)))) {
        flag = true;
        System.out.println("The original Location must start with an 's' followed by 5 digits");
        throw new IllegalArgumentException();
      }
      System.out.print("\nEnter the price: ");
      double price = input.nextDouble();
      if (price < 00.00) {
        System.out.println("Price cannot be a negative.");
        input.nextLine();
        throw new IllegalArgumentException();
      }
      input.nextLine();
      list.insertInfo(name, rfid, price, origLocation);
      System.out.println("\nItem " + name + " has been added to the store");
    } catch (InputMismatchException | IllegalArgumentException e) {
      if (!flag) {
        System.out.println("You must type in a double for the price option!");
        input.nextLine();
      }
    }
  }

  /**
   * Description: Moves an item's current location to a different location with
   * the inputted rfid, original location, and its new location to be moved to
   * 
   * @throws IllegalArgumentException to check if the rfid, original location, and
   *                                  its new location are all correctly
   *                                  formmatted and valid
   *
   */
  public static void optionM() {
    try {
      System.out.print("\nEnter the RFID: ");
      String rfid = input.nextLine();
      if (rfid.length() != 9 || exclusionHex(rfid)) {
        System.out.println("RFID must be 9 characters long");
        throw new IllegalArgumentException();
      }
      System.out.print("\nEnter the original location: ");
      String origLocation = input.nextLine();
      if (((origLocation.length() != 6 || origLocation.charAt(0) != 's' || (!isNumeric(origLocation.substring(1))))
          && (origLocation.length() != 4 || origLocation.charAt(0) != 'c' || (!isNumeric(origLocation.substring(1)))))
          || (origLocation.equals("out"))) {
        System.out.println(
            "The Original Location must be 6 characters long, that has the first letter of 's', followed by a 5 digit number");
        throw new IllegalArgumentException();
      }
      System.out.print("\nEnter the new location: ");
      String newCurrentLocation = input.nextLine();
      if (((newCurrentLocation.length() != 6 || newCurrentLocation.charAt(0) != 's'
          || !(isNumeric(newCurrentLocation.substring(1))))
          && (newCurrentLocation.length() != 4 || newCurrentLocation.charAt(0) != 'c'
              || !(isNumeric(newCurrentLocation.substring(1)))))
          && !(newCurrentLocation.equals("out"))) {
        System.out.println(
            "The new Current Location must be 4 characters long, that has the first letter of 'c', followed by a 3 digit number. or, it must be 6 characters long, that has the first letter of 's', followed by a 5 digit number. OR the new current location must be 'out'");
        throw new IllegalArgumentException();
      }
      list.moveItem(rfid, origLocation, newCurrentLocation);
    } catch (IllegalArgumentException e) {

    }
  }

  /**
   * Description: Method that prints all the items matching the inputted location
   *
   * @throws IllegalArgumentException To check if the inputted current location is
   *                                  correctly formatted and valid
   *
   */
  public static void optionL() {
    try {
      System.out.print("\nEnter the location: ");
      String location = input.nextLine();
      if (((location.length() != 6 || location.charAt(0) != 's' || !(isNumeric(location.substring(1))))
          && (location.length() != 4 || location.charAt(0) != 'c' || !(isNumeric(location.substring(1)))))
          && !(location.equals("out"))) {
        System.out.println(
            "The inputted Current Location must be 4 characters long, that has the first letter of 'c', followed by a 3 digit number. or, it must be 6 characters long, that has the first letter of 's', followed by a 5 digit number. OR the new current location must be 'out'");
        throw new IllegalArgumentException();
      }
      list.printByLocation(location);
    } catch (IllegalArgumentException e) {

    }
  }

  /**
   * Description: Prints all Items in the doubly linked list
   */
  public static void optionP() {
    list.printAll();
  }

  /**
   * Description: Checks out all of the items with the specified cart number
   *
   * @throws IllegalArgumentException To check if the inputted cart number is
   *                                  invalid
   *
   */
  public static void optionO() {
    try {
      System.out.print("\nEnter the cart number: ");
      String cartNumber = input.nextLine();
      if (cartNumber.length() != 4 || cartNumber.charAt(0) != 'c' || !(isNumeric(cartNumber.substring(1)))) {
        System.out.println(
            "The cart number most be 4 characters long with the first letter being 'c', followed by a 3 digit number.");
        throw new IllegalArgumentException();
      }
      list.checkOut(cartNumber);
    } catch (IllegalArgumentException e) {

    }
  }

  /**
   * Description: Moves all Items that have their current locations unequal to
   * their original locations
   */
  public static void optionC() {
    System.out.println("The following item(s) have been moved back to their original locations");
    list.cleanStore();
  }

  /**
   * Description: Removes all items that have their current location listed as
   * 'out'
   */
  public static void optionU() {
    System.out.println("The following item(s) have been removed from the system:");
    list.removeAllPurchased();
  }

  /**
   * Description: Prints all the Items that have the specified RFID inputted
   *
   * @throws IllegalArgumentException to check if the inputted RFID is invalid
   *
   */
  public static void optionR() {
    try {
      System.out.print("\nEnter the RFID: ");
      String rfid = input.nextLine();
      if (rfid.length() != 9 || exclusionHex(rfid)) {
        System.out.println("RFID must be 9 characters long");
        throw new IllegalArgumentException();
      }
      list.commonRfid(rfid);
    } catch (IllegalArgumentException e) {

    }
  }

  /**
   * Description: Quits the program
   */
  public static void optionQ() {
    System.exit(0);
  }

  /**
   * Description: Checks if all the characters of the inputted string are integers
   *
   * @return True if all the characters of the inputted string are integers, false
   *         if there is a character that is not an integer
   *
   * @throws NumberFormatException To check if there is a character that is not an
   *                               integer
   */
  public static boolean isNumeric(String string) {
    if (string == null || string.length() == 0) {
      return false;
    }
    try {
      int intValue = Integer.parseInt(string);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  /**
   * Description: Checks the RFID Tag number if there's any letters in that string
   * that are after 'f'
   *
   * @param rfid The string to be checked if there's any letters after 'f'
   * @return returns true if there's a letter that is not valid in the hexidecimal
   *         place false, if there's no letter in the string after 'f'
   */
  public static boolean exclusionHex(String rfid) {
    String exclude = "GHIJKLMNOPQRSTUVWXYZ";
    for (int i = 0; i < exclude.length(); i++) {
      if (rfid.toUpperCase().contains(exclude.substring(i, i + 1)))
        return true;
    }
    return false;
  }

  /**
   * Description: The main in which prints all the specified methods
   */
  public static void main(String[] args) {
    System.out.println("Welcome");
    while (true) {
      menu();
      String option = input.nextLine();
      if (option.equalsIgnoreCase("I"))
        optionI();
      else if (option.equalsIgnoreCase("M"))
        optionM();
      else if (option.equalsIgnoreCase("L"))
        optionL();
      else if (option.equalsIgnoreCase("P"))
        optionP();
      else if (option.equalsIgnoreCase("O"))
        optionO();
      else if (option.equalsIgnoreCase("C"))
        optionC();
      else if (option.equalsIgnoreCase("U"))
        optionU();
      else if (option.equalsIgnoreCase("R"))
        optionR();
      else if (option.equalsIgnoreCase("Q"))
        optionQ();
      else
        System.out.println("That is not one of the inputs listed!");
    }
  }
}
