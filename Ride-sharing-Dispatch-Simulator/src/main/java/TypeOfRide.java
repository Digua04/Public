import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum TypeOfRide {

  EXPRESS,
  STANDARD,
  WAIT_AND_SAVE,
  ENVIRONMENTALLY_CONSCIOUS;

  private static final List<TypeOfRide> VALUES =
      Collections.unmodifiableList(Arrays.asList(values()));
  private static final int SIZE = VALUES.size();
  private static Random RANDOM = new Random();

  public static TypeOfRide randomTypeOfRide()  {
    RANDOM.setSeed(1000l);
    return VALUES.get(RANDOM.nextInt(SIZE));
  }
}
