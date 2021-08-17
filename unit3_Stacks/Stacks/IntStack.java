//Questions for this class:
//1. how does the isBalanced method convert the char temp in s.push(temp)?
//2. 
//Things I learned that I had no idea
//java automatically casts a char value into an int
public class IntStack implements Cloneable {
  public final int CAPACITY = 100;
  private int[] data;
  private int top;

  // IntStack methods (clone not shown)
  public IntStack() {
    top = -1;// the reason it's -1 shows that there are no elements when the first element of
             // the stack is added, the top (being the only element at the moment) would = 0
    data = new int[CAPACITY];
  }

  public boolean isEmpty() {
    return (top == -1);
  }

  public void push(int item) {
    if (top == CAPACITY - 1) // so it can hold from 0 - 99 inclusive
      throw new RuntimeException("...");
    top++;
    data[top] = item;
  }

  public int pop() {
    int answer;
    if (top == -1) // isEmpty() - why can't we call is isEmpty method in the if statement?
      throw new RuntimeException("Stack is empty");
    answer = data[top];// we need to make a temp called answer before we can return the value so we can
                       // also subtract the top
    top--;
    return answer;
  }

  public int peek() { // returning the top
    int answer;
    if (top == -1) // isEmpty();
      throw new RuntimeException("...");
    answer = data[top];
    return answer; // i don't understand why we can't just return data[top]
  }

  // isBalanecd method searches for all false cases
  public static boolean isBalanced(String str) { // checking if the parentheses are balanced
    // this creates a whole new stack, we add to the stack every character iteration
    IntStack s = new IntStack();
    int size = 0;
    for (int i = 0; i < str.length(); i++) {
      char temp = str.charAt(i); // returns a character of the string, not a string
      if (temp == '(' || temp == '[' || temp == '{') {
        size++;
        s.push(temp); // if we get an error, the problem is going to be this line
      } else if (size > 0) { // this is when temp has
                             // to = a closing
                             // bracket
        char compare = (char) s.pop();// comapre will always get an open, because we are only pushing the opens on the
                                      // stack and checking them with the closes in the string - we pop it because
                                      // if it's true then we go to the next open and close pair after, if the top
                                      // doesn not = the next close index, then it's false
        // if we wanted to include numbers in this problem, we would need another if
        // statement right here to check if compare is any of the three braces
        if ((compare != '(' && temp == ')') || (compare != '[' && temp == ']') || (compare != '{' && temp == '}')) {
          return false;
        }
        size--;// when we do size--, that means there is a pair(s) found
      } else {
        return false;
      }
    }
    return (size == 0 && str.length() > 0);// size == 0 would mean that there's a close for every open brace
  }

  public static boolean isBalancedWithNum(String str) { // to acccommodate numbers and letters
    IntStack s = new IntStack();
    int size = 0;
    for (int i = 0; i < str.length(); i++) {
      // if here statement to see if temp == any open or close brace
      char temp = str.charAt(i);
      if (temp == '(' || temp == '[' || temp == '{' || temp == ')' || temp == ']' || temp == '}') {
        if (temp == '(' || temp == '[' || temp == '{') {
          size++;
          s.push(temp);
        } else if (size > 0) {
          char compare = (char) s.pop();
          if ((compare != '(' && temp == ')') || (compare != '[' && temp == ']') || (compare != '{' && temp == '}')) {
            return false;
          }
          size--;
        } else {
          return false;
        }
      }
    }
    return (size == 0 && str.length() > 0);
  }

  public static int tracedAl(String str) {
    if (!isBalancedWithNum(str)) {
      throw new RuntimeException("Parentheses are not Balanced");
    }
    IntStack NumStack = new IntStack(); // this stores the operands
    IntStack OpStack = new IntStack(); // this stores the operators
    for (int i = 0; i < str.length(); i++) {
      char token = str.charAt(i);
      if (Character.isDigit(token)) {// if its an operand
        NumStack.push(Character.getNumericValue(token));// convert char to int add to Number STack
      } else {// if its an operator
        OpStack.push(token);
        if (token == ')') {
          OpStack.pop(); // pop close parentheses here
          int operand1 = NumStack.pop();// first number in the operation
          int operand2 = NumStack.pop();// second number in the operation
          char operator = (char) OpStack.pop(); // get the operator in between the parentheses
          OpStack.pop();// pop open parentheses here
          // check if operator equals a +, -, *, or /
          if (operator == '+') {
            NumStack.push(operand2 + operand1);
          } else if (operator == '-')
            NumStack.push(operand2 - operand1);
          else if (operator == '*')
            NumStack.push(operand2 * operand1);
          else if (operator == '/')
            NumStack.push(operand2 / operand1);
        }
      }
    }
    return NumStack.pop();
  }

