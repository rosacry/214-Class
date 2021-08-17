package CodeAlong.Examples;

/*
    Fix This code so that:
        0. All code is uncommented.
        1. Method A, Method B and Method C run
        2. All exceptions are caught in method A!
        3. Note: You can only add try/catch blocks or throws clauses. Do not change any other code
 */
public class Example4_HotPotato {

    public static void main(String[] args) {
        methodA();
    }

    public static void methodA(){
        System.out.println("Method A Run!");
        methodB(1,2);
    }

    public static void methodB(int a, int b){
        System.out.println("Method B Run!");
        methodC(a, b);
    }

    public static void methodC(int a, int b){
        System.out.println("Method C Run!");
        if(a != b){
            //throw new Exception("Exception thrown in Method C! integers must be equal!");
        }
    }

}
