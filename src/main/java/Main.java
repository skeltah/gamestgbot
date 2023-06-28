import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        // static variables
        ArrayList<String> wordList = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();


        String choosenWord = "already initialized below.";
        String userAnswer;
        String[] symbolsGuessed = new String[]{"1","2","3","4","5"};

        int errorCounter = 0;



        // array loading ( wordlist );
        {

            String filePath = "C:\\Users\\heltah\\Desktop\\russian_nouns.txt";

            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                System.out.println("\nStarted loading array!");
                while (true) {
                    try {
                        if ((line = br.readLine()) == null) break;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    String[] words = line.split(" ");
                    for (String word : words) {
                        if (word.length() >= 5) {
                            wordList.add(word);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("\nWord array loaded!");

        }
        //load 5-letter word to choosenWord;
        {
            while(true)
            {
                String currentWord = wordList.get(rand.nextInt(wordList.size()));
                if(currentWord.length()==5)
                {
                    choosenWord = currentWord;
                    break;
                }
            }
        }

        while(true) {

            // getting guess from user.
            while (true) {
                System.out.print("\nВводите слово: ");
                userAnswer = scan.nextLine();
                if (userAnswer.length() == 5) {
                    break;
                } else {
                    System.out.println("\nСлово неверного формата.");
                }
            }

            // working with correct guesses
            for (int i = 0; i < choosenWord.length(); i++) {

                if (choosenWord.charAt(i) == userAnswer.charAt(i)) {
                    symbolsGuessed[i] = "\uD83D\uDFE9"; // green symbol
                }
                else {

                    if (choosenWord.contains(String.valueOf(userAnswer.charAt(i)))) {
                        symbolsGuessed[i] = "\uD83D\uDFE8"; // yellow symbol
                    } else {
                        symbolsGuessed[i] = "\uD83D\uDFE5"; // red symbol
                    }
                }
            }

            // outputting correct guesses
            String tempOutput = "              ";

            for(String currentSymbol : symbolsGuessed)
            {
                tempOutput = tempOutput + currentSymbol;
            }

            System.out.println(tempOutput);

            // check win/loose.

            if (errorCounter < 15) {

                if (!choosenWord.equalsIgnoreCase(userAnswer)) {
                    System.out.print("Слово неверное!");
                    errorCounter++;
                } else {
                    System.out.println("Верное слово: " + choosenWord);
                    break;
                }

            }
            else
            {
                System.out.println("Достигнуто максимальное количество ошибок.\nКонец игры!\n\nправильным словом было: " + choosenWord);
                break;
            }

        }














    }
}