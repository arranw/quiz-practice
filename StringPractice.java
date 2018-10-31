import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class StringPractice {
	static Scanner kb = new Scanner(System.in);
	static int questions;
	static int correctAnswers;
	static String[][] testStrings;
	static Random r = new Random();

	public static void main(String[] args) throws IOException {
		String[] testNames = { "Length Method", "Compare Method", "Substring Method", "CharAt Method" };
		int testQty = testNames.length;
		int testChoice;

		do {
			System.out.println("==============================");
			System.out.println("Test List:");
			for (int i = 0; i < testNames.length; i++) {
				System.out.println("-" + testNames[i] + " (" + i + ")");
			}
			System.out.println("==============================");
			testChoice = checkedInt("Enter test number: ");
			while (testChoice < -1 || testChoice > testQty - 1) {
				System.out.println("Invalid entry.");
				testChoice = checkedInt("Enter test number:");
			}

			switch (testChoice) {
			case 0:
				lengthPractice();
				break;
			case 1:
				comparePractice();
				break;
			case 2:
				substringPractice();
				break;
			case 3:
				charAtPractice();
				break;
			}
		} while (testChoice != -1);
	}

	private static void charAtPractice() throws IOException {
		initializeTest(".charAt() Practice", 1);
		
		for (int i = 0; i < questions; i++) {
			int index = r.nextInt(testStrings[0][i].length());
			System.out.println("str" + i + " = " + testStrings[0][i]);
			System.out.println("str" + i + ".charAt(" + index + ")");
			
			String answer = "" + testStrings[0][i].charAt(index);
			System.out.print("\nPredict the output: ");
			String guess = kb.next();

			if (guess.equals(answer)) {
				System.out.println("Correct");
				correctAnswers++;
			} else {
				System.out.println("Incorrect, answer is " + answer);
			}
			System.out.print("\n");
		}
		printScore(correctAnswers, questions);
	}

	private static void substringPractice() throws IOException {
		initializeTest(".subString() Practice", 1);

		for (int i = 0; i < questions; i++) {
			int beginIndex = r.nextInt(testStrings[0][i].length() - 2);
			int endIndex = r.nextInt(testStrings[0][i].length() - beginIndex + 1) + beginIndex;

			if (beginIndex - endIndex == 0) {
				endIndex++;
			}

			System.out.println("String str" + i + " = " + testStrings[0][i]);
			System.out.println("str" + i + ".substring(" + beginIndex + "," + endIndex + ");");

			System.out.print("\nPredict the output: ");
			String guess = kb.next();

			String answer = testStrings[0][i].substring(beginIndex, endIndex);

			if (guess.equals(answer)) {
				System.out.println("Correct");
				correctAnswers++;
			} else {
				System.out.println("Incorrect, answer is " + answer);
			}
			System.out.print("\n");
		}
		printScore(correctAnswers, questions);
	}

	private static void comparePractice() throws IOException {
		initializeTest(".compareTo() Practice", 2);

		int difficulty = checkedInt("Easy (0) or hard (1): ");

		for (int i = 0; i < questions; i++) {
			System.out.println("String one" + i + " = \"" + testStrings[0][i] + "\";");
			System.out.println("String two" + i + " = \"" + testStrings[1][i] + "\";");
			System.out.println("first" + i + ".compareTo(" + "second" + i + ");\n");

			int guess;
			int answer;

			switch (difficulty) {
			case 0: // easy questions
				guess = checkedInt("Positive(1), Negative(-1), or Even(0): ");
				answer = testStrings[0][i].compareTo(testStrings[1][i]);
				if (answer == 0 && guess == 0 || answer < 0 && guess == -1 || answer > 0 && guess == 1) {
					System.out.println("Correct");
					correctAnswers++;
				} else {
					System.out.println("Incorrect");
				}
				break;
			case 1: // hard questions
				guess = checkedInt("What will be returned?: ");
				answer = testStrings[0][i].compareTo(testStrings[1][i]);
				if (guess == answer) {
					System.out.println("Correct");
					correctAnswers++;
				} else {
					System.out.println("Incorrect, answer is " + answer);
				}
				break;
			}
			System.out.print("\n");
		}
		printScore(correctAnswers, questions);
	}

	private static void lengthPractice() throws IOException {
		initializeTest(".length() Practice", 1);

		for (int i = 0; i < questions; i++) {
			System.out.println("String str" + i + " = \"" + testStrings[0][i] + "\";");
			System.out.print("System.out.println(str" + i + ".length());\n");
			
			int answer = testStrings[0][i].length();
			int guess = checkedInt("\nPredict the output: ");

			if (guess == answer) {
				System.out.println("Correct");
				correctAnswers++;
			} else {
				System.out.println("Incorrect");
			}
			System.out.print("\n");
		}
		printScore(correctAnswers, questions);
	}

	private static void printScore(int correctAnswers, int questions) throws IOException {
		System.out.println("Score: " + correctAnswers + "/" + questions);
		pause();
	}

	public static void pause() throws IOException {
		System.out.println("(Enter)");
		System.in.read();
	}

	public static String[] generateRandomWords(int numberOfWords) {
		String[] words = new String[numberOfWords];
		Random r = new Random();

		for (int i = 0; i < numberOfWords; i++) {
			char[] word = new char[r.nextInt(9) + 3];
			for (int j = 0; j < word.length; j++) {
				word[j] = (char) (r.nextInt(26) + 'a');
			}
			words[i] = new String(word);
		}

		return words;
	}

	private static int checkedInt(String prompt) {
		System.out.print(prompt);
		while (!kb.hasNextInt()) {
			System.out.println("NaN");
			System.out.print(prompt);
			kb.next();
		}
		System.out.print("\n");
		return kb.nextInt();
	}

	private static void printHeader(String header) {
		String headerLine = "";
		for (int i = 0; i < header.length(); i++) {
			headerLine += "=";
		}
		System.out.println(headerLine + "\n" + header + "\n" + headerLine);
	}

	private static void initializeTest(String title, int lists) {
		printHeader(title);
		questions = checkedInt("How many examples do you want? ");
		testStrings = new String[lists][questions];
		
		for (int i = 0; i < lists; i++) {
			testStrings[i] = generateRandomWords(questions);
		}
		
		correctAnswers = 0;
		
	}

}
