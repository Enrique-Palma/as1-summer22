import java.io.FileNotFoundException;
import java.io.IOException;

// Running loop for Deposit Thread
public class depositThread implements Runnable {
    private Account account;

    // Constructor to link thread to main shared account
    public depositThread (Account mainAccount) {
        account = mainAccount;
    }

    public void run() throws RuntimeException {
        try {
// Infinite Loop
            while (true) {
                account.deposit();
                Thread.sleep(7500);
            }
        } catch (InterruptedException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}