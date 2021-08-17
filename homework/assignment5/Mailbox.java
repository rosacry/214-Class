import java.io.*;
import java.util.*;

/**
 * This class represents both the main and the Mailbox object that holds an
 * ArrayList of the Folders object
 * 
 * @author Christopher Rosales
 * @ID #114328928
 * @Assignment #5, Mailbox simulation
 */

public class Mailbox implements Serializable {
  private Folder inbox = new Folder("Inbox"); // Stores the inbox, which is a special folder which should never be
                                              // allowed to
  // be deleted or renamed. All new emails go here.
  private Folder trash = new Folder("Trash"); // tores the trash, which is a special folder which should never be
                                              // allowed to
  // be deleted or renamed. When an email is deleted, it is moved here.
  private ArrayList<Folder> folders = new ArrayList<Folder>(); // Stores all of the custom folders contained in the
                                                               // mailbox. (It can be any
  // data collection, ArrayList is only an example)
  private static Mailbox mailbox = new Mailbox(); // Stores the main mailbox that will used by the application. This
  // mailbox
  // should be instantiated in the main method.
  private static Scanner inputStatic = new Scanner(System.in);
  private static Folder folderSelected;
  private String filename = "mailbox.obj";

  /**
   * Description: The constructor that initializes the Mailbox object
   * 
   */
  public Mailbox() {
    addFolder(inbox);
    addFolder(trash);
  }

  /**
   * Description: Adds a Folder to the ArrayList of the Mailbox object class
   *
   * @param folder The Folder object being added
   */
  public void addFolder(Folder folder) { // Adds the given folder to the list of custom folders. Note: check to make
                                         // sure a folder with that given name does not already exist. If it does,
                                         // return an error in some manner.
    if (folders.contains(folder))
      System.out.println("Error, folder name already exists!");
    else {
      folders.add(folder);
    }
  }

  /**
   * Description: Deletes a folder in the ArrayList of the Mailbox object class
   *
   * @param name The name of the Folder object being deleted
   *
   */
  public boolean deleteFolder(String name) { // Removes the given folder from the list of custom folders
    for (int i = 0; i < folders.size(); i++) {
      if (folders.get(i).getName().equals(name)) {
        folders.remove(i);
        return true;
      }
    }
    return false;
  }

  /**
   * Description: Composes an email to be added to the inbox
   *
   * 
   */
  public static void composeEmail(Mailbox mail) { // Gets user input on the contents of the email and adds it to the
                                                  // inbox.
    System.out.print("\nEnter recipient (To): ");
    String msgTo = inputStatic.nextLine();
    System.out.print("\nEnter carbon copy recipients (CC): ");
    String cc = inputStatic.nextLine();
    System.out.print("\nEnter blind carbon copy recipeints (BCC): ");
    String bcc = inputStatic.nextLine();
    System.out.print("\nEnter subject line: ");
    String subject = inputStatic.nextLine();
    System.out.print("\nEnter body: ");
    String body = inputStatic.nextLine();
    mailbox.getFolder("Inbox").addEmail(new Email(msgTo, cc, bcc, subject, body));
    System.out.println("\nEmail successfully added to Inbox");
  }

  /**
   * Description: Moves an email from the current Folder to the trash
   * 
   * @param email The email passed to be moved to trash
   */
  public void deleteEmail(Email email) { // Moves the email to the trash. (This method shouldn’t remove any emails from
                                         // folders, the method removeEmail should be called and then deleteEmail is
                                         // called on the result)
    moveEmail(email, trash);
  }

  /**
   * Description: Clears the trash (deletes) folder of all emails
   */
  public void clearTrash() { // Removes all emails from the trash folder.
    trash.clearFolder();
  }

