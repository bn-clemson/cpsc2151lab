package cpsc2150.banking.models;

public class Mortgage extends AbsMortgage implements IMortgage
{
    private double homeCost = 0;
    private double downPayment = 0;
    private int years = 0;
    private ICustomer customer;

    Mortgage(double home, double down, int y, ICustomer cust) {
        homeCost = home;
        downPayment = down;
        years = y;
        customer = cust;
    }
}
