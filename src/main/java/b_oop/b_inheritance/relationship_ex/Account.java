package b_oop.b_inheritance.relationship_ex;

public class Account {
    private final int accountId;
    private final String accountName;
    private String accountAddress;
    private double balance;

    public Account(int accountId, String accountName, double balance) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.balance = balance;
    }

    public void balanceAccount(double amount) {
        if (amount > 0.0) {
            balance -= amount;
        }
    }

    public String getAccountAddress() {
        return accountAddress;
    }

    public void setAccountAddress(String accountAddress) {
        this.accountAddress = accountAddress;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Account: " + "Id: " + accountId +
                ", Name: " + accountName +
                ", Balance: " + balance;
    }
}
