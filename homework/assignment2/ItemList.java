import java.text.DecimalFormat;

/**
 * This class represents a doubly linked list class for the ItemInfoNode's class
 * 
 * @author Christopher Rosales
 * @ID #114328928
 * @Assignment #2, Department Store
 */
public class ItemList {
  private ItemInfoNode head;
  private ItemInfoNode tail;
  private ItemInfoNode cursor;
  private int manyItems = 0;
  private String formattedResult = "%-20s %-20s %-20s %-20s %-20s %n";
  private ItemInfoNode nodePtr;
  private boolean flag = false;
  private static DecimalFormat df = new DecimalFormat("#.00");

  /**
   * Description: An empty Constructor that initializes the Doubly linked list for
   * the ItemInfoNode class
   */
  public ItemList() {
    head = null;
    tail = null;
    cursor = null;
  }

  /**
   * Description: Inserts the content of an ItemInfoNode object in the order of
   * its RFID
   *
   * @param name             The products name
   * @param rfidTag          The products RFID Tag
   * @param price            The products price
   * @param originalLocation The products original location it resided
   *
   */
  // O(1) for adding at head and tail, O(n) for traversing through list if the
  // newNode cannot be inserted at the head or tail
  public void insertInfo(String name, String rfidTag, double price, String originalLocation) {
    // Inserts the info into the list in its correct position based on its
    // rfidTagNumber.
    // sort after adding
    // check for empty before inserting
    // if not empty, compare rfidTag's using for loop until you found the correct
    // spot
    ItemInfoNode newNode = new ItemInfoNode(
        new ItemInfo(name, rfidTag.toUpperCase(), price, originalLocation.toLowerCase()));
    if (isEmpty() || newNode.getInfo().getRfidTagNumber().compareTo(head.getInfo().getRfidTagNumber()) <= 0)
      addBeginning(newNode);// O(1)
    else if (newNode.getInfo().getRfidTagNumber().compareTo(tail.getInfo().getRfidTagNumber()) >= 0) // tail
      addEnd(newNode);// O(1)
    else { // if newNode is not inserting to head or tail O(n)
      nodePtr = head;
      while (nodePtr != null) {
        if (newNode.getInfo().getRfidTagNumber().compareTo(nodePtr.getInfo().getRfidTagNumber()) >= 0
            && newNode.getInfo().getRfidTagNumber().compareTo(nodePtr.getNext().getInfo().getRfidTagNumber()) <= 0) {
          insertInList(nodePtr, newNode);
          nodePtr = tail;
        }
        nodePtr = nodePtr.getNext();
      }
    }
    manyItems++;
  }

  /**
   * Description: Adds the content of an ItemInfoNode object if it's the lowest
   * RFID value in the list
   *
   * @param newNode The new ItemInfoNode that was initialized in the insertInfo
   *                method
   */
  private void addBeginning(ItemInfoNode newNode) {
    newNode.setNext(head);
    newNode.setPrev(null);
    if (!isEmpty())
      head.setPrev(newNode);
    else
      tail = cursor = newNode;
    head = newNode;
  }

  /**
   * Description: Adds the content of an ItemInfoNode object if it's the greatest
   * RFID value in the list
   *
   * @param newNode The new ItemInfoNOde that was initialized in the insertInfo
   *                method
   */
  private void addEnd(ItemInfoNode newNode) {
    newNode.setNext(null);
    tail.setNext(newNode);
    newNode.setPrev(tail);
    tail = newNode;
  }

  /**
   * Description: Adds the content of an ItemInfoNode object in the doubly linked
   * list where it's next value has a greater rfid and it's previous value has a
   * lower rfid value
   *
   * @param prevNode The previous node that comes before the newly added node
   *
   * @param newNode  The new node that is added after the previous node
   */
  private void insertInList(ItemInfoNode prevNode, ItemInfoNode newNode) {
    newNode.setNext(prevNode.getNext());
    prevNode.setNext(newNode);
    newNode.setPrev(newNode);
    if (newNode.getNext() != null)
      newNode.getNext().setPrev(newNode);
  }

