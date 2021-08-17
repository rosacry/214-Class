import NodeFolder.*;

public class NodeStack implements Cloneable {
  private IntNode top;

  // IntStack methods (clone not shown :( )
  public NodeStack() {
    top = null;
  }

  public boolean isEmpty() {
    return (top == null);
  }

  public void push(int item) {
    IntNode newNode = new IntNode(item);
    newNode.setLink(top);
    top = newNode;
  }

  // stakc overfloww?!?!?!
  public int pop() {
    int answer;
    if (top == null)// isEmpty()
      throw new RuntimeException("...");
    answer = top.getData();
    top = top.getLink();
    return answer;
  }

  public int peek() {
    int answer;
    if (top == null) // isEmpty()
      throw new RuntimeException("...");
    answer = top.getData();
    return answer;
  }
}
