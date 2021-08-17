public class List {
  private Node head;
  private Node tail;
  private Node cursor;

  public void addNewHead(Object element) {
    Node newNode = new Node(element);
    newNode.setLink(head);
    head = newNode;
    if (tail == null)
      tail = head;
    cursor = head;
  }

  public boolean listSearch(Object target) {
    Node nodePtr = head;
    while (nodePtr != null) {
      if (target.equals(nodePtr.getData())) {
        cursor = nodePtr;
        return true;
      }
      nodePtr = nodePtr.getLink();
    }
    return false;
  }

}
