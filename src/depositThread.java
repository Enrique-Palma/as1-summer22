// Running loop for Deposit Thread
public class depositThread implements Runnable {
    private Account account;

    // Constructor to link thread to main shared account
    public depositThread (Account mainAccount) {
        account = mainAccount;
    }

    public void run() {
        try {
// Infinate Loop
            while (true) {
                account.deposit();
                Thread.sleep(7500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}