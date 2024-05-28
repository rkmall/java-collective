package f_concurrency.e_synchronization.synchronized_method_blocks.bank_ex;

public class BankAccountWithLock {
    private String name;
    private double balance;

    // Locks
    private final Object lockName = new Object();
    private final Object lockBalance = new Object();

    public BankAccountWithLock(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        synchronized (lockName) {  // synchronized on other object than this instance
            this.name = name;
            System.out.println("Updated name: " + this.name);
        }
    }

    public void deposit(double amount) {
        try {
            System.out.println("code not associated with the critical section");
            Thread.sleep(5000); // emulate delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (lockBalance) { // lock
            double previousBalance = balance;
            balance += amount;
            System.out.printf("STARTING BALANCE: %.1f, DEPOSIT: %.1f,  " +
                    "NEW BALANCE: %.1f%n", previousBalance, amount, balance);
            addPromo(amount);   // this method requires lock
        }
    }

    private void addPromo(double amount) {
        if (amount > 5000) {
            synchronized (lockBalance) { // lock
                System.out.println("Congratulations, promotional deposit is added");
                balance += 25;
            }
        }
    }

    public void withdraw(double amount) {
        try {
            Thread.sleep(100); // emulate delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (this) {
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
}
