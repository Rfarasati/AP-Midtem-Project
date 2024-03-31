import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Random;
import javax.swing.*;
public class AmusementPark extends JFrame {
    private class Card {
        int point;
        String specialCoin;
        int blackCoin;
        int blueCoin;
        int greenCoin;
        int redCoin;
        int whiteCoin;

        Card(int point, String specialCoin, int blackCoin, int blueCoin, int greenCoin, int redCoin, int whiteCoin) {
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

    private class PrizeClaw {
        int point;
        int blackCoin;
        int blueCoin;
        int greenCoin;
        int redCoin;
        int whiteCoin;

        PrizeClaw(int point, int blackCoin, int blueCoin, int greenCoin, int redCoin, int whiteCoin) {
            this.point = point;
            this.blackCoin = blackCoin;
            this.blueCoin = blueCoin;
            this.greenCoin = greenCoin;
            this.redCoin = redCoin;
            this.whiteCoin = whiteCoin;
        }
    }

    private class Coin {
        int black;
        int blue;
        int green;
        int red;
        int white;
        int gold;

        Coin(int black, int blue, int green, int red, int white, int gold) {
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

    private class Player {
        public int score;
        int reserveCards;
        int blackCoin;
        int blueCoin;
        int greenCoin;
        int redCoin;
        int whiteCoin;
        int goldCoin;

        public int getTotalCoin() {
            return blackCoin + blueCoin + greenCoin + redCoin + whiteCoin + goldCoin;
        }
    }
    JButton pick3Button;
    JButton pick2Button;
    JRadioButton blackBtn;
    JRadioButton blueBtn;
    JRadioButton greenBtn;
    JRadioButton redBtn;
    JRadioButton whiteBtn;
    JCheckBox blackBox;
    JCheckBox blueBox;
    JCheckBox greenBox;
    JCheckBox redBox;
    JCheckBox whiteBox;
    ButtonGroup group2;
    Player p1;
    Player p2;
    AmusementPark() {
        startGame();

        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //frame.setUndecorated(true);
        frame.setSize(500,300);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    frame.dispose(); // Close the frame
                }
            }
        });
         pick2Button = new JButton("I want 2 coins with the same color");
         pick3Button = new JButton("I want 3 coins with different colors");

        pick2Button.addActionListener(new ActionListener() { //when the pick2Button is pressed:
            public void actionPerformed(ActionEvent e) {
                buttonPanel.removeAll();

                JPanel radioPanel = new JPanel();
                blackBtn = new JRadioButton("Black");
                blackBtn.setFocusable(false);
                radioPanel.add(blackBtn);
                blueBtn = new JRadioButton("Blue");
                blueBtn.setFocusable(false);
                radioPanel.add(blueBtn);
                greenBtn = new JRadioButton("Green");
                greenBtn.setFocusable(false);
                radioPanel.add(greenBtn);
                redBtn = new JRadioButton("Red");
                redBtn.setFocusable(false);
                radioPanel.add(redBtn);
                whiteBtn = new JRadioButton("White");
                whiteBtn.setFocusable(false);
                radioPanel.add(whiteBtn);

                group2 = new ButtonGroup();
                group2.add(blackBtn);
                group2.add(blueBtn);
                group2.add(greenBtn);
                group2.add(redBtn);
                group2.add(whiteBtn);

                buttonPanel.add(radioPanel);

                frame.revalidate();
                frame.repaint();
            }
        });
        pick3Button.addActionListener(new ActionListener() { //when the pick3Buttons is pressed:
            public void actionPerformed(ActionEvent e) {
                buttonPanel.removeAll();

                JPanel checkBoxPanel = new JPanel();
                blackBox = new JCheckBox("Black");
                blackBox.setFocusable(false);
                checkBoxPanel.add(blackBox);
                blueBox = new JCheckBox("Blue");
                blueBox.setFocusable(false);
                checkBoxPanel.add(blueBox);
                greenBox = new JCheckBox("Green");
                greenBox.setFocusable(false);
                checkBoxPanel.add(greenBox);
                redBox = new JCheckBox("Red");
                redBox.setFocusable(false);
                checkBoxPanel.add(redBox);
                whiteBox = new JCheckBox("White");
                whiteBox.setFocusable(false);
                checkBoxPanel.add(whiteBox);

                int maxSelections = 3;
                ItemListener itemListener = new ItemListener() {
                    private int selectedCount = 0;

                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        JCheckBox source = (JCheckBox) e.getSource();
                        if (source.isSelected()) {
                            selectedCount++;
                        } else {
                            selectedCount--;
                        }

                        // Disable other checkboxes when 3 are selected
                        for (Component component : checkBoxPanel.getComponents()) {
                            if (component instanceof JCheckBox) {
                                JCheckBox checkBox = (JCheckBox) component;
                                checkBox.setEnabled(selectedCount < maxSelections || checkBox.isSelected());
                            }
                        }
                        if (selectedCount == maxSelections) {
                            if(blackBox.isSelected() && blueBox.isSelected() && greenBox.isSelected()) {
                                System.out.println("1");
                            }
                            else if(blackBox.isSelected() && blueBox.isSelected() && redBox.isSelected()) {

                            }
                            else if(blackBox.isSelected() && blueBox.isSelected() && whiteBox.isSelected()) {

                            }
                            else if(blackBox.isSelected() && greenBox.isSelected() && redBox.isSelected()) {

                            }
                            else if(blackBox.isSelected() && greenBox.isSelected() && whiteBox.isSelected()) {


                            }
                            else if(blackBox.isSelected() && redBox.isSelected() && whiteBox.isSelected()) {

                            }
                            else if(blueBox.isSelected() && greenBox.isSelected() && redBox.isSelected()) {


                            }
                            else if(blueBox.isSelected() && greenBox.isSelected() && whiteBox.isSelected()) {

                            }
                            else if(blueBox.isSelected() && redBox.isSelected() && whiteBox.isSelected()) {

                            }
                            else if(greenBox.isSelected() && redBox.isSelected() && whiteBox.isSelected()) {

                            }
                        }
                    }
                };

                blackBox.addItemListener(itemListener);
                blueBox.addItemListener(itemListener);
                greenBox.addItemListener(itemListener);
                redBox.addItemListener(itemListener);
                whiteBox.addItemListener(itemListener);

                buttonPanel.add(checkBoxPanel);

                frame.revalidate();
                frame.repaint();
            }
        });

