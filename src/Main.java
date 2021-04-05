
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        //Get the number of integer to play from the user
        System.out.println("Input the length of the secret code:");
        String secretCodeInput = scanner.nextLine();

        if (!secretCodeInput.matches("\\d+") || Integer.parseInt(secretCodeInput) <= 0) {
            System.out.println("Error: " + secretCodeInput + " isn't a valid number.");
            return;
        }
        int n = Integer.parseInt(secretCodeInput);

        //Get the number of different character the secret code will contain. Number should be greater than the
        //secret code and less than or equals 36
        System.out.println("Input the number of possible symbols in the code:");

        String characterAmountInput = scanner.nextLine();
        if (!characterAmountInput.matches("\\d+") || Integer.parseInt(characterAmountInput) <= 0) {
            System.out.println("Error: " + characterAmountInput + " isn't a valid number.");
            return;
        } else if (n > Integer.parseInt(characterAmountInput)) {
            System.out.println("Error: it's not possible to generate a code with a length of " + n + " with " +
                    Integer.parseInt(characterAmountInput) + " unique symbols.");
            return;
        } else if (Integer.parseInt(characterAmountInput) > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            return;
        }
        int m = Integer.parseInt(characterAmountInput);

        //Declaring all possible characters that a secret code contain
        List<String> totalCharacters = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c",
                "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
                "x", "y", "z");

        List<String> clone = new ArrayList<>(totalCharacters);


        //Creating the list of the characters the secret code will be made of according to user input (m)
        List<String> codeCharacters = totalCharacters.subList(0, m);

        //Generate a random secret number according to user input (n)
        List<String> secretCode = null;
        if (n > 36) {
            System.out.println("Error: can't generate a secret number with a length more than 36 because " +
                    "there aren't enough unique characters.");
        } else {
            Collections.shuffle(codeCharacters);
            secretCode = codeCharacters.subList(0, n);
            //System.out.println(secretCode);
        }

        //Prints the secret code preparation text according to user input (m)
        if (m > 0 && m < 11) {
            System.out.println("The secret is prepared: " + generateStars(n) + " (0-" + clone.get(m - 1) + ")");
            System.out.println("Okay, let's start a game!");
        } else if (m >= 11 && m <= 36) {
            System.out.println("The secret is prepared: " + generateStars(n) + " (0-9, a-" + clone.get(m - 1) + ")");
            System.out.println("Okay, let's start a game!");
        }

        //This flag controls whether game ends or not, until all numbers are correctly guessed
        boolean flag = false;
        int turn = 1;
        while (!flag) {

            System.out.println("Turn " + turn + ":");
            List<String> guessedCode = Arrays.asList(scanner.nextLine().split(""));

            //We grade the given input here for how many bulls and cows it has
            int bull = 0;
            int cow = 0;

            for (int inputIndex = 0; inputIndex < n; inputIndex++) {
                for (int secretIndex = 0; secretIndex < n; secretIndex++) {
                    if (guessedCode.get(inputIndex).equals(secretCode.get(secretIndex))) {
                        if (inputIndex == secretIndex) {
                            bull++;
                        }
                        cow++;
                    }
                }
            }

            cow -= bull;

            //We give the output to the user of his guess
            if (cow > 0 && bull > 0) {
                System.out.println("Grade: " + bull + " bull(s) and " + cow + " cow(s)");
            } else if (cow > 0 && bull == 0) {
                System.out.println("Grade: " + cow + " cow(s)");
            } else if (bull > 0 && cow == 0 && bull < n) {
                System.out.println("Grade: " + bull + " bull(s)");
            } else if (bull == 0 && cow == 0) {
                System.out.println("Grade: None");
            } else if (bull == n) {
                System.out.println("Grade: " + bull + " bulls");
                System.out.println("Congratulations! You guessed the secret code.");
                flag = true;
                break;
            }
            turn++;
        }

    }

    private static StringBuilder generateStars(int number) {
        StringBuilder stars = new StringBuilder();
        for (int i = 0; i < number; i++) {
            stars.append("*");
        }
        return stars;
    }
}


