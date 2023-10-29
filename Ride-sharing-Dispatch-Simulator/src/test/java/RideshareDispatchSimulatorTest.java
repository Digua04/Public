import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RideshareDispatchSimulatorTest {

  @BeforeEach
  void setUp() {
  }

  @Test
  void main() {
    String input = "25";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    RideshareDispatchSimulator.main(new String[]{});
    String expectedOutput = "Please enter the number of drivers." + System.lineSeparator() +
        "When there are 25 rides," + System.lineSeparator() +
        "the average wait time is 00:00:00:000," + "\n" +
        "and the average number of rides a driver has handled is 1.0." + System.lineSeparator()
            + "When there are 100 rides," + System.lineSeparator() +
        "the average wait time is 00:00:00:234," + "\n" +
        "and the average number of rides a driver has handled is 4.0." + System.lineSeparator()
        + "When there are 2500 rides," + System.lineSeparator() +
        "the average wait time is 00:00:08:855," + "\n" +
        "and the average number of rides a driver has handled is 100.0." + System.lineSeparator();
    assertEquals(expectedOutput, outContent.toString());
  }
}