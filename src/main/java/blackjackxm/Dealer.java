package main.java.blackjackxm;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Dealer extends Player
{

    int standValue = 17; //Stand value for dealer

    public Stack<Card> shuffleCardDeck(Stack<Card> cards)
    {
        Collections.shuffle(cards);
        return cards;
    }


    @Override
    public void resolveCurrentCards()
    {
        totalSumofCards = myCards.stream().mapToInt(card->card.getValue()).sum();
        if(totalSumofCards<standValue)
            setHitDesired(true);
        else if(totalSumofCards>=standValue && totalSumofCards < bustValue)
        {
            setHitDesired(false);
            setStandDesired(true);
        }
        else if(totalSumofCards >= bustValue)
        {
            setBust(true);
            System.out.println("Dealer BUSTS!");
        }



    }
}
