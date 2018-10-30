package main.java.blackjackxm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Player implements IPlayer
{
    protected boolean myTurn = false;
    protected List<Card> myCards = new ArrayList<>();
    private boolean bust = false;
    private boolean hitDesired = false;
    private boolean standDesired = false;
    protected int bustValue = 22;
    protected int totalSumofCards = 0;

    public boolean isMyTurn()
    {
        return myTurn;
    }

    public void setMyTurn(boolean myTurn)
    {
        this.myTurn = myTurn;
    }

    @Override
    public boolean isBust()
    {
        return bust;
    }

    public void setBust(boolean bust)
    {
        this.bust = bust;
    }

    public List<Card> getMyCards()
    {
        return myCards;
    }

    public boolean isHitDesired()
    {
        return hitDesired;
    }

    public void setHitDesired(boolean hitDesired)
    {
        this.hitDesired = hitDesired;
    }

    public boolean isStandDesired()
    {
        return standDesired;
    }

    public void setStandDesired(boolean standDesired)
    {
        this.standDesired = standDesired;
    }

    public int getTotalSumofCards()
    {
        return totalSumofCards;
    }

    public void setTotalSumofCards(int totalSumofCards)
    {
        this.totalSumofCards = totalSumofCards;
    }

    @Override
    public void reset()
    {
        myCards.clear();
        setMyTurn(false);
        setBust(false);
        setHitDesired(false);
        setStandDesired(false);
        setTotalSumofCards(0);
    }

    @Override
    public void receiveCard(Card card)
    {
        myCards.add(card);
    }

    @Override
    public void receiveCards(Card[] cards)
    {
        myCards.addAll(Arrays.asList(cards));
    }
}