  /**
   * Description: Moves an email from the current directory to the specified
   * directory passed
   * 
   * @param email  The email passed to be moved to the target folder
   * @param target The folder to receive the email that's being passed
   */
  public void moveEmail(Email email, Folder target) { // Takes the given email and puts in in the given folder. If the
                                                      // folder cannot be found, instead move it to the Inbox. (Again,
                                                      // this method shouldn’t remove any emails from folders, the
                                                      // method removeEmail should be called and then moveEmail is
                                                      // called on the result)
    if (folders.contains(target))
      target.addEmail(email);
    else {
      inbox.addEmail(email);
    }
  }

  /**
   * Description: Retrieves a folder by passing it's name attribute
   *
   * @param name The name passed to retrieve the folder
   * @return The folder object, if it's not found, it returns null
   */
  public Folder getFolder(String name) { // Returns a folder by folder name.
    for (int i = 0; i < folders.size(); i++) {
      if (folders.get(i).getName().equals(name))
        return folders.get(i);
    }
    return null;
  }

  /**
   * Description: Retrieves a folder by passing it's index in the ArrayList
   *
   * @param position The position to Retrieve the folder from it's index
   * @return The folder object with it's passed position
   *
   */
  public Folder getFolder(int position) {
    return folders.get(position);
  }

  /**
   * Description: Retrieves the size of the Folder ArrayList
   *
   * @return returns the size of the folder arraylist in the mailbox class
   */
  public int size() {
    return folders.size();
  }

  /**
   * Description: Lists the folders in the ArrayList of the Mailbox class
   */
  public void listFolders() {
    System.out.println("\nFolders:\n");
    for (int i = 0; i < folders.size(); i++) {
      System.out.println((i + 1) + " " + folders.get(i).getName());
    }
  }

  /**
   * Description: Serializes the mailbox object
   * 
   * @exception IOException If the file could not be serialized
   */
  public void serialize() {
    try {
      FileOutputStream file = new FileOutputStream(filename);
      ObjectOutputStream out = new ObjectOutputStream(file);
      out.writeObject(mailbox); // this is the problem
      out.close();
      file.close();
      System.out.println("Mailbox has been saved");
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("File couldn't be saved");
    }
  }

  /**
   * Description: Deserializes an object
   *
   * @exception IOException            If the file could not be deserialized
   * @exception ClassNotFoundException If the class of the object being
   *                                   deserialized is not found
   *
   */
  public void deserialize() {
    try {
      FileInputStream file = new FileInputStream(filename);
      ObjectInputStream in = new ObjectInputStream(file);
      mailbox = (Mailbox) in.readObject();
      in.close();
      file.close();
    } catch (IOException exception) {
      System.out.println("Previous save not found, starting with an empty mailbox.");
    } catch (ClassNotFoundException e) {
      System.out.println("Class not found!");
    }
  }

  /**
   * Description: Adds folder to the mailbox object
   */
  public static void optionA() {
    System.out.print("\nEnter the folder name you would like to add: ");
    String name = inputStatic.nextLine();
    mailbox.addFolder(new Folder(name));
    System.out.println("\nFolder " + name + " has been added");
  }

  /**
   * Description: Removes a folder from the mailbox object
   */
  public static void optionR() {
    System.out.print("\nEnter the folder name you would like to remove: ");
    String name = inputStatic.nextLine();
    if (mailbox.deleteFolder(name))
      System.out.println("\nFolder '" + name + "' has been removed");
    else
      System.out.println("Folder was not found to delete!");
  }

  /**
   * Description: Composes an email to the mailbox object
   */
  public static void optionC() {
    Mailbox.composeEmail(mailbox);
  }

  /**
   * Description: Views a folder from the mailbox object
   */
  public static void optionF() {
    System.out.print("\nEnter the folder name you would like to view: ");
    folderSelected = mailbox.getFolder(inputStatic.nextLine());
    if (folderSelected == null) {
      System.out.println("Folder is not found!");
      menu();
    } else
      submenu();
  }

  /**
   * Description: Views the inbox folder of the mailbox object
   */
  public static void optionI() {
    folderSelected = mailbox.getFolder("Inbox");
    submenu();
  }

