import java.util.Scanner;

public class Tree {
  private TreeNode root;
  private Scanner input = new Scanner(System.in);

  public Tree() {
    root = null;
  }

  // find parentLabel then add left oriented, if there's no parent label return
  // false;
  public boolean addNode(String label, String prompt, String message, String parentLabel) {
    if (label.length() >= 60 || prompt.length() >= 60 || message.length() >= 60 || parentLabel.length() >= 60) {
      System.out.println("One of the strings exceeds the limit of 60!");
      return false;
    }
    if (root == null) // this would be the root node of tree
      root = new TreeNode(label, prompt, parentLabel);
    else { // traverse the list to find parentLabel
      TreeNode newNode = getNodeReference(parentLabel, root);
      if (newNode != null) {
        if (newNode.getLabel() == parentLabel && root.isFull()) { // this means all childs are full
          System.out.println("Child nodes are full");
          return false;
        } else if (newNode.getLeft() == null)
          newNode.setLeft(new TreeNode(label, prompt, message));
        else if (newNode.getMid() == null)
          newNode.setMid(new TreeNode(label, prompt, message));
        else if (newNode.getRight() == null)
          newNode.setRight(new TreeNode(label, prompt, message));
      } else {
        System.out.println("Parent node was not found!");
        return false;
      }
    }
    return true;
  }

  public TreeNode getNodeReference(String label, TreeNode root) { // find the parent label,
    if (root == null)
      return null;
    else if (root.getLabel() == label)
      return root;
    else {
      getNodeReference(label, root.getLeft());
      getNodeReference(label, root.getMid());
      getNodeReference(label, root.getRight());
    }
    return root;
  }

  public void preOrder(TreeNode root) {
    if (root == null)
      return;
    System.out.println(root);
    preOrder(root.getLeft());
    preOrder(root.getMid());
    preOrder(root.getRight());
  }

  public void beginSession(TreeNode root) {
    System.out.println(root.getMessage());
    System.out.println(
        root.getLeft().getLabel().substring(root.getLeft().getLabel().length() - 1, root.getLeft().getLabel().length())
            + " " + root.getLeft().getPrompt()); // prints the label and the prompt of left
    System.out.println(
        root.getMid().getLabel().substring(root.getMid().getLabel().length() - 1, root.getMid().getLabel().length())
            + " " + root.getMid().getPrompt()); // prints the label and the prompt of middle
    System.out.println(root.getRight().getLabel().substring(root.getRight().getLabel().length() - 1,
        root.getRight().getLabel().length()) + " " + root.getRight().getPrompt()); // prints label and prompt of right
    String option = input.nextLine();
    if (option == root.getLeft().getLabel().substring(root.getLeft().getLabel().length() - 1,
        root.getLeft().getLabel().length()))
      beginSession(root.getLeft());
    else if (option == root.getMid().getLabel().substring(root.getMid().getLabel().length() - 1,
        root.getMid().getLabel().length()))
      beginSession(root.getMid());
    else if (option == root.getRight().getLabel().substring(root.getRight().getLabel().length() - 1,
        root.getRight().getLabel().length()))
      beginSession(root.getRight());
    else
      return;
  }

  public TreeNode getRoot() {
    return root;
  }
  // for the extra credit.. recursion in a for loop
}
