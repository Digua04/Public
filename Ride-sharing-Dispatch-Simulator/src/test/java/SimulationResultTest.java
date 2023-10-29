import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimulationResultTest {

  private SimulationResult testSimulationResult;
  private SimulationResult expectedSimulationResult;

  @BeforeEach
  void setUp() {
    testSimulationResult = new SimulationResult(3*60*1000d, 25);
  }

  @Test
  void getWaitTime() {
    assertEquals(3*60*1000l, testSimulationResult.getWaitTime());
  }

  @Test
  void getNumOfRides() {
    assertEquals(25, testSimulationResult.getNumOfRides());
  }

  @Test
  void testEquals_sameObject() {
    assertTrue(testSimulationResult.equals(testSimulationResult));
  }

  @Test
  void testEquals_null() {
    assertFalse(testSimulationResult.equals(null));
  }

  @Test
  void testEquals_differentClass() {
    assertFalse(testSimulationResult.equals("test"));
  }

  @Test
  void testEquals_differentWaitTime() {
    expectedSimulationResult = new SimulationResult(2*60*1000d, 25);
    assertFalse(testSimulationResult.equals(expectedSimulationResult));
  }

  @Test
  void testEquals_differentNumOfRides() {
    expectedSimulationResult = new SimulationResult(3*60*1000d, 20);
    assertFalse(testSimulationResult.equals(expectedSimulationResult));
  }

  @Test
  void testEquals_sameFields() {
    expectedSimulationResult = new SimulationResult(3*60*1000d, 25);
    assertTrue(testSimulationResult.equals(expectedSimulationResult));
  }

  @Test
  void testHashCode() {
    int result = Objects.hash(testSimulationResult.getWaitTime(),
        testSimulationResult.getNumOfRides());
    assertEquals(result, testSimulationResult.hashCode());
  }

  @Test
  void testToString() {
    String result = "SimulationResult{" +
        "waitTime=" + testSimulationResult.getWaitTime() +
        ", numOfRides=" + testSimulationResult.getNumOfRides() +
        '}';
    assertEquals(result, testSimulationResult.toString());
  }
}