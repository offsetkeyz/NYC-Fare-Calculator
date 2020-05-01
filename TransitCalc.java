import javax.swing.*;
import java.text.DecimalFormat;

public class TransitCalc {
    private int numDays;
    private int numRides;

    // final values of the passes
    public final double PAY_PER_RIDE = 2.75;
    public final double SEVEN_DAY_UNLIMITED = 33.00;
    public final double THIRTY_DAY_UNLIMITED = 127.00;

    /**
     * Constructor for the TransitCalc
     * @param numDaysIn number of days
     * @param numRidesIn number of rides
     */
    public TransitCalc(int numDaysIn, int numRidesIn) {
        numDays = numDaysIn;
        numRides = numRidesIn;
    }

    /**
     * Get's the price per ride if purchasing 7-day passes.
     * @return double
     */
    public double unlimited7Price() {
        double pricePerRide = 0;
        // total 7-day passes needed to buy
        int totalPasses = (numDays / 7) + 1;
        double totalPrice = totalPasses * SEVEN_DAY_UNLIMITED;
        pricePerRide = totalPrice / numRides;

        return pricePerRide;
    }

    /**
     * Get's the price per ride for each ticket type.
     * @return double[]
     */
    public double[] getRidePrices() {
        double[] prices = new double[3];
        //price per ride if you pay per ride
        prices[0] = PAY_PER_RIDE;

        //unlimited 7 price per ride
        prices[1] = this.unlimited7Price();

        //unlimited 30 per ride
        prices[2] = THIRTY_DAY_UNLIMITED / numRides;
        return prices;
    }

    /**
     * Get's the best fare given #of rides and #of days
     * @return Sting
     */
    public String getBestFare() {
        DecimalFormat df = new DecimalFormat("$#0.00");
        String advice = "You should get the ";
        double[] prices = this.getRidePrices();
            if(prices[0] <= prices[1] && prices[0] <= prices[2]) {
                advice += "Pay-per-ride option at " + df.format(PAY_PER_RIDE) + " per ride.";
            } else if (prices[1] <= prices[2]) {
                advice += "7-day Unlimited option at " + df.format(prices[1]) + " per ride.";
            } else if (prices[2] <= prices[1] && prices[2] <= prices[0]) {
                advice += "30-day Unlimited option at " + df.format(prices[2]) + " per ride.";
            } else {
                advice += "heck out of dodge! Just kidding my program is broken lulz.";
            }
        return advice;
    }

    public int getNumDays() {
        return numDays;
    }

    public void setNumDays(int numDays) {
        this.numDays = numDays;
    }

    public int getNumRides() {
        return numRides;
    }

    public void setNumRides(int numRides) {
        this.numRides = numRides;
    }

    /**
     * Driver for the Transit Calculator.
     * @param args not used
     */
    public static void main(String[] args) {
        int numDays = 0;
        int numRides = 0;
        int runAgain = 0;

        do { //loops as long as user wants
            try {
                numDays = Integer.parseInt
                        (JOptionPane.showInputDialog("How many days will you be riding?"));
                numRides = Integer.parseInt
                        (JOptionPane.showInputDialog("How many rides?"));
                TransitCalc yourRecommendation = new TransitCalc(numDays, numRides);
                JOptionPane.showMessageDialog(null, yourRecommendation.getBestFare());

                runAgain = JOptionPane.showConfirmDialog(null,
                        "Calculate again?"); // checks to see if user wants to go again
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "Please enter a positive integer.");
            }
        } while (runAgain == 0);


    }


}
