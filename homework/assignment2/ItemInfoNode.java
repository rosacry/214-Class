/**
 * This class represents a Node for the Item's info
 * 
 * @author Christopher Rosales
 * @ID #114328928
 * @Assignment #2, Department Store
 */

public class ItemInfoNode {
  private ItemInfo info;
  private ItemInfoNode link;
  private ItemInfoNode prev;

  /**
   * Description: ItemInfoNode constructor initializes an Object for the Node of
   * an Item Info
   *
   * @param initialInfo The initial info for an Item Info class
   */
  public ItemInfoNode(ItemInfo initialInfo) {
    this.info = initialInfo;
    this.link = null;
  }

  /**
   * Description: Sets new info for the node
   *
   * @param newInfo Sets info equal to the new inputted info
   */
  public void setInfo(ItemInfo newInfo) {
    info = newInfo;
  }

  /**
   * Description: Retrieves the Info for the Item Info class
   *
   * @return returns the info for the Item Info class
   */
  public ItemInfo getInfo() {
    return info;
  }

  /**
   * Description: Sets the nodes link (next) value
   *
   * @param node the next nodes value (ItemInfoNode)
   */
  public void setNext(ItemInfoNode node) {
    this.link = node;
  }

  /**
   * Description: sets the nodes previous value
   *
   * @param node the previous nodes value
   */
  public void setPrev(ItemInfoNode node) {
    this.prev = node;
  }

  /**
   * Description: Retrieves the ItemInfoNodes link (next) value
   *
   * @return returns the ItemInfoNodes link (next) value
   */
  public ItemInfoNode getNext() {
    return link;
  }

  /**
   * Description: Retrieves the ItemInfoNodes previous value
   *
   * @return returns the ItemInfoNodes previous value
   */
  public ItemInfoNode getPrev() {
    return prev;
  }
}
