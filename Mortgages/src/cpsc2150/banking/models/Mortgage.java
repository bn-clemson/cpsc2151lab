package cpsc2150.banking.models;

/**
 * @invariant homeCost >= 0 AND downPayment >= 0 AND years >= 0 AND customer >= 0
 * @correspondence self.HomeCost = homeCost AND self.DownPayment = downPayment AND self.Years = years AND
 *                  self.Customer = customer
 */
public class Mortgage extends AbsMortgage implements IMortgage
{
    private double payment = 0;
    private double rate = 0;
    private ICustomer Customer;
    private double debtToIncomeRatio = 0;
    private double principal = 0;
    private int numberOfPayments = 0;
    private double percentDown = 0;

    Mortgage(double homeCost, double downPayment, int years, ICustomer cust) {
        principal = homeCost - downPayment;
    }

    double getRate
}