        frame.setVisible(true);

        gamePanel.setLayout(new BorderLayout());
        gamePanel.setBackground(new Color(51, 77, 104));
        frame.add(gamePanel);

        pick2Button.setFocusable(false);
        buttonPanel.add(pick2Button);
        pick3Button.setFocusable(false);
        buttonPanel.add(pick3Button);
        frame.add(buttonPanel, BorderLayout.SOUTH);


    }

    public void startGame() {
        p1 = new Player();
        p2 = new Player();
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
        buildPrizeClaw();
    }

    //Card class arraylists:
    ArrayList<Card> level1; //storing level1 cards
    ArrayList<Card> level2; //storing level2 cards
    ArrayList<Card> level3; //storing level3 cards
    ArrayList<Card> playerOneHand; //storing cards in player one's hand
    ArrayList<Card> playerTwoHand; //storing cards in player two's hand
    ArrayList<Card> playerOneReserves; //storing reserve cards of player one
    ArrayList<Card> playerTwoReserves; //storing reserve cards of player two

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
        Card card7 = new Card(3, "blue", 2, 2, 0, 1, 2);
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
        Card card7 = new Card(4, "blue", 2, 0, 2, 2, 2);
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

    // prize claw class arraylists:
    ArrayList<PrizeClaw> prizeClaw; //arraylist to store prize claw cards
    ArrayList<PrizeClaw> playerOnePC; //arraylist to store player one prize claw cards //PC stands for prize claw
    ArrayList<PrizeClaw> getPlayerTwoPC; //arraylist to store player two prize claw cards //PC stands for prize claw

    public void buildPrizeClaw() {
        prizeClaw = new ArrayList<PrizeClaw>();
        PrizeClaw card1 = new PrizeClaw(3, 2, 0, 2, 2, 2);
        prizeClaw.add(card1);
        PrizeClaw card2 = new PrizeClaw(4, 2, 2, 2, 2, 2);
        prizeClaw.add(card2);
        PrizeClaw card3 = new PrizeClaw(4, 3, 3, 2, 2, 2);
        prizeClaw.add(card3);
    }

    //window
    JFrame frame = new JFrame("Amusement Park");
    JPanel gamePanel = new JPanel();
    JPanel buttonPanel = new JPanel();

}