  // create a method to compile post fix
  public static int compilePostFix(String str) {
    IntStack postFix = new IntStack();
    for (int i = 0; i < str.length(); i++) {
      char token = str.charAt(i);
      if (Character.isDigit(token)) { // if the token is a number
        postFix.push(Character.getNumericValue(token));
      } else { // if it's not a operand it's an operator
        int operand2 = postFix.pop();
        int operand1 = postFix.pop();
        if (token == '+')
          postFix.push(operand1 + operand2);
        else if (token == '-')
          postFix.push(operand1 - operand2);
        else if (token == '*')
          postFix.push(operand1 * operand2);
        else if (token == '/')
          postFix.push(operand1 / operand2);
      }
    }
    return postFix.pop();
  }

  // create another method to translate intfix to postfix
  // pre condition: the whole equation must be wrapped by parentheses
  public static String intfixToPostfix(String str) {
    IntStack operators = new IntStack();// make a stack for the operators
    String P = ""; // temp for result
    for (int i = 0; i < str.length(); i++) {
      char token = str.charAt(i);
      if (Character.isDigit(token)) { // checking for numbers is a priority since this is a post fix.. if it was a
                                      // prefix the priority would be the operators
        P += token;
      } else if (token == '+' || token == '-' || token == '*' || token == '/') { // check for operators next
        operators.push(token);
      } else if (token == ')') { // we need to konw when to take out the operator
        P += (char) operators.pop();
      }
    }
    return P;
  }

  // define a precedence function
  public static int precedence(char operator) {
    if (operator == '$')
      return 0;
    if (operator == '(')
      return 1;
    if (operator == '+' || operator == '-')
      return 2;
    if (operator == '*' || operator == '/')
      return 3;
    else {
      throw new RuntimeException("Not an operator");
    }
  }

  // this method translates from infix to postfix without the use of full
  // parentheses wrapping around the equation
  // think of it like you would in your head but ask yourself why for each step of
  // the process
  public static String intfixToPostfixFull(String str) {
    IntStack OpStack = new IntStack();
    String P = "";
    OpStack.push((char) '$');
    for (int i = 0; i < str.length(); i++) {
      char token = str.charAt(i);
      if (Character.isLetter(token)) // letters are priority since they must come first in postfix notation
        P += token;
      else if (token == '(') // we make this statement (down)
        OpStack.push(token);
      else if (token == ')') { // so we can check this statement
        char topOp = (char) OpStack.pop(); // add all operators to P that are in ("...") from ')' to '('
        while (topOp != '(') {
          P += topOp;
          topOp = (char) OpStack.pop();
        }
      } else if (token == '+' || token == '-' || token == '*' || token == '/') { // we need to check if it has a high
                                                                                 // enough priority to be added to P,
                                                                                 // without the use of a ')'
        char topOp = (char) OpStack.peek(); // so we obtain the peek of the stack
        // if topOp has a higher or equal to priority than i, we add topOp to P
        // previous has to be >= than the previous
        while (precedence(topOp) >= precedence(token)) { // we use a dollar sign so we don't get an error when comparing
                                                         // the first or last op in the stack
          P += (char) OpStack.pop();
          topOp = (char) OpStack.peek();
        }
        OpStack.push((char) token); // i don't need to cast as a char
      } // this while loop can only be true if it's compared to only +, -, *, and /
    }
    char topOp = (char) OpStack.pop();
    while (topOp != '$') { // if the previous(op) is
      P += topOp;
      topOp = (char) OpStack.pop();
    }
    return P;
  }

  // Evaluates the given postfix string using a stack
  // Preconditions:
  // The operands in the postfix string are single-digit integers.
  // The postfix string contains only perands and operators +,-,*, and /
  //
  // Postconditions:
  // Returns the integer value of the given postfix string if the string is a
  // valid postfix string.
  public static int evaluate(String postfix) { // this is the same method as compileToPostfix
    IntStack compile = new IntStack();
    // int value;
    for (int i = 0; i < postfix.length(); i++) {
      char token = postfix.charAt(i);
      if (Character.isDigit(token)) {
        compile.push(Character.getNumericValue(token));
        // or you can do value = (int) ch - (int) '0';
      } else {
        int operand2 = compile.pop();
        int operand1 = compile.pop();
        if (token == '+')
          compile.push(operand1 + operand2);
        else if (token == '-')
          compile.push(operand1 - operand2); // the reason we pop operand2 before operand1 is so we can do the operation
                                             // in the correct order
        else if (token == '*')
          compile.push(operand1 * operand2);
        else if (token == '/') {
          if (operand2 == 0) // very important, always check for this
            throw new RuntimeException("Division by zero exception");
          compile.push(operand1 / operand2); // same thing goes with this
        }
      }
    }
    return compile.pop();
  }
}
