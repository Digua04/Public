import java.util.Objects;

/**
 * Class SimulationResult contains information about a SimulationResult.
 */
public class SimulationResult {

  public Double waitTime;
  public Integer numOfRides;

  /**
   * Constructor for a SimulationResult class.
   * @param waitTime Double, the wait time of a ride
   * @param numOfRides Integer, the number of rides
   */
  public SimulationResult(Double waitTime, Integer numOfRides) {
    this.waitTime = waitTime;
    this.numOfRides = numOfRides;
  }

  /**
   * Getter for the wait time.
   * @return Double, the wait time
   */
  public Double getWaitTime() {
    return waitTime;
  }

  /**
   * Getter for the number of rides.
   * @return Integer, the number of rides
   */
  public Integer getNumOfRides() {
    return numOfRides;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SimulationResult that = (SimulationResult) o;
    return Objects.equals(waitTime, that.waitTime) && Objects.equals(numOfRides,
        that.numOfRides);
  }

  @Override
  public int hashCode() {
    return Objects.hash(waitTime, numOfRides);
  }

  @Override
  public String toString() {
    return "SimulationResult{" +
        "waitTime=" + waitTime +
        ", numOfRides=" + numOfRides +
        '}';
  }
}
