/********************************************************************************
 Enrique Palma Project 1
 CNT4714 Summer C001
 Title:  “Project 1:  An Application Employing Synchronized/Cooperating Multiple
 Threads In Java Using Locks – A Banking Simulator”
 Points:   100 points
 Due Date:  Sunday June 5, 2022 by 11:59 pm (WebCourses time)
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