import java.util.Objects;

/**
 * Class Ride contains information about a ride and a customer.
 */
public class Ride implements Comparable<Ride>{

  private static final Double EXPRESS_SCORE = 40d;
  private static final Double STANDARD_SCORE = 30d;
  private static final Double WAIT_AND_SAVE_SCORE = 20d;
  private static final Double ENVIRONMENTALLY_CONSCIOUS_SCORE = 10d;
  private static final Double AVERAGE_SPEED = 60d;
  private Customer customer;
  private Long anticipatedDistanceInMile;
  private Double requestedTime;
  private Double arrivalTime;
  private Double duration;
  private Double departureTime;
  private TypeOfRide typeOfRide;
  private static final Double Second_IN_HOUR = 60*60d;

  /**
   * Constructor for a Ride object.
   * @param customer the customer who requests for a ride
   * @param anticipatedDistanceInMile Long, the anticipated distance of a ride in mile
   * @param requestedTime Double, the time when the ride is requested in seconds
   * @param typeOfRide the category of a ride
   */
  public Ride(Customer customer, Long anticipatedDistanceInMile, Double requestedTime,
      TypeOfRide typeOfRide) {
    this.customer = customer;
    this.anticipatedDistanceInMile = anticipatedDistanceInMile;
    this.requestedTime = requestedTime;
    this.typeOfRide = typeOfRide;
    this.duration = Second_IN_HOUR * ((float) this.anticipatedDistanceInMile / AVERAGE_SPEED);
  }

  /**
   * Setter for the departure time of a ride.
   * @param departureTime
   */
  public void setDepartureTime(Double departureTime) {
    this.departureTime = departureTime;
  }

  /**
   * Setter for the arrival time of a ride.
   */
  public void setArrivalTime() {
    this.arrivalTime = this.departureTime + this.getDuration();
  }

  /**
   * Getter for the departure time.
   * @return Double, the departure time in seconds
   */
  public Double getDepartureTime() {
    return this.departureTime;
  }

  /**
   * Getter for the arrival time.
   * @return Double, the arrival time in seconds
   */
  public Double getArrivalTime() {
    return arrivalTime;
  }

  /**
   * Maps the category of a ride to its score.
   * @return Double, the score of the category
   */
  public Double typeOfRideScore() {
    switch (this.typeOfRide) {
      case EXPRESS -> {return EXPRESS_SCORE;}
      case STANDARD -> {return STANDARD_SCORE;}
      case WAIT_AND_SAVE -> {return WAIT_AND_SAVE_SCORE;}
      case ENVIRONMENTALLY_CONSCIOUS -> {return ENVIRONMENTALLY_CONSCIOUS_SCORE;}
      default -> {return 0d;}
    }
  }

  /**
   * Customers with the same priority are served in order of arrival. When two customers with the
   * same priority are waiting for a driver, they have the same departure time. Then, the one has
   * shorter distance will arrive first.
   * @param o the object to be compared.
   * @return int, 1 means this ride has lower priority than the other ride, -1 otherwise
   */
  @Override
  public int compareTo(Ride o) {
    if (this.typeOfRideScore() + this.anticipatedDistanceInMile >
        o.typeOfRideScore() + o.anticipatedDistanceInMile) {
      return -1;
    }
    else if (this.typeOfRideScore() + this.anticipatedDistanceInMile <
        o.typeOfRideScore() + o.anticipatedDistanceInMile) {
      return 1;
    }
    else if (this.getAnticipatedDistanceInMile() > o.getAnticipatedDistanceInMile()) {
      return 1;
    }
    else if (this.getAnticipatedDistanceInMile() < o.getAnticipatedDistanceInMile()) {
      return -1;
    }
    else {
      return 0;
    }
  }

  /**
   * Getter for the customer.
   * @return the customer
   */
  public Customer getCustomer() {
    return customer;
  }

  /**
   * Getter for the anticipated distance in mile.
   * @return Long, the anticipated distance
   */
  public Long getAnticipatedDistanceInMile() {
    return anticipatedDistanceInMile;
  }

  /**
   * Getter for the requested time.
   * @return Double, the requested time in seconds
   */
  public Double getRequestedTime() {
    return requestedTime;
  }

  /**
   * Getter for the category of a ride.
   * @return the category of a ride
   */
  public TypeOfRide getTypeOfRide() {
    return typeOfRide;
  }

  /**
   * Getter for the duration of a ride.
   * @return Double, the duration
   */
  public Double getDuration() {
    return duration;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Ride ride = (Ride) o;
    return Objects.equals(customer, ride.customer) && Objects.equals(
        anticipatedDistanceInMile, ride.anticipatedDistanceInMile) && Objects.equals(
        requestedTime, ride.requestedTime) && typeOfRide == ride.typeOfRide;
  }

  @Override
  public int hashCode() {
    return Objects.hash(customer, anticipatedDistanceInMile, requestedTime, typeOfRide);
  }

  @Override
  public String toString() {
    return "Ride{" +
        "customer=" + customer +
        ", anticipatedDistanceInMile=" + anticipatedDistanceInMile +
        ", requestedTime=" + requestedTime +
        ", typeOfRide=" + typeOfRide +
        '}';
  }
}
