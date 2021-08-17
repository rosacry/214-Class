import java.lang.Math;

/**
 * This class creates a request object for the Request queue
 * 
 * @author Christopher Rosales
 * @ID #114328928
 * @Assignment #3, Elevator
 */
public class Request {
  private int sourceFloor;
  private int destinationFloor;
  private int timeEntered; // the time that this request was placed on the queue
  private int numberOfFloors;

  /**
   * Description: The constructor the initialzes Request as an object
   *
   * @param numFloors The max number of floors for the elevator simulator to know
   *                  the range for the random generation
   *
   */
  public Request(int numFloors) {
    numberOfFloors = numFloors;
    sourceFloor = (int) (Math.random() * numFloors) + 1;
    destinationFloor = (int) (Math.random() * numFloors) + 1;
  }

  /**
   * Description: Retrieves the destination floor of the request
   *
   * @return Returns the destination floor of the request
   */
  public int getDestinationFloor() {
    return destinationFloor;
  }

  /**
   * Description: Retrieves the Source floor of the request
   * 
   * @return Returns the source floor of the request
   */
  public int getSourceFloor() {
    return sourceFloor;
  }

  /**
   * Description: Retrieves the time that this request was placed on the queue
   * (after setting it first)
   *
   * @return returns the time entered when it was placed on the queue
   */
  public int getTimeEntered() {
    return timeEntered;
  }

  /**
   * Description: Retrieves the number of floors in the simulation
   * 
   * @return returns the number of floors for the simulation
   */
  public int getNumberOfFloors() {
    return numberOfFloors;
  }

  /**
   * Description: Method for knowing if the passenger is going up or down
   *
   * @return True if passenger is going down, false if passenger is going up
   */
  public boolean passengerGoingDown() {
    if (sourceFloor > destinationFloor)
      return true;
    return false;
  }

  /**
   * Description: Changes the destination floor for the request
   *
   * @param newDestinationFloor The new Destination floor for the request
   *
   * 
   */
  public void changeDestinationFloor(int newDestinationFloor) {
    destinationFloor = newDestinationFloor;
  }

  /**
   * Description: Changes the source floor for the request
   *
   * @param newSourceFloor The new source floor for the request
   */
  public void changeSourceFloor(int newSourceFloor) {
    sourceFloor = newSourceFloor;
  }

  /**
   * Description: Changes or sets the time entered for the request
   *
   * @param newTimeEntered The new time entered for the request
   */
  public void changeTimeEntered(int newTimeEntered) {
    timeEntered = newTimeEntered;
  }

  /**
   * Description: Changes the number of floors entered for the request
   *
   * @param newNumberOfFloors The new number of floors for the request
   */
  public void changeNumberOfFloors(int newNumberOfFloors) {
    numberOfFloors = newNumberOfFloors;
  }
}
