//this class was for the midterm practice 
public class SortedIntList extends IntList {

  public static IntList sortIntListMain(IntList list) {
    IntList newList = IntList.listCopy(list);
    int i = 1;
    while (!list.isEmpty()) {
      int max = list.getNodeData();
      while (i <= list.listLength()) {
        if (list.getNodeData() > max)
          max = list.getNodeData();
        list.advanceCursor();
        i++;
      }
      // once we find the max..
      newList.addIntAfter(max); // if this doesn't work change max to max.getData();
      list.removeIntegrated(max);
    }
    return newList;
  }

  // this one is my personal answer
  public boolean remove(int item) { // my brute force method of removing a node given
    // Removes the item from the sorted list, if it exists, and return true.
    // Otherwise, return false
    if (!(head == null || tail == null)) { // this checks if the list is empty
      if (head.getData() == item) { // need statement for head because we are checking for node one behind the
                                    // target to set THAT nodes link to the node one ahead of the target
        if (head.getLink() == null) { // meaning the only element in the list
          head = null;
          tail = null;
        }
        head = head.getLink();
        return true;
      }
      IntNode nodePtr = head;
      while (item != nodePtr.getLink().getData()) {
        if (nodePtr.getLink().getLink() == null) { // this checks if we hit the end of the list without finding an
                                                   // occurence of the item
          System.out.println("Item not in list!");
          return false;
        }
        nodePtr = nodePtr.getLink();
      }
      nodePtr.setLink(nodePtr.getLink().getLink());
      return true;
    }
    System.out.println("List is empty!");
    return false;
  }

  // Questions for this method
  // why is all of the booleans returned in parenthesis
  // why is cursor being the first thing to define if we're not using it until we
  // know that head != item
  public boolean removeKey(int item) { // Answer key for the midterm method
    IntNode cursor; // to save a small amount of time it would be better to define cursor after
                    // knowing that head is not the item
    if (head == null) // if the list is empty..
      return (false); // why is this in parenthesis
    else if (head == tail) { // this means that there's only one item in the list
      if (head.getData() == item) { // if the item = head
        head = null;
        tail = null;
        return (true);
      } else
        return (false);
    } else { // 2 or more nodes in list
      if (head.getData() == item) {
        head = head.getLink();
        return (true);
      }
      cursor = head;
      while (cursor.getLink() != null) {
        if (cursor.getLink().getData() == item) {
          cursor.setLink(cursor.getLink().getLink());
          if (cursor.getLink() == null)
            tail = cursor;
          return (true);
        } else if (cursor.getLink().getData() < item)
          return (false); // can't be there
        else
          cursor = cursor.getLink();
      }
      return (false); // not found in entire list
    }
  }

  public int maximum() throws Exception {
    if (head == null)
      throw new Exception("list is empty");
    else
      return (head.getData());
  }
}
