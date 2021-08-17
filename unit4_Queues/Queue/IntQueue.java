//IntQueue Using Arrays:
//questions on this class
//1. for rear = (rear+1) % CAPACITY , why do we need to do mod C21jkjAPACITY?
//same for dequeue method
public class IntQueue implements Cloneable {
  public final int CAPACITY = 100; // exclusive value, 0-99 inclusive
  private int[] data;
  private int front; // length -1
  private int rear; // ar[0]

  // IntQueue methods (clone not shown)
  public IntQueue() {
    front = -1;
    rear = -1;
    data = new int[CAPACITY];
  }

  public boolean isEmpty() {
    return (front == -1);
  }

  public void enqueue(int item) { // insert from the rear
    if ((rear + 1) % CAPACITY == front) // if rear == 99 + 1 mod CAPACITY, that will be 0, front is = 0 (it never
                                        // changes)
      throw new RuntimeException("FullQueueException");
    if (front == -1) { // isEmpty() - if this is the first element in the queue
      front = 0;
      rear = 0;
    } else
      rear = (rear + 1) % CAPACITY; // we're doing rear += 1
    data[rear] = item;
  }

  public int dequeue() { // we dequeue from the front
    int answer;
    if (front == -1) // isEmpty
      throw new RuntimeException("EmptQueueException");
    answer = data[front];
    if (front == rear) { // if it's the only element in the queue
      front = -1;
      rear = -1;
    } else
      front = (front + 1) % CAPACITY;
    return answer;
  } // this is like a pop method, we pop from the front
}
