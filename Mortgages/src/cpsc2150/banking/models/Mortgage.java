package cpsc2150.banking.models;
//import java.lang.Math;

/**
 * @invariant homeCost >= 0 AND downPayment >= 0 AND years >= 0 AND customer >= 0
 * @correspondence self.HomeCost = homeCost AND self.DownPayment = downPayment AND self.Years = years AND
 *                  self.Customer = customer
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

    Mortgage(double homeCost, double downPayment, int years, ICustomer cust)
    {
        this.years = years;

        Customer = cust;
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
