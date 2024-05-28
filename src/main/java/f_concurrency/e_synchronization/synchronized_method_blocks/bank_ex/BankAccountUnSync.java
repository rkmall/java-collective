package f_concurrency.e_synchronization.synchronized_method_blocks.bank_ex;

public class BankAccountUnSync {
    private double balance;

    public BankAccountUnSync(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        try {
            Thread.sleep(100); // emulate delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        double previousBalance = balance;
        balance += amount;
        System.out.printf("STARTING BALANCE: %.1f, DEPOSIT: %.1f,  " +
                "NEW BALANCE: %.1f%n", previousBalance, amount, balance);
    }

    public void withdraw(double amount) {
        try {
            Thread.sleep(100); // emulate delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        double previousBalance = balance;
        if (amount <= balance) {
            balance -= amount;
            System.out.printf("STARTING BALANCE: %.1f, WITHDRAWAL: %.1f,  " +
                    "NEW BALANCE: %.1f%n", previousBalance, amount, balance);
        } else {
            System.out.printf("STARTING BALANCE: %.1f, WITHDRAWAL: %.1f,  " +
                    "INSUFFICIENT FUNDS", previousBalance, amount);
        }
    }
}
