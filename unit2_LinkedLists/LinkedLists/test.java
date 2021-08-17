//this is to test the remove method
public class test {
  public static void main(String[] args) {
    SortedIntList test = new SortedIntList();
    // removing the elements in a random order
    test.remove(1);
    for (int i = 1; i <= 10; i++) {
      test.addIntAfter(i);
      test.advanceCursor();
    }
    test.remove(11);
    test.remove(2);
    test.remove(10);
    test.remove(7);
    test.remove(1);
    test.resetCursor();
    for (int i = 0; i < test.listLength(); i++) {
      System.out.print(test.getNodeData() + " ");
      test.advanceCursor();
    }
    // removing the last element
    System.out.println();
    SortedIntList test2 = new SortedIntList();
    test2.addIntAfter(1);
    System.out.println(test2.getNodeData());
    // test2.remove(1);
    // System.out.println(test2.getNodeData());

    // sorting a list node
    SortedIntList test3 = new SortedIntList();
    for (int i = 10; i > 0; i--) {
      test3.addIntAfter(i);
      test3.advanceCursor();
    }
    test3.resetCursor();
    IntList.listCopy(test3);
    System.out.print("SortedIntList: ");
    for (int i = 0; i < test3.listLength(); i++) {
      System.out.print(test3.getNodeData() + " ");
      test3.advanceCursor();
    }
  }
}
