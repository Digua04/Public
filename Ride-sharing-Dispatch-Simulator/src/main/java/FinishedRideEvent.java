/**
 * Class FinishedRideEvent contains information about a finished ride event.
 */
public class FinishedRideEvent extends Event {

  /**
   * Constructor for an event.
   * @param assigningRide Ride, the ride under assigning
   * @param assignedRide Ride, the ride that a driver is or was currently handling
   */
  public FinishedRideEvent(Ride assigningRide, Ride assignedRide) {
    super(assigningRide, assignedRide);
  }
}
