public class BankTest {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();

        Runnable depositor = () -> {
            for (int i = 0; i < 10000; i++) {
                account.deposit(1);
            }
        };

        Runnable withdrawer = () -> {
            for (int i = 0; i < 10000; i++) {
                account.withdraw(1);
            }
        };

        Thread d1 = new Thread(depositor);
        Thread d2 = new Thread(depositor);
        Thread w1 = new Thread(withdrawer);
        Thread w2 = new Thread(withdrawer);

        d1.start();
        d2.start();
        w1.start();
        w2.start();

        try {
            d1.join();
            d2.join();
            w1.join();
            w2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final balance: " + account.getBalance());
    }
}