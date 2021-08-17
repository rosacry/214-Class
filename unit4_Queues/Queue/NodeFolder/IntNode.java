package NodeFolder;

public class IntNode {
  private int data;
  private IntNode link;

  // IntNode methods
  public IntNode(int initialData) {// Constructor - Because this is a class for one node.. you can only pass one
                                   // set of data
    data = initialData;
    link = null; // we don't have a link yet because we don't know what the link is pointing to
                 // for its next reference
  }

  // Accessors
  public int getData() { // returns data
    return data;
  }

  public IntNode getLink() { // returns link
    return link;
  }

  // Mutators
  public void setData(int newData) { // sets the data to something else
    data = newData;
  }

  public void setLink(IntNode newLink) { // sets the link to something else
    link = newLink;
  }
}
