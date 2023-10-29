import java.util.List;
import java.util.Scanner;

/**
 * Class RideshareDispatchSimulator contains information about a RideshareDispatchSimulator object.
 */
public class RideshareDispatchSimulator {

  static Integer numOfRides1 = 25;
  static Integer numOfRides2 = 100;
  static Integer numOfRides3 = 2500;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    while (Boolean.TRUE) {
      System.out.println("Please enter the number of drivers.");
      String commandLine = scanner.nextLine();
      Integer numOfDrivers = Utils.checkInput(commandLine);

      if (numOfDrivers > 0) {
        OneSimulation oneSimulation1 = new OneSimulation(numOfDrivers, numOfRides1);
        List<SimulationResult> simulationResultList1 = oneSimulation1.simulation();
        Utils.show(simulationResultList1, numOfDrivers, numOfRides1);

        OneSimulation oneSimulation2 = new OneSimulation(numOfDrivers, numOfRides2);
        List<SimulationResult> simulationResultList2 = oneSimulation2.simulation();
        Utils.show(simulationResultList2, numOfDrivers, numOfRides2);

        OneSimulation oneSimulation3 = new OneSimulation(numOfDrivers, numOfRides3);
        List<SimulationResult> simulationResultList3 = oneSimulation3.simulation();
        Utils.show(simulationResultList3, numOfDrivers, numOfRides3);
        break;
      }
    }
  }
}
