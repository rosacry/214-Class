package CodeAlong.Examples;

/*
    Instructions:
    1. Complete The IncompleteException Code
    2. Throw an IncompleteException with the message "IncompleteException with a message!" from Method B
    3. Throw an IncompleteException with no message from method C
    4. Ensure that all exceptions are caught in Method A and all exception messages are printed out.
    6. Ensure Method A, Method B and Method C run
    5. Note: You can only add try/catch blocks, throws clauses and the specified exceptions. Do not change any other code
 */
public class Example5_ArtisanalException {
    public static void main(String[] args) {
        methodA();
    }

    public static void methodA(){
        System.out.println("Method A Run!");
        methodB();
        methodC();
    }

    public static void methodB(){
        System.out.println("Method A Run!");
    }

    public static void methodC(){
        System.out.println("Method A Run!");
    }
}
