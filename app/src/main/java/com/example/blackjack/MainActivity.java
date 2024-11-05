package com.example.blackjack;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
public class MainActivity extends AppCompatActivity {
    //Initialize Variables needed
    private int playerScore = 0;
    private int playerMoney = 0;
    private int playerBet = 0;
    private int dealerScore =0;
    private TextView playerScoreView;
    private TextView dealerScoreView;
    private TextView gameStatusView;
    private GridLayout playerCardsLayout;
    private GridLayout dealerCardsLayout;
    private ArrayList<String> deck;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize views
        playerScoreView = findViewById(R.id.playerScore);
        dealerScoreView = findViewById(R.id.dealerScore);
        gameStatusView = findViewById(R.id.gameStatus);
        playerCardsLayout = findViewById(R.id.playerCards);
        dealerCardsLayout = findViewById(R.id.dealerCards);

        // Initialize deck and deal initial cards
        initializeDeck();
        dealInitialCards();

        // Set up button listeners
        Button hitButton = findViewById(R.id.hitButton);
        Button standButton = findViewById(R.id.standButton);
        Button resetButton = findViewById(R.id.resetButton);


        hitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add logic for Hit
            }
        });

        standButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add logic for Stand
            }
        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    private void initializeDeck() {
        // Create a standard deck of cards
        deck = new ArrayList<>();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        for (String suit : suits) {
            for (String value : values) {
                deck.add(value + " of " + suit);
            }
        }
        Collections.shuffle(deck); // Shuffle the deck
    }

    private void dealInitialCards() {
        // Deal two cards to player and dealer
        for (int i = 0; i < 2; i++) {
            playerScore += getCardValue(deck.remove(0)); // Update player score with the value of the card
            dealerScore += getCardValue(deck.remove(0)); // Update dealer score with the value of the card
        }
        updateScoreViews();
    }

    private int getCardValue(String card) {
        // Determine the value of the card
        String value = card.split(" ")[0]; // Get the card value
        switch (value) {
            case "Jack":
            case "Queen":
            case "King":
                return 10; // Face cards are worth 10 points
            case "Ace":
                return 11; // Aces can be worth 11 (you can handle logic for changing it to 1 later)
            default:
                return Integer.parseInt(value); // Numeric cards are worth their value
        }
    }

    private void updateScoreViews() {
        playerScoreView.setText("Player Score: " + playerScore);
        dealerScoreView.setText("Dealer Score: " + dealerScore);
    }

    private void resetGame() {
        // Reset game logic
        playerScore = 0;
        dealerScore = 0;
        playerCardsLayout.removeAllViews();
        dealerCardsLayout.removeAllViews();
        initializeDeck();
        dealInitialCards();
        gameStatusView.setText("");
    }
}

