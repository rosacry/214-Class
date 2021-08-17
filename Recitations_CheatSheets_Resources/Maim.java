public class Maim {
  public static int[] selectionSort(int[] array) {
    for (int outer = 0; outer < array.length - 1; outer++) {
      int minLoc = outer;
      int minVal = array[outer];
      for (int inner = outer + 1; inner < array.length; inner++) {
        for (int i = 0; i < array.length; i++) {
          System.out.print(array[i] + " ");
        }
        System.out.println();
        int currentVal = array[inner];
        if (currentVal < minVal) {
          minLoc = inner;
          minVal = currentVal;
        }
      }
      array[minLoc] = array[outer];
      array[outer] = minVal;
    }
    return array;
  }

  public static void main(String[] args) {
    int[] testArray = new int[] { 22, 44, 33, 11, 55 };
    Maim.selectionSort(testArray);
    for (int i = 0; i < testArray.length; i++) {
      System.out.print(testArray[i] + " ");
    }
  }
}
