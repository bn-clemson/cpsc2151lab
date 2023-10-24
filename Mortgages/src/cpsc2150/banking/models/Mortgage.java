package cpsc2150.banking.models;
//import java.lang.Math;

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
        Customer = cust;
        principal = homeCost - downPayment;
        percentDown = downPayment/homeCost;
        numberOfPayments = years * 12;

        //Calculating the APR and then the interest rate
        rate = .025;
        rate = ((years<30) ? rate+0.005 : rate+.01);
        rate = ((percentDown<0.20) ? rate+0.05 : rate);
        double credit = cust.getCreditScore();
        if (credit < 500) {
            rate += 0.10;
        }
        else if (credit >= 500 && credit < 600) {
            rate += 0.05;
        }
        else if (credit >= 600 && credit < 700) {
            rate += 0.01;
        }
        else if (credit >= 700 && credit < 750) {
            rate += 0.05;
        }
        rate /= 12; //divide APR by 12 to get monthly interest rate

        payment = (rate*principal)/Math.pow((1-(1+rate)),-numberOfPayments);

        debtToIncomeRatio = (cust.getMonthlyDebtPayments()+payment)/cust.getIncome();
    }

}
