//Changes made to the program:
//extended IntList to allow the head variable to be accessed from IntList
//Questions for this class
//1. for the add method, why aren't we calling the addIntAfter method instead of the addNewHead method?
//this would be a child class, comparent to the parent classes IntNode and IntList
public class IntLinkedBag implements Cloneable {
  private IntList data;
  private int manyItems;

  public IntLinkedBag() {
    manyItems = 0;
    data = new IntList();
    // No need to worry about the bag’s capacity
    // since a linked list has no maximum
    // capacity! Only one constructor is needed.
  }

  public int getCapacity() { // linked lists have no max in this situation
    return Integer.MAX_VALUE;
  }

  public int size() {
    return manyItems;
  }

  public void ensureCapacity(int minimumCapacity) {
    // no work needed lol
  }

  public void add(int element) {// this is interesting, because we are adding at the end of the left side, not
                                // the right side
                                // i'm not sure why we don't call the addIntAfter method..?
    data.addNewHead(element);
    manyItems++;
  }

  public int countOccurrences(int target) { // counts the occurences up to the length of the LinkedList
    int answer = 0;
    data.resetCursor();// the problem with this is that if we reset the cursor, we could have in a spot
                       // where we don't want it to be
    // I'd rather make a temp node to count instead
    for (int index = 0; index < manyItems; index++) {
      if (target == data.getNodeData()) // compare to the data not the object
        answer++;
      data.advanceCursor();
    }
    return answer;
  }

  public boolean remove(int target) {
    IntNode nodePtr = data.head;// mistake on the pdf should be data.head
    IntNode prevPtr = null;
    while (nodePtr != null && nodePtr.getData() != target) { // nodePtr != null would only be false if the element we
                                                             // are trying to remove is not in the LinkedList
      prevPtr = nodePtr;
      nodePtr = nodePtr.getLink();
    }
    if (nodePtr != null)// if we found the target and there is a target that == nodePtr.getData(); ....
      prevPtr.setLink(nodePtr.getLink());// we set the previous pointer to the link of the node that we are removing
    return (nodePtr != null);
  }

}
