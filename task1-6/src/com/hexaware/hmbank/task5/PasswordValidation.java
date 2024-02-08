package com.hexaware.hmbank.task5;
import java.util.Scanner;
import java.util.regex.Pattern;
public class PasswordValidation {
	static void validatePassword(Scanner scanner) {
        System.out.println("Create a password for your bank account: ");
        String password = scanner.nextLine();

        if (password.length() < 8) {
            System.out.println("Invalid password. It must be at least 8 characters long.");
            validatePassword(scanner);
        } else if (!Pattern.compile("[A-Z]").matcher(password).find()) {
            System.out.println("Invalid password. It must contain at least one uppercase letter.");
            validatePassword(scanner);
        } else if (!Pattern.compile("[0-9]").matcher(password).find()) {
            System.out.println("Invalid password. It must contain at least one digit.");
            validatePassword(scanner);
        } else {
            System.out.println("Your password is valid.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        validatePassword(scanner);
        scanner.close();
    
}
}
