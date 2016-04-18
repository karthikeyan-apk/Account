package Beam;

/**
 * Created by karthikeyan on 08-Apr-16.
 */
public class Users  {
    String name;
    int totalAmount;
    int balanceAmount;

    public Users(String name) {
        this.name = name;
    }

    public Users(String name, int totalAmount, int balanceAmount) {
        this.name = name;
        this.totalAmount = totalAmount;
        this.balanceAmount = balanceAmount;
    }

    public int getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(int balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }
}
