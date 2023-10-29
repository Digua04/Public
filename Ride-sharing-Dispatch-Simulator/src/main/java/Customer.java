import java.util.Objects;

/**
 * Class Customer contains information about a Customer.
 */
public class Customer {

  private Integer customerID;
  private String startLocation;
  private String desiredLocation;

  /**
   * Constructor for a Customer object.
   * @param customerID Integer, the ID of a customer
   * @param startLocation String, the start location of a customer
   * @param desiredLocation String, the desired location of a customer
   */
  public Customer(Integer customerID, String startLocation, String desiredLocation) {
    this.customerID = customerID;
    this.startLocation = startLocation;
    this.desiredLocation = desiredLocation;
  }

  /**
   * Getter for the customer ID
   * @return Integer, the customer ID
   */
  public Integer getCustomerID() {
    return customerID;
  }

  /**
   * Getter for the start location
   * @return String, the start location
   */
  public String getStartLocation() {
    return startLocation;
  }

  /**
   * Getter for the desired location
   * @return String, the desired location
   */
  public String getDesiredLocation() {
    return desiredLocation;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Customer customer = (Customer) o;
    return Objects.equals(customerID, customer.customerID) && Objects.equals(
        startLocation, customer.startLocation) && Objects.equals(desiredLocation,
        customer.desiredLocation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customerID, startLocation, desiredLocation);
  }

  @Override
  public String toString() {
    return "Customer{" +
        "customerID=" + customerID +
        ", startLocation='" + startLocation + '\'' +
        ", desiredLocation='" + desiredLocation + '\'' +
        '}';
  }
}
