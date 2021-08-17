import NodeFolder.*;

//IntQueue using Linked lists
public class IntQueueNode implements Cloneable {
  private IntNode front;
  private IntNode rear;

  // IntQueue methods (clone not shown)
  public IntQueueNode() {
    front = null;
    rear = null;
  }

  public boolean isEmpty() {
    return (front == null);
  }

  public void enqueue(int item) {
    IntNode newNode = new IntNode(item);
    if (front == null) { // if it's the first element in the LinkedList
      front = newNode;
      rear = front;
      // because theres only one element in the array
    } else { // if it's not the first element..
      rear.setLink(newNode); // we set the link to the current rear
      rear = newNode; // and set the new rear to the newNode that was enqueued
    }
  }

  public int dequeue() {
    int answer;
    if (front == null) // There's no more elements to dequeue from the list
      throw new RuntimeException("...");
    answer = front.getData(); // we get the number for the element we are dequeueing
    front = front.getLink(); // front is = to the previous element in the queue
    if (front == null) // if that was the last element we just dequeued from the list
      rear = null;
    return answer;
  }
}
