import java.io.IOException;

// Running loop for Withdrawl Thread
public class withdrawlThread implements Runnable {
    private Account account;

    // Constructor to link thread to main shared account
    public withdrawlThread (Account mainAccount) {
        account = mainAccount;
    }

    public void run() {
        try {
// Infinate Loop
            while (true) {
                account.withdrawl();
                Thread.sleep(5);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}