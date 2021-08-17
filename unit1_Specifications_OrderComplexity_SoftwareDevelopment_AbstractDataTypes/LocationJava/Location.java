public class Location implements Cloneable { // add a cloneable interface so the clone() method can return an instance
                                             // of the same class as the object on which it's called
                                             // if i'm correct it's the reason why we can just return super.clone()
  private double x; // state variables
  private double y;

  public Location(double xInitial, double yInitial) { // constructor Support method, provides common support for objects
    x = xInitial;
    y = yInitial;
  }

  public Object clone() { // Generates a copy of this Location - Support method, provides common support
                          // for objects
    Location answer; // Clone output
    try {
      answer = (Location) super.clone(); // type casting super.clone() to the Location class
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException("...");
    }
    return answer;
  }

  public static double distance(Location p1, Location p2) {// returns information about a set of one or more objects
    double a, b, c_squared;
    if ((p1 == null) || (p2 == null))
      return Double.NaN; // returns "not a number of type double" if p1 or p2 is not a number
    a = p1.x - p2.x;
    b = p1.y - p2.y;
    c_squared = (a * a) + (b * b);
    return Math.sqrt(c_squared);
  }

  public boolean equals(Object obj) { // Support method, provides common support for objects
    if (obj instanceof Location) {
      Location cadidate = (Location) obj; // type casting obj to Location class so that we can compare data members
      return (cadidate.x == x) && (cadidate.y == y); // Compare the data members and return accordingly
    } else
      return false;
  }

  public double getX() { // Accessor method (returns information about the state of an object without
                         // altering the state of the object)
    return x;
  }

  public double getY() { // Accessor method (returns information about the state of an object without
                         // altering the state of the object)
    return y;
  }

  public static Location midpoint(Location p1, Location p2) { // returns information about a set of one or more objects
    double xMid, yMid;
    if ((p1 == null) || (p2 == null)) {
      return null;
    }
    xMid = (p1.x / 2) + (p2.x / 2);
    yMid = (p1.y / 2) + (p2.y / 2);
    Location answer = new Location(xMid, yMid);
    return answer;
  }

  public void rotate90() { // Mutator method, changes the state of an object through its invocation
    double xNew, yNew;
    xNew = y;
    yNew = -x;
    x = xNew;
    y = yNew;
  }

  public void shift(double xAmount, double yAmount) { // Mutator method, changes the state of an object through its
                                                      // invocation
    x += xAmount;
    y += yAmount;
  }

  public String toString() { // Support method, provides common support for objects
    return "(x=" + x + " y=" + y + ")";
  }
}
