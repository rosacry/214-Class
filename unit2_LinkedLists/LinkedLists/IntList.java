//Changes made to the program:
//changed all private variables to protected in order for IntLinkedBag to access the head variable
//Questions on this class

public class IntList {
  protected IntNode head; // head references the first node in a list
  protected IntNode tail; // tail references the last node in a list
  protected IntNode cursor; // cursor is a link that points to one of the nodes of the list

  // IntList methods
  public IntList() { // Empty Constructor... initializes the IntList
    head = null;
    tail = null;
    cursor = null;
  }

  public void addNewHead(int element) {// adding a new head of list
    IntNode newNode = new IntNode(element); // we initialize a new node
    newNode.setLink(head);
    head = newNode; // we set the created node (newNode) = to the head of the LinkedList
    if (tail == null)// if there is no tail defined (meaning this is our first entry in the linked
                     // list) we set the tail the same as the head
      tail = head;
    cursor = head; // resetting the cursor to the first node in the list
  }

  public void addIntAfter(int element) {// Adding an integer after cursor
    IntNode newNode = new IntNode(element); // initializing a new node
    if (cursor == null) {// The only time cursor will = null is when there are no elements in the linked
                         // list
      head = newNode;
      tail = newNode;
      cursor = newNode;
    } else {// if there already is an element in the LinkedList before the method was
            // called.. then
      newNode.setLink(cursor.getLink());// so we are inserting this node right after cursor, before we do that.. we need
                                        // to know the original link that the cursor was pointing to, we set that link
                                        // to the newNodes link and then set the cursors new link to the newNode that
                                        // was just inserted
      cursor.setLink(newNode);
      cursor = newNode;// advance the cursor to the newNode created
      if (cursor.getLink() == null)// the only time cursor.getlink can = null is when the cursor is = to the tail
        tail = cursor;
    }
  }

  public void removeIntAfter() {// remove after cursor
    if (cursor != tail) {// checking if there is a node after cursor
      cursor.setLink(cursor.getLink().getLink());// we set the link of the cursor to the deleted nodes link
      if (cursor.getLink() == null)// if the deleted node was the last node in the linked list, then the cursor
                                   // must = the tail
        tail = cursor; // last node
    }
  }

  public void removeHead() { // removing the head of list
    if (head != null)// we set the head equal to what its link is to push the head forward
      head = head.getLink();
    if (head == null)// this means that there are no elements in the list
      tail = null;
    cursor = head;
  }

  public boolean advanceCursor() { // working with the cursor
    if (cursor != tail) {// we cannot move forward if the cursor is = to the tail
      cursor = cursor.getLink();
      return true;
    } else
      return false;
  }

  public void resetCursor() {
    cursor = head; // self explanatory
  }

  public boolean isEmpty() {
    return (cursor == null);
  }

  public int listLength() {
    IntNode nodePtr = head;// we made a temp called nodePtr so we don't move head
    int answer = 0;
    while (nodePtr != null) {
      answer++;
      nodePtr = nodePtr.getLink();
    }
    return answer;
  }

  public boolean listSearch(int target) {
    IntNode nodePtr = head; // create a temp so we don't change head
    while (nodePtr != null) {
      if (target == nodePtr.getData()) {// we do getData() because without it, we would be referencing the object
        cursor = nodePtr; // we return true but we also set our cursor to the target
        return true;
      }
      nodePtr = nodePtr.getLink(); // advancing forward
    }
    return false;

  }

  public boolean listPosition(int position) {// instead of searching by the data of the node we search by its position
                                             // instead
    IntNode nodePtr = head;
    int i = 1;// why isn't this 0 based
    if (position <= 0)
      throw new RuntimeException("..");
    while (i < position && nodePtr != null) {
      nodePtr = nodePtr.getLink();
      i++;
    }
    if (nodePtr != null)
      cursor = nodePtr;
    return (nodePtr != null);
  }

  public static IntList listCopy(IntList source) { // This would be something like,
                                                   // IntList other = IntList.listCopy(original); i think
    IntList newList = new IntList();
    IntNode nodePtr = source.head; // we start at the head
    while (nodePtr != null) { // until nodePtr == no object
      newList.addIntAfter(nodePtr.getData()); // we are adding each node one by one in this while loop
      nodePtr = nodePtr.getLink(); // and then moving forward
    }
    return newList;
  }

  public int getNodeData() throws IllegalStateException { // where the cursor falls
    if (cursor == null) {
      throw new IllegalStateException("...");
    }
    return (cursor.getData());
  }

  public void getNodePositionOneBack(int target) { // retrieves the first occurences of a nodes position
    IntNode nodePtr = head;
    if (!isEmpty() && listSearch(target)) {
      while (target != nodePtr.getLink().getData()) {
        nodePtr = nodePtr.getLink();
      }
    } else {
      throw new RuntimeException("List is either empty or the target is not in the list!");
    }
    cursor = nodePtr;
  }

  public void setNodeData(int element) throws IllegalStateException {// changing what the data the cursor is equal to
    if (cursor == null)
      throw new IllegalStateException("...");
    cursor.setData(element);
  }

  // two things: to delete node we use the remove method... and check the remove
  // method for length of 1 and 2
  // also we need to account for removing the last node and/or the head in the
  // linked list
  // lets try a different approach
  public boolean removeIntegrated(int item) { // smarter method of remove method
    if (!isEmpty()) { // if the list is not empty and the node is in the list
      if (listSearch(item)) {
        resetCursor(); // to make sure the cursor starts at the head
        if (head.getData() == item) { // check if the head is the one we need to remove
          removeHead();
          return true;
        }
        getNodePositionOneBack(item); // now cursor is = to the node before the target
        cursor.setLink(cursor.getLink().getLink()); // skipping item to remove to remove it from list
        return true;
      }
      System.out.println("Item is not in the list!");
      return false;
    }
    System.out.println("List is empty!");
    return false; // either the node is not in the list or the list is empty
  }
}
