/**
 * Class AssignRideService stimulates the process of assigning a ride to a driver.
 */
public class AssignRideService {

  /**
   * Stimulates the process of assigning a ride to a driver.
   * @param event Event, an event includes a ride (driver) to be assigned
   *              and a ride is under assigning
   * @return StimulationResult, the wait time and 1 as the number of rides a driver has handled
   */
  public static SimulationResult onEventHappened(Event event) {

    Ride requestedRide = event.getAssigningRide();
    Ride assignedRide = event.getAssignedRide();
    Double assignedRideArrivalTimeInS = assignedRide.getArrivalTime();
    Double requestedRideRequestedTime = requestedRide.getRequestedTime();
    Double waitTime = assignedRideArrivalTimeInS > requestedRideRequestedTime ?
        assignedRideArrivalTimeInS - requestedRideRequestedTime : 0l;
    return new SimulationResult(waitTime, 1);
  }
}
