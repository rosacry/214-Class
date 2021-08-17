import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * This class represents an Email message object that is added to the inbox
 * folder unless specified otherwise
 * 
 * @author Christopher Rosales
 * @ID #114328928
 * @Assignment #5, Mailbox simulation
 */

public class Email implements Serializable {
  private String to;
  private String cc;
  private String bcc;
  private String subject;
  private String body;
  private GregorianCalendar timestamp = new GregorianCalendar();

  /**
   * Description: Email constructor that initialzes the Email object
   *
   * @param messageTo    the String of whom will receive the email
   *
   * @param messageCopy  the String of which party the will reveive the email
   * @param messageBlind the String of which party of recipiant will be ignored
   *                     from the outgoing email
   * @param subjectLine  the String subject of the email
   * @param messageBody  the String of the body of the email
   * @param time         the time when the email was sent
   * 
   */
  public Email(String messageTo, String messageCopy, String messageBlind, String subjectLine, String messageBody) {
    to = messageTo;
    cc = messageCopy;
    bcc = messageBlind;
    subject = subjectLine;
    body = messageBody;
  }

  /**
   * Description: Retrieves the 'to' variable of the email class
   *
   * @return returns the 'to' in the email
   *
   */
  public String getTo() {
    return to;
  }

  /**
   * Description: Retrieves the carbon copy variable of the email class
   *
   *
   * @return returns the carbon copy variable of the email class
   */
  public String getCC() {
    return cc;
  }

  /**
   * Description: Retrieves the blind carbon copy variable of the email class
   *
   * @return returns the blind carbon copy variable of the email class
   */
  public String getBCC() {
    return bcc;
  }

  /**
   * Description: Retrieves the subject line variable of the email class
   *
   * @return returns the subject variable of the email class
   *
   */
  public String getSubject() {
    return subject;
  }

  /**
   * Description: Retrieves the body variable of the email class
   *
   * @return returns the body variable of the email class
   */
  public String getBody() {
    return body;
  }

  /**
   * Description: Retrieves the timestamp of when the email was sent
   *
   * @return returns the timestamp of when the email was sent
   * 
   */
  public GregorianCalendar getTimeStamp() {
    return timestamp;
  }

  /**
   * Description: Changes or sets the 'to' variable
   *
   * @param newTo The new 'to' variable
   */
  public void setTo(String newTo) {
    to = newTo;
  }

  /**
   * Description: Changes or sets the 'cc' variable
   *
   * @param newCC The new 'cc' variable
   */
  public void setCC(String newCC) {
    cc = newCC;
  }

  /**
   * Description: Changes or sets the 'bcc' variable
   *
   * @param newBCC The new 'bcc' variable
   */
  public void setBCC(String newBCC) {
    bcc = newBCC;
  }

  /**
   * Description: Changes or sets the 'subject' variable
   *
   * @param newSubject The new 'subject' variable
   */
  public void setSubject(String newSubject) {
    subject = newSubject;
  }

  /**
   * Description: Description
   *
   * @param newBody The new 'body' variable
   */
  public void setBody(String newBody) {
    body = newBody;
  }

  /**
   * Description: Changes or sets the 'timestamp' variable
   *
   * @param newTimeStamp The new 'timestamp' variable
   */
  public void setTimeStamp(GregorianCalendar newTimeStamp) {
    timestamp = newTimeStamp;
  }

  /**
   * Description: The overwritten of the toString() method
   *
   * @return The custom toString of the Email object class
   */
  public String toString() {
    return "\nTo: " + to + "\nCC: " + cc + "\nBCC: " + bcc + "\nSubject: " + subject + "\n" + body;
  }
}
