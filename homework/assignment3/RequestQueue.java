import java.util.ArrayList;

/**
 * This class creates a queue data structor while extending the ArrayList class
 * to implement its methods
 * 
 * @author Christopher Rosales
 * @ID #114328928
 * @Assignment #3, Elevator
 */
public class RequestQueue extends ArrayList<Request> {

  /**
   * Description: An empty constructor that initializes the RequestQueue queue
   */
  public RequestQueue() {
  }

  /**
   * Description: Enqueues a Request object into the RequestQueue queue
   *
   * @param item The item that is being inserted into the queue
   */
  public void enqueue(Request item) { // O(1)
    this.add(item);
  }

  /**
   * Description: Dequeues a Request object out of the RequestQueue queue
   *
   * @return The request being dequeued
   */
  public Request dequeue() { // O(n)
    return this.remove(0);
  }
}
