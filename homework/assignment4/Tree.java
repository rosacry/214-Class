import java.util.InputMismatchException;
import java.util.Scanner;

//The extra credit method, first displays the preorder() traversal, then takes an input from the user asking which label it would like to go to, then that input takes you to a specific TreeNode that has an equal label to the user input, and then calls the beginSession() with that TreeNode value

/**
 * This class represents a Tree data structor that holds nodes in a sorted
 * manner
 * 
 * @author Christopher Rosales
 * @ID #114328928
 * @Assignment #4, Tech Support Tree
 */
public class Tree {
  private TreeNode root;
  private boolean flag;
  private Scanner input = new Scanner(System.in);

  /**
   * Description: The constructor class for the Tree data structor that
   * initializes the object, sets root to null
   */
  public Tree() {
    root = null;
  }

  /**
   * Description: Adds a node to the tree in a sorted manner
   *
   * @param label       The label of the TreeNode to add
   * @param prompt      The prompt of the TreeNode to add
   * @param message     The message of the TreeNode to add
   * @param parentLabel
   *
   * @return returns true if the TreeNode was added, false if there was an error
   */
  public boolean addNode(String label, String prompt, String message, String parentLabel) {
    if (label.length() > 60 || prompt.length() > 60 || message.length() > 60 || parentLabel.length() > 60) {
      System.out.println("One of the strings exceeds the limit of 60!");
      return false;
    }
    if (root == null) // this would be the root node of tree
      root = new TreeNode(label, prompt, message);
    else { // traverse the list to find parentLabel
      TreeNode newNode = getNodeReference(parentLabel, root, 0); // parent of the node we're adding
      if (newNode != null) { // if the parent label is found
        if (newNode.getLabel() == parentLabel && newNode.isFull()) { // this means all childs are full
          System.out.println("Child nodes are full");
          return false;
        }
        newNode.setChild(newNode.childLength(), new TreeNode(label, prompt, message));
      } else {
        System.out.println("Parent node was not found!");
        return false;
      }
    }
    return true;
  }

  /**
   * Description: Retrieves the Node reference of the matching label (parentLabel)
   * as well
   *
   * @param parentLabel The label to search for in the tree
   * @param node        The node value to return
   * @param count       the count is 0, but because we are changing the value
   *                    recursively, we need to store the variable
   * @return returns the TreeNode of the node reference if it was found, null if
   *         there was no value found
   */
  private TreeNode getNodeReference(String parentLabel, TreeNode node, int count) { // find the parent
    if (node.getLabel().equals(parentLabel))
      return node;
    if (count > parentLabel.length()) // if the node was not found
      return null;
    return getNodeReference(parentLabel,
        node.getChildNode(Integer.parseInt(parentLabel.substring(count, count + 1)) - 1), count + 2);
  } // account for root

  /**
   * Description: Traverse the tree in a preOrder fashion while printing each node
   * along the way
   *
   * @param node The node value that is being printed
   *
   */
  public void preOrder(TreeNode node) {
    if (treeLoaded()) {
      if (node == null)
        return;
      System.out.println(node);
      for (int i = 0; i < node.childLength(); i++) {
        preOrder(node.getChildNode(i));
      }
    }
  }

