package CodeAlong.Examples;

/*
    Fix This code so that:
        0. All code is uncommented.
        1.  Method A, Method B and Method C run
        2. All exceptions are caught in method A!
        3. Note: You can only add try/catch blocks or throws clauses. Do not change any other code
 */
public class Example2_NotMyJob {

    public static void main(String[] args) {
        methodA();
    }

    public static void methodA(){
        System.out.println("Method A Run!");
        methodB();
        methodC();
    }

    public static void methodB(){
        System.out.println("Method B Run!");
        //throw new Exception("An Exception from B!");
    }

    public static void methodC(){
        System.out.println("Method C Run!");
        //throw new RuntimeException("Exception from C!");
    }

}
