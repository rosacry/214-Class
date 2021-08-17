class LinkedList {

  static Node head;

  static class Node {

    int data;
    Node next;

    Node(int d) {
      data = d;
      next = null;
    }
  }

  /* Function to reverse the linked list */
  Node reverse(Node node) {
    Node prev = null;
    Node current = node;
    Node next = null;
    while (current != null) {
      next = current.next; // we store the next value so we can traverse to the next node in the linked
                           // list we set current.next equal to a variable because we are changing it
      current.next = prev; // setting the link to the previous node
      prev = current; // store this value so the next node will be linked to the prev
      current = next; // go to the next node
    }
    node = prev;
    return node;
  }

  // prints content of double linked list
  void printList(Node node) {
    while (node != null) {
      System.out.print(node.data + " ");
      node = node.next;
    }
  }

  // Driver Code
  public static void main(String[] args) {
    LinkedList list = new LinkedList();
    list.head = new Node(85);
    list.head.next = new Node(15);
    list.head.next.next = new Node(4);
    list.head.next.next.next = new Node(20);

    System.out.println("Given Linked list");
    list.printList(head);
    head = list.reverse(head);
    System.out.println("");
    System.out.println("Reversed linked list ");
    list.printList(head);
  }
}
