/********************************************************************************
 Enrique Palma Project 1
 Course: CNT 4714 Summer 2022
 Assignment title: Project 1 – Synchronized, Cooperating Threads Under Locking
 Due Date: June 5, 2022
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