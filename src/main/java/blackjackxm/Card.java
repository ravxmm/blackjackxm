package main.java.blackjackxm;

import java.util.HashMap;
import java.util.Map;

public class Card
{
    private boolean isAce;
    private int value;
    private char letter;
    private CardType type;
    public static HashMap<Character,AJQKType> letterToWordMap;
    public enum AJQKType
    {
        ACE,
        JACK,
        QUEEN,
        KING
    }

    public enum CardType
    {
        HEARTS,
        DIAMONDS,
        CLUBS,
        SPADES
    }
    static
    {
        letterToWordMap = new HashMap<>();
        letterToWordMap.put('A', AJQKType.ACE);
        letterToWordMap.put('J', AJQKType.JACK);
        letterToWordMap.put('Q', AJQKType.QUEEN);
        letterToWordMap.put('K', AJQKType.KING);

    }

    public Card(boolean isAce, int value, char letter, CardType type)
    {
        this.isAce = isAce;
        this.letter = letter;
        this.value = value;
        this.type = type;
    }

    public boolean isAce()
    {
        return isAce;
    }

    public int getValue()
    {
        return value;
    }

    public char getLetter()
    {
        return letter;
    }

    public CardType getType()
    {
        return type;
    }

    @Override
    public String toString()
    {
        String returnStr = "";
        if(letter!='\u0000')
        {
            returnStr = String.format("%s (%c) of %s (%d)", letterToWordMap.get(letter),letter,type.toString(), value);
        }
        else
        {
            returnStr = String.format("%d of %s", value, type.toString());
        }
        return returnStr;
    }

    public static char getAJQKChar(AJQKType ajqkType)
    {
        Map.Entry<Character,AJQKType> keyValueSet;
        keyValueSet = letterToWordMap.entrySet().stream().filter(entry->entry.getValue()==ajqkType).findAny().get();
        return keyValueSet.getKey();
    }


}
