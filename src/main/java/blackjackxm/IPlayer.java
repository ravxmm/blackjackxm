package main.java.blackjackxm;

import java.util.ArrayList;
import java.util.List;

public interface IPlayer
{


    void resolveCurrentCards();

    void receiveCard(Card card);
    void receiveCards(Card[] cards);
    void reset();
    boolean isBust();

}
