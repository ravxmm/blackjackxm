package main.java.blackjackxm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class GameManager
{
    //BlackJackTable table;
    boolean done;
    Thread gameThread;
   /* private Dealer dealer;
    private HumanPlayer human;*/
    private Stack<Card> cardDeck;
    private Player[] players;
    public GameManager()
    {
        //table = new BlackJackTable();
        gameThread = new Thread(()->runGame());
        /*dealer = new Dealer();
        human = new HumanPlayer();*/
        cardDeck = new Stack<>();
        players = new Player[2];
    }

    public void initializeGame()
    {
        gameThread.setName("Game Running...");
        players[0] = new HumanPlayer();
        players[1] = new Dealer();
        gameThread.start();
    }

    private void runGame()
    {
        while(!done)
        {
            setupGame();
            try
            {
                for(int i=0;i<players.length;i++)
                {
                    players[i].setMyTurn(true);
                    while(players[i].isMyTurn())
                    {
                        players[i].resolveCurrentCards();
                        if(players[i].isBust())
                            players[i].setMyTurn(false);
                        else if(players[i].isHitDesired())
                            players[i].receiveCard(cardDeck.pop());
                        else if(players[i].isStandDesired())
                            players[i].setMyTurn(false);
                    }
                }
                done = true;

                int playerTotal = players[0].getTotalSumofCards();
                int dealerTotal = players[1].getTotalSumofCards();
                System.out.println("Total for Dealer: " + dealerTotal);
                System.out.println("Total for Player: " + playerTotal);

                boolean bothPlayersBust = players[0].isBust() && players[1].isBust();
                boolean playerWins = false;
                boolean dealerWins = false;
                boolean tie = false;
                if(bothPlayersBust)
                {
                    dealerWins = true;
                    System.out.println("Dealer Wins!");
                }
                if(playerTotal > dealerTotal && !players[0].isBust())
                {
                    playerWins = true;
                    System.out.println("Player Wins!");
                }
                else if(dealerTotal > playerTotal && !players[1].isBust())
                {
                    dealerWins = true;
                    System.out.println("Dealer Wins!");
                }
                else if(dealerTotal == playerTotal)
                {
                    tie = true;
                    System.out.println("Tie!");
                }

                handleNewGameInput();
                if(!done)
                    resetGame();

                Thread.sleep(1);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

    }

    private void buildDeck()
    {
        int cardTypes = 4;
        //Iterate through 4 different card types (Spades, Hearts, Diamonds, Clubs)
        for(Card.CardType type: Card.CardType.values())
        {
            for(int i=2;i<=10;i++) // Iterate and create cards 2 through 10
            {
                Card card = new Card(false,i,Character.MIN_VALUE,type);
                cardDeck.push(card);
            }

            // Iterate and create special cards (Ace, Jack, Queen, King)
            for(Card.AJQKType ajqkType:Card.AJQKType.values())
            {
                Card card;
                if(ajqkType==Card.AJQKType.ACE)
                    card = new Card(true,11,'A',type);
                else
                    card = new Card(false, 10,Card.getAJQKChar(ajqkType),type);
                cardDeck.push(card);
            }
        }
        System.out.println("Build Deck: Total cards: " + cardDeck.size());
    }

    private void handleNewGameInput()
    {

        try
        {
            int in = 0;
            boolean validInput = false;
            while (!validInput)
            {
                InputStreamReader inputStream = new InputStreamReader(System.in);
                BufferedReader bufferedReader = new BufferedReader(inputStream);

                System.out.println("New Game? (Y)/(N)? ");
                in = bufferedReader.read();
                char inputChar = (char) in;
                if (inputChar == 'Y' || inputChar == 'y')
                {
                    validInput = true;
                    done = false;
                }
                else if (inputChar == 'N' || inputChar == 'n')
                {
                    validInput = true;
                    done = true;
                }
                else
                    System.out.println("Invalid Input. Please Try Again.");
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

    }

    private void setupGame()
    {
        buildDeck(); // Build standard 52 card deck
        ((Dealer)players[1]).shuffleCardDeck(cardDeck); //Dealer shuffles cards

        Card[] dealerStartCards = new Card[]{cardDeck.pop(),cardDeck.pop()}; //Two initial cards for dealer
        Card[] playerStartCards = new Card[]{cardDeck.pop(),cardDeck.pop()}; //Two initial cards for human
        players[0].receiveCards(playerStartCards); // Human gets his two cards
        players[1].receiveCards(dealerStartCards); // Dealer gets his two cards
    }

    private void resetGame()
    {
        players[0].reset();
        players[1].reset();
        cardDeck.clear();
    }



    private void handleDealerTurn()
    {

    }




}
