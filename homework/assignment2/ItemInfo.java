
/**
 * This class represents an Item's Info for a product at the Department Store
 * 
 * @author Christopher Rosales
 * @ID #114328928
 * @Assignment #2, Department Store
 */
// I extended ItemList to implement is isNumeric function
public class ItemInfo extends ItemList {
  // contains various information about a specific item that can or has been sold
  // in a given department store
  private String productName;
  private double productPrice;
  private String rfidTagNumber;
  private String originalLocation;
  private String currentLocation;

  // Constructors
  /**
   * Description: ItemInfo constructor initializes an Object for the contents of
   * Item's Info
   *
   * @param name         The name of a product
   * @param rfidTag      The Radio-Frequency Identification Tag of a product
   * @param price        The price of a product
   * @param origLocation The original Location the product resided
   */
  public ItemInfo(String name, String rfidTag, double price, String origLocation) {
    this.productName = name;
    this.rfidTagNumber = rfidTag;
    this.productPrice = price;
    this.originalLocation = origLocation;
    this.currentLocation = this.originalLocation;
  }

  // Accessors
  /**
   * Description: Retrieves the products name
   *
   *
   * @return The Products name
   */
  public String getProductName() {
    return this.productName;
  }

  /**
   * Description: Retrieves the products price
   *
   * @return The Products price
   */
  public double getProductPrice() {
    return this.productPrice;
  }

  /**
   * Description: Retrieves the Products Radio-frequency identification
   *
   * @return The products RFID Tag number
   */
  public String getRfidTagNumber() {
    return this.rfidTagNumber;
  }

  /**
   * Description: Retrieves the products Original Location
   *
   * @return The original Location the product resided
   */
  public String getOriginalLocation() {
    return this.originalLocation;
  }

  /**
   * Description: Retrieves the products Current Location
   *
   * @return Returns the products current location it resides
   */
  public String getCurrentLocation() {
    return this.currentLocation;
  }

  // Mutators
  /**
   * Description: Changes the products name
   *
   * @param newProductName The new product's name
   */
  public void changeProductName(String newProductName) {
    this.productName = newProductName;
  }

  /**
   * Description: Changes the products price
   *
   * @param newProductPrice The new product's price
   * @throws IllegalArgumentException To check if price is less than $0.00
   */
  public void changeProductPrice(double newProductPrice) {
    try {
      if (newProductPrice < 00.00) {
        System.out.println("New product price cannot be a negative!");
        throw new IllegalArgumentException();
      } else {
        this.productPrice = newProductPrice;
      }
    } catch (IllegalArgumentException e) {

    }
  }

  /**
   * Description: Changes the Radio-Frequency Identification Tag number
   *
   * @param newRfidTagNumber The new RFID Tag Number
   *
   * @throws IllegalArgumentException To check if the new RFID Number is not equal
   *                                  to 9 or has any letters after 'f'
   *
   */
  public void changeRfidTagNumber(String newRfidTagNumber) {
    try {
      if (newRfidTagNumber.length() != 9 || exclusionHex(newRfidTagNumber))
        throw new IllegalArgumentException();
      else
        this.rfidTagNumber = newRfidTagNumber.toUpperCase();
    } catch (IllegalArgumentException e) {
      System.out.println("The new RfidTagNumber must be 9 characters long!");
      // Go back to menu here
    }
  }

  /**
   * Description: Changes the Original Location of the product
   *
   * @param newOriginalLocation The new products original Location
   *
   * @throws IllegalArgumentException To check if the new original location is not
   *                                  on the shelf
   */
  public void changeOriginalLocation(String newOriginalLocation) {
    try {
      if (newOriginalLocation.length() == 6 && newOriginalLocation.charAt(0) == 's'
          && isNumeric(newOriginalLocation.substring(1))) {
        this.originalLocation = newOriginalLocation;
        this.currentLocation = this.originalLocation;
      } else
        throw new IllegalArgumentException();

    } catch (IllegalArgumentException e) {
      System.out.println(
          "The new Original Location must be 6 characters long, that has the first letter of 's', followed by a 5 digit number");
      // Go back to menu here
    }
  }

  /**
   * Description: Description
   *
   * @param newCurrentLocation The new current location the product resides
   * @throws exception To check if the new Location is not in a cart or on a shelf
   *                   or out
   */
  public void changeCurrentLocation(String newCurrentLocation) {
    try {
      if ((newCurrentLocation.length() == 6 && newCurrentLocation.charAt(0) == 's'
          && isNumeric(newCurrentLocation.substring(1))) || newCurrentLocation.equals("out")
          || (newCurrentLocation.length() == 4 && newCurrentLocation.charAt(0) == 'c'
              && isNumeric(newCurrentLocation.substring(1))))
        this.currentLocation = newCurrentLocation;
      else
        throw new IllegalArgumentException();
    } catch (IllegalArgumentException e) {
      System.out.println(
          "The new Current Location must be 4 characters long, that has the first letter of 'c', followed by a 3 digit number. or, it must be 6 characters long, that has the first letter of 's', followed by a 5 digit number. OR the new current location must be 'out'");
      // Go back to menu here
    }
  }

  /**
   * Description: The to String conversion of the ItemInfo object
   *
   * @return returns the toString value of the ItemInfo object with all of its
   *         necessary contents
   */
  public String toString() {
    return this.productName + this.rfidTagNumber + this.originalLocation + this.currentLocation
        + String.valueOf(this.productPrice);
  }

}
