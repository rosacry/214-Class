//Questions for this assignment
//1. For the ensureCapacity method, why is minimumCapacity passed but not named to maximumCapacity?
//2. For the add method, why don't we add just one to the maximumCapacity instead of multiplying the length * 2 + 1? Wasn't the point of this example to minimize memory?
//
//
//Bag: A collection of items of the same type
//A specific item may appear any number of times in a bag.
//A bag is not a set.
//A bag is not ordered.
//The items of a bag will be stored in an array (for now).
//A bag may have limited size due to memory constraints.
public class IntArrayBag implements Cloneable {
  private int[] data;// array itself
  private int manyItems;// the current amount of items in the array, not including the 0's in the
                        // capacity when being increased

  public IntArrayBag(int initialCapacity) {// this is the contructor if you don't want the initialCapacity to be 10
                                           // initially
    if (initialCapacity < 0)
      throw new IllegalArgumentException();
    manyItems = 0;
    data = new int[initialCapacity];
  }

  public IntArrayBag() {// this is contructing a bag of integers with a capacity of 10 (default) which
                        // is the initial capacity
    manyItems = 0;
    data = new int[10];
  }

  public int getCapacity() {// the capacity determines the current capacity of the bag
    return data.length;
  }

  public int size() {// the size determines the number of elements in the bag
    return manyItems;
  }

  // Sidenote: System.arraycopy(src,si,dest,di,n);
  // src = reference of array to copy FROM
  // si = starting position to copy FROM
  // dest = reference of array to copy TO
  // di = starting position to copy TO
  // n = how many elements to copy
  public void ensureCapacity(int minimumCapacity) {// change the current capacity of this bag - I don't understand why
                                                   // its called minimumCapacity and not maximumCapacity
    int biggerArray[];
    if (data.length < minimumCapacity) {
      biggerArray = new int[minimumCapacity];
      System.arraycopy(data, 0, biggerArray, 0, manyItems); // we are copying from the "data" array, "0" The starting
                                                            // position in the source array, we are copying up to the
                                                            // "biggerArray" array, "0" is the starting position in the
                                                            // destination, "manyItems" is the number of array elements
                                                            // to be copid
                                                            // So my assumption from this is that we copy all of the
                                                            // elements from data to bigger array, and the rest are
                                                            // filled with 0 if the entered in minimumCapacity is
                                                            // greater than the length of the original array
      data = biggerArray; // then we just set data = to bigger array
    }
  }

  public void printArray() {// added my own method for fun so i don't have to for loop
    for (int i = 0; i < manyItems; i++) {
      System.out.print(data[i] + ", ");
    }
  }

  public void add(int element) {
    if (manyItems == data.length)
      ensureCapacity(manyItems * 2 + 1); // many items could be 0

    data[manyItems] = element;// we add the element to the length because there is only a "0" there
    manyItems++;// we increment many items to know the max size and if we want to and another
                // element later on
  }

  public void addAll(IntArrayBag addend) {// add other contents of another bag to this bag
    ensureCapacity(manyItems + addend.manyItems);
    System.arraycopy(addend.data, 0, data, manyItems, addend.manyItems);
    manyItems += addend.manyItems;
  }

  public static IntArrayBag union(IntArrayBag b1, IntArrayBag b2) {// returns a union of the two other bags
    IntArrayBag answer = new IntArrayBag(b1.getCapacity() + b2.getCapacity());
    System.arraycopy(b1.data, 0, answer.data, 0, b1.manyItems);// here we add b1 to answer
    System.arraycopy(b2.data, 0, answer.data, b1.manyItems, b2.manyItems);// then we add b2 right after b1[-1]
    answer.manyItems = b1.manyItems + b2.manyItems;
    return answer;
  }

  public int countOccurrences(int target) {// count the number of occurrences of a particular value in this bag
    int answer = 0;
    for (int index = 0; index < manyItems; index++) {
      if (target == data[index]) {
        answer++;
      }
    }
    return answer;
  }

  public boolean remove(int target) {// Returns true if the target was found in the bag.. it also removed one copy of
                                     // it
    int index = 0;
    while ((index < manyItems) && (target != data[index]))
      index++;
    if (index == manyItems)
      return false;
    else {
      manyItems--;
      data[index] = data[manyItems];
      return true;
    }
  }

  public void trimToSize() {// Reduce the current capacity of this bag to its actual size
    int trimmedArray[];
    if (data.length != manyItems) {
      trimmedArray = new int[manyItems];
      System.arraycopy(data, 0, trimmedArray, 0, manyItems);
      data = trimmedArray;
    }
  }

  public Object clone() {// Generates a copy of this bag
    IntArrayBag answer;
    try {
      answer = (IntArrayBag) super.clone();// Type casted the clone method to the IntArrayBag class before answer can be
                                           // used
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException("...");
    }
    answer.data = (int[]) data.clone(); // deep copy
    return answer;
  }
}
