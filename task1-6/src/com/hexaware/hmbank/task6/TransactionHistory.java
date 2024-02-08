package com.hexaware.hmbank.task6;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class TransactionHistory {
    static class Transaction {
        String type;
        double amount;

        Transaction(String type, double amount) {
            this.type = type;
            this.amount = amount;
        }

        @Override
        public String toString() {
            return type + ": " + amount;
        }
    }

    static void bankTransactions(Scanner scanner, List<Transaction> transactions) {
        System.out.println("Enter the type of transaction (1 for deposit, 2 for withdrawal) or 0 to quit: ");
        int option = scanner.nextInt();

        switch (option) {
            case 1:
            case 2:
                System.out.println("Enter the amount of the transaction: ");
                double amount = scanner.nextDouble();
                String transactionType = option == 1 ? "Deposit" : "Withdrawal";
                transactions.add(new Transaction(transactionType, amount));
                bankTransactions(scanner, transactions);
                break;
            case 0:
                System.out.println("\nTransaction History:");
                for (int i = 0; i < transactions.size(); i++) {
                    System.out.println((i + 1) + ". " + transactions.get(i));
                }
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                bankTransactions(scanner, transactions);
                break;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Transaction> transactions = new ArrayList<>();
        bankTransactions(scanner, transactions);
        scanner.close();
    }
}
