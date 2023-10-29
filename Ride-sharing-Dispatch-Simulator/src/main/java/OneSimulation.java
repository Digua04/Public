import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

/**
 * Class OneSimulation simulates a scenario with a given number of available drivers and rides.
 */
public class OneSimulation {

  static Random distanceRandom = new Random();
  static Random requestedTimeRandom = new Random();

  private Integer numOfAvailableDrivers;
  private List<Ride> rideList = new ArrayList<>();

  private Queue<Ride> rideRequestsPriority = new PriorityQueue<>();
  private List<Ride> requestedTimeSortedRides;
  private Queue<Ride> currentlyActiveRides = new PriorityQueue<>();
  private List<Ride> currentArrivalTimeSortedRides;
  private List<SimulationResult> simulationResultList = new ArrayList<>();
  private static final String UNKNOWN_START = "unknown";
  private static final String UNKNOWN_END = "unknown";
  private static final Double SECONDS_IN_DAY = 10*60d;
  private static final long MAX_DISTANCE = 1360l;

  /**
   * Constructor for a OneSimulation class.
   * @param numOfAvailableDrivers Integer, the number of available drivers
   * @param numOfRides Integer, the number of rides
   */
  public OneSimulation(Integer numOfAvailableDrivers, Integer numOfRides) {
    this.createRideRequests(numOfRides);
    this.numOfAvailableDrivers = numOfAvailableDrivers;
  }

  /**
   * Getter for the number of available drivers.
   * @return Integer, the number of available drivers
   */
  public Integer getNumOfAvailableDrivers() {
    return numOfAvailableDrivers;
  }

  /**
   * Getter for the list of rides.
   * @return List<Ride>, a list of rides
   */
  public List<Ride> getRideList() {
    return rideList;
  }

  /**
   * Helper method called by Constructor to create the list of rides.
   * @param numOfRides
   */
  private void createRideRequests(Integer numOfRides) {
    distanceRandom.setSeed(10000l);
    requestedTimeRandom.setSeed(20000l);
    for (int i = 0; i < numOfRides; i++) {
      Customer customer = new Customer(i, UNKNOWN_START, UNKNOWN_END);
      this.rideList.add(new Ride(customer, distanceRandom.nextLong(MAX_DISTANCE),
          requestedTimeRandom.nextDouble(SECONDS_IN_DAY), TypeOfRide.randomTypeOfRide()));
    }
    Collections.sort(this.rideList);
  }

  /**
   * Simulates a scenario with a given number of available drivers and a list of rides.
   * @return
   */
  public List<SimulationResult> simulation() {

    for (Ride ride : this.rideList) {
      this.rideRequestsPriority.add(ride);
    }

    Comparator<Ride> arrivalTime = new Comparator<Ride>() {
      @Override
      public int compare(Ride o1, Ride o2) {
        return o1.getArrivalTime().compareTo(o2.getArrivalTime());
      }
    };

    Comparator<Ride> requestedTime = new Comparator<Ride>() {
      @Override
      public int compare(Ride o1, Ride o2) {
        if (o1.getRequestedTime() > o2.getRequestedTime()) {
          return 1;
        }
        else if (o1.getRequestedTime() < o2.getRequestedTime()) {
          return -1;
        }
        else return 0;
      }
    };


    if (this.numOfAvailableDrivers >= this.rideRequestsPriority.size()) {
      this.simulationResultList.add(new SimulationResult(0d, this.rideRequestsPriority.size()));
    }
    else {
      this.requestedTimeSortedRides = new ArrayList<>(this.rideRequestsPriority);
      this.requestedTimeSortedRides.sort(requestedTime);
      // adds requested rides to currently active drivers according to their requested time
      for (int i = 0; i < this.numOfAvailableDrivers; i++) {
        Ride assigningRide = this.requestedTimeSortedRides.remove(0);
        assigningRide.setDepartureTime(assigningRide.getRequestedTime());
        assigningRide.setArrivalTime();
        this.currentlyActiveRides.add(assigningRide);
        this.simulationResultList.add(new SimulationResult(0d, 1));
      }

      Collections.sort(this.requestedTimeSortedRides);
      this.buildRideRequestsPriorityQueue(this.requestedTimeSortedRides);

      // when there are unhandled requested rides
      while (this.rideRequestsPriority.size() != 0) {
        // organizes currently active rides
        this.currentArrivalTimeSortedRides = new ArrayList<>(this.currentlyActiveRides);
        this.currentArrivalTimeSortedRides.sort(arrivalTime);

        // a driver finished a ride before a requested ride
        Collections.sort(this.requestedTimeSortedRides, requestedTime);
        if (this.currentArrivalTimeSortedRides.get(0).getArrivalTime() <
            this.requestedTimeSortedRides.get(0).getRequestedTime()) {
          Ride assignedRide = this.currentArrivalTimeSortedRides.remove(0);
          Ride assigningRide = this.requestedTimeSortedRides.remove(0);
          // set the departure time of the ride under assigning
          assigningRide.setDepartureTime(assigningRide.getRequestedTime());
          assigningRide.setArrivalTime();
          // adds the ride to the currently active rides list
          this.currentArrivalTimeSortedRides.add(assigningRide);

          FinishedRideEvent finishedRideEvent = new FinishedRideEvent(assigningRide, assignedRide);
          this.simulationResultList.add(AssignRideService.onEventHappened(finishedRideEvent));
          this.buildCurrentlyActiveRidesQueue(this.currentArrivalTimeSortedRides);
          this.buildRideRequestsPriorityQueue(this.requestedTimeSortedRides);
        }
        else {
          Ride assignedRide = this.currentArrivalTimeSortedRides.remove(0);
          Ride assigningRide = this.rideRequestsPriority.poll();
          // assign the departure time of the ride under assigning to be the arrival time of the
          // assigned ride
          assigningRide.setDepartureTime(assignedRide.getArrivalTime());
          assigningRide.setArrivalTime();

          this.currentArrivalTimeSortedRides.add(assignedRide);
          RequestedRideEvent requestedRideEvent = new RequestedRideEvent(assigningRide,
              assignedRide);
          this.simulationResultList.add(AssignRideService.onEventHappened(requestedRideEvent));
          this.buildCurrentlyActiveRidesQueue(currentArrivalTimeSortedRides);
        }
      }
    }
    return this.simulationResultList;
  }

  private void buildRideRequestsPriorityQueue(List<Ride> requestedRides) {
    Collections.sort(requestedRides);
    this.rideRequestsPriority.clear();
    for (Ride ride : requestedRides) {
      this.rideRequestsPriority.add(ride);
    }
  }

  /**
   * Helper method called by simulation method to build the queue of currently active rides.
   * @param currentlySortedRides
   */
  private void buildCurrentlyActiveRidesQueue(List<Ride> currentlySortedRides) {
    this.currentlyActiveRides.clear();
    for (Ride ride : currentlySortedRides) {
      this.currentlyActiveRides.add(ride);
    }
  }
}
