/**
 * Abstract class Event contains information about an abstract event.
 */
public abstract class Event {
  private Ride assigningRide;
  private Ride assignedRide;

  /**
   * Constructor for an event.
   * @param assigningRide Ride, the ride under assigning
   * @param assignedRide Ride, the ride that a driver is or was currently handling
   */
  public Event(Ride assigningRide, Ride assignedRide) {
    this.assigningRide = assigningRide;
    this.assignedRide = assignedRide;
  }

  /**
   * Getter for the assigning ride
   * @return Ride
   */
  public Ride getAssigningRide() {
    return assigningRide;
  }

  /**
   * Getter for the ride that a driver is or was currently handling.
   * @return Ride
   */
  public Ride getAssignedRide() {
    return assignedRide;
  }
}
