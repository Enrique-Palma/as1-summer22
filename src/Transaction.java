/********************************************************************************
 Enrique Palma Project 1
 CNT4714 Summer C001
 Title:  “Project 1:  An Application Employing Synchronized/Cooperating Multiple
 Threads In Java Using Locks – A Banking Simulator”
 Points:   100 points
 Due Date:  Sunday June 5, 2022 by 11:59 pm (WebCourses time)
 *********************************************************************************/
import java.util.Random;
public class Transaction extends Throwable
{
    // true = deposit, false = withdrawal
    public static int newNumber(boolean operator)
    {
        int randomAmmount = 0;
        // If depositing money should be
        if (operator == true)
        {
            randomAmmount = integerRange(1, 500);
            return randomAmmount;
            // If withdrawing money
        }
        else
        {
            randomAmmount = integerRange(1, 99);
            return randomAmmount;
        }
    }

    // keeps range.
    private static int integerRange(int min, int max)
    {
        Random randomGenerator = new Random();
        int i = 0;
        // Loops until generates Random Number within range
        while (min >= i || max <= i)
        {
            i = randomGenerator.nextInt();
        }
        return i;
    }
}