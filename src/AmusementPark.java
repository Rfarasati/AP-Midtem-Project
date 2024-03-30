import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
public class AmusementPark {
    private class Card {
        int point;
        String specialCoin;
        int blackCoin;
        int blueCoin;
        int greenCoin;
        int redCoin;
        int whiteCoin;
        Card (int point, String specialCoin, int blackCoin, int blueCoin, int greenCoin, int redCoin, int whiteCoin) {
            this.point = point;
            this.specialCoin = specialCoin;
            this.blackCoin = blackCoin;
            this.blueCoin = blueCoin;
            this.greenCoin = greenCoin;
            this.redCoin = redCoin;
            this.whiteCoin = whiteCoin;
        }
    }
    ArrayList<Card> level1; //storing level1 cards
    ArrayList<Card> level2; //storing level2 cards
    ArrayList<Card> level3; //storing level3 cards
    Random random = new Random(); //for shuffling
    public void buildLevel1() {
        level1 = new ArrayList<Card>();
        Card card1 = new Card(0, "black", 2, 0, 0, 0, 2);
        level1.add(card1);
        Card card2 = new Card(0, "white", 0, 2, 0, 2, 0);
        level1.add(card2);
        Card card3 = new Card(0, "green", 0, 0, 2, 2, 0);
        level1.add(card3);
        Card card4 = new Card(0, "red", 0, 2, 0, 0, 2);
        level1.add(card4);
        Card card5 = new Card(0, "blue", 2, 0, 0, 2, 0);
        level1.add(card5);
        Card card6 = new Card(1, "black", 0, 2, 0, 2, 1);
        level1.add(card6);
        Card card7 = new Card(1, "blue", 1, 0, 0, 2, 2);
        level1.add(card7);
        Card card8 = new Card(1, "red", 2, 0, 1, 0, 2);
        level1.add(card8);
        Card card9 = new Card(1, "green", 2, 0, 0, 0, 3);
        level1.add(card9);
        Card card10 = new Card(1, "red", 0, 3, 2, 0, 0);
        level1.add(card10);
        Card card11 = new Card(1, "green", 0, 2, 0, 2, 2);
        level1.add(card11);
        Card card12 = new Card(1, "white", 2, 0, 0, 2, 2);
        level1.add(card12);
        Card card13 = new Card(1, "blue", 2, 0, 2, 0, 2);
        level1.add(card13);
        Card card14 = new Card(1, "black", 2, 2, 0, 0, 2);
        level1.add(card14);
        Card card15 = new Card(1, "white", 2, 0, 2, 2, 0);
        level1.add(card15);
    }
    public void buildLevel2() {
        level2 = new ArrayList<Card>();
        Card card1 = new Card(2, "black", 0, 2, 0, 2, 2);
        level2.add(card1);
        Card card2 = new Card(2, "blue", 2, 0, 0, 2, 2);
        level2.add(card2);
        Card card3 = new Card(2, "red", 2, 0, 2, 0, 2);
        level2.add(card3);
        Card card4 = new Card(2, "white", 2, 2, 0, 0, 2);
        level2.add(card4);
        Card card5 = new Card(2, "green", 2, 0, 2, 2, 0);
        level2.add(card5);
        Card card6 = new Card(3, "black", 2, 0, 2, 2, 1);
        level2.add(card6);
        Card card7 = new Card(3, "blue",  2, 2, 0, 1, 2);
        level2.add(card7);
        Card card8 = new Card(3, "green", 2, 1, 2, 0, 2);
        level2.add(card8);
        Card card9 = new Card(3, "red", 2, 0, 1, 2, 2);
        level2.add(card9);
        Card card10 = new Card(3, "white", 1, 2, 0, 2, 2);
        level2.add(card10);
        Card card11 = new Card(4, "white", 2, 2, 0, 2, 2);
        level2.add(card11);
        Card card12 = new Card(4, "red", 2, 2, 2, 2, 0);
        level2.add(card12);
        Card card13 = new Card(4, "green", 2, 2, 2, 0, 2);
        level2.add(card13);
        Card card14 = new Card(4, "blue", 2, 0, 2, 2, 2);
        level2.add(card14);
        Card card15 = new Card(4, "black", 0, 2, 2, 2, 2);
        level2.add(card15);
    }
    public void buildLevel3() {
        level3 = new ArrayList<Card>();
        Card card1 = new Card(3, "black", 2, 2, 1, 2, 0);
        level3.add(card1);
        Card card2 = new Card(3, "blue", 2, 0, 1, 2, 2);
        level3.add(card2);
        Card card3 = new Card(3, "green", 0, 2, 1, 2, 2);
        level3.add(card3);
        Card card4 = new Card(3, "red", 2, 2, 1, 0, 2);
        level3.add(card4);
        Card card5 = new Card(3, "white", 2, 2, 0, 1, 2);
        level3.add(card5);
        Card card6 = new Card(4, "black", 2, 2, 2, 2, 0);
        level3.add(card6);
        Card card7 = new Card(4, "blue",  2, 0, 2, 2, 2);
        level3.add(card7);
        Card card8 = new Card(4, "green", 0, 2, 2, 2, 2);
        level3.add(card8);
        Card card9 = new Card(4, "red", 2, 2, 2, 0, 2);
        level3.add(card9);
        Card card10 = new Card(4, "white", 2, 2, 0, 2, 2);
        level3.add(card10);
        Card card11 = new Card(5, "white", 2, 2, 1, 2, 2);
        level3.add(card11);
        Card card12 = new Card(5, "red", 2, 2, 2, 1, 2);
        level3.add(card12);
        Card card13 = new Card(5, "green", 1, 2, 2, 2, 2);
        level3.add(card13);
        Card card14 = new Card(5, "blue", 2, 2, 2, 2, 2);
        level3.add(card14);
        Card card15 = new Card(5, "black", 2, 3, 2, 0, 3);
        level3.add(card15);
    }
}
