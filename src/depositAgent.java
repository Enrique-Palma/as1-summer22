/********************************************************************************
 Enrique Palma Project 1
 Course: CNT 4714 Summer 2022
 Assignment title: Project 1 – Synchronized, Cooperating Threads Under Locking
 Due Date: June 5, 2022
 *********************************************************************************/
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

// loop for Deposit Thread
public class depositAgent implements Runnable
{
    private Account account;

    // Constructor to link thread to account
    public depositAgent(Account mainAccount)
    {
        account = mainAccount;
    }

    public void run() throws RuntimeException
    {
        try
        {
            // Infinite Loop
            while (true)
            {
                account.deposit();
                Random random = new Random();
                int value = random.nextInt(7500);
                Thread.sleep(value);
            }
        }
        catch (InterruptedException | FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}