/*
 * Class: CMSC203 
 * Instructor: Dr. Grinberg
 * Description: (Give a brief description for each Class)
 * Due: 02/17/2025
 * Platform/compiler: Github
 * I pledge that I have completed the programming assignment 
* independently. I have not copied the code from a student or   * any source. I have not given my code to any student.
 * Print your Name here: Adonai Getahun
*/


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class ESPGame {
    public static void main(String[] args) {
        final String FILE_NAME = "colors.txt";
        final int MAX_COLORS = 16;
        Scanner input = new Scanner(System.in);
        Random rand = new Random();
        
        System.out.println("CMSC203 Assignment1: Test your ESP skills!");
        System.out.println("Welcome to ESP - extrasensory perception!");
        
        while (true) {
            System.out.println("\nWould you please choose one of the 4 options from the menu:");
            System.out.println("1- Read and display the first 16 colors from a file.");
            System.out.println("2- Read and display the first 10 colors from a file.");
            System.out.println("3- Read and display the first 5 colors from a file.");
            System.out.println("4- Exit the program.");
            System.out.print("Enter your option: ");
            
            int option = input.nextInt();
            input.nextLine(); // Consume newline
            
            if (option == 4) {
                System.out.println("Exiting the program...");
                break;
            }
            
            int numColors = option == 1 ? 16 : (option == 2 ? 10 : 5);
            String[] colors = new String[MAX_COLORS];
            
            try {
                Scanner fileScanner = new Scanner(new File(FILE_NAME));
                int index = 0;
                while (fileScanner.hasNextLine() && index < MAX_COLORS) {
                    colors[index] = fileScanner.nextLine().trim();
                    index++;
                }
                fileScanner.close();
            } catch (FileNotFoundException e) {
                System.out.println("Error: File not found.");
                continue;
            }
            
            System.out.println("There are " + numColors + " colors from the file:");
            for (int i = 0; i < numColors; i++) {
                System.out.println((i + 1) + " " + colors[i]);
            }
            
            int correctGuesses = 0;
            for (int round = 1; round <= 3; round++) {
                System.out.println("\nRound " + round);
                System.out.println("I am thinking of a color.");
                
                int randomIndex = rand.nextInt(numColors);
                String selectedColor = colors[randomIndex];
                
                System.out.print("Enter your guess: ");
                String userGuess = input.nextLine().trim();
                
                System.out.println("I was thinking of " + selectedColor + ".");
                
                if (userGuess.equalsIgnoreCase(selectedColor)) {
                    correctGuesses++;
                }
            }
            
            System.out.println("\nGame Over");
            System.out.println("You guessed " + correctGuesses + " out of 3 colors correctly.");
            
            System.out.print("Would you like to continue the game? Type Yes/No: ");
            String playAgain = input.nextLine().trim();
            if (!playAgain.equalsIgnoreCase("Yes")) {
                break;
            }
        }
        
        System.out.print("Enter your name: ");
        String userName = input.nextLine();
        
        System.out.print("Enter a description about yourself: ");
        String userDescription = input.nextLine();
        
        System.out.print("Enter Due Date (MM/DD/YY): ");
        String dueDate = input.nextLine();
        
        System.out.println("\nGame Over");
        System.out.println("Username: " + userName);
        System.out.println("User Description: " + userDescription);
        System.out.println("Due Date: " + dueDate);
        
        try {
            PrintWriter writer = new PrintWriter("EspGameResults.txt");
            writer.println("Game Over");
            writer.println("Username: " + userName);
            writer.println("User Description: " + userDescription);
            writer.println("Due Date: " + dueDate);
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: Could not write to file.");
        }
        
        input.close();
    }
}
