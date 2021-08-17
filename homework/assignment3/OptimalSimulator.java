import java.text.DecimalFormat;

/**
 * This class represents the simulation of the elevator probability of requests
 * (extra credit)
 * 
 * @author Christopher Rosales
 * @ID #114328928
 * @Assignment #3, Elevator
 */

public class OptimalSimulator {
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
    BooleanSource arrival = new BooleanSource(p);
    OptimalElevator[] elevators = new OptimalElevator[numElevators];
    for (int i = 0; i < elevators.length; i++) {
      elevators[i] = new OptimalElevator();
    }
    for (int seconds = 0; seconds < lengthOfSimulation; seconds++) {
      if (arrival.requestArrived()) {
        Request request = new Request(numFloors);
        queue.enqueue(request);
      }
      for (int i = 0; i < elevators.length; i++) {
        if (elevators[i].getElevatorState().equals("IDLE")) {
          if (!queue.isEmpty()) {
            elevators[i].setRequest(queue.dequeue());
            elevators[i].getRequest().changeTimeEntered(seconds);
            elevators[i].changeToSource();
          }
        }
        if (elevators[i].getElevatorState().equals("TO_SOURCE")) { // the elevator is on its way to source
          if (elevators[i].getCurrentFloor() == elevators[i].getRequest().getSourceFloor()) {
            elevators[i].changeToDestination();
            totalWaitTime += seconds - elevators[i].getRequest().getTimeEntered();
            totalRequests++;
          } else {
            // we change elevators current floor + 1 or -1 closer to source
            if (elevators[i].getCurrentFloor() < elevators[i].getRequest().getSourceFloor())
              elevators[i].setCurrentFloor(elevators[i].getCurrentFloor() + 1);
            else // we need to also check if there's a passenger on floor+1
              elevators[i].setCurrentFloor(elevators[i].getCurrentFloor() - 1);
          }
        }
        if (elevators[i].getElevatorState().equals("TO_DESTINATION")) { // the elevator is on its way from source to
                                                                        // destination
          if (elevators[i].getCurrentFloor() == elevators[i].getRequest().getDestinationFloor()) {
            if (elevators[i].isEmpty()) {
              elevators[i].changeToIdle();
              elevators[i].setRequest(null);
            } else {
              // if it's not empty that means we can dequeue
              elevators[i].setRequest(elevators[i].dequeue());
              elevators[i].getRequest().changeTimeEntered(seconds);
              elevators[i].changeToSource(); // we go to source right away
            }
          } else {
            if (elevators[i].getCurrentFloor() < elevators[i].getRequest().getDestinationFloor()) {
              elevators[i].setCurrentFloor(elevators[i].getCurrentFloor() + 1);
              // left off here
            } else
              elevators[i].setCurrentFloor(elevators[i].getCurrentFloor() - 1);
            elevators[i].pickupPassengers();
          }
        }
      }
    }
    System.out.println("Total Wait Time: " + totalWaitTime);
    System.out.println("Total Requests: " + totalRequests);
    System.out.println("Average Wait Time: " + df.format((double) totalWaitTime / totalRequests));
  }
}
