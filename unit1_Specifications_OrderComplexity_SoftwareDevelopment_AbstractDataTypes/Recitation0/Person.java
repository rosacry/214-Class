//package Recitation0;

public class Person {
  private String name;
  private int age;
  private String ssn;

  /**
   * This method adds two positive numbers together
   *
   * @param num1 The first number to be added
   * @param num2 The second number to be added
   *
   * @return The sum of the first and second number
   *
   * @throws IllegalArgumentException when either of the numbers is negative
   */
  public int add(int num1, int num2) throws IllegalArgumentException {
    if (num1 < 0 || num2 < 0)
      throw new IllegalArgumentException("One of the numbers is negative!");
    return num1 + num2;
  }

  /**
   * This is a Constructor used to create a new Person object
   *
   * @param name The name of the person
   * @param age  The age of the Person
   * @param ssn  The social security number of the person
   */

  public Person(String name, int age, String ssn) {
    this.name = name;
    this.age = age;
    this.ssn = ssn;
  }

  public Person() {

  }

  // NOTE: the definition i cultivated from the meaning of type casting is
  // we are casting one data type to different data type

  // Here we are overriding the equals method for our own use case
  /**
   * The point of this method is to check whether one objects given contents equal
   * another objects given contents, if so we type cast Person p to the object
   * passed in the method
   */
  public Person clone() {
    Person classCopy = new Person();
    for (this. in Person){
      classCopy.add(p.clone());
    }
    return classCopy;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Person) { // We are checking if the object we are passing is an
      // instance of the Person class
      Person p = (Person) obj; // This is to tell the compiler that the object we are passing is in fact an
      // instance of the person class
      Boolean sameName = (this.name.equals(p.name));
      Boolean sameAge = (this.age == p.age);
      Boolean sameSSN = (this.ssn.equals(p.ssn));
      return (sameName && sameAge && sameSSN);
    }
    return false;
  }

}