  /**
   * Description: The size of the doubly linked list
   * 
   * @return size of the doubly linked list (integer)
   */
  public int size() {
    return manyItems;
  }

  /**
   * Description: Checks if the doubly linked list is empty
   *
   * @return true if the linked list is empty, false if the linked list is not
   *         empty
   */
  public boolean isEmpty() {
    return (manyItems == 0);
  }

  /**
   * Description: Removed all the products with the current location equaling to
   * 'out'
   *
   */
  // O(n) we are traversing through the list, O(1) if empty
  public void removeAllPurchased() {
    if (isEmpty())
      System.out.println("Nothing to remove!");
    else {
      nodePtr = head;
      System.out.printf(formattedResult, "", "", " Original", " Current", "");
      System.out.printf(formattedResult, "Item Name", "  RFID", " Location", " Location", " Price");
      System.out.printf(formattedResult, "---------", "---------", "----------", "----------", "-------");
      while (nodePtr != null) {
        if (nodePtr.getInfo().getCurrentLocation().toLowerCase().equals("out")) {
          // print
          System.out.printf(formattedResult, nodePtr.getInfo().getProductName(), nodePtr.getInfo().getRfidTagNumber(),
              "  " + nodePtr.getInfo().getOriginalLocation(), "  " + nodePtr.getInfo().getCurrentLocation(),
              " " + df.format(nodePtr.getInfo().getProductPrice()));
          // add node to removed list to display
          // removed.insertInfo(nodePtr.getInfo().getProductName(),
          // nodePtr.getInfo().getRfidTagNumber(),
          // nodePtr.getInfo().getProductPrice(),
          // nodePtr.getInfo().getOriginalLocation());
          // remove node from current list
          if (head == nodePtr) // if node is the head
            head = nodePtr.getNext();
          if (nodePtr.getNext() != null) // if node is not the tail
            nodePtr.getNext().setPrev(nodePtr.getPrev());
          if (nodePtr.getPrev() != null) // if node is not the head
            nodePtr.getPrev().setNext(nodePtr.getNext());
          // decrement manyItems
          manyItems--;
        }
        nodePtr = nodePtr.getNext();
      }
    }
  }

  /**
   * Description: Changes an items current location
   *
   * @param rfidTag The Radio Frequency Identification Tag of the product desired
   *                to be changed
   * @param source  The original location of the product desired to be changed
   * @param dest    The new current location of the product desired to be changed
   * @return Returns true if the product is found, false if the linked list is
   *         empty or the product is not found or the format is invalid
   * @throws exception If the format of either the source or dest are incorrent to
   *                   be searched for o(1)
   * 
   */
  // O(rfidTag) - when we hit an equal rfid tag (best case) O(n) (worst case) we
  // are traversing through the list, O(1) if empty
  public boolean moveItem(String rfidTag, String source, String dest) {
    if (isEmpty())
      System.out.println("Nothing to move!");
    else {
      try {
        if (invalidFormat(source, dest))
          throw new IllegalArgumentException();
        nodePtr = head;
        while (nodePtr != null) {
          if (nodePtr.getInfo().getRfidTagNumber().equals(rfidTag.toUpperCase())) {
            // change here
            if (nodePtr.getInfo().getCurrentLocation().equals(source.toLowerCase())) {
              nodePtr.getInfo().changeCurrentLocation(dest.toLowerCase());
              System.out.println("Item found!");
              return true;
            }
          }
          nodePtr = nodePtr.getNext();
        }
      } catch (IllegalArgumentException e) {
        System.out.println("Invalid format for moving the item");
        return false;
      }
    }
    System.out.println("Item not found!");
    return false;
  }

