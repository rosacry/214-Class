import java.text.DecimalFormat;

/**
 * This class represents the simulation of the elevator probability of requests
 * 
 * @author Christopher Rosales
 * @ID #114328928
 * @Assignment #3, Elevator
 */

public class Simulator {
  private static DecimalFormat df = new DecimalFormat("#.00");

  /**
   * Description: The Simulate method that carries out the simulation
   *
   * @param p                  The probability of a request being made in a second
   * @param numFloors          The number of total floors there are in the
   *                           building
   * @param numElevators       The number of elevators there are in the building
   * @param lengthOfSimulation The total length of the simulation
   */
  public static void simulate(double p, int numFloors, int numElevators, int lengthOfSimulation) {
    int totalWaitTime = 0;
    int totalRequests = 0;
    RequestQueue queue = new RequestQueue();
    Elevator[] elevators = new Elevator[numElevators];
    BooleanSource arrival = new BooleanSource(p);
    for (int i = 0; i < elevators.length; i++) {
      elevators[i] = new Elevator();
    }
    for (int seconds = 0; seconds < lengthOfSimulation; seconds++) {
      if (arrival.requestArrived()) {
        Request request = new Request(numFloors); // passenger
        request.changeTimeEntered(seconds);
        queue.enqueue(request);
      }
      for (int i = 0; i < elevators.length; i++) {
        if (elevators[i].getElevatorState() == 0) { // if idle
          if (!queue.isEmpty()) {
            elevators[i].setRequest(queue.dequeue());
            elevators[i].changeToSource();
          }
        }
        if (elevators[i].getElevatorState() == -1) { // the elevator is on its way to source
          if (elevators[i].getCurrentFloor() == elevators[i].getRequest().getSourceFloor()) {
            elevators[i].changeToDestination();
            totalWaitTime += seconds - elevators[i].getRequest().getTimeEntered();
            totalRequests++;
          } else {
            // we change elevators current floor + 1 or -1 closer to source
            if (elevators[i].getCurrentFloor() < elevators[i].getRequest().getSourceFloor())
              elevators[i].setCurrentFloor(elevators[i].getCurrentFloor() + 1);
            else
              elevators[i].setCurrentFloor(elevators[i].getCurrentFloor() - 1);
          }
        }
        if (elevators[i].getElevatorState() == -2) { // the elevator is on its way from source to
                                                     // destination
          if (elevators[i].getCurrentFloor() < elevators[i].getRequest().getDestinationFloor())
            elevators[i].setCurrentFloor(elevators[i].getCurrentFloor() + 1);
          else if (elevators[i].getCurrentFloor() > elevators[i].getRequest().getDestinationFloor())
            elevators[i].setCurrentFloor(elevators[i].getCurrentFloor() - 1);
          if (elevators[i].getCurrentFloor() == elevators[i].getRequest().getDestinationFloor()) {
            elevators[i].changeToIdle();
            elevators[i].setRequest(null);
          }
        }
      }
    }
    System.out.println("Total Wait Time: " + totalWaitTime);
    System.out.println("Total Requests: " + totalRequests);
    if (totalWaitTime == 0 && totalRequests == 0)
      System.out.println("Average Wait Time: No Requests were taken in (Division By Zero)");
    else
      System.out.println("Average Wait Time: " + df.format((double) totalWaitTime / totalRequests));
  }
}
