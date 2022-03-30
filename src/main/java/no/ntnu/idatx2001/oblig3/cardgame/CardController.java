package no.ntnu.idatx2001.oblig3.cardgame;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;



public class CardController extends Application {
    HandOfCards handOfCards;
    ArrayList<PlayingCard> handOfCardsList;
    String text = "";

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Card Application");

        DeckOfCards deckOfCards = new DeckOfCards();

        // Creates a gridpane.

        GridPane gp = new GridPane();
        gp.setMinSize(600,200);
        gp.setPadding(new Insets(50,50,50,50));
        gp.setHgap(15);
        gp.setVgap(15);


        // Stackpane showing cards.
        StackPane sp = new StackPane();
        sp.setStyle("-fx-border-color: red");
        sp.setMinSize(450,300);

         // Text.
        Text cards = new Text("Card values will be displayed here when you press Deal Hand");
        Text sumOfFaces = new Text("Sum of the faces: ");
        Text sumOfFacesResult = new Text();
        Text cardsOfHearts = new Text("Cards of hearts: ");
        Text cardsOfHeartsResult = new Text();
        Text flush = new Text("Flush: ");
        Text flushResult = new Text();
        Text queenOfSpades = new Text("Queen of spades: ");
        Text queenOfSpadesResult = new Text();

        /**
         * Fonts.
         */
        sumOfFaces.setFont(Font.font("Times", FontWeight.BOLD,20));
        cardsOfHearts.setFont(Font.font("Times", FontWeight.BOLD,20));
        flush.setFont(Font.font("Times", FontWeight.BOLD,20));
        queenOfSpades.setFont(Font.font("Times", FontWeight.BOLD,20));
        cards.setFont(Font.font("Times", FontWeight.BOLD,17));
        flushResult.setFont(Font.font("Times", FontWeight.BOLD,20));
        queenOfSpadesResult.setFont(Font.font("Times", FontWeight.BOLD,20));
        cardsOfHeartsResult.setFont(Font.font("Times", FontWeight.BOLD,20));
        sumOfFacesResult.setFont(Font.font("Times", FontWeight.BOLD,20));

        /**
         * Buttons.
         */
        Button dealHand = new Button("Deal hand");
        Button checkHand = new Button("Check hand");
        dealHand.setPrefSize(100,20);
        checkHand.setPrefSize(100,20);

        /**
         * What happens when deal hand button is pressed.
         */
        dealHand.setOnAction(actionEvent -> {
            text = "";
            handOfCards = (HandOfCards) deckOfCards.dealHand(5);
            handOfCardsList = handOfCards.getHandOfCards();
            handOfCardsList.forEach(c -> text += c.getAsString()+"   ");
            cards.setText(text);
        });

        /**
         * What happens when check hand button is pressed.
         */
        checkHand.setOnAction(actionEvent -> {
            /**
             * Check if there is a flush.
             */
            if(handOfCards.checkForFlush(5)){
                flushResult.setText("Yes");
            }else{flushResult.setText("No");}

            /**
             * Finds sum of faces.
             */
            sumOfFacesResult.setText(handOfCardsList.stream().map(PlayingCard::getFace).reduce((a,b) -> (a+b)).get().toString());

            /**
             * Checks for queen of spades.
             */
            if(handOfCardsList.stream().anyMatch(c -> c.getAsString().equals("S12"))){
                queenOfSpadesResult.setText("Yes");
            }else{queenOfSpadesResult.setText("No");}

            /**
             * Finds the amount of cards wit heart faces.
             */
            text = "";
            handOfCardsList.stream().filter(c -> c.getSuit()=='H').forEach(c -> text += c.getAsString()+" ");
            if(!(text.isBlank())){
                cardsOfHeartsResult.setText(text);
            }else{cardsOfHeartsResult.setText("No hearts");}
        });

        /**
         * Adds nodes to the gridpane.
         */
        sp.getChildren().add(cards);
        gp.add(sp, 1,1,6,6);
        gp.add(dealHand,12,2);
        gp.add(checkHand,12,5);
        gp.add(sumOfFaces,2,9);
        gp.add(sumOfFacesResult,3,9);
        gp.add(cardsOfHearts,5,9);
        gp.add(cardsOfHeartsResult,6,9);
        gp.add(flush,2,10);
        gp.add(flushResult,3,10);
        gp.add(queenOfSpades,5,10);
        gp.add(queenOfSpadesResult,6,10);

        /**
         * Adds the gridpane to the scene
         */
        primaryStage.setScene(new Scene(gp,700,500));
        primaryStage.show();
    }

}



