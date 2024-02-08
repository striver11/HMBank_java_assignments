package com.hexaware.hmbank.task4;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Random;

public class DataValidation {
    static Map<String, Double> bankAccounts = new HashMap<String, Double>() {{
        put("1234567890", 5000.0);
        put("0987654321", 10000.0);
        put("1122334455", 7500.0);
        put("5566778899", 3000.0);
    }};

    static void checkBalance(Scanner scanner) {
        System.out.println("Enter your account number: ");
        String accountNumber = scanner.nextLine();

        if (bankAccounts.containsKey(accountNumber)) {
            // Generate and display OTP
            int otp = generateOTP();
            System.out.println("OTP for your account is: " + otp);
            System.out.println("Please check your registered mobile number for the OTP.");

            // Verify OTP
            boolean isOtpVerified = verifyOTP(otp, scanner);

            if (isOtpVerified) {
                System.out.println("Your account balance is: " + bankAccounts.get(accountNumber));
            } else {
                System.out.println("Invalid OTP. Please try again later.");
            }
        } else {
            System.out.println("Invalid account number. Please try again.");
            checkBalance(scanner);
        }
    }

    static int generateOTP() {
        // Generate a random 4-digit OTP
        return new Random().nextInt(9000) + 1000;
    }

    static boolean verifyOTP(int generatedOTP, Scanner scanner) {
        int attempts = 3;

        while (attempts > 0) {
            System.out.print("Enter OTP received on your registered mobile number: ");
            int enteredOTP = scanner.nextInt();

            if (enteredOTP == generatedOTP) {
                System.out.println("OTP verified successfully.");
                return true;
            } else {
                System.out.println("Invalid OTP. Attempts left: " + --attempts);
            }
        }

        System.out.println("Too many incorrect attempts. Account blocked.");
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        checkBalance(scanner);
        scanner.close();
    }
}