  /**
   * Description: Checks the format for the moveItem methods source and dest
   * parameters
   *
   * @param source The original location of the product desired to be changed
   * @param dest   The new current location of the product desired to be changed
   * @return returns true if the format of moveItems source or dest are invalid
   *         returns false if there are no errors with source or dest
   */
  private boolean invalidFormat(String source, String dest) {
    if (source.equals("out")
        || ((!dest.equals("out")) && (dest.charAt(0) != 'c' && (!isNumeric(dest.substring(1))))
            && (dest.charAt(0) != 's' && (!isNumeric(dest.substring(1)))))
        || (dest.charAt(0) != 's' && (!isNumeric(dest.substring(1))))) {
      return true;
    }
    return false;
  }

  /**
   * Description: prints all of the ItemInfoNodes in the doubly linked list of
   * this class
   *
   */
  // O(n) we are traversing through the list, O(1) if empty
  public void printAll() {
    if (isEmpty()) {
      System.out.println("Nothing to print because list is empty");
    } else {
      nodePtr = head;
      System.out.printf(formattedResult, "", "", " Original", " Current", "");
      System.out.printf(formattedResult, "Item Name", "  RFID", " Location", " Location", " Price");
      System.out.printf(formattedResult, "---------", "---------", "----------", "----------", "-------");
      while (nodePtr != null) {
        System.out.printf(formattedResult, nodePtr.getInfo().getProductName(), nodePtr.getInfo().getRfidTagNumber(),
            "  " + nodePtr.getInfo().getOriginalLocation(), "  " + nodePtr.getInfo().getCurrentLocation(),
            " " + df.format(nodePtr.getInfo().getProductPrice()));
        nodePtr = nodePtr.getNext();
      }
    }
  }

  /**
   * Description: Prints all the ItemInfoNodes in the doubly linked list that
   * contains the specified current location
   *
   * @param location The current location to be matching for all of the printed
   *                 Items
   * 
   */
  // O(n) we are traversing through the list, O(1) if empty
  public void printByLocation(String location) {
    if (isEmpty()) {
      System.out.println("Nothing to print because list is empty!");
    } else {
      nodePtr = head;
      System.out.printf(formattedResult, "", "", " Original", " Current", "");
      System.out.printf(formattedResult, "Item Name", "  RFID", " Location", " Location", " Price");
      System.out.printf(formattedResult, "---------", "---------", "----------", "----------", "-------");
      while (nodePtr != null) {
        if (nodePtr.getInfo().getCurrentLocation().equals(location.toLowerCase()))
          System.out.printf(formattedResult, nodePtr.getInfo().getProductName(), nodePtr.getInfo().getRfidTagNumber(),
              "  " + nodePtr.getInfo().getOriginalLocation(), "  " + nodePtr.getInfo().getCurrentLocation(),
              " " + df.format(nodePtr.getInfo().getProductPrice()));
        nodePtr = nodePtr.getNext();
      }
    }
  }

  /**
   * Description: Places every product back to their original location if their
   * current location is not equal to the original location
   */
  // O(n) we are traversing through the list, O(1) if empty
  public void cleanStore() {
    if (isEmpty()) {
      System.out.println("No Items to be placed back because list is empty!");
    } else {
      nodePtr = head;
      System.out.printf(formattedResult, "", "", " Original", " Current", "");
      System.out.printf(formattedResult, "Item Name", "  RFID", " Location", " Location", " Price");
      System.out.printf(formattedResult, "---------", "---------", "----------", "----------", "-------");
      while (nodePtr != null) {
        if (!(nodePtr.getInfo().getOriginalLocation().equals(nodePtr.getInfo().getCurrentLocation()))
            && nodePtr.getInfo().getCurrentLocation().charAt(0) == 's') {
          // don't need to check for the numbers because other exeptions cover that set
          // back equal to original and then print it
          // set it back to orig
          // print
          System.out.printf(formattedResult, nodePtr.getInfo().getProductName(), nodePtr.getInfo().getRfidTagNumber(),
              "  " + nodePtr.getInfo().getOriginalLocation(), "  " + nodePtr.getInfo().getCurrentLocation(),
              " " + df.format(nodePtr.getInfo().getProductPrice()));
          nodePtr.getInfo().changeCurrentLocation(nodePtr.getInfo().getOriginalLocation());

        }
        // go forward
        nodePtr = nodePtr.getNext();
      }
    }
  }

