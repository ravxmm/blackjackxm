package main.java.blackjackxm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HumanPlayer extends Player
{

    public void resolveCurrentCards()
    {
        showHumanCards();
        if (totalSumofCards >= bustValue)
        {
            setBust(true);
            System.out.println("Player BUSTS!");
        }
        if(!isBust())
            handleHumanInput();


    }

    private void showHumanCards()
    {
        System.out.println("Your current cards: ");
        for (Card card : myCards)
            System.out.println(card);
        totalSumofCards = myCards.stream().mapToInt(card -> card.getValue()).sum();
        System.out.println("Sum: " + totalSumofCards);
    }

    private void handleHumanInput()
    {

        try
        {
            int in = 0;
            boolean validInput = false;
            while (!validInput)
            {
                InputStreamReader inputStream = new InputStreamReader(System.in);
                BufferedReader bufferedReader = new BufferedReader(inputStream);
                System.out.println("Enter 'H' for Hit or 'S' for Stand");
                /*while ((in = bufferedReader.read()) != -1)
                {*/
                in = bufferedReader.read();
                char inputChar = (char) in;
                if (inputChar == 'H' || inputChar == 'h')
                {
                    validInput = true;
                    setHitDesired(true);
                    break;

                }
                else if (inputChar == 'S' || inputChar == 's')
                {
                    validInput = true;
                    setHitDesired(false);
                    setStandDesired(true);
                    break;
                }
                else
                {
                    System.out.println("Invalid Input. Please Try Again.");
                    showHumanCards();
                }
                //}

            }


        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