  /**
   * Description: Views the trash folder of the mailbox object
   */
  public static void optionT() {
    folderSelected = mailbox.getFolder("Trash");
    submenu();
  }

  /**
   * Description: Empties the trash folder of the mailbox object
   */
  public static void optionE() {
    System.out.println("\n" + mailbox.getFolder("Trash").size() + " item(s) successfully deleted");
    mailbox.clearTrash();
  }

  /**
   * Description: Moves an email of the currentFolder specified
   * 
   * @exception IndexOutOfBoundsException To check if the index the user inputs is
   *                                      out of bounds
   * @exception InputMismatchException    To check if the user inputs a string
   *                                      instead of an int
   */
  public static void optionM() {
    try {
      folderSelected.listEmailsBySubject();
      System.out.print("\nEnter the index of which email you would like to move: ");
      int option = inputStatic.nextInt() - 1;
      inputStatic.nextLine();
      Email checker = folderSelected.getEmail(option); // to catch exception
      mailbox.listFolders();
      System.out.print("\nEnter the name of which folder you would like to move this email to: ");
      String folder = inputStatic.nextLine();
      mailbox.moveEmail(folderSelected.removeEmail(option), mailbox.getFolder(folder));
      System.out.print("\nEmail '" + checker.getSubject() + "' has been moved to ");
      if (mailbox.getFolder(folder) == null)
        System.out.println("Inbox");
      else
        System.out.println(mailbox.getFolder(folder));
    } catch (IndexOutOfBoundsException e) {
      System.out.println("Index is out of bounds");
    } catch (InputMismatchException e) {
      System.out.println("You must enter an int!");
      inputStatic.nextLine();
    }
    submenu();
  }

  /**
   * Description: Deletes an email of the current folder specified
   * 
   * @exception IndexOutOfBoundsException To check if the user inputs an index out
   *                                      of bounds
   * @exception InputMismatchException    To check if the user inputs a string
   *                                      instead of an int
   */
  public static void optionD() {
    try {
      folderSelected.listEmailsBySubject();
      System.out.print("\nEnter the index of which email you would like to remove: ");
      int option = inputStatic.nextInt() - 1;
      inputStatic.nextLine();
      Email deletedEmail = folderSelected.removeEmail(option);
      mailbox.deleteEmail(deletedEmail);
      System.out.println("\nEmail '" + deletedEmail.getSubject() + "' has been removed from folder " + folderSelected
          + " and moved into Trash");
    } catch (IndexOutOfBoundsException e) {
      System.out.println("Index is out of bounds");
    } catch (InputMismatchException e) {
      System.out.println("You must enter an int!");
      inputStatic.nextLine();
    }
    submenu();
  }

  /**
   * Description: Views the contents of an email of the current folder specified
   * 
   * @exception IndexOutOfBoundsException To check if the user inputs an index out
   *                                      of bounds
   * @exception InputMismatchException    To check if the user inputs a string
   *                                      instead of an int
   */
  public static void optionV() {
    try {
      folderSelected.listEmailsBySubject();
      System.out.print("\nEnter the index of which email you would like to view the contents of: ");
      int option = inputStatic.nextInt() - 1;
      inputStatic.nextLine();
      System.out.println(folderSelected.getEmail(option));
    } catch (IndexOutOfBoundsException e) {
      System.out.println("Index is out of bounds");
    } catch (InputMismatchException e) {
      System.out.println("You must enter an int!");
      inputStatic.nextLine();
    }
    submenu();
  }

  /**
   * Description: Sorts the current folder specified by Subject Ascending
   */
  public static void optionSA() {
    folderSelected.sortBySubjectAscending();
    System.out.println(folderSelected + " has been sorted in subject ascending order");
    submenu();
  }

  /**
   * Description: Sorts the current folder specified by Subject Descending
   */
  public static void optionSD() {
    folderSelected.sortBySubjectDescending();
    System.out.println(folderSelected + " has been sorted in subject descending order");
    submenu();
  }