  /**
   * Description: Sets all the items in an inputted cart to 'out'
   *
   * @param cartNumber The specified cart number for the items matching that cart
   *                   number to be checked 'out'
   * @return The total value of the items in the specified cart
   */
  // O(n) we are traversing through the list, O(1) if empty
  public double checkOut(String cartNumber) {
    double total = 00.00;
    if (isEmpty()) {
      System.out.println("Nothing to check out!");
      return total;
    } else {
      nodePtr = head;
      System.out.printf(formattedResult, "", "", " Original", " Current", "");
      System.out.printf(formattedResult, "Item Name", "  RFID", " Location", " Location", " Price");
      System.out.printf(formattedResult, "---------", "---------", "----------", "----------", "-------");
      while (nodePtr != null) {
        if (nodePtr.getInfo().getCurrentLocation().equals(cartNumber.toLowerCase())) {
          // add total
          total += nodePtr.getInfo().getProductPrice();
          // print
          System.out.printf(formattedResult, nodePtr.getInfo().getProductName(), nodePtr.getInfo().getRfidTagNumber(),
              "  " + nodePtr.getInfo().getOriginalLocation(), "  " + nodePtr.getInfo().getCurrentLocation(),
              " " + df.format(nodePtr.getInfo().getProductPrice()));
          nodePtr.getInfo().changeCurrentLocation("out");
        }
        nodePtr = nodePtr.getNext();
      }
    }
    if (total == 00.00)
      System.out.println("No Items found in your cart to checkout");
    else
      System.out.print("\nThe total cost for all merchandise in cart $" + total);
    return total;
  }

  /**
   * Description: Checks if the digits after 'c' or 's' are number and not
   * containing any letters
   *
   * @param string The assumed numbers to be checked if all are integers
   * @return true if the string is all numbers, false if it contains a letter or
   *         if the string is empty
   * @throws NumberFormatException To check if the string contains a character
   *                               that is not an integer
   */
  public boolean isNumeric(String string) {
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
  public boolean exclusionHex(String rfid) {
    String exclude = "GHIJKLMNOPQRSTUVWXYZ";
    for (int i = 0; i < exclude.length(); i++) {
      if (rfid.toUpperCase().contains(exclude.substring(i, i + 1)))
        return true;
    }
    return false;
  }

  /**
   * Description: Prints all the ItemInfoNodes that have matching RFID Tag's
   *
   * @param rfid The specified RFID Tag for the items matching that RFID Tag to be
   *             printed out in the list
   */
  // O(n) we are traversing through the list, O(1) if empty
  public void commonRfid(String rfid) {
    if (isEmpty())
      System.out.println("Nothing to print!");
    else {
      nodePtr = head;
      System.out.printf(formattedResult, "", "", " Original", " Current", "");
      System.out.printf(formattedResult, "Item Name", "  RFID", " Location", " Location", " Price");
      System.out.printf(formattedResult, "---------", "---------", "----------", "----------", "-------");
      while (nodePtr != null) {
        if (nodePtr.getInfo().getRfidTagNumber().equals(rfid.toUpperCase())) {
          System.out.printf(formattedResult, nodePtr.getInfo().getProductName(), nodePtr.getInfo().getRfidTagNumber(),
              "  " + nodePtr.getInfo().getOriginalLocation(), "  " + nodePtr.getInfo().getCurrentLocation(),
              " " + df.format(nodePtr.getInfo().getProductPrice()));
        }
        nodePtr = nodePtr.getNext();
      }
    }
  }
}
