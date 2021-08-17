package CodeAlong.Examples;

/*
        Fix This code so that:
        0. All code is uncommented.
        1. Method A, Method B and Method C run to completion!! (Both the start end end messages print for each method)
        2. Note: You can only add try/catch blocks or throws clauses. Do not change any other code
*/
public class Example6_NoInteruptionsInClass {
    public static void main(String[] args) {
        methodA();
    }

    public static void methodA(){
        System.out.println("Method A Run!");
        methodB();
        methodC();
        System.out.println("Method A Finished!");
    }

    public static void methodB(){
        System.out.println("Method B Run!");
        //throw new Exception("An Exception from B!");
        System.out.println("Method B Finished!");
    }

    public static void methodC(){
        System.out.println("Method C Run!");
        //throw new RuntimeException("Exception from C!");
        System.out.println("Method C Finished!");
    }
}
