import java.io.*;
import java.io.IOException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
public class Account
{
    private int balance;
    private Lock accessLock;
    private Condition sufficientFunds;

    // Construcutor to setup an Account with Locking System
    public Account()
    {
        balance = 0;
        accessLock = new ReentrantLock();
        sufficientFunds = accessLock.newCondition();
    }

    // Locking system for Handeling a deposit
    public void deposit() throws InterruptedException, IOException
    {
        int add = randTransaction.newNumber(true);
        accessLock.lock();

        //checks for deposit over $350
        if(add > 350)
        {
            System.out.printf("Flagged deposit over $350\n");
        }
        ////////////////////////////////////////////////////

        //testing flagging to text file - appending
        java.util.Date date= new java.util.Date();
        try (FileWriter f = new FileWriter("flaggedTransactionsLog.txt:", true);
             BufferedWriter b = new BufferedWriter(f);
             PrintWriter p = new PrintWriter(b);) {
            if(add > 350)
            {
                //System.out.printf("Flagged deposit over $350\n");
                p.println("Depositor  Agent ID:" + "\t" + Thread.currentThread().getName() + " issued deposit\t of $"+ add + "\t"  + " Timestamp: " + date.toString());
                //p.println(Thread.currentThread().getName());
                //p.println();
            }

           /* p.println("Gaura");
            p.println("Bori");*/

        } catch (IOException i) {
            i.printStackTrace();
        }

        try
        {
            balance += add;
            System.out.printf("Agent %s deposits $%-3d -> -> -> -> -> -> -> -> \t    (+) Balance is $%-3d\n", Thread.currentThread().getName(), add, balance);
            sufficientFunds.signalAll();
        }
        finally
        {
            accessLock.unlock();
        }
    }

    // Locking system for Handling a withdrawal
    public void withdrawl() throws InterruptedException ,IOException
    {
        int sub = randTransaction.newNumber(false);
        accessLock.lock();

        //checks for withdrawal over $75
        if(sub > 75)
        {
            System.out.printf("Flagged deposit over $75\n");
        }
        ////////////////////////////////////////////////////

        //testing flagging to text file - appending
        java.util.Date date= new java.util.Date();
        try (FileWriter f = new FileWriter("flaggedTransactionsLog.txt:", true);
             BufferedWriter b = new BufferedWriter(f);
             PrintWriter p = new PrintWriter(b);) {
            if(sub > 75)
            {
                //System.out.printf("Flagged deposit over $350\n");
                p.println("Withdrawal Agent ID:" + "\t" + Thread.currentThread().getName() + " issued withdrawal of $" + sub+  "\t"  + " Timestamp: " + date.toString());
                //p.println(Thread.currentThread().getName());
                //p.println();
            }

           /* p.println("Gaura");
            p.println("Bori");*/

        } catch (IOException i) {
            i.printStackTrace();
        }

        try
        {
            /*if(sub > 75)
            {
                System.out.printf("Flagged withdrawal over $75\n");
            }*/
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

        } catch (InterruptedException e)
        {
            e.printStackTrace();
        } finally
                {
                    accessLock.unlock();
                }
    }
}