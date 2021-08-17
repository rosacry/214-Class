public class expression {
  public static void main(String[] args) {
    String str = "A+B*(C*D-E/F)/G-H";
    System.out.println(IntStack.intfixToPostfixFull(str));
    String test = "23+45+*";
    System.out.println(IntStack.evaluate(test));
    System.out.println((int) '5');
    System.out.println((int) '0');
    System.out.println((int) '5' - (int) '0');
  }
}
