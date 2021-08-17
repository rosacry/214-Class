public class Node {
  private Object data;
  private Node link;

  // Node methods
  public Node(Object initialData) {
    data = initialData;
    link = null;
  }
  // as for using the constructor
  // Integer intObject = new Integer(214);
  // Node newNode = new Node(intObject);

  public Object getData() {
    return data;
  }
  // as for using the accessor:
  // Integer I =
  // (Integer)newNode.getData();
  // System.out.println(I.intValue());

  public void setData(Object newData) {
    data = newData;
  }
  // as for using the mutator
  // Integer J = new Integer(220);
  // newNode.setData(J);
}
