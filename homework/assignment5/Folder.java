import java.io.Serializable;
import java.util.*;

/**
 * This class represents a Folder of an email that consists an ArrayList of
 * email
 * 
 * @author Christopher Rosales
 * @ID #114328928
 * @Assignment #5, Mailbox simulation
 */
public class Folder implements Serializable {
  private ArrayList<Email> emails = new ArrayList<Email>(); // Stores all of the emails contained in this folder.
  private String name; // Stores the name of the folder.
  private int currentSortingMethod;
  private String[] sortingMethods = new String[] { "Subject Ascending", "Subject Descending", "Date Ascending",
      "Date Descending" }; // Stores the current sorting method (however that you see fit) so that emails
  transient Scanner input = new Scanner(System.in);
  private String formattedResult = "%-8s %-20s %-9s";
  // added can be properly sorted without having to first resort the folder.
  // Notes: Default is date descending.

  /**
   * Description: The Constructor of Folder that initializes the Folder object
   *
   * @param folderName The name of the folder
   */
  public Folder(String folderName) { // if no sorting Method default is date descending
    name = folderName;
    currentSortingMethod = 3;
  }

  /**
   * Description: Adds an email to the ArrayList of emails
   *
   * @param email The email being added to the ArrayList of emails
   */
  public void addEmail(Email email) { // Adds an email to the folder according to the current sorting method.
    emails.add(email);
    if (currentSortingMethod == 0)
      sortBySubjectAscending();
    else if (currentSortingMethod == 1)
      sortBySubjectDescending();
    else if (currentSortingMethod == 2)
      sortByDateAscending();
    else if (currentSortingMethod == 3)
      sortByDateDescending();
  }

  /**
   * Description: Removes an email given the index for the ArrayList
   *
   * @param index
   *
   * @return Returns the Email object removed
   *
   */
  public Email removeEmail(int index) { // Removes an email from the folder by index.
    return emails.remove(index);
  }

  /**
   * Description: Retrieves the name of the folder
   *
   * @return returns the name of the folder
   */
  public String getName() {
    return name;
  }

  /**
   * Description: Retrieves an Email object given an index for the ArrayList
   *
   * @param index The position to return an Email
   * @return The Email object from the ArrayList
   *
   */
  public Email getEmail(int index) {
    return emails.get(index);
  }

  /**
   * Description: Retrieves the size of the ArrayList
   *
   * @return Returns the size of the ArrayList of emails
   *
   */
  public int size() {
    return emails.size();
  }

  /**
   * Description: Returns the index of the passed Email
   *
   * @param email The email object passed to retrieve the index
   * @return returns the index of the email if it is found, if it's not it returns
   *         -1
   */
  public int getEmail(Email email) {
    for (int i = 0; i < emails.size(); i++) {
      if (emails.get(i).equals(email))
        return i;
    }
    return -1;
  }

  /**
   * Description: Returns the current sorting method that is being used to sort
   * the Email objects in the ArrayList of the Folder class
   *
   * @return The current sorting method being used
   */
  public String getCurrentSortingMethod() {
    return sortingMethods[currentSortingMethod];
  }

  /**
   * Description: The overwritten toString() of the Folder class
   *
   * @return Returns the name of the Folder class
   */
  public String toString() {
    return name;
  }

  /**
   * Description: Changes or Sets the 'name' variable
   *
   * @param newName The new 'name' variable
   */
  public void setName(String newName) {
    name = newName;
    System.out.println("Folder name has been set to " + name);
  }

  /**
   * Description: Lists the emails by their subject attribute
   * 
   */
  public void listEmailsBySubject() {
    System.out.println();
    if (emails.size() == 0)
      System.out.println(getName() + " is empty");
    else {
      System.out.println(String.format(formattedResult, "Index |", "Time", "| Subject"));
      System.out.println("---------------------------------------");
      for (int i = 0; i < emails.size(); i++) {
        String minute;
        String ampm;
        if (emails.get(i).getTimeStamp().get(Calendar.MINUTE) < 10)
          minute = "0" + String.valueOf(emails.get(i).getTimeStamp().get(Calendar.MINUTE));
        else
          minute = String.valueOf(emails.get(i).getTimeStamp().get(Calendar.MINUTE));
        if (emails.get(i).getTimeStamp().get(Calendar.AM_PM) == 1)
          ampm = "PM";
        else
          ampm = "AM";
        System.out.println(String.format(formattedResult, "  " + (i + 1) + "   |",
            emails.get(i).getTimeStamp().get(Calendar.HOUR_OF_DAY) + ":" + minute + ampm + " "
                + emails.get(i).getTimeStamp().get(Calendar.MONTH) + "/"
                + emails.get(i).getTimeStamp().get(Calendar.DAY_OF_MONTH) + "/"
                + emails.get(i).getTimeStamp().get(Calendar.YEAR),
            "| " + emails.get(i).getSubject()));
      }
    }
  }

  /**
   * Description: Removes all the contents (Emails) in the Folder
   *
   */
  public void clearFolder() {
    emails.clear();
  }

  /**
   * Description: Sets the curent sorting method of the Folder class
   *
   * @exception IllegalArgumentException if the index is out of bounds
   */
  public void setCurrentSortingMethod() {
    try {
      System.out.println("\nWhich sorting method would you like to pick?\n");
      for (int i = 0; i < sortingMethods.length; i++) {
        System.out.println((i + 1) + " " + sortingMethods[i]);
      }
      int option = input.nextInt() - 1;
      input.nextLine();
      if (option < 0 || option >= sortingMethods.length)
        throw new IllegalArgumentException();
      currentSortingMethod = option;
      if (currentSortingMethod == 0)
        sortBySubjectAscending();
      else if (currentSortingMethod == 1)
        sortBySubjectDescending();
      else if (currentSortingMethod == 2)
        sortByDateAscending();
      else if (currentSortingMethod == 3)
        sortByDateDescending();
      System.out.println("Sorting method has been set to: " + getCurrentSortingMethod());
    } catch (IllegalArgumentException e) {
      System.out.println("That is not a valid option");
    }
  }

  /**
   * Description: Sorts the Folder class by Subject Ascending
   */
  public void sortBySubjectAscending() { // Sorts the emails alphabetically by subject in ascending order.
    Collections.sort(emails, new SortBySubjectAscending());
  }

  /**
   * Description: Sorts the Folder class by Subject Descending
   */

  public void sortBySubjectDescending() { // Sorts the emails alphabetically by subject in descending order.
    Collections.sort(emails, new SortBySubjectAscending().reversed());
  }

  /**
   * Description: Sorts the Folder class by DateAscending
   */

  public void sortByDateAscending() { // Sorts the emails by date in ascending order.
    Collections.sort(emails, new SortByDateAscending());
  }

  /**
   * Description: Sorts the Folder class by Date Descending
   */
  public void sortByDateDescending() { // Sorts the emails by date in descending order.
    Collections.sort(emails, new SortByDateAscending().reversed());
  }

  /**
   * Description: This class represents the Sorting of the Subject Ascending that
   * is passed as a second parameters in the Collections.sort() method
   */
  class SortBySubjectAscending implements Comparator<Email> {
    public int compare(Email a, Email b) {
      return a.getSubject().compareTo(b.getSubject());
    }
  }

  /**
   * Description: This class represents the Sorting of the Date Ascending that is
   * passed as a second parameters in the Collections.sort() method
   */
  class SortByDateAscending implements Comparator<Email> {
    public int compare(Email a, Email b) {
      return a.getTimeStamp().compareTo(b.getTimeStamp());
    }
  }
}
