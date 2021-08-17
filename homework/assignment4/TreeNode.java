/**
 * This class represents a Node object of a Tree that can contain up to 9 child
 * nodes
 * 
 * @author Christopher Rosales
 * @ID #114328928
 * @Assignment #4, Tech Support Tree
 */

public class TreeNode {
  private TreeNode[] childNodes = new TreeNode[9]; // get back to this when you do ec
  private String label;
  private String message;
  private String prompt;
  private int manyItems;

  /**
   * Description: Constructor that initializes a Node object for a Tree, takes the
   * input of a label, prompt, and message
   *
   * @param nodeLabel   The label assosiated to the TreeNode object, we don't see
   *                    this visually but it's the Tag for the object to search
   *                    for it
   * 
   * @param nodePrompt  The prompt, usually an ID or a response to the message
   *                    value
   *
   * @param nodeMessage The message that is assosiated to the TreeNode object,
   *                    likely a question
   * 
   *
   */
  public TreeNode(String nodeLabel, String nodePrompt, String nodeMessage) {
    this.label = nodeLabel;
    this.prompt = nodePrompt;
    this.message = nodeMessage;
    // all nodes in childNodes are null
  }

  /**
   * Description: Retrieves the child node of the TreeNode given a position 0-8
   *
   * @param position The index of the child to retrieve
   * @return returns the child node of the TreeNode given a position of 0-8
   */
  public TreeNode getChildNode(int position) {
    return childNodes[position];
  }

  /**
   * Description: Retrieves the label of the TreeNode object
   *
   * @return returns a String label of the TreeNode object
   *
   */
  public String getLabel() {
    return label;
  }

  /**
   * Description: Retrieves the message of the TreeNode object
   *
   * @return returns the message of the TreeNode object
   */
  public String getMessage() {
    return message;
  }

  /**
   * Description: Retrieves the prompt of the TreeNode object
   *
   * @return returns the prompt of the TreeNode object
   */
  public String getPrompt() {
    return prompt;
  }

  /**
   * Description: Overrides the toString of the object to return a custom string
   * value of all the initial parameters of the constructor
   *
   * @return returns the toString value of the TreeNode object, includes all
   *         parameters from constructor
   *
   */
  public String toString() {
    return "\nLabel: " + getLabel() + "\nPrompt: " + getPrompt() + "\nMessage: " + getMessage();
  }

  /**
   * Description: Checks if the TreeNode has any child nodes, meaning if it's a
   * leaf or not
   *
   * @return returns if manyItems is equal to 0
   *
   */
  public boolean isLeaf() {
    return (manyItems == 0);
  }

  /**
   * Description: Checks if the TreeNode has 9 childs, meaning that it's full
   *
   * @return returns if manyItems is equal to childNodes.length
   *
   */
  public boolean isFull() {
    return (manyItems == childNodes.length);
  }

  /**
   * Description: Returns the number of childs in the TreeNode object
   *
   * @return The number of childs (manyItems) in the TreeNode object
   */
  public int childLength() {
    return manyItems;
  }

  /**
   * Description: Checks if the child passed is a child of the TreeNode object
   *
   * @param child The TreeNode value that we are checking is a child of the
   *              current TreeNode object
   * @return retuns true if the passed TreeNode is a child of the TreeNode object
   */
  public boolean hasChild(TreeNode child) {
    for (int i = 0; i < manyItems; i++) {
      if (childNodes[i].equals(child)) // is it .equals or ==?
        return true;
    }
    return false;
  }

  /**
   * Description: Sets a child to the TreeNode from an index of 0-8
   *
   * @param position The index of where to place the TreeNode object
   * @param newNode  The child value set of the TreeNode from 0-8 position
   */
  public void setChild(int position, TreeNode newNode) {
    if (position >= 0 && position < childNodes.length) {
      if (childNodes[position] == null) {
        childNodes[position] = newNode;
        manyItems++;
      } else
        System.out.println("there's a node already in this position!");
    } else
      System.out.println("index out of bounds");
  }

  /**
   * Description: Removes a child node given an index of 0-8
   *
   * @param position The index where to set the child node of TreeNode to null
   *
   */
  public void removeChild(int position) {
    if (position >= 0 && position < childNodes.length) {
      childNodes[position] = null;
      manyItems--;
    } else
      System.out.println("index out of bounds!");
  }

  /**
   * Description: Sets the label of the TreeNode object
   *
   * @param newLabel The new label of the TreeNode object
   */
  public void setLabel(String newLabel) {
    label = newLabel;
  }

  /**
   * Description: Sets the message of the TreeNode object
   *
   * @param newMessage The new message of the TreeNode object
   */
  public void setMessage(String newMessage) {
    message = newMessage;
  }

  /**
   * Description: Sets the prompt of the TreeNode prompt
   *
   * @param newPrompt The new prompt of the TreeNode object
   */
  public void setPrompt(String newPrompt) {
    prompt = newPrompt;
  }
}
