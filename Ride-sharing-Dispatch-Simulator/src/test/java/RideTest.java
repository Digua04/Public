import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RideTest {

  private Ride testRide;
  private Ride expectedRide;
  private Customer testCustomer;
  private TypeOfRide testTypeOfRide;

  @BeforeEach
  void setUp() {
    testTypeOfRide = TypeOfRide.EXPRESS;
    testCustomer = new Customer(2, "this", "that");
    testRide = new Ride(testCustomer, 30l, 6000d, testTypeOfRide);
  }

  @Test
  void getCustomer() {
    assertEquals(testCustomer, testRide.getCustomer());
  }

  @Test
  void getDepartureTime() {
    assertEquals(null, testRide.getDepartureTime());
  }

  @Test
  void getTypeOfRide() {
    assertEquals(TypeOfRide.EXPRESS, testRide.getTypeOfRide());
  }

  @Test
  void testEquals_sameObject() {
    assertTrue(testRide.equals(testRide));
  }

  @Test
  void testEquals_null() {
    assertFalse(testRide.equals(null));
  }

  @Test
  void testEquals_differentClass() {
    assertFalse(testRide.equals("test"));
  }

  @Test
  void testEquals_differentCustomer() {
    Customer otherCustomer = new Customer(3, "this", "that");
    expectedRide = new Ride(otherCustomer, 30l, 6000d, testTypeOfRide);
    assertFalse(testRide.equals(expectedRide));
  }

  @Test
  void testEquals_differentAnticipatedDistanceInMile() {
    expectedRide = new Ride(testCustomer, 20l, 6000d, testTypeOfRide);
    assertFalse(testRide.equals(expectedRide));
  }

  @Test
  void testEquals_differentRequestedTime() {
    expectedRide = new Ride(testCustomer, 30l, 2000d, testTypeOfRide);
    assertFalse(testRide.equals(expectedRide));
  }

  @Test
  void testEquals_differentTypeOfRide() {
    expectedRide = new Ride(testCustomer, 30l, 6000d, TypeOfRide.STANDARD);
    assertFalse(testRide.equals(expectedRide));
  }

  @Test
  void testEquals_sameFields() {
    expectedRide = new Ride(testCustomer, 30l, 6000d, testTypeOfRide);
    assertTrue(testRide.equals(expectedRide));
  }

  @Test
  void testHashCode() {
    int result = Objects.hash(testRide.getCustomer(), testRide.getAnticipatedDistanceInMile(),
        testRide.getRequestedTime(), testRide.getTypeOfRide());
    assertEquals(result, testRide.hashCode());
  }

  @Test
  void testToString() {
    String result = "Ride{" +
        "customer=" + testRide.getCustomer() +
        ", anticipatedDistanceInMile=" + testRide.getAnticipatedDistanceInMile() +
        ", requestedTime=" + testRide.getRequestedTime() +
        ", typeOfRide=" + testRide.getTypeOfRide() +
        '}';
    assertEquals(result, testRide.toString());
  }
}