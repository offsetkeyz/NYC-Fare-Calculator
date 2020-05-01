import org.junit.After;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class TransitCalcTest {

    TransitCalc forTest = new TransitCalc(0, 0);

    @After
    public void afterTests() {
        forTest.setNumDays(0);
        forTest.setNumRides(0);
    }

    /**
     * Tests for the unlimited7Price method.
     */
    @Test
    public void unlimited7Price() {
        forTest.setNumDays(20);
        forTest.setNumRides(20);
        Assert.assertEquals(4.95, forTest.unlimited7Price(), .001);

        forTest.setNumRides(50);
        forTest.setNumDays(22);
        Assert.assertEquals(2.64, forTest.unlimited7Price(), .001);

        forTest.setNumRides(14);
        forTest.setNumDays(6);
        Assert.assertEquals(2.36, forTest.unlimited7Price(), .01);

    }

    @Test
    public void getRidePrices() {
        forTest.setNumDays(24);
        forTest.setNumRides(11);

        double[] test = {30.25, 12, 11.5454};

        Assert.assertArrayEquals(test, forTest.getRidePrices(), .001);
    }

    @Test
    public void getBestFare() {
        //test 1
        forTest.setNumRides(54);
        forTest.setNumDays(26);
        String message = "You should get the 30-day Unlimited " +
                "option at $2.35 per ride.";
        Assert.assertEquals(message, forTest.getBestFare());

        //test 2
        forTest.setNumDays(5);
        forTest.setNumRides(12);

        String message2 = "You should get the Pay-per-ride option at $2.75 per ride.";
        Assert.assertEquals(message2, forTest.getBestFare());

    }
}