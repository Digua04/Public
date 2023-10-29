import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Class Utils provides utility methods for various operations in the simulator system.
 */
public class Utils {

  private static final Integer DEFAULT_VALUE = -1;

  /**
   * Checks and processes an input of number of drivers.
   * @param commandLine The user's command.
   * @return The number of drivers, or a default value if the command is invalid.
   */
  public static Integer checkInput(String commandLine) {
    Integer numOfDrivers = DEFAULT_VALUE;
    if (commandLine.matches("[0-9]+")) {
      numOfDrivers = Integer.parseInt(commandLine);
      if (numOfDrivers == 0) {
        System.out.println("The reserved seats must be greater than 0! ");
      }
    } else {
      System.out.println(
          "Input a positive integer to reserve that number of seats! Please try again.");
    }
    return numOfDrivers;
  }

  /**
   * Shows the average wait time for a ride and the average number of rides a driver has handled.
   * @param simulationResultList the list of SimulationResults
   * @param numOfDrivers the number of drivers
   * @param numOfRides the number of requested rides
   */
  public static void show(List<SimulationResult> simulationResultList, Integer numOfDrivers, Integer
      numOfRides) {
    long averageWaitTime = calculateAverageWaitTime(simulationResultList, numOfRides).longValue();
    Instant instant = Instant.ofEpochMilli(averageWaitTime);
    String convertedAverageWaitTime = convertToHour(instant);
    Double averageNumOfRides = calculateAverageNumOfRides(simulationResultList, numOfDrivers);
    System.out.println("When there are " + numOfRides + " rides,\n" + "the average wait time is " +
        convertedAverageWaitTime + ",\n" + "and the average number of rides a driver has handled is "
        + averageNumOfRides + ".");
  }

  /**
   * Calculates the average number of rides a driver has handled.
   * @param simulationResultList the list of SimulationResults
   * @param numOfDrivers the number of drivers
   * @return Double, the average number of rides
   */
  private static Double calculateAverageNumOfRides(List<SimulationResult> simulationResultList,
      Integer numOfDrivers) {
    Double totalNumOfRides = 0d;
    for (SimulationResult simulationResult : simulationResultList) {
      totalNumOfRides += simulationResult.getNumOfRides().doubleValue();
    }
    return totalNumOfRides / numOfDrivers;
  }

  /**
   * Calculates the average number of wait time for a ride.
   * @param simulationResultList the list of SimulationResults
   * @param numOfRides the number of rides
   * @return Long, the average number of wait time
   */
  private static Double calculateAverageWaitTime(List<SimulationResult> simulationResultList,
      Integer numOfRides) {
    Double totalWaitTime = 0d;
    for (SimulationResult simulationResult : simulationResultList) {
      totalWaitTime += simulationResult.getWaitTime();
    }
    return totalWaitTime / numOfRides;
  }

  /**
   * Converts the time in long into String.
   * @param instant Instant, the wait time
   * @return String, the converted wait time
   */
  private static String convertToHour(Instant instant) {
    ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, ZoneOffset.UTC);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss:SSS");
    return formatter.format(zdt);
  }
}
