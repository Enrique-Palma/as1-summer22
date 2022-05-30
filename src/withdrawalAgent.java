/********************************************************************************
 Enrique Palma Project 1
 CNT4714 Summer C001
 Title:  “Project 1:  An Application Employing Synchronized/Cooperating Multiple
 Threads In Java Using Locks – A Banking Simulator”
 Points:   100 points
 Due Date:  Sunday June 5, 2022 by 11:59 pm (WebCourses time)
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