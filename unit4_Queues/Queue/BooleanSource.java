//Questions for this class:
public class BooleanSource {
  private double probability;

  public BooleanSource(double p) { // We have a constructor to dictate what probability is
    if (p < 0.0 || p > 1.0)
      throw new RuntimeException("p is not in range");
    probability = p;
  }

  public boolean occurs() { // returns the probably of this method returning true or false (you enter in the
                            // probability)
    return (Math.random() < probability);
    // this is guaranteed to not throw an error
  }

  // washTime = the time it takes for a car to be washed
  // arrivalProb = the probability (0.0-1.0) for the car to arrive
  // totalTime is how long you want the time to run for
  public static void carWash(int washTime, double arrivalProb, int totalTime) { // car wash probability simulator
    if (washTime <= 0 || arrivalProb < 0.0 || arrivalProb > 1.0 || totalTime < 0) { // if any of the variables are out
                                                                                    // of the range for the specified
                                                                                    // order, we cannot complete the
                                                                                    // simulation
      System.out.println("No Simulation");
      return;
    }
    // simulation variables
    IntQueue cars = new IntQueue();
    BooleanSource arrival = new BooleanSource(arrivalProb);
    int totalWaitTime = 0;
    int carsWashed = 0;
    int timeLeftInWasher = 0;
    double avgWaitTime;
    // loop simulates each second of time
    for (int currentSecond = 1; currentSecond <= totalTime; currentSecond++) {
      // Event 1: has a car arrived?
      if (arrival.occurs()) // we take in the probability of a car arriving based on what the input was
        cars.enqueue(currentSecond); // example: if arrivalProb was .25 there's a 1 in 4 chance of this statement
                                     // occuring
      // Event 2: can we take a car off the queue
      // and put it in the car wash?
      if ((timeLeftInWasher == 0) && (!cars.isEmpty())) { // this means the car that is first in queue is ready to be
                                                          // washed
        timeLeftInWasher = washTime;// reset the timeLeftInWasher variable to the washTime input
        totalWaitTime += (currentSecond - cars.dequeue()); // incrementing the total wait time
        carsWashed++; // and the carsWashed value to average it out at the end
      }
      // Event 3: wash a car for 1 second
      if (timeLeftInWasher > 0) // if the first two if statements do not work, then we decrement the
                                // timeLeftInWasher by -=1
        timeLeftInWasher--; // the assumption made by this is that a car my arrive to the car wash every
                            // single second
    } // end for loop
    // calculated final statistics
    avgWaitTime = (double) totalWaitTime / carsWashed;
    System.out.println("Avg wait time = " + avgWaitTime + " seconds.");
  }
}
