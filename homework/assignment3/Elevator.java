/**
 * This class represents an elevator object that accepts Requests from the
 * RequestQueue queue
 * 
 * @author Christopher Rosales
 * @ID #114328928
 * @Assignment #3, Elevator
 */
public class Elevator {
  private final int IDLE = 0;
  private final int TO_SOUCE = -1;
  private final int TO_DESTINATION = -2;
  private int elevatorState; // figure what to do with this
  private int currentFloor;
  private Request request;

  /**
   * Description: A constructor that initializes the Elevator object
   */
  public Elevator() {
    request = null;
    elevatorState = 0;
    currentFloor = 1;
  }

  /**
   * Description: Retrieves the state of the elevator
   * 
   * @return Returns the state of the elevator
   */
  public int getElevatorState() {
    return elevatorState;
  }

  /**
   * Description: Retrieves the current floor of the elevator
   *
   * @return Returns the current floor of the elevator
   */
  public int getCurrentFloor() {
    return currentFloor;
  }

  /**
   * Description: Retrieves the current request of the elevator object
   * 
   * @return Returns the request assigned to the elevator object
   */
  public Request getRequest() {
    return request;
  }

  /**
   * Description: Changes the elevator state to idle
   */
  public void changeToIdle() {
    elevatorState = 0;
  }

  /**
   * Description: Changes the elevator state to to_Source
   */
  public void changeToSource() {
    elevatorState = -1;
  }

  /**
   * Description: Changes the elevator state to to_Destination
   */
  public void changeToDestination() {
    elevatorState = -2;
  }

  /**
   * Description: Sets the current floor to an inputted floor
   *
   * @param newFloor The new current floor
   */
  public void setCurrentFloor(int newFloor) {
    currentFloor = newFloor;
  }

  /**
   * Description: Sets the current request to a new request
   *
   * @param newRequest The new current request
   *
   */
  public void setRequest(Request newRequest) {
    request = newRequest;
  }
}
