public class Main {
        public static void main(String[] args) {

// Create Shared Bank Account
                Account account = new Account();

// Prepare Base Output
                System.out.printf("Deposit Threads\t\t\tWithdrawal Threads\t\tBalance\t\t\t\n");
                System.out.printf("---------------\t\t\t-----------------\t\t---------------\t\t\t\n");

// Setup Type of Threads
                depositThread d = new depositThread(account);
                withdrawlThread w = new withdrawlThread(account);

// Initialize threads
                Thread d1 = new Thread(d, "d1");
                Thread d2 = new Thread(d, "d2");
                Thread d3 = new Thread(d, "d3");
                Thread d4 = new Thread(d, "d4");
                Thread d5 = new Thread(d, "d5");

                Thread w1 = new Thread(w, "4");
                Thread w2 = new Thread(w, "5");
                Thread w3 = new Thread(w, "6");
                Thread w4 = new Thread(w, "7");
                Thread w5 = new Thread(w, "8");

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
        }
}