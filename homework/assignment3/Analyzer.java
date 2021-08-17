import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A class that retrieves the inputs for the calls either the OptimalSimulator
 * or the Simulator classes and follows by calling either one of those clasees
 * 
 * @author Christopher Rosales
 * @ID #114328928
 * @Assignment #3, Elevator
 */
public class Analyzer {
  /**
   * Description: Main to get the input from user to call the simulation
   *
   * @param args
   *
   * @throws IllegalArgumentException To check all of the ranges for the variables
   *                                  that are passed in the simulation
   *
   */
  public static void main(String[] args) {
    boolean flag = true;
    Scanner input = new Scanner(System.in);
    try {
      System.out.print("\nPlease enter the probability of arrival for Requests: ");
      double p = input.nextDouble();
      if (p < 0.0 || p > 1.0) {
        System.out.println("The probability must be between 0 and 1!");
        flag = false;
        input.close();
        throw new IllegalArgumentException();
      }
      System.out.print("\nPlease enter the number of floors: ");
      int numFloors = input.nextInt();
      if (numFloors <= 1) {
        System.out.println("The number of floors must be greater than 1!");
        flag = false;
        input.close();
        throw new IllegalArgumentException();
      }
      System.out.print("\nPlease enter the number of elevators: ");
      int numElevators = input.nextInt();
      if (numElevators <= 0) {
        System.out.println("The number of elevators must be greater than 0!");
        flag = false;
        input.close();
        throw new IllegalArgumentException();
      }
      System.out.print("\nPlease enter the length of the simulation (in time units): ");
      int lengthOfSimulation = input.nextInt();
      if (lengthOfSimulation <= 0) {
        System.out.println("The time length of the simulation (in seconds) must be greater than 0!");
        flag = false;
        input.close();
        throw new IllegalArgumentException();
      }
      Simulator.simulate(p, numFloors, numElevators, lengthOfSimulation);
      input.close();
    } catch (InputMismatchException | IllegalArgumentException e) {
      if (flag)
        System.out.println("Input must either be a double or an int");
    }
  }
}
