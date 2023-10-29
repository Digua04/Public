/**
 * Class RequestedRideEvent contains information about a requested ride event.
 */
public class RequestedRideEvent extends Event {

  /**
   * Constructor for an abstract event.
   * @param assigningRide Ride, the ride under assigning
   * @param assignedRide Ride, the ride that a driver is or was currently handling
   */
  public RequestedRideEvent(Ride assigningRide, Ride assignedRide) {
    super(assigningRide, assignedRide);
  }
}
