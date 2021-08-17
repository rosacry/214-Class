
class LocationTester {
  public static void main(String[] args) {
    Location server = new Location(2.0, 4.5);
    Location mobile = (Location) server.clone();
    System.out.println(server.equals(mobile));
    mobile.shift(-3.0, -3.0); // we are manipulating the location points
    System.out.println(server.equals(mobile)); // checking the equals overwritten method
    System.out.println("The devices are " + Location.distance(server, mobile) + " blocks away from each other");

  }
}
