/********************************************************************************
 Enrique Palma Project 1
 Course: CNT 4714 Summer 2022
 Assignment title: Project 1 – Synchronized, Cooperating Threads Under Locking
 Due Date: June 5, 2022
 *********************************************************************************/
public class Main
{
        public static void main(String[] args)
        {
                // Create Shared Bank Account
                Account account = new Account();
                // Prepare Base Output
                System.out.printf("Deposit Agents\t\t\tWithdrawal Agents\t\t\tBalance\t\t\t\n");
                System.out.printf("--------------------------------------------------------------------\n");
                // Setup Type of Threads
                depositAgent d = new depositAgent(account);
                withdrawalAgent w = new withdrawalAgent(account);
                // Initialize threads
                Thread d1 = new Thread(d, "DT1");
                Thread d2 = new Thread(d, "DT2");
                Thread d3 = new Thread(d, "DT3");
                Thread d4 = new Thread(d, "DT4");
                Thread d5 = new Thread(d, "DT5");
                //////////////////////////////////////
                Thread w1 = new Thread(w, "WT1");
                Thread w2 = new Thread(w, "WT2");
                Thread w3 = new Thread(w, "WT3");
                Thread w4 = new Thread(w, "WT4");
                Thread w5 = new Thread(w, "WT5");
                Thread w6 = new Thread(w, "WT6");
                Thread w7 = new Thread(w, "WT7");
                Thread w8 = new Thread(w, "WT8");
                Thread w9 = new Thread(w, "WT9");
                Thread w10 = new Thread(w, "W10");
                // Start threads
                d1.start();
                w1.start();
                d2.start();
                w2.start();
                w3.start();
                w4.start();
                w5.start();
                d3.start();
                d4.start();
                d5.start();
                w6.start();
                w7.start();
                w8.start();
                w9.start();
                w10.start();
        }
}