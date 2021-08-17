/**
 * This class represents an elevator object that accepts Requests from the
 * RequestQueue queue
 * 
 * @author Christopher Rosales
 * @ID #114328928
 * @Assignment #3, Elevator
 */
public class OptimalElevator extends RequestQueue {
  private final String IDLE = "IDLE";
  private final String TO_SOUCE = "TO_SOURCE";
  private final String TO_DESTINATION = "TO_DESTINATION";
  private String elevatorState; // figure what to do with this
  private int currentFloor;
  private Request request;

  /**
   * Description: A constructor that initializes the Elevator object
   */
  public OptimalElevator() {
    request = null;
    elevatorState = IDLE;
    currentFloor = 1;
  }

  /**
   * Description: Retrieves the state of the elevator
   * 
   * @return Returns the state of the elevator
   */
  public String getElevatorState() {
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

  public void pickupPassengers() {
    for (int i = 0; i < this.size(); i++) { // loop through the queue
      if ((this.get(i).passengerGoingDown() && this.getRequest().passengerGoingDown())
          && this.get(i).getSourceFloor() == this.getCurrentFloor()) {
        // then we pick up the passengers that are only going down
      
      } else if (!(this.get(i).passengerGoingDown() && this.getRequest().passengerGoingDown())
          && this.get(i).getSourceFloor() == this.getCurrentFloor()) {
        // then we pick up the passengers that are only going up
      }
    }
  }

  public boolean elevatorGoingDown() {
    if (getRequest().passengerGoingDown())
      return true;
    return false;
  }

  /**
   * Description: Changes the elevator state to idle
   */
  public void changeToIdle() {
    elevatorState = IDLE;
  }

  /**
   * Description: Changes the elevator state to to_Source
   */
  public void changeToSource() {
    elevatorState = TO_SOUCE;
  }

  /**
   * Description: Changes the elevator state to to_Destination
   */
  public void changeToDestination() {
    elevatorState = TO_DESTINATION;
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
