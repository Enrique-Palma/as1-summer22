/********************************************************************************
Enrique Palma Project 1
CNT4714 Summer C001
Title:  “Project 1:  An Application Employing Synchronized/Cooperating Multiple
        Threads In Java Using Locks – A Banking Simulator”
        Points:   100 points
        Due Date:  Sunday June 5, 2022 by 11:59 pm (WebCourses time)
*********************************************************************************/
import java.io.*;
import java.io.IOException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class Account
{
    private int balance;
    private Lock accessLock;
    private Condition sufficientFunds;

    // Constructor to setup an Account with Locking System
    public Account()
    {
        balance = 0;
        accessLock = new ReentrantLock();
        sufficientFunds = accessLock.newCondition();
    }

    // Locking system for Handling a deposit
    public void deposit() throws InterruptedException, IOException
    {
        int add = Transaction.newNumber(true);
        accessLock.lock();

        //flagging to text file - appending - START of CODE BLOCK
        java.util.Date date= new java.util.Date();
        try (FileWriter f = new FileWriter("flaggedTransactionsLog.txt", true);
             BufferedWriter b = new BufferedWriter(f);
             PrintWriter p = new PrintWriter(b);)
        {
            if(add > 350)
            {
                p.println("Depositor  Agent ID:" + "\t" + Thread.currentThread().getName() + " issued deposit\s\s\s of $"+ add + "\t"  + " Timestamp: " + date.toString());
                System.out.printf("\n***Flagged deposit over $350 - Depositor Agent " + Thread.currentThread().getName()+" amount $" + add +"\sCheck log file\n\n");
            }
        }
        catch (IOException i)
        {
            i.printStackTrace();
        }
        //flagging to text file - appending - END of CODE BLOCK

        try
        {
            balance += add;
            System.out.printf("Agent %s deposits $%-3d -> -> -> -> -> -> -> ->\s    (+) Balance is $%-3d\n", Thread.currentThread().getName(), add, balance);
            sufficientFunds.signalAll();
        }
        finally
        {
            accessLock.unlock();
        }
    }

    // Locking system for Handling a withdrawal
    public void withdrawal() throws InterruptedException ,IOException
    {
        int sub = Transaction.newNumber(false);
        accessLock.lock();

        //flagging to text file - appending - START of CODE BLOCK
        java.util.Date date= new java.util.Date();
        try (FileWriter f = new FileWriter("flaggedTransactionsLog.txt:", true);
             BufferedWriter b = new BufferedWriter(f);
             PrintWriter p = new PrintWriter(b);)
        {
            if(sub > 75)
            {
                //System.out.printf("Flagged deposit over $350\n");
                p.println("Withdrawal Agent ID:" + "\t" + Thread.currentThread().getName() + "\sissued withdrawal of $" + sub+  "\t"  + " Timestamp: " + date.toString());
                System.out.printf("\n***Flagged withdrawal over $75 - Withdrawal Agent " + Thread.currentThread().getName()+" amount $" + sub +"\sCheck log file\n\n");
            }
        }
        catch (IOException i)
        {
            i.printStackTrace();
        }
        //flagging to text file - appending - END of CODE BLOCK

        try
        {
            // If funds, perform transactions.
            if (balance > sub)
            {
                balance -= sub;
                System.out.printf("\t\t\t\t\t\tAgent %s withdrawals $%-3d\t(-) Balance is $%-3d\n", Thread.currentThread().getName(), sub, balance);
            }
            // If insufficient funds, wait until deposit to try again
            else
            {
                while (balance < sub)
                {
                    System.out.printf("\t\t\t\t\t\tAgent %s withdrawals $%-3d\t(****)withdrawals - Blocked - Insufficient Funds\n", Thread.currentThread().getName(), sub);
                    sufficientFunds.await();
                }
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        finally
        {
            accessLock.unlock();
        }
    }
}