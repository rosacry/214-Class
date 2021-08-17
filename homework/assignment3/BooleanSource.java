/**
 * This class creates a probability object with a method that returns true if
 * the random value 0-1 is in range of the probability
 * 
 * @author Christopher Rosales
 * @ID #114328928
 * @Assignment #3, Elevator
 */
public class BooleanSource {
  private double probability;

  /**
   * Description: A constructor that initializes the BooleanSource object
   *
   * @param p The probability being entered from 0-p
   *
   */
  public BooleanSource(double p) {
    probability = p;
  }

  /**
   * Description: The boolean value of a probability of a request being
   * made/created
   *
   * @return True of the random value is within range of the probability, false if
   *         it is not
   */
  public boolean requestArrived() {
    return (Math.random() < probability);
  }
}
