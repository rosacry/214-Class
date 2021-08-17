import java.io.*;
import java.util.Scanner;

/**
 * This class represents the main of the Tree Data structor, it loads the text
 * files, and creates it into a tree, as well as calls beginSession() to start
 * the session and traverses the Tree in a preorder fashion
 * 
 * @author Christopher Rosales
 * @ID #114328928
 * @Assignment #4, Tech Support Tree
 */
public class TreeDriver {
  private static Scanner input = new Scanner(System.in);
  private static Tree techSupport = new Tree();

  /**
   * Description: Calls the menu of the main
   */
  public static void menu() {
    System.out.println(
        "\n\nL) - Load a Tree.\nH) - Begin a Help Session.\nT) - Traverse the Tree in preorder\nQ) - Quit\nZ) - Go to a specific message within the tree\n");
    System.out.print("Choice> ");
  }

  /**
   * Description: Loads a text file and creates the lines into a TreeNode and adds
   * that into a Tree in a sorted manner
   * 
   * @exception IOException This checks if the file is found from the given input
   *
   */
  public static void optionL() {
    try {
      System.out.print("\nEnterh the File name> ");
      String fileName = input.nextLine();
      if (!fileName.contains(".txt"))
        fileName += ".txt";
      File file = new File("/Users/chrisrosales/programming/sbuWork/214/homework/assignment4/textFiles/" + fileName);
      BufferedReader br = new BufferedReader(new FileReader(file));
      // we should start here by getting the root
      String label = br.readLine();
      if (!label.contains("-") && !label.contains("root") && label.length() == 1) {
        // then we know that it's not a label
        System.out.println("Not a label in for this line!");
        main(null);
      }
      while (label.length() == 0) // handling if next (or next couple) lines are blank
        label = br.readLine();
      String prompt = br.readLine();
      while (prompt.length() == 0)
        prompt = br.readLine();
      String message = br.readLine();
      while (message.length() == 0)
        message = br.readLine();
      techSupport.addNode(label, prompt, message, label);
      String node;
      while ((node = br.readLine()) != null) {
        while (node.length() == 0) // handling if next (or next couple) lines are blank
          node = br.readLine();
        int count = 0;
        int nodeCount = Integer.parseInt(node.substring(node.length() - 1));
        if (nodeCount > 9 || nodeCount < 1) {
          System.out.println("Node count is not in the correct bounds");
          main(null);
        }
        while (nodeCount != count) {
          label = br.readLine();
          while (label.length() == 0)
            label = br.readLine();
          prompt = br.readLine();
          while (prompt.length() == 0)
            prompt = br.readLine();
          message = br.readLine();
          while (message.length() == 0)
            message = br.readLine();
          String parentLabel;
          if (label.length() > 2) // if label is not a single digit
            parentLabel = label.substring(0, label.length() - 2);
          else // if digit is a single digit
            parentLabel = "root";
          count++;
          techSupport.addNode(label, prompt, message, parentLabel);
        }
      }
      System.out.println("\nTree Created Successfully!");
      br.close();
    } catch (IOException e) {
      System.out.println("File not found!");
    }
  }

  /**
   * Description: Starts the session troubleshooter
   */
  public static void optionH() {
    System.out.print("\nHelp Session Starting...");
    techSupport.beginSession(techSupport.getRoot());
  }

  /**
   * Description: Traverses and prints the tree in a preorder fashion
   */
  public static void optionT() {
    techSupport.preOrder(techSupport.getRoot());
  }

  /**
   * Description: Quits the program
   */
  public static void optionQ() {
    System.out.println("\nThank you for using our automated help services!\n");
    System.exit(0);
  }

  /**
   * Description:Goes to a specific TreeNode that has an equal label as the user
   * inputted (extra credit method)
   */
  public static void optionZ() {
    techSupport.goToMessage();
  }

  /**
   * Description: The main of this class that calls the methods previously listed
   * if the input is equal to the key value
   */
  public static void main(String[] args) {
    while (true) {
      menu();
      String option = input.nextLine();
      if (option.equalsIgnoreCase("L"))
        optionL();
      else if (option.equalsIgnoreCase("H"))
        optionH();
      else if (option.equalsIgnoreCase("T"))
        optionT();
      else if (option.equalsIgnoreCase("Q"))
        optionQ();
      else if (option.equalsIgnoreCase("Z"))
        optionZ();
      else {
        System.out.println("That is not one of the inputs listed");
      }
    }
  }
}
