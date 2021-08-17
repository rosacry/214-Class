public class factorial {
  public static int evaluateFactorial(int n) { // 4! would be 24, 4 * (4-1) * ((4-1)-1) * (((4-1)-1)-1) = 24
    if (n == 0)
      return 1;
    return evaluateFactorial(n - 1) * n;
  }

  public static int fib(int n) {
    if (n == 0 || n == 1)
      return n;
    return fib(n - 1) + fib(n - 2);
  }

  public static void main(String[] args) {
    System.out.println(evaluateFactorial(4));
    System.out.println(fib(8));
  }
}
