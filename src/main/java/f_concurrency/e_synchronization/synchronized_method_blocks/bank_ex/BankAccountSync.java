package f_concurrency.e_synchronization.synchronized_method_blocks.bank_ex;

public class BankAccountSync {
    private String name;
    private double balance;

    public BankAccountSync(String name, double balance) {
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
        synchronized (this.name) {  // synchronized on other object than this instance
            this.name = name;
            System.out.println("Updated name: " + this.name);
        }
    }

    /**
     * When using synchronized method, the current instance is implicitly locked.
     */
    public synchronized void deposit(double amount) {
        try {
            System.out.println("code not associated with the critical section");
            Thread.sleep(5000); // emulate delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        double previousBalance = balance;
        balance += amount;
        System.out.printf("STARTING BALANCE: %.1f, DEPOSIT: %.1f,  " +
                "NEW BALANCE: %.1f%n", previousBalance, amount, balance);
    }

    /**
     * When using synchronized block, the current instance is explicitly locked
     */
    public void depositSynchronizedBlock(double amount) {
        try {
            System.out.println("code not associated with the critical section");
            Thread.sleep(5000); // emulate delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (this) {
            double previousBalance = balance;
            balance += amount;
            System.out.printf("STARTING BALANCE: %.1f, DEPOSIT: %.1f,  " +
                    "NEW BALANCE: %.1f%n", previousBalance, amount, balance);
        }
    }

    public synchronized void withdraw(double amount) {
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

    public void withdrawSynchronizedBlock(double amount) {
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