  /**
   * Description: Starts the session troubleshooter of the file loaded
   *
   * @param node The node value that is being printed
   *
   * @exception InputMismatchException Checks if the input is a string or an
   *                                   integer
   */
  public void beginSession(TreeNode node) {
    if (treeLoaded()) {
      try {
        flag = false;
        System.out.println("\n" + node.getMessage());
        if (node.isLeaf()) {
          System.out.println("\nThank you for using our automated help services!");
          TreeDriver.main(null);
        }
        for (int i = 0; i < node.childLength(); i++) {
          System.out.println(node.getChildNode(i).getLabel().substring(node.getChildNode(i).getLabel().length() - 1,
              node.getChildNode(i).getLabel().length()) + " " + node.getChildNode(i).getPrompt());
        }
        System.out.println("0 Exit Session.\n");
        System.out.println("B) back");
        System.out.print("Choice> ");
        String choice = input.nextLine(); // index 0 based
        if (choice.equalsIgnoreCase("B"))
          // we go back here. call the method
          beginSession(moveBack(root, node, 0));
        // we are now assuming it's an int
        int position = Integer.parseInt(choice);
        if (position == 0)
          TreeDriver.main(null);
        if (position <= node.childLength() && position > 0)
          beginSession(node.getChildNode(position - 1));
        else {
          flag = true;
          throw new NumberFormatException();
        }
      } catch (NullPointerException | NumberFormatException e) {
        if (flag)
          System.out.println("That is not one of the options");
        else
          System.out.println("You must enter an int or 'b' (case insensitive) that is specified!");
      }
    } else
      System.out.println("No tree is loaded");
  }

  public void beginSession2() {
    // iteratively traverse through the tree
    try {
      TreeNode cursor = root;
      int count = 0;
      while (count < cursor.getLabel().length()) { // this condition vs (cursor != null)?
        for (int i = 0; i < cursor.childLength(); i++)
          System.out.println(cursor.getChildNode(i).getLabel().substring(cursor.getChildNode(i).getLabel().length() - 1,
              cursor.getChildNode(i).getLabel().length()) + " " + cursor.getChildNode(i).getPrompt());
        System.out.println("0 Exit Session.\n");
        System.out.print("Choice> ");
        String choice = input.nextLine(); // index 0 based
        int position = Integer.parseInt(choice);
        if (position <= cursor.childLength() && position > 0) {
          cursor = cursor.getChildNode(position - 1);
          count += 2;
        } else {
          flag = true;
          throw new NumberFormatException();
        }
      }
    } catch (NumberFormatException e) {
      if (flag)
        System.out.println("That is not one of the options");
      else
        System.out.println("You must enter in an int!)");
    }
    System.out.println("\nThank you for using our automated help services!");
  }

  /**
   * Description: This method changes the current node to its parent for the
   * beginSession()
   *
   * @param parent The TreeNode we are change child to
   *
   * @param child  The current node that is in the recursion loop for the
   *               beginSession() method
   *
   * @param count  the count is 0, but because we are changing the value
   *               recursively, we need to store the variable
   * 
   * @return parent of the child node
   */
  private TreeNode moveBack(TreeNode parent, TreeNode child, int count) {
    // start from the top and check and return the parent of child node
    if (parent.hasChild(child) || root.equals(child))
      return parent;
    // recurse down
    return moveBack(parent.getChildNode(Integer.parseInt(child.getLabel().substring(count, count + 1)) - 1), child,
        count + 2);
  }

  /**
   * Description: Goes to a specific TreeNode that has an equal label as the user
   * inputted (extra credit method)
   *
   * @exception NumberFormatException We need to check if we entered in a correct
   *                                  label from the user input
   */
  public void goToMessage() {
    // traverse the tree until you find message, call the preorder then first, do
    // method, then call the beginSession
    try {
      if (treeLoaded()) {
        preOrder(root);
        System.out.print("\nWhich label would like like to go to?> ");
        String label = input.nextLine();
        // we need to check if label is anything other than an int, '-', or 'root'
        beginSession(getNodeReference(label, root, 0));
      } else {
        System.out.println("No tree is loaded");
      }
    } catch (NumberFormatException e) {
      System.out.println("You must enter in the label or the root!");
    }
  }

  /**
   * Description: Checks if the addNode method was used at all, to know if we can
   * do any of the other methods in this class
   *
   * @param interable
   *
   * @return value
   *
   * @throws exception
   *
   * @exception exception
   *
   */
  private boolean treeLoaded() {
    return (root != null);
  }

  /**
   * Description: Retrieves the root of the Tree data structor
   *
   * @return returns the root value of the Tree data structor
   */

  public TreeNode getRoot() {
    return root;
  }
}
