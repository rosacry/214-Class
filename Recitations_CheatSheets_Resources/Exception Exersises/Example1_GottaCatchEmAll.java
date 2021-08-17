package CodeAlong.Examples;

/*
    Fix This code so that:
        1. Method A, Method B and Method C run
        2. All exceptions are caught!
        3. Note: You can only add try/catch blocks or throws clauses. Do not change any other code
 */
public class Example1_GottaCatchEmAll {

    public static void main(String[] args) {
        methodA();
    }

    public static void methodA(){
        System.out.println("Method A run!");
        int i = methodB(-1,1);
        methodC("cse");
    }

    /**
     * Adds two positive numbers. Precondition: both integers must be positive.
     * @param a The first integer to be added
     * @param b The Second Integer to be added
     * @return the some of teh two integers
     * @throws IllegalArgumentException
     * If either integer is negative
     */
    public static int methodB(int a, int b) throws IllegalArgumentException{
        System.out.println("Method B Run!");
        if(a < 0 || b < 0) {
            throw new IllegalArgumentException("Both integers bust be positive!");
        }

        return a + b;
    }

    public static void methodC(String s){
        System.out.println("Method C Run!");
        int i = Integer.parseInt(s);
    }

}