  /**
   * Description: Sorts the current folder specified by Date Ascending
   */
  public static void optionDA() {
    folderSelected.sortByDateAscending();
    System.out.println(folderSelected + " has been sorted in date ascending order");
    submenu();
  }

  /**
   * Description: Sorts the current folder specified by Date Descending
   */
  public static void optionDD() {
    folderSelected.sortByDateDescending();
    System.out.println(folderSelected + " has been sorted in data descending order");
    submenu();
  }

  /**
   * Description: Calls the menu()
   */
  public static void optionR2() {
    menu();
  }

  /**
   * Description: Serializes the object and then Quits out of the program
   */
  public static void optionQ() {
    mailbox.serialize();
    System.exit(0);
  }

  /**
   * Description: Checks if there's a serialized 'object.obj' and deserializes it,
   * if not, we create a completely new mailbox object
   */
  public static void checkForSave() {
    File directory = new File("/Users/chrisrosales/programming/sbuWork/214/homework/assignment5");
    if (directory.list() == null) {
      System.out.println("There are no files in the directory");
    } else {
      for (int i = 0; i < directory.list().length; i++) {
        if (directory.list()[i].equalsIgnoreCase("mailbox.obj")) {
          mailbox.deserialize();
          System.out.println("\nPrevious save found");
          return;
        }
      }
    }
    System.out.println("\nNo previous save found, creating new mailbox");
  }

  /**
   * Description: Displays the menu of the program
   */
  public static void menu() {
    System.out.println("\nMailbox:\n--------");
    for (int i = 0; i < mailbox.size(); i++) {
      System.out.println(mailbox.getFolder(i));
    }
    System.out.println(
        "\n\nA) - Add Folder\nR) - Remove\nC) - Compose Email\nF) - View Folder\nI) - View Inbox\nT) - View Trash\nE) - Empty Trash\nQ) - Quit the program\n");
    System.out.print("Choice> ");
    String option = inputStatic.nextLine();
    if (option.equalsIgnoreCase("A"))
      optionA();
    else if (option.equalsIgnoreCase("R"))
      optionR();
    else if (option.equalsIgnoreCase("C"))
      optionC();
    else if (option.equalsIgnoreCase("F"))
      optionF();
    else if (option.equalsIgnoreCase("I"))
      optionI();
    else if (option.equalsIgnoreCase("T"))
      optionT();
    else if (option.equalsIgnoreCase("E"))
      optionE();
    else if (option.equalsIgnoreCase("Q"))
      optionQ();
    else {
      System.out.println("\nThat is not one of the options\n");
      menu();
    }
  }

  /**
   * Description: Displays the submenu of the program
   */
  public static void submenu() {
    System.out.println("\n" + folderSelected);
    folderSelected.listEmailsBySubject();
    System.out.println(
        "\n\nM) - Move email\nD) - Delete email\nV) - View email contents\nSA) - Sort by subject ascending\nSD) - Sort by subject descending\nDA) - Sort by data ascending\nDD) - Sort by date descending\nR) - Return to main menu\n");
    System.out.print("Choice> ");
    String option = inputStatic.nextLine();
    if (option.equalsIgnoreCase("M"))
      optionM();
    else if (option.equalsIgnoreCase("D"))
      optionD();
    else if (option.equalsIgnoreCase("V"))
      optionV();
    else if (option.equalsIgnoreCase("SA"))
      optionSA();
    else if (option.equalsIgnoreCase("SD"))
      optionSD();
    else if (option.equalsIgnoreCase("DA"))
      optionDA();
    else if (option.equalsIgnoreCase("DD"))
      optionDD();
    else if (option.equalsIgnoreCase("R"))
      optionR2();
    else {
      System.out.println("That is not one of the options");
      submenu();
    }
  }

  /**
   * Description: The main that calls the program
   */
  public static void main(String[] args) {
    checkForSave();
    while (true) {
      menu();
    }
  }
}
