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
        public String toString() { // A method in order to print arraylist to check
            return point + "-" + specialCoin + "-" + blackCoin + "-" + blueCoin + "-" + greenCoin + "-" + redCoin + "-" + whiteCoin;
        }
    }
    private class Coin {
        int black;
        int blue;
        int green;
        int red;
        int white;
        int gold;
        Coin (int black, int blue, int green, int red, int white, int gold) {
            this.black = black;
            this.blue = blue;
            this.green = green;
            this.red = red;
            this.white = white;
            this.gold = gold;
        }
        public String toString() {
            return black + "-" + blue + "-" + green + "-" + red + "-" + white + "-" + gold;
        }
    }
    AmusementPark() {
        startGame();
    }
    public void startGame() {
        buildSlotMachineBlack();
        buildSlotMachineBlue();
        buildSlotMachineGreen();
        buildSlotMachineRed();
        buildSlotMachineWhite();
        buildLevel1();
        buildLevel2();
        buildLevel3();
        shuffleLevel1();
        shuffleLevel2();
        shuffleLevel3();
    }
    ArrayList<Card> level1; //storing level1 cards
    ArrayList<Card> level2; //storing level2 cards
    ArrayList<Card> level3; //storing level3 cards
    public void buildLevel1() { //building an arraylist to store level1 cards
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
    public void buildLevel2() { //building an arraylist to store level2 cards
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
    public void buildLevel3() { //building an arraylist to store level3 cards
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
    Random random = new Random(); //for shuffling
    public void shuffleLevel1() { //shuffling level1
        for (int i = 0; i < level1.size(); i++) {
            int j = random.nextInt(level1.size());
            Card currCard = level1.get(i);
            Card randomCard = level1.get(j);
            level1.set(i, randomCard);
            level1.set(j, currCard);
        }
    }
    public void shuffleLevel2() { //shuffling level2
        for (int i = 0; i < level2.size(); i++) {
            int j = random.nextInt(level2.size());
            Card currCard = level2.get(i);
            Card randomCard = level2.get(j);
            level2.set(i, randomCard);
            level2.set(j, currCard);
        }
    }
    public void shuffleLevel3() { //shuffling level3
        for (int i = 0; i < level3.size(); i++) {
            int j = random.nextInt(level3.size());
            Card currCard = level3.get(i);
            Card randomCard = level3.get(j);
            level3.set(i, randomCard);
            level3.set(j, currCard);
        }
    }
    ArrayList<Coin> slotMachineBlack;
    ArrayList<Coin> slotMachineBlue;
    ArrayList<Coin> slotMachineGreen;
    ArrayList<Coin> slotMachineRed;
    ArrayList<Coin> slotMachineWhite;
    public void buildSlotMachineBlack() { //building black slot machine
        slotMachineBlack = new ArrayList<Coin>();
        for (int i = 0; i < 4; i++) {
            Coin coin = new Coin(4, 0, 0, 0, 0, 0);
            slotMachineBlack.add(coin);
        }
    }
    public void buildSlotMachineBlue() { //building blue slot machine
        slotMachineBlue = new ArrayList<Coin>();
        for (int i = 0; i < 4; i++) {
            Coin coin = new Coin(0, 4, 0, 0, 0, 0);
            slotMachineBlue.add(coin);
        }
    }
    public void buildSlotMachineGreen() { //building green slot machine
        slotMachineGreen = new ArrayList<Coin>();
        for (int i = 0; i < 4; i++) {
            Coin coin = new Coin(0, 0, 4, 0, 0, 0);
            slotMachineGreen.add(coin);
        }
    }
    public void buildSlotMachineRed() { //building red slot machine
        slotMachineRed = new ArrayList<Coin>();
        for (int i = 0; i < 4; i++) {
            Coin coin = new Coin(0, 0, 0, 4, 0, 0);
            slotMachineRed.add(coin);
        }
    }
    public void buildSlotMachineWhite() { //building white slot machine
        slotMachineWhite = new ArrayList<Coin>();
        for (int i = 0; i < 4; i++) {
            Coin coin = new Coin(0, 0, 0, 0, 4, 0);
            slotMachineWhite.add(coin);
        }
    }
}
