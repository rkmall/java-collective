package f_concurrency.e_synchronization.synchronized_method_blocks.bank_ex;

public class BankExTest {
    public static void main(String[] args) {

        //bankAccountUnSyncTest();
        //bankAccountSynchronizedMethodTest();
        //bankAccountSynchronizedBlockTest();
        //bankAccountLockTest();
    }

    public static void bankAccountUnSyncTest() {
        BankAccountUnSync account = new BankAccountUnSync(10000);
        Thread t1 = new Thread(() -> account.withdraw(2000));
        Thread t2 = new Thread(() -> account.deposit(5000));
        Thread t3 = new Thread(() -> account.withdraw(3000));
        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Final balance: " + account.getBalance());
    }

    public static void bankAccountSynchronizedMethodTest() {
        BankAccountSync account = new BankAccountSync("Jim", 10000);
        Thread t1 = new Thread(() -> account.withdraw(2000));
        Thread t2 = new Thread(() -> account.deposit(5000));
        Thread t3 = new Thread(() -> account.withdraw(3000));
        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Final balance: " + account.getBalance());
    }

    public static void bankAccountSynchronizedBlockTest() {
        BankAccountSync account = new BankAccountSync("Jim", 10000);
        Thread t1 = new Thread(() -> account.withdrawSynchronizedBlock(2000));
        Thread t2 = new Thread(() -> account.depositSynchronizedBlock(5000));
        Thread t3 = new Thread(() -> account.setName("Jimmy"));
        Thread t4 = new Thread(() -> account.withdrawSynchronizedBlock(3000));
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Final balance: " + account.getBalance());
    }

    public static void bankAccountLockTest() {
        BankAccountWithLock account = new BankAccountWithLock("Jim", 10000.0);
        Thread t1 = new Thread(() -> account.withdraw(2000.0));
        Thread t2 = new Thread(() -> account.deposit(5000.0));
        Thread t3 = new Thread(() -> account.withdraw(3000.0));
        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Final balance: " + account.getBalance());
    }
}
