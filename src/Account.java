import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private int balance;
    private Lock accessLock;
    private Condition sufficientFunds;

    // Construcutor to setup an Account with Locking System
    public Account() {
        balance = 0;
        accessLock = new ReentrantLock();
        sufficientFunds = accessLock.newCondition();
    }

    // Locking system for Handeling a deposit
    public void deposit() throws InterruptedException{
        int add = randTransaction.newNumber(true);
        accessLock.lock();
        try {
            if (add > 350)
            {
                System.out.printf("Flagged deposit over $350\n");
            }

            balance += add;
            System.out.printf("Agent %s deposits $%-3d -> -> -> -> -> -> -> -> -> -> -> ->  Balance is $%-3d\n", Thread.currentThread().getName(), add, balance);
            sufficientFunds.signalAll();
        } finally {
            accessLock.unlock();
        }
    }

    // Locking system for Handling a withdrawal
    public void withdrawl() throws InterruptedException {
        int sub = randTransaction.newNumber(false);
        accessLock.lock();
        try {
// If funds, perform transactions.
            if (balance > sub) {
                balance -= sub;
                System.out.printf("\t\t\t\t\t\t\t\t\tAgent %s withdrawals $%-3d\tBalance is $%-3d\n", Thread.currentThread().getName(), sub, balance);
            }

// If insufficient funds, wait until deposit to try again
            else {
                while (balance < sub) {
                    System.out.printf("\t\t\t\t\t\t\t\t\tAgent %s withdrawals $%-3d\twithdrawals - Blocked - Insufficient Funds\n", Thread.currentThread().getName(), sub);
                    sufficientFunds.await();
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            accessLock.unlock();
        }
    }
}