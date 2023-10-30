/*
    Team: Isiah Pham, Korey Bryant, Ben Nazaruk, Kaylee Pierce
 */
package cpsc2150.banking.models;

/**
 * Mortgage contains the necessary information for a home mortgage.
 *
 * @invariant MIN_YEARS <= years <= MAX_YEARS
 *
 * @correspondense Payment = payment
 * @correspondense Rate = rate
 * @correspondense Customer = this.Customer
 * @correspondense DebtToIncomeRatio = debtToIncomeRation
 * @correspondense Principal = principal
 * @correspondense NumberOfPayments = numberOfPayments
 * @correspondense PercentDown = percentDown
 */
public class Mortgage extends AbsMortgage implements IMortgage
{
    private int years = 0;
    private double payment = 0;
    private double rate = 0;
    private ICustomer Customer;
    private double debtToIncomeRatio = 0;
    private double principal = 0;
    private int numberOfPayments = 0;
    private double percentDown = 0;

    /**
     * Constructor for Mortgage object. Initializes all the variables via the parameters.
     *
     * @pre homeCost > 0 AND 0 <= downPayment < homeCost AND MIN_YEARS <= loanYears <= MAX_YEARS
     *
     * @param homeCost the total cost of the home
     * @param downPayment the down payment on the home
     * @param loanYears the length of the loan in years
     * @param cust the customer of the loan
     *
     * @post this.years = years AND Customer = cust AND principal = homeCost - downPayment AND
     * percentDown = downPayment / homeCost AND numberOfPayments = years * MONTHS_IN_YEARS AND [rate
     * is calculated based on credit score, length of the mortgage, and percent down] AND
     * payment = (rate * principle) / (1 - (1 + rate) ^ (- numberOfPayments)) AND
     * debtToIncomeRation = ([customer's monthly debt payments] + payments) / ([the customer's yearly income] / MONTHS_IN_YEAR)
     */
    Mortgage(double homeCost, double downPayment, int loanYears, ICustomer cust)
    {
        Customer = cust;
        years = loanYears;
        principal = homeCost - downPayment;
        percentDown = downPayment / homeCost;
        numberOfPayments = years * MONTHS_IN_YEAR;

        //Calculating the APR and then the interest rate
        rate = BASERATE;
        rate = ((years < MAX_YEARS) ? rate + GOODRATEADD : rate + NORMALRATEADD);
        rate = ((percentDown < PREFERRED_PERCENT_DOWN) ? rate + GOODRATEADD : rate);
        double credit = cust.getCreditScore();
        if (credit < BADCREDIT) {
            rate += VERYBADRATEADD;
        }
        else if (credit < FAIRCREDIT) {
            rate += BADRATEADD;
        }
        else if (credit < GOODCREDIT) {
            rate += NORMALRATEADD;
        }
        else if (credit < GREATCREDIT) {
            rate += GOODRATEADD;
        }
        rate /= MONTHS_IN_YEAR;

        payment = (rate * principal) / (1 - (Math.pow((1 + rate), -numberOfPayments)));

        debtToIncomeRatio = (cust.getMonthlyDebtPayments() + payment) / (cust.getIncome() / MONTHS_IN_YEAR);
    }

    @Override
    public double getPrincipal()
    {
        return principal;
    }

    @Override
    public int getYears()
    {
        return years;
    }

    @Override
    public boolean loanApproved()
    {
        return (rate * MONTHS_IN_YEAR < RATETOOHIGH) && (percentDown >= MIN_PERCENT_DOWN) && (debtToIncomeRatio <= DTOITOOHIGH);
    }

    @Override
    public double getPayment()
    {
        return payment;
    }

    @Override
    public double getRate()
    {
        return rate * 12;
    }
}
