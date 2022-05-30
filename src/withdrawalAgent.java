/********************************************************************************
 Enrique Palma Project 1
 Course: CNT 4714 Summer 2022
 Assignment title: Project 1 – Synchronized, Cooperating Threads Under Locking
 Due Date: June 5, 2022
 *********************************************************************************/
import java.io.IOException;
import java.util.Random;

// loop for Withdrawal Thread
public class withdrawalAgent implements Runnable
{
    private Account account;
    // Constructor to link thread to main account
    public withdrawalAgent(Account mainAccount)
    {
        account = mainAccount;
    }
    public void run()
    {
        try
        {
            // Infinite Loop
            while (true)
            {
                account.withdrawal();
                Random random = new Random();
                int value = random.nextInt(5);
                Thread.sleep(value);
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}