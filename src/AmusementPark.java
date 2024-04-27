import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.*;

public class AmusementPark extends JFrame {

    //initialization class
    Initialization init;

    //initialing graphics components
    JFrame frame = new JFrame("Amusement Park");
    JPanel gamePanel = new JPanel();
    JLabel[] cardLabel1 = new JLabel[4];
    JLabel[] cardLabel2 = new JLabel[4];
    JLabel[] cardLabel3 = new JLabel[4];
    JLabel[] prizeClawLabel = new JLabel[3];
    JPanel buttonPanel = new JPanel();
    JButton pick3Button;
    JButton pick2Button;
    JButton buyButton;
    JButton reserveButton;
    JButton cancelButton;
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
    ItemListener itemListener;
    Player p1;
    Player p2;
    boolean isP1Turn = true;
    Card[] level1Card = new Card[4];
    Card[] level2Card = new Card[4];
    Card[] level3Card = new Card[4];
    PrizeClaw prizeClawCard;
    int cardWidth = 108; //ratio should 1/1.4
    int cardHeight = 144;
    JLabel blackCoinLabel;
    JLabel blueCoinLabel;
    JLabel greenCoinLabel;
    JLabel redCoinLabel;
    JLabel goldCoinLabel;
    JLabel whiteCoinLabel;
    Font font = new Font("Arial", Font.BOLD, 30);
    int blackCoinNum = 4;
    int blueCoinNum = 4;
    int greenCoinNum = 4;
    int redCoinNum = 4;
    int whiteCoinNum = 4;

    AmusementPark() throws IOException {
        startGame();

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
//        frame.setSize(1200,800);
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

        twoBtn();
        threeBtn();
        drawLevel1();
        drawlevel2();
        drawlevel3();
        drawCoins();
        drawPrizeClaws();

        frame.setVisible(true);
        gamePanel.setLayout(null);
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
        init = new Initialization();
    }
    public void twoBtn () {
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
                if (isP1Turn) {
                    blackBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            System.out.println(isP1Turn);
                            isP1Turn = false;

                            Coin blackC1 = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                            init.player1Black.add(blackC1);
                            p1.blackCoin++;
                            Coin blackC2 = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                            init.player1Black.add(blackC2);
                            p1.blackCoin++;


                            System.out.println(init.slotMachineBlack);

                            blackCoinNum -= 2;

                            blackCoinLabel.setText(String.valueOf(blackCoinNum));

                            if (blackCoinNum < 1) {
                                emptySlotCount++;
                            }

                            buttonPanel.remove(radioPanel);
                            if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                            if (blackCoinNum > 0 || blueCoinNum > 0 || greenCoinNum > 0 || redCoinNum > 0 || whiteCoinNum > 0) buttonPanel.add(pick3Button);
                            blackBtn.addActionListener(this);
                            buttonPanel.revalidate();
                            buttonPanel.repaint();
                        }
                    });
                    blueBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            System.out.println(isP1Turn);
                            isP1Turn = false;

                            Coin blueC1 = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                            init.player1Blue.add(blueC1);
                            p1.blueCoin++;
                            Coin blueC2 = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                            init.player1Blue.add(blueC2);
                            p1.blueCoin++;

                            System.out.println(init.slotMachineBlue);

                            blueCoinNum -= 2;

                            blueCoinLabel.setText(String.valueOf(blueCoinNum));

                            if (blueCoinNum < 1) {
                                emptySlotCount++;
                            }

                            buttonPanel.remove(radioPanel);
                            if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                            if (blackCoinNum > 0 || blueCoinNum > 0 || greenCoinNum > 0 || redCoinNum > 0 || whiteCoinNum > 0) buttonPanel.add(pick3Button);
                            blueBtn.addActionListener(this);
                            buttonPanel.revalidate();
                            buttonPanel.repaint();
                        }
                    });
                    greenBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            System.out.println(isP1Turn);
                            isP1Turn = false;

                            Coin greenC1 = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                            init.player1Green.add(greenC1);
                            p1.greenCoin++;
                            Coin greenC2 = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                            init.player1Green.add(greenC2);
                            p1.greenCoin++;

                            System.out.println(init.slotMachineGreen);

                            greenCoinNum -= 2;

                            greenCoinLabel.setText(String.valueOf(greenCoinNum));

                            if (greenCoinNum < 1) {
                                emptySlotCount++;
                            }

                            buttonPanel.remove(radioPanel);
                            if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                            if (blackCoinNum > 0 || blueCoinNum > 0 || greenCoinNum > 0 || redCoinNum > 0 || whiteCoinNum > 0) buttonPanel.add(pick3Button);
                            greenBtn.addActionListener(this);
                            buttonPanel.revalidate();
                            buttonPanel.repaint();
                        }
                    });
                    redBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            System.out.println(isP1Turn);
                            isP1Turn = false;

                            Coin redC1 = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                            init.player1Red.add(redC1);
                            p1.redCoin++;
                            Coin redC2 = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                            init.player1Red.add(redC2);
                            p1.redCoin++;

                            System.out.println(init.slotMachineRed);

                            redCoinNum -= 2;

                            redCoinLabel.setText(String.valueOf(redCoinNum));

                            if (redCoinNum < 1) {
                                emptySlotCount++;
                            }

                            buttonPanel.remove(radioPanel);
                            if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                            if (blackCoinNum > 0 || blueCoinNum > 0 || greenCoinNum > 0 || redCoinNum > 0 || whiteCoinNum > 0) buttonPanel.add(pick3Button);
                            redBtn.addActionListener(this);
                            buttonPanel.revalidate();
                            buttonPanel.repaint();
                        }
                    });
                    whiteBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            System.out.println(isP1Turn);
                            isP1Turn = false;

                            Coin whiteC1 = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                            init.player1White.add(whiteC1);
                            p1.whiteCoin++;
                            Coin whiteC2 = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                            init.player1White.add(whiteC2);
                            p1.whiteCoin++;

                            System.out.println(init.slotMachineWhite);

                            whiteCoinNum -= 2;

                            whiteCoinLabel.setText(String.valueOf(whiteCoinNum));

                            if (whiteCoinNum < 1) {
                                emptySlotCount++;
                            }

                            buttonPanel.remove(radioPanel);
                            if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                            if (blackCoinNum > 0 || blueCoinNum > 0 || greenCoinNum > 0 || redCoinNum > 0 || whiteCoinNum > 0) buttonPanel.add(pick3Button);
                            whiteBtn.addActionListener(this);
                            buttonPanel.revalidate();
                            buttonPanel.repaint();
                        }
                    });
                }
                
                else {
                    blackBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            System.out.println(isP1Turn);
                            isP1Turn = true;

                            Coin blackC1 = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                            init.player2Black.add(blackC1);
                            p2.blackCoin++;
                            Coin blackC2 = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                            init.player2Black.add(blackC2);
                            p2.blackCoin++;


                            System.out.println(init.slotMachineBlack);

                            blackCoinNum -= 2;

                            blackCoinLabel.setText(String.valueOf(blackCoinNum));

                            if (blackCoinNum < 1) {
                                emptySlotCount++;
                            }

                            buttonPanel.remove(radioPanel);
                            if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                            if (blackCoinNum > 0 || blueCoinNum > 0 || greenCoinNum > 0 || redCoinNum > 0 || whiteCoinNum > 0) buttonPanel.add(pick3Button);
                            blackBtn.addActionListener(this);
                            buttonPanel.revalidate();
                            buttonPanel.repaint();
                        }
                    });
                    blueBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            System.out.println(isP1Turn);
                            isP1Turn = true;

                            Coin blueC1 = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                            init.player2Blue.add(blueC1);
                            p2.blueCoin++;
                            Coin blueC2 = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                            init.player2Blue.add(blueC2);
                            p2.blueCoin++;

                            System.out.println(init.slotMachineBlue);

                            blueCoinNum -= 2;

                            blueCoinLabel.setText(String.valueOf(blueCoinNum));

                            if (blueCoinNum < 1) {
                                emptySlotCount++;
                            }

                            buttonPanel.remove(radioPanel);
                            if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                            if (blackCoinNum > 0 || blueCoinNum > 0 || greenCoinNum > 0 || redCoinNum > 0 || whiteCoinNum > 0) buttonPanel.add(pick3Button);
                            blueBtn.addActionListener(this);
                            buttonPanel.revalidate();
                            buttonPanel.repaint();
                        }
                    });
                    greenBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            System.out.println(isP1Turn);
                            isP1Turn = true;

                            Coin greenC1 = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                            init.player2Green.add(greenC1);
                            p2.greenCoin++;
                            Coin greenC2 = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                            init.player2Green.add(greenC2);
                            p2.greenCoin++;

                            System.out.println(init.slotMachineGreen);

                            greenCoinNum -= 2;

                            greenCoinLabel.setText(String.valueOf(greenCoinNum));

                            if (greenCoinNum < 1) {
                                emptySlotCount++;
                            }

                            buttonPanel.remove(radioPanel);
                            if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                            if (blackCoinNum > 0 || blueCoinNum > 0 || greenCoinNum > 0 || redCoinNum > 0 || whiteCoinNum > 0) buttonPanel.add(pick3Button);
                            greenBtn.addActionListener(this);
                            buttonPanel.revalidate();
                            buttonPanel.repaint();
                        }
                    });
                    redBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            System.out.println(isP1Turn);
                            isP1Turn = true;

                            Coin redC1 = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                            init.player2Red.add(redC1);
                            p2.redCoin++;
                            Coin redC2 = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                            init.player2Red.add(redC2);
                            p2.redCoin++;

                            System.out.println(init.slotMachineRed);

                            redCoinNum -= 2;

                            redCoinLabel.setText(String.valueOf(redCoinNum));

                            if (redCoinNum < 1) {
                                emptySlotCount++;
                            }

                            buttonPanel.remove(radioPanel);
                            if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                            if (blackCoinNum > 0 || blueCoinNum > 0 || greenCoinNum > 0 || redCoinNum > 0 || whiteCoinNum > 0) buttonPanel.add(pick3Button);
                            redBtn.addActionListener(this);
                            buttonPanel.revalidate();
                            buttonPanel.repaint();
                        }
                    });
                    whiteBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            System.out.println(isP1Turn);
                            isP1Turn = true;

                            Coin whiteC1 = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                            init.player2White.add(whiteC1);
                            p2.whiteCoin++;
                            Coin whiteC2 = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                            init.player2White.add(whiteC2);
                            p2.whiteCoin++;

                            System.out.println(init.slotMachineWhite);

                            whiteCoinNum -= 2;

                            whiteCoinLabel.setText(String.valueOf(whiteCoinNum));

                            if (whiteCoinNum < 1) {
                                emptySlotCount++;
                            }

                            buttonPanel.remove(radioPanel);
                            if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                            if (blackCoinNum > 0 || blueCoinNum > 0 || greenCoinNum > 0 || redCoinNum > 0 || whiteCoinNum > 0) buttonPanel.add(pick3Button);
                            whiteBtn.addActionListener(this);
                            buttonPanel.revalidate();
                            buttonPanel.repaint();
                        }
                    });
                }
                buttonPanel.add(radioPanel);

                frame.revalidate();
                frame.repaint();
            }
        });
    }
    int maxSelections = 3;
    int emptySlotCount = 0;
    public void threeBtn () {
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


                itemListener = new ItemListener() {
                    private int selectedCount = 0;

                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (emptySlotCount >= 3) {
                            maxSelections = 2;
                            if (emptySlotCount >= 4) {
                                maxSelections = 1;
                            }
                        }
                        JCheckBox source = (JCheckBox) e.getSource();
                        if (source.isSelected()) {
                            selectedCount++;
                        } else {
                            selectedCount--;
                        }

                        // Disable other checkboxes when 3 or 2 are selected
                        for (Component component : checkBoxPanel.getComponents()) {
                            if (component instanceof JCheckBox) {
                                JCheckBox checkBox = (JCheckBox) component;
                                checkBox.setEnabled(selectedCount < maxSelections || checkBox.isSelected());
                            }
                        }

                        if (selectedCount == maxSelections) {
                            if (isP1Turn) {

                                //when maxSelection == 3:
                                if (blackBox.isSelected()) {
                                    if (blueBox.isSelected()) {
                                        if (greenBox.isSelected()) {
                                            System.out.println(isP1Turn);
                                            isP1Turn = false;

                                            Coin blackC = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                            init.player1Black.add(blackC);
                                            p1.blackCoin++;
                                            Coin blueC = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                            init.player1Blue.add(blueC);
                                            p1.blueCoin++;
                                            Coin greenC = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                            init.player1Green.add(greenC);
                                            p1.greenCoin++;

                                            System.out.println(init.slotMachineBlack);
                                            System.out.println(init.slotMachineBlue);
                                            System.out.println(init.slotMachineGreen);

                                            greenCoinNum--;
                                            blackCoinNum--;
                                            blueCoinNum--;
                                            blackCoinLabel.setText(String.valueOf(blackCoinNum));
                                            blueCoinLabel.setText(String.valueOf(blueCoinNum));
                                            greenCoinLabel.setText(String.valueOf(greenCoinNum));

                                            if (greenCoinNum < 1) {
                                                emptySlotCount++;
                                            }
                                            if (blackCoinNum < 1) {
                                                emptySlotCount++;
                                            }
                                            if (blueCoinNum < 1) {
                                                emptySlotCount++;
                                            }


                                            buttonPanel.remove(checkBoxPanel);
                                            if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                            if (blackCoinNum >= 1 || blueCoinNum >= 1 || greenCoinNum >= 1 || redCoinNum >= 1 || whiteCoinNum >= 1) buttonPanel.add(pick3Button);
                                            buttonPanel.add((Component) itemListener);
                                            buttonPanel.revalidate();
                                            buttonPanel.repaint();
                                        }
                                        if (redBox.isSelected()) {
                                            System.out.println(isP1Turn);
                                            isP1Turn = false;

                                            Coin blackC = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                            init.player1Black.add(blackC);
                                            p1.blackCoin++;
                                            Coin blueC = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                            init.player1Blue.add(blueC);
                                            p1.blueCoin++;
                                            Coin redC = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                            init.player1Red.add(redC);
                                            p1.redCoin++;

                                            System.out.println(init.slotMachineBlack);
                                            System.out.println(init.slotMachineBlue);
                                            System.out.println(init.slotMachineRed);

                                            blackCoinNum--;
                                            blueCoinNum--;
                                            redCoinNum--;
                                            blackCoinLabel.setText(String.valueOf(blackCoinNum));
                                            blueCoinLabel.setText(String.valueOf(blueCoinNum));
                                            redCoinLabel.setText(String.valueOf(redCoinNum));

                                            if (redCoinNum < 1) {
                                                emptySlotCount++;
                                            }
                                            if (blackCoinNum < 1) {
                                                emptySlotCount++;
                                            }
                                            if (blueCoinNum < 1) {
                                                emptySlotCount++;
                                            }

                                            buttonPanel.remove(checkBoxPanel);
                                            if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                            if (blackCoinNum >= 1 || blueCoinNum >= 1 || greenCoinNum >= 1 || redCoinNum >= 1 || whiteCoinNum >= 1) buttonPanel.add(pick3Button);
                                            buttonPanel.add((Component) itemListener);
                                            buttonPanel.revalidate();
                                            buttonPanel.repaint();
                                        }
                                        if (whiteBox.isSelected()) {
                                            System.out.println(isP1Turn);
                                            isP1Turn = false;

                                            Coin blackC = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                            init.player1Black.add(blackC);
                                            p1.blackCoin++;
                                            Coin blueC = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                            init.player1Blue.add(blueC);
                                            p1.blueCoin++;
                                            Coin whiteC = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                            init.player1White.add(whiteC);
                                            p1.whiteCoin++;

                                            System.out.println(init.slotMachineBlack);
                                            System.out.println(init.slotMachineBlue);
                                            System.out.println(init.slotMachineWhite);

                                            blackCoinNum--;
                                            blueCoinNum--;
                                            whiteCoinNum--;
                                            blackCoinLabel.setText(String.valueOf(blackCoinNum));
                                            blueCoinLabel.setText(String.valueOf(blueCoinNum));
                                            whiteCoinLabel.setText(String.valueOf(whiteCoinNum));

                                            if (whiteCoinNum < 1) {
                                                emptySlotCount++;
                                            }
                                            if (blackCoinNum < 1) {
                                                emptySlotCount++;
                                            }
                                            if (blueCoinNum < 1) {
                                                emptySlotCount++;
                                            }

                                            buttonPanel.remove(checkBoxPanel);
                                            if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                            if (blackCoinNum >= 1 || blueCoinNum >= 1 || greenCoinNum >= 1 || redCoinNum >= 1 || whiteCoinNum >= 1) buttonPanel.add(pick3Button);

                                            buttonPanel.add((Component) itemListener);
                                            buttonPanel.revalidate();
                                            buttonPanel.repaint();
                                        }
                                    }
                                    if (greenBox.isSelected()) {
                                        if (redBox.isSelected()) {
                                            System.out.println(isP1Turn);
                                            isP1Turn = false;

                                            Coin blackC = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                            init.player1Black.add(blackC);
                                            p1.blackCoin++;
                                            Coin greenC = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                            init.player1Green.add(greenC);
                                            p1.greenCoin++;
                                            Coin redC = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                            init.player1Red.add(redC);
                                            p1.redCoin++;

                                            System.out.println(init.slotMachineBlack);
                                            System.out.println(init.slotMachineRed);
                                            System.out.println(init.slotMachineGreen);

                                            blackCoinNum--;
                                            greenCoinNum--;
                                            redCoinNum--;
                                            blackCoinLabel.setText(String.valueOf(blackCoinNum));
                                            greenCoinLabel.setText(String.valueOf(greenCoinNum));
                                            redCoinLabel.setText(String.valueOf(redCoinNum));

                                            if (greenCoinNum < 1) {
                                                emptySlotCount++;
                                            }
                                            if (blackCoinNum < 1) {
                                                emptySlotCount++;
                                            }
                                            if (redCoinNum < 1) {
                                                emptySlotCount++;
                                            }

                                            buttonPanel.remove(checkBoxPanel);
                                            if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                            if (blackCoinNum >= 1 || blueCoinNum >= 1 || greenCoinNum >= 1 || redCoinNum >= 1 || whiteCoinNum >= 1) buttonPanel.add(pick3Button);

                                            buttonPanel.add((Component) itemListener);
                                            buttonPanel.revalidate();
                                            buttonPanel.repaint();
                                        }
                                        if (whiteBox.isSelected()) {
                                            System.out.println(isP1Turn);
                                            isP1Turn = false;

                                            Coin blackC = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                            init.player1Black.add(blackC);
                                            p1.blackCoin++;
                                            Coin greenC = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                            init.player1Green.add(greenC);
                                            p1.greenCoin++;
                                            Coin whiteC = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                            init.player1White.add(whiteC);
                                            p1.whiteCoin++;

                                            System.out.println(init.slotMachineBlack);
                                            System.out.println(init.slotMachineWhite);
                                            System.out.println(init.slotMachineGreen);

                                            blackCoinNum--;
                                            greenCoinNum--;
                                            whiteCoinNum--;
                                            blackCoinLabel.setText(String.valueOf(blackCoinNum));
                                            greenCoinLabel.setText(String.valueOf(greenCoinNum));
                                            whiteCoinLabel.setText(String.valueOf(whiteCoinNum ));

                                            if (greenCoinNum < 1) {
                                                emptySlotCount++;
                                            }
                                            if (blackCoinNum < 1) {
                                                emptySlotCount++;
                                            }
                                            if (whiteCoinNum < 1) {
                                                emptySlotCount++;
                                            }

                                            buttonPanel.remove(checkBoxPanel);
                                            if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                            if (blackCoinNum >= 1 || blueCoinNum >= 1 || greenCoinNum >= 1 || redCoinNum >= 1 || whiteCoinNum >= 1) buttonPanel.add(pick3Button);

                                            buttonPanel.add((Component) itemListener);
                                            buttonPanel.revalidate();
                                            buttonPanel.repaint();
                                        }
                                    }
                                    if (redBox.isSelected()) {
                                        if (whiteBox.isSelected()) {
                                            System.out.println(isP1Turn);
                                            isP1Turn = false;

                                            Coin blackC = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                            init.player1Black.add(blackC);
                                            p1.blackCoin++;
                                            Coin redC = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                            init.player1Red.add(redC);
                                            p1.redCoin++;
                                            Coin whiteC = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                            init.player1White.add(whiteC);
                                            p1.whiteCoin++;

                                            System.out.println(init.slotMachineBlack);
                                            System.out.println(init.slotMachineRed);
                                            System.out.println(init.slotMachineWhite);

                                            blackCoinNum--;
                                            redCoinNum--;
                                            whiteCoinNum--;
                                            blackCoinLabel.setText(String.valueOf(blackCoinNum));
                                            redCoinLabel.setText(String.valueOf(redCoinNum));
                                            whiteCoinLabel.setText(String.valueOf(whiteCoinNum));

                                            if (whiteCoinNum < 1) {
                                                emptySlotCount++;
                                            }
                                            if (blackCoinNum < 1) {
                                                emptySlotCount++;
                                            }
                                            if (redCoinNum < 1) {
                                                emptySlotCount++;
                                            }

                                            buttonPanel.remove(checkBoxPanel);
                                            if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                            if (blackCoinNum >= 1 || blueCoinNum >= 1 || greenCoinNum >= 1 || redCoinNum >= 1 || whiteCoinNum >= 1) buttonPanel.add(pick3Button);

                                            buttonPanel.add((Component) itemListener);
                                            buttonPanel.revalidate();
                                            buttonPanel.repaint();
                                        }
                                    }
                                }
                                if (blueBox.isSelected()) {
                                    if (greenBox.isSelected()) {
                                        if (redBox.isSelected()) {
                                            System.out.println(isP1Turn);
                                            isP1Turn = false;

                                            Coin blueC = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                            init.player1Blue.add(blueC);
                                            p1.blueCoin++;
                                            Coin greenC = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                            init.player1Green.add(greenC);
                                            p1.greenCoin++;
                                            Coin redC = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                            init.player1Red.add(redC);
                                            p1.redCoin++;

                                            System.out.println(init.slotMachineRed);
                                            System.out.println(init.slotMachineBlue);
                                            System.out.println(init.slotMachineGreen);
                                            greenCoinNum--;
                                            blueCoinNum--;
                                            redCoinNum--;

                                            blueCoinLabel.setText(String.valueOf(blueCoinNum));
                                            greenCoinLabel.setText(String.valueOf(greenCoinNum));
                                            redCoinLabel.setText(String.valueOf(redCoinNum));

                                            if (greenCoinNum < 1) {
                                                emptySlotCount++;
                                            }
                                            if (redCoinNum < 1) {
                                                emptySlotCount++;
                                            }
                                            if (blueCoinNum < 1) {
                                                emptySlotCount++;
                                            }

                                            buttonPanel.remove(checkBoxPanel);
                                            if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                            if (blackCoinNum >= 1 || blueCoinNum >= 1 || greenCoinNum >= 1 || redCoinNum >= 1 || whiteCoinNum >= 1) buttonPanel.add(pick3Button);

                                            buttonPanel.add((Component) itemListener);
                                            buttonPanel.revalidate();
                                            buttonPanel.repaint();
                                        }
                                        if (whiteBox.isSelected()) {
                                            System.out.println(isP1Turn);
                                            isP1Turn = false;

                                            Coin blueC = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                            init.player1Blue.add(blueC);
                                            p1.blueCoin++;
                                            Coin greenC = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                            init.player1Green.add(greenC);
                                            p1.greenCoin++;
                                            Coin whiteC = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                            init.player1White.add(whiteC);
                                            p1.whiteCoin++;

                                            System.out.println(init.slotMachineWhite);
                                            System.out.println(init.slotMachineBlue);
                                            System.out.println(init.slotMachineGreen);

                                            blueCoinNum--;
                                            greenCoinNum--;
                                            whiteCoinNum--;
                                            blueCoinLabel.setText(String.valueOf(blueCoinNum));
                                            greenCoinLabel.setText(String.valueOf(greenCoinNum));
                                            whiteCoinLabel.setText(String.valueOf(whiteCoinNum));

                                            if (greenCoinNum < 1) {
                                                emptySlotCount++;
                                            }
                                            if (whiteCoinNum < 1) {
                                                emptySlotCount++;
                                            }
                                            if (blueCoinNum < 1) {
                                                emptySlotCount++;
                                            }

                                            buttonPanel.remove(checkBoxPanel);
                                            if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                            if (blackCoinNum >= 1 || blueCoinNum >= 1 || greenCoinNum >= 1 || redCoinNum >= 1 || whiteCoinNum >= 1) buttonPanel.add(pick3Button);

                                            buttonPanel.add((Component) itemListener);
                                            buttonPanel.revalidate();
                                            buttonPanel.repaint();
                                        }
                                    }
                                    if (redBox.isSelected()) {
                                        if (whiteBox.isSelected()) {
                                            System.out.println(isP1Turn);
                                            isP1Turn = false;
                                            Coin blueC = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                            init.player1Blue.add(blueC);
                                            p1.blueCoin++;
                                            Coin redC = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                            init.player1Red.add(redC);
                                            p1.redCoin++;
                                            Coin whiteC = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                            init.player1White.add(whiteC);
                                            p1.whiteCoin++;

                                            System.out.println(init.slotMachineWhite);
                                            System.out.println(init.slotMachineBlue);
                                            System.out.println(init.slotMachineRed);

                                            blueCoinNum--;
                                            redCoinNum--;
                                            whiteCoinNum--;
                                            blueCoinLabel.setText(String.valueOf(blueCoinNum));
                                            whiteCoinLabel.setText(String.valueOf(whiteCoinNum));
                                            redCoinLabel.setText(String.valueOf(redCoinNum));

                                            if (whiteCoinNum < 1) {
                                                emptySlotCount++;
                                            }
                                            if (redCoinNum < 1) {
                                                emptySlotCount++;
                                            }
                                            if (blueCoinNum < 1) {
                                                emptySlotCount++;
                                            }

                                            buttonPanel.remove(checkBoxPanel);
                                            if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                            if (blackCoinNum >= 1 || blueCoinNum >= 1 || greenCoinNum >= 1 || redCoinNum >= 1 || whiteCoinNum >= 1) buttonPanel.add(pick3Button);

                                            buttonPanel.add((Component) itemListener);
                                            buttonPanel.revalidate();
                                            buttonPanel.repaint();
                                        }
                                    }
                                }
                                if (greenBox.isSelected()) {
                                    if (redBox.isSelected()) {
                                        if (whiteBox.isSelected()) {
                                            System.out.println(isP1Turn);
                                            isP1Turn = false;

                                            Coin greenC = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                            init.player1Green.add(greenC);
                                            p1.greenCoin++;
                                            Coin redC = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                            init.player1Red.add(redC);
                                            p1.redCoin++;
                                            Coin whiteC = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                            init.player1White.add(whiteC);
                                            p1.whiteCoin++;

                                            System.out.println(init.slotMachineRed);
                                            System.out.println(init.slotMachineWhite);
                                            System.out.println(init.slotMachineGreen);

                                            whiteCoinNum--;
                                            greenCoinNum--;
                                            redCoinNum--;
                                            whiteCoinLabel.setText(String.valueOf(whiteCoinNum));
                                            greenCoinLabel.setText(String.valueOf(greenCoinNum));
                                            redCoinLabel.setText(String.valueOf(redCoinNum));

                                            if (greenCoinNum < 1) {
                                                emptySlotCount++;
                                            }
                                            if (whiteCoinNum < 1) {
                                                emptySlotCount++;
                                            }
                                            if (redCoinNum < 1) {
                                                emptySlotCount++;
                                            }

                                            buttonPanel.remove(checkBoxPanel);
                                            if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                            if (blackCoinNum >= 1 || blueCoinNum >= 1 || greenCoinNum >= 1 || redCoinNum >= 1 || whiteCoinNum >= 1) buttonPanel.add(pick3Button);

                                            buttonPanel.add((Component) itemListener);
                                            buttonPanel.revalidate();
                                            buttonPanel.repaint();
                                        }
                                    }
                                }


                                //when maxSelection == 2:
                                if (blackBox.isSelected()) {
                                    if (blueBox.isSelected()) {
                                        System.out.println(isP1Turn);
                                        isP1Turn = false;

                                        Coin blackC = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                        init.player1Black.add(blackC);
                                        p1.blackCoin++;
                                        Coin blueC = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                        init.player1Blue.add(blueC);
                                        p1.blueCoin++;

                                        System.out.println(init.slotMachineBlack);
                                        System.out.println(init.slotMachineBlue);

                                        blackCoinNum--;
                                        blueCoinNum--;
                                        blackCoinLabel.setText(String.valueOf(blackCoinNum));
                                        blueCoinLabel.setText(String.valueOf(blueCoinNum));

                                        if (blackCoinNum < 1) {
                                            emptySlotCount++;
                                        }
                                        if (blueCoinNum < 1) {
                                            emptySlotCount++;
                                        }

                                        buttonPanel.remove(checkBoxPanel);
                                        if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                        if (!(blackCoinNum < 1 && blueCoinNum < 1)) buttonPanel.add(pick3Button);
                                        buttonPanel.add((Component) itemListener);
                                        buttonPanel.revalidate();
                                        buttonPanel.repaint();
                                    }
                                    if (greenBox.isSelected()) {
                                        System.out.println(isP1Turn);
                                        isP1Turn = false;

                                        Coin blackC = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                        init.player1Black.add(blackC);
                                        p1.blackCoin++;
                                        Coin greenC = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                        init.player1Green.add(greenC);
                                        p1.greenCoin++;

                                        System.out.println(init.slotMachineBlack);
                                        System.out.println(init.slotMachineGreen);

                                        blackCoinNum--;
                                        greenCoinNum--;
                                        blackCoinLabel.setText(String.valueOf(blackCoinNum));
                                        greenCoinLabel.setText(String.valueOf(greenCoinNum));

                                        if (blackCoinNum < 1) {
                                            emptySlotCount++;
                                        }
                                        if (greenCoinNum < 1) {
                                            emptySlotCount++;
                                        }

                                        buttonPanel.remove(checkBoxPanel);
                                        if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                        if (!(blackCoinNum < 1 && greenCoinNum < 1)) buttonPanel.add(pick3Button);
                                        buttonPanel.add((Component) itemListener);
                                        buttonPanel.revalidate();
                                        buttonPanel.repaint();
                                    }
                                    if (redBox.isSelected()) {
                                        System.out.println(isP1Turn);
                                        isP1Turn = false;

                                        Coin blackC = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                        init.player1Black.add(blackC);
                                        p1.blackCoin++;
                                        Coin redC = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                        init.player1Red.add(redC);
                                        p1.redCoin++;

                                        System.out.println(init.slotMachineBlack);
                                        System.out.println(init.slotMachineRed);

                                        blackCoinNum--;
                                        redCoinNum--;
                                        blackCoinLabel.setText(String.valueOf(blackCoinNum));
                                        redCoinLabel.setText(String.valueOf(redCoinNum));

                                        if (blackCoinNum < 1) {
                                            emptySlotCount++;
                                        }
                                        if (redCoinNum < 1) {
                                            emptySlotCount++;
                                        }

                                        buttonPanel.remove(checkBoxPanel);
                                        if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                        if (!(blackCoinNum < 1 && redCoinNum < 1)) buttonPanel.add(pick3Button);
                                        buttonPanel.add((Component) itemListener);
                                        buttonPanel.revalidate();
                                        buttonPanel.repaint();
                                    }
                                    if (whiteBox.isSelected()) {
                                        System.out.println(isP1Turn);
                                        isP1Turn = false;

                                        Coin blackC = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                        init.player1Black.add(blackC);
                                        p1.blackCoin++;
                                        Coin whiteC = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                        init.player1White.add(whiteC);
                                        p1.whiteCoin++;

                                        System.out.println(init.slotMachineBlack);
                                        System.out.println(init.slotMachineWhite);

                                        blackCoinNum--;
                                        whiteCoinNum--;
                                        blackCoinLabel.setText(String.valueOf(blackCoinNum));
                                        whiteCoinLabel.setText(String.valueOf(whiteCoinNum));

                                        if (blackCoinNum < 1) {
                                            emptySlotCount++;
                                        }
                                        if (whiteCoinNum < 1) {
                                            emptySlotCount++;
                                        }

                                        buttonPanel.remove(checkBoxPanel);
                                        if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                        if (!(blackCoinNum < 1 && whiteCoinNum < 1)) buttonPanel.add(pick3Button);
                                        buttonPanel.add((Component) itemListener);
                                        buttonPanel.revalidate();
                                        buttonPanel.repaint();
                                    }
                                }
                                if (blueBox.isSelected()) {
                                    if (greenBox.isSelected()) {
                                        System.out.println(isP1Turn);
                                        isP1Turn = false;

                                        Coin blueC = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                        init.player1Blue.add(blueC);
                                        p1.blueCoin++;
                                        Coin greenC = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                        init.player1Green.add(greenC);
                                        p1.greenCoin++;

                                        System.out.println(init.slotMachineBlue);
                                        System.out.println(init.slotMachineGreen);

                                        blueCoinNum--;
                                        greenCoinNum--;
                                        blueCoinLabel.setText(String.valueOf(blueCoinNum));
                                        greenCoinLabel.setText(String.valueOf(greenCoinNum));

                                        if (blueCoinNum < 1) {
                                            emptySlotCount++;
                                        }
                                        if (greenCoinNum < 1) {
                                            emptySlotCount++;
                                        }

                                        buttonPanel.remove(checkBoxPanel);
                                        if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                        if (!(blueCoinNum < 1 && greenCoinNum < 1)) buttonPanel.add(pick3Button);
                                        buttonPanel.add((Component) itemListener);
                                        buttonPanel.revalidate();
                                        buttonPanel.repaint();
                                    }
                                    if (redBox.isSelected()) {
                                        System.out.println(isP1Turn);
                                        isP1Turn = false;

                                        Coin blueC = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                        init.player1Blue.add(blueC);
                                        p1.blueCoin++;
                                        Coin redC = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                        init.player1Red.add(redC);
                                        p1.redCoin++;

                                        System.out.println(init.slotMachineBlue);
                                        System.out.println(init.slotMachineRed);

                                        blueCoinNum--;
                                        redCoinNum--;
                                        blueCoinLabel.setText(String.valueOf(blueCoinNum));
                                        redCoinLabel.setText(String.valueOf(redCoinNum));

                                        if (blueCoinNum < 1) {
                                            emptySlotCount++;
                                        }
                                        if (redCoinNum < 1) {
                                            emptySlotCount++;
                                        }

                                        buttonPanel.remove(checkBoxPanel);
                                        if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                        if (!(blueCoinNum < 1 && redCoinNum < 1)) buttonPanel.add(pick3Button);
                                        buttonPanel.add((Component) itemListener);
                                        buttonPanel.revalidate();
                                        buttonPanel.repaint();
                                    }
                                    if (whiteBox.isSelected()) {
                                        System.out.println(isP1Turn);
                                        isP1Turn = false;

                                        Coin blueC = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                        init.player1Blue.add(blueC);
                                        p1.blueCoin++;
                                        Coin whiteC = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                        init.player1White.add(whiteC);
                                        p1.whiteCoin++;

                                        System.out.println(init.slotMachineBlue);
                                        System.out.println(init.slotMachineWhite);

                                        blueCoinNum--;
                                        whiteCoinNum--;
                                        blueCoinLabel.setText(String.valueOf(blueCoinNum));
                                        whiteCoinLabel.setText(String.valueOf(whiteCoinNum));

                                        if (blueCoinNum < 1) {
                                            emptySlotCount++;
                                        }
                                        if (whiteCoinNum < 1) {
                                            emptySlotCount++;
                                        }

                                        buttonPanel.remove(checkBoxPanel);
                                        if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                        if (!(blueCoinNum < 1 && whiteCoinNum < 1)) buttonPanel.add(pick3Button);
                                        buttonPanel.add((Component) itemListener);
                                        buttonPanel.revalidate();
                                        buttonPanel.repaint();
                                    }
                                }
                                if (greenBox.isSelected()) {
                                    if (redBox.isSelected()) {
                                        System.out.println(isP1Turn);
                                        isP1Turn = false;

                                        Coin greenC = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                        init.player1Green.add(greenC);
                                        p1.greenCoin++;
                                        Coin redC = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                        init.player1Red.add(redC);
                                        p1.redCoin++;

                                        System.out.println(init.slotMachineGreen);
                                        System.out.println(init.slotMachineRed);

                                        greenCoinNum--;
                                        redCoinNum--;
                                        greenCoinLabel.setText(String.valueOf(greenCoinNum));
                                        redCoinLabel.setText(String.valueOf(redCoinNum));

                                        if (greenCoinNum < 1) {
                                            emptySlotCount++;
                                        }
                                        if (redCoinNum < 1) {
                                            emptySlotCount++;
                                        }

                                        buttonPanel.remove(checkBoxPanel);
                                        if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                        if (!(greenCoinNum < 1 && redCoinNum < 1)) buttonPanel.add(pick3Button);
                                        buttonPanel.add((Component) itemListener);
                                        buttonPanel.revalidate();
                                        buttonPanel.repaint();
                                    }
                                    if (whiteBox.isSelected()) {
                                        System.out.println(isP1Turn);
                                        isP1Turn = false;

                                        Coin greenC = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                        init.player1Green.add(greenC);
                                        p1.greenCoin++;
                                        Coin whiteC = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                        init.player1White.add(whiteC);
                                        p1.whiteCoin++;

                                        System.out.println(init.slotMachineGreen);
                                        System.out.println(init.slotMachineWhite);

                                        greenCoinNum--;
                                        whiteCoinNum--;
                                        greenCoinLabel.setText(String.valueOf(greenCoinNum));
                                        whiteCoinLabel.setText(String.valueOf(whiteCoinNum));

                                        if (greenCoinNum < 1) {
                                            emptySlotCount++;
                                        }
                                        if (whiteCoinNum < 1) {
                                            emptySlotCount++;
                                        }

                                        buttonPanel.remove(checkBoxPanel);
                                        if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                        if (!(greenCoinNum < 1 && whiteCoinNum < 1)) buttonPanel.add(pick3Button);
                                        buttonPanel.add((Component) itemListener);
                                        buttonPanel.revalidate();
                                        buttonPanel.repaint();
                                    }
                                }
                                if (redBox.isSelected()) {
                                    if (whiteBox.isSelected()) {
                                        System.out.println(isP1Turn);
                                        isP1Turn = false;

                                        Coin whiteC = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                        init.player1White.add(whiteC);
                                        p1.whiteCoin++;
                                        Coin redC = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                        init.player1Red.add(redC);
                                        p1.redCoin++;

                                        System.out.println(init.slotMachineWhite);
                                        System.out.println(init.slotMachineRed);

                                        whiteCoinNum--;
                                        redCoinNum--;
                                        whiteCoinLabel.setText(String.valueOf(whiteCoinNum));
                                        redCoinLabel.setText(String.valueOf(redCoinNum));

                                        if (whiteCoinNum < 1) {
                                            emptySlotCount++;
                                        }
                                        if (redCoinNum < 1) {
                                            emptySlotCount++;
                                        }

                                        buttonPanel.remove(checkBoxPanel);
                                        if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                        if (!(redCoinNum < 1 && whiteCoinNum < 1)) buttonPanel.add(pick3Button);
                                        buttonPanel.add((Component) itemListener);
                                        buttonPanel.revalidate();
                                        buttonPanel.repaint();
                                    }
                                }

                                //when maxSelection == 1:
                                if (blackBox.isSelected()) {
                                    System.out.println(isP1Turn);
                                    isP1Turn = false;

                                    Coin blackC = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                    init.player1Black.add(blackC);
                                    p1.blackCoin++;

                                    System.out.println(init.slotMachineBlack);

                                    blackCoinNum--;
                                    
                                    blackCoinLabel.setText(String.valueOf(blackCoinNum));

                                    if (blackCoinNum < 1) {
                                        emptySlotCount++;
                                    }

                                    buttonPanel.remove(checkBoxPanel);
                                    if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                    if (emptySlotCount < 5) buttonPanel.add(pick3Button);
                                    buttonPanel.add((Component) itemListener);
                                    buttonPanel.revalidate();
                                    buttonPanel.repaint();
                                }
                                if (blueBox.isSelected()) {
                                    System.out.println(isP1Turn);
                                    isP1Turn = false;

                                    Coin blueC = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                    init.player1Blue.add(blueC);
                                    p1.blueCoin++;

                                    System.out.println(init.slotMachineBlue);

                                    blueCoinNum--;

                                    blueCoinLabel.setText(String.valueOf(blueCoinNum));

                                    if (blueCoinNum < 1) {
                                        emptySlotCount++;
                                    }

                                    buttonPanel.remove(checkBoxPanel);
                                    if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                    if (emptySlotCount < 5) buttonPanel.add(pick3Button);
                                    buttonPanel.add((Component) itemListener);
                                    buttonPanel.revalidate();
                                    buttonPanel.repaint();
                                }
                                if (greenBox.isSelected()) {
                                    System.out.println(isP1Turn);
                                    isP1Turn = false;

                                    Coin greenC = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                    init.player1Green.add(greenC);
                                    p1.greenCoin++;

                                    System.out.println(init.slotMachineGreen);

                                    greenCoinNum--;

                                    greenCoinLabel.setText(String.valueOf(greenCoinNum));

                                    if (greenCoinNum < 1) {
                                        emptySlotCount++;
                                    }

                                    buttonPanel.remove(checkBoxPanel);
                                    if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                    if (emptySlotCount < 5) buttonPanel.add(pick3Button);
                                    buttonPanel.add((Component) itemListener);
                                    buttonPanel.revalidate();
                                    buttonPanel.repaint();
                                }
                                if (redBox.isSelected()) {
                                    System.out.println(isP1Turn);
                                    isP1Turn = false;

                                    Coin redC = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                    init.player1Red.add(redC);
                                    p1.redCoin++;

                                    System.out.println(init.slotMachineRed);

                                    redCoinNum--;

                                    redCoinLabel.setText(String.valueOf(redCoinNum));

                                    if (redCoinNum < 1) {
                                        emptySlotCount++;
                                    }

                                    buttonPanel.remove(checkBoxPanel);
                                    if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                    if (emptySlotCount < 5) buttonPanel.add(pick3Button);
                                    buttonPanel.add((Component) itemListener);
                                    buttonPanel.revalidate();
                                    buttonPanel.repaint();
                                }
                                if (whiteBox.isSelected()) {
                                    System.out.println(isP1Turn);
                                    isP1Turn = false;

                                    Coin whiteC = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                    init.player1White.add(whiteC);
                                    p1.whiteCoin++;

                                    System.out.println(init.slotMachineWhite);

                                    whiteCoinNum--;

                                    whiteCoinLabel.setText(String.valueOf(whiteCoinNum));

                                    if (whiteCoinNum < 1) {
                                        emptySlotCount++;
                                    }

                                    buttonPanel.remove(checkBoxPanel);
                                    if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                    if (emptySlotCount < 5) buttonPanel.add(pick3Button);
                                    buttonPanel.add((Component) itemListener);
                                    buttonPanel.revalidate();
                                    buttonPanel.repaint();
                                }
                            }



                            //player2's turn:
                            else {
                                //when maxSelection == 3:
                                if (blackBox.isSelected()) {
                                    if (blueBox.isSelected()) {
                                        if (greenBox.isSelected()) {
                                            System.out.println(isP1Turn);
                                            isP1Turn = true;

                                            Coin blackC = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                            init.player2Black.add(blackC);
                                            p2.blackCoin++;
                                            Coin blueC = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                            init.player2Blue.add(blueC);
                                            p2.blueCoin++;
                                            Coin greenC = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                            init.player2Green.add(greenC);
                                            p2.greenCoin++;

                                            System.out.println(init.slotMachineBlack);
                                            System.out.println(init.slotMachineBlue);
                                            System.out.println(init.slotMachineGreen);

                                            greenCoinNum--;
                                            blackCoinNum--;
                                            blueCoinNum--;
                                            blackCoinLabel.setText(String.valueOf(blackCoinNum));
                                            blueCoinLabel.setText(String.valueOf(blueCoinNum));
                                            greenCoinLabel.setText(String.valueOf(greenCoinNum));

                                            if (greenCoinNum < 1) {
                                                emptySlotCount++;
                                            }
                                            if (blackCoinNum < 1) {
                                                emptySlotCount++;
                                            }
                                            if (blueCoinNum < 1) {
                                                emptySlotCount++;
                                            }

                                            buttonPanel.remove(checkBoxPanel);
                                            if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                            if (blackCoinNum >= 1 || blueCoinNum >= 1 || greenCoinNum >= 1 || redCoinNum >= 1 || whiteCoinNum >= 1) buttonPanel.add(pick3Button);

                                            buttonPanel.add((Component) itemListener);
                                            buttonPanel.revalidate();
                                            buttonPanel.repaint();
                                        }
                                        if (redBox.isSelected()) {
                                            System.out.println(isP1Turn);
                                            isP1Turn = true;

                                            Coin blackC = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                            init.player2Black.add(blackC);
                                            p2.blackCoin++;
                                            Coin blueC = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                            init.player2Blue.add(blueC);
                                            p2.blueCoin++;
                                            Coin redC = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                            init.player2Red.add(redC);
                                            p2.redCoin++;

                                            System.out.println(init.slotMachineBlack);
                                            System.out.println(init.slotMachineBlue);
                                            System.out.println(init.slotMachineRed);

                                            blackCoinNum--;
                                            blueCoinNum--;
                                            redCoinNum--;
                                            blackCoinLabel.setText(String.valueOf(blackCoinNum));
                                            blueCoinLabel.setText(String.valueOf(blueCoinNum));
                                            redCoinLabel.setText(String.valueOf(redCoinNum));

                                            if (redCoinNum < 1) {
                                                emptySlotCount++;
                                            }
                                            if (blackCoinNum < 1) {
                                                emptySlotCount++;
                                            }
                                            if (blueCoinNum < 1) {
                                                emptySlotCount++;
                                            }

                                            buttonPanel.remove(checkBoxPanel);
                                            if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                            if (blackCoinNum >= 1 || blueCoinNum >= 1 || greenCoinNum >= 1 || redCoinNum >= 1 || whiteCoinNum >= 1) buttonPanel.add(pick3Button);

                                            buttonPanel.add((Component) itemListener);
                                            buttonPanel.revalidate();
                                            buttonPanel.repaint();
                                        }
                                        if (whiteBox.isSelected()) {
                                            System.out.println(isP1Turn);
                                            isP1Turn = true;

                                            Coin blackC = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                            init.player2Black.add(blackC);
                                            p2.blackCoin++;
                                            Coin blueC = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                            init.player2Blue.add(blueC);
                                            p2.blueCoin++;
                                            Coin whiteC = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                            init.player2White.add(whiteC);
                                            p2.whiteCoin++;

                                            System.out.println(init.slotMachineBlack);
                                            System.out.println(init.slotMachineBlue);
                                            System.out.println(init.slotMachineWhite);

                                            blackCoinNum--;
                                            blueCoinNum--;
                                            whiteCoinNum--;
                                            blackCoinLabel.setText(String.valueOf(blackCoinNum));
                                            blueCoinLabel.setText(String.valueOf(blueCoinNum));
                                            whiteCoinLabel.setText(String.valueOf(whiteCoinNum));

                                            if (whiteCoinNum < 1) {
                                                emptySlotCount++;
                                            }
                                            if (blackCoinNum < 1) {
                                                emptySlotCount++;
                                            }
                                            if (blueCoinNum < 1) {
                                                emptySlotCount++;
                                            }

                                            buttonPanel.remove(checkBoxPanel);
                                            if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                            if (blackCoinNum >= 1 || blueCoinNum >= 1 || greenCoinNum >= 1 || redCoinNum >= 1 || whiteCoinNum >= 1) buttonPanel.add(pick3Button);

                                            buttonPanel.add((Component) itemListener);
                                            buttonPanel.revalidate();
                                            buttonPanel.repaint();
                                        }
                                    }
                                    if (greenBox.isSelected()) {
                                        if (redBox.isSelected()) {
                                            System.out.println(isP1Turn);
                                            isP1Turn = true;

                                            Coin blackC = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                            init.player2Black.add(blackC);
                                            p2.blackCoin++;
                                            Coin greenC = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                            init.player2Green.add(greenC);
                                            p2.greenCoin++;
                                            Coin redC = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                            init.player2Red.add(redC);
                                            p2.redCoin++;

                                            System.out.println(init.slotMachineBlack);
                                            System.out.println(init.slotMachineRed);
                                            System.out.println(init.slotMachineGreen);

                                            blackCoinNum--;
                                            greenCoinNum--;
                                            redCoinNum--;
                                            blackCoinLabel.setText(String.valueOf(blackCoinNum));
                                            greenCoinLabel.setText(String.valueOf(greenCoinNum));
                                            redCoinLabel.setText(String.valueOf(redCoinNum));

                                            if (greenCoinNum < 1) {
                                                emptySlotCount++;
                                            }
                                            if (blackCoinNum < 1) {
                                                emptySlotCount++;
                                            }
                                            if (redCoinNum < 1) {
                                                emptySlotCount++;
                                            }

                                            buttonPanel.remove(checkBoxPanel);
                                            if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                            if (blackCoinNum >= 1 || blueCoinNum >= 1 || greenCoinNum >= 1 || redCoinNum >= 1 || whiteCoinNum >= 1) buttonPanel.add(pick3Button);

                                            buttonPanel.add((Component) itemListener);
                                            buttonPanel.revalidate();
                                            buttonPanel.repaint();
                                        }
                                        if (whiteBox.isSelected()) {
                                            System.out.println(isP1Turn);
                                            isP1Turn = true;

                                            Coin blackC = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                            init.player2Black.add(blackC);
                                            p2.blackCoin++;
                                            Coin greenC = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                            init.player2Green.add(greenC);
                                            p2.greenCoin++;
                                            Coin whiteC = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                            init.player2White.add(whiteC);
                                            p2.whiteCoin++;

                                            System.out.println(init.slotMachineBlack);
                                            System.out.println(init.slotMachineWhite);
                                            System.out.println(init.slotMachineGreen);

                                            blackCoinNum--;
                                            greenCoinNum--;
                                            whiteCoinNum--;
                                            blackCoinLabel.setText(String.valueOf(blackCoinNum));
                                            greenCoinLabel.setText(String.valueOf(greenCoinNum));
                                            whiteCoinLabel.setText(String.valueOf(whiteCoinNum ));

                                            if (greenCoinNum < 1) {
                                                emptySlotCount++;
                                            }
                                            if (blackCoinNum < 1) {
                                                emptySlotCount++;
                                            }
                                            if (whiteCoinNum < 1) {
                                                emptySlotCount++;
                                            }

                                            buttonPanel.remove(checkBoxPanel);
                                            if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                            if (blackCoinNum >= 1 || blueCoinNum >= 1 || greenCoinNum >= 1 || redCoinNum >= 1 || whiteCoinNum >= 1) buttonPanel.add(pick3Button);

                                            buttonPanel.add((Component) itemListener);
                                            buttonPanel.revalidate();
                                            buttonPanel.repaint();
                                        }
                                    }
                                    if (redBox.isSelected()) {
                                        if (whiteBox.isSelected()) {
                                            System.out.println(isP1Turn);
                                            isP1Turn = true;

                                            Coin blackC = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                            init.player2Black.add(blackC);
                                            p2.blackCoin++;
                                            Coin redC = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                            init.player2Red.add(redC);
                                            p2.redCoin++;
                                            Coin whiteC = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                            init.player2White.add(whiteC);
                                            p2.whiteCoin++;

                                            System.out.println(init.slotMachineBlack);
                                            System.out.println(init.slotMachineRed);
                                            System.out.println(init.slotMachineWhite);

                                            blackCoinNum--;
                                            redCoinNum--;
                                            whiteCoinNum--;
                                            blackCoinLabel.setText(String.valueOf(blackCoinNum));
                                            redCoinLabel.setText(String.valueOf(redCoinNum));
                                            whiteCoinLabel.setText(String.valueOf(whiteCoinNum));

                                            if (redCoinNum < 1) {
                                                emptySlotCount++;
                                            }
                                            if (blackCoinNum < 1) {
                                                emptySlotCount++;
                                            }
                                            if (whiteCoinNum < 1) {
                                                emptySlotCount++;
                                            }

                                            buttonPanel.remove(checkBoxPanel);
                                            if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                            if (blackCoinNum >= 1 || blueCoinNum >= 1 || greenCoinNum >= 1 || redCoinNum >= 1 || whiteCoinNum >= 1) buttonPanel.add(pick3Button);

                                            buttonPanel.add((Component) itemListener);
                                            buttonPanel.revalidate();
                                            buttonPanel.repaint();
                                        }
                                    }
                                }
                                if (blueBox.isSelected()) {
                                    if (greenBox.isSelected()) {
                                        if (redBox.isSelected()) {
                                            System.out.println(isP1Turn);
                                            isP1Turn = true;

                                            Coin blueC = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                            init.player2Blue.add(blueC);
                                            p2.blueCoin++;
                                            Coin greenC = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                            init.player2Green.add(greenC);
                                            p2.greenCoin++;
                                            Coin redC = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                            init.player2Red.add(redC);
                                            p2.redCoin++;

                                            System.out.println(init.slotMachineRed);
                                            System.out.println(init.slotMachineBlue);
                                            System.out.println(init.slotMachineGreen);

                                            greenCoinNum--;
                                            blueCoinNum--;
                                            redCoinNum--;
                                            blueCoinLabel.setText(String.valueOf(blueCoinNum));
                                            greenCoinLabel.setText(String.valueOf(greenCoinNum));
                                            redCoinLabel.setText(String.valueOf(redCoinNum));

                                            if (greenCoinNum < 1) {
                                                emptySlotCount++;
                                            }
                                            if (redCoinNum < 1) {
                                                emptySlotCount++;
                                            }
                                            if (blueCoinNum < 1) {
                                                emptySlotCount++;
                                            }

                                            buttonPanel.remove(checkBoxPanel);
                                            if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                            if (blackCoinNum >= 1 || blueCoinNum >= 1 || greenCoinNum >= 1 || redCoinNum >= 1 || whiteCoinNum >= 1) buttonPanel.add(pick3Button);

                                            buttonPanel.add((Component) itemListener);
                                            buttonPanel.revalidate();
                                            buttonPanel.repaint();
                                        }
                                        if (whiteBox.isSelected()) {
                                            System.out.println(isP1Turn);
                                            isP1Turn = true;

                                            Coin blueC = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                            init.player2Blue.add(blueC);
                                            p2.blueCoin++;
                                            Coin greenC = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                            init.player2Green.add(greenC);
                                            p2.greenCoin++;
                                            Coin whiteC = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                            init.player2White.add(whiteC);
                                            p2.whiteCoin++;

                                            System.out.println(init.slotMachineWhite);
                                            System.out.println(init.slotMachineBlue);
                                            System.out.println(init.slotMachineGreen);

                                            blueCoinNum--;
                                            greenCoinNum--;
                                            whiteCoinNum--;
                                            blueCoinLabel.setText(String.valueOf(blueCoinNum));
                                            greenCoinLabel.setText(String.valueOf(greenCoinNum));
                                            whiteCoinLabel.setText(String.valueOf(whiteCoinNum));

                                            if (greenCoinNum < 1) {
                                                emptySlotCount++;
                                            }
                                            if (whiteCoinNum < 1) {
                                                emptySlotCount++;
                                            }
                                            if (blueCoinNum < 1) {
                                                emptySlotCount++;
                                            }

                                            buttonPanel.remove(checkBoxPanel);
                                            if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                            if (blackCoinNum >= 1 || blueCoinNum >= 1 || greenCoinNum >= 1 || redCoinNum >= 1 || whiteCoinNum >= 1) buttonPanel.add(pick3Button);

                                            buttonPanel.add((Component) itemListener);
                                            buttonPanel.revalidate();
                                            buttonPanel.repaint();
                                        }
                                    }
                                    if (redBox.isSelected()) {
                                        if (whiteBox.isSelected()) {
                                            System.out.println(isP1Turn);
                                            isP1Turn = true;

                                            Coin blueC = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                            init.player2Blue.add(blueC);
                                            p2.blueCoin++;
                                            Coin redC = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                            init.player2Red.add(redC);
                                            p2.redCoin++;
                                            Coin whiteC = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                            init.player2White.add(whiteC);
                                            p2.whiteCoin++;

                                            System.out.println(init.slotMachineWhite);
                                            System.out.println(init.slotMachineBlue);
                                            System.out.println(init.slotMachineRed);

                                            blueCoinNum--;
                                            redCoinNum--;
                                            whiteCoinNum--;
                                            blueCoinLabel.setText(String.valueOf(blueCoinNum));
                                            whiteCoinLabel.setText(String.valueOf(whiteCoinNum));
                                            redCoinLabel.setText(String.valueOf(redCoinNum));

                                            if (whiteCoinNum < 1) {
                                                emptySlotCount++;
                                            }
                                            if (redCoinNum < 1) {
                                                emptySlotCount++;
                                            }
                                            if (blueCoinNum < 1) {
                                                emptySlotCount++;
                                            }

                                            buttonPanel.remove(checkBoxPanel);
                                            if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                            if (blackCoinNum >= 1 || blueCoinNum >= 1 || greenCoinNum >= 1 || redCoinNum >= 1 || whiteCoinNum >= 1) buttonPanel.add(pick3Button);

                                            buttonPanel.add((Component) itemListener);
                                            buttonPanel.revalidate();
                                            buttonPanel.repaint();
                                        }
                                    }
                                }
                                if (greenBox.isSelected()) {
                                    if (redBox.isSelected()) {
                                        if (whiteBox.isSelected()) {
                                            System.out.println(isP1Turn);
                                            isP1Turn = true;

                                            Coin greenC = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                            init.player2Green.add(greenC);
                                            p2.greenCoin++;
                                            Coin redC = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                            init.player2Red.add(redC);
                                            p2.redCoin++;
                                            Coin whiteC = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                            init.player2White.add(whiteC);
                                            p2.whiteCoin++;

                                            System.out.println(init.slotMachineWhite);
                                            System.out.println(init.slotMachineRed);
                                            System.out.println(init.slotMachineGreen);

                                            whiteCoinNum--;
                                            greenCoinNum--;
                                            redCoinNum--;
                                            whiteCoinLabel.setText(String.valueOf(whiteCoinNum));
                                            greenCoinLabel.setText(String.valueOf(greenCoinNum));
                                            redCoinLabel.setText(String.valueOf(redCoinNum));

                                            if (greenCoinNum < 1) {
                                                emptySlotCount++;
                                            }
                                            if (whiteCoinNum < 1) {
                                                emptySlotCount++;
                                            }
                                            if (redCoinNum < 1) {
                                                emptySlotCount++;
                                            }

                                            buttonPanel.remove(checkBoxPanel);
                                            if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                            if (blackCoinNum >= 1 || blueCoinNum >= 1 || greenCoinNum >= 1 || redCoinNum >= 1 || whiteCoinNum >= 1) buttonPanel.add(pick3Button);

                                            buttonPanel.add((Component) itemListener);
                                            buttonPanel.revalidate();
                                            buttonPanel.repaint();
                                        }
                                    }
                                }


                                //when maxSelection == 2:
                                if (blackBox.isSelected()) {
                                    if (blueBox.isSelected()) {
                                        System.out.println(isP1Turn);
                                        isP1Turn = true;

                                        Coin blackC = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                        init.player2Black.add(blackC);
                                        p2.blackCoin++;
                                        Coin blueC = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                        init.player2Blue.add(blueC);
                                        p2.blueCoin++;

                                        System.out.println(init.slotMachineBlack);
                                        System.out.println(init.slotMachineBlue);

                                        blackCoinNum--;
                                        blueCoinNum--;
                                        blackCoinLabel.setText(String.valueOf(blackCoinNum));
                                        blueCoinLabel.setText(String.valueOf(blueCoinNum));

                                        if (blackCoinNum < 1) {
                                            emptySlotCount++;
                                        }
                                        if (blueCoinNum < 1) {
                                            emptySlotCount++;
                                        }

                                        buttonPanel.remove(checkBoxPanel);
                                        if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                        if (!(blackCoinNum < 1 && blueCoinNum < 1)) buttonPanel.add(pick3Button);
                                        buttonPanel.add((Component) itemListener);
                                        buttonPanel.revalidate();
                                        buttonPanel.repaint();
                                    }
                                    if (greenBox.isSelected()) {
                                        System.out.println(isP1Turn);
                                        isP1Turn = true;

                                        Coin blackC = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                        init.player2Black.add(blackC);
                                        p2.blackCoin++;
                                        Coin greenC = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                        init.player2Green.add(greenC);
                                        p2.greenCoin++;

                                        System.out.println(init.slotMachineBlack);
                                        System.out.println(init.slotMachineGreen);

                                        blackCoinNum--;
                                        greenCoinNum--;
                                        blackCoinLabel.setText(String.valueOf(blackCoinNum));
                                        greenCoinLabel.setText(String.valueOf(greenCoinNum));

                                        if (blackCoinNum < 1) {
                                            emptySlotCount++;
                                        }
                                        if (greenCoinNum < 1) {
                                            emptySlotCount++;
                                        }

                                        buttonPanel.remove(checkBoxPanel);
                                        if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                        if (!(blackCoinNum < 1 && greenCoinNum < 1)) buttonPanel.add(pick3Button);
                                        buttonPanel.add((Component) itemListener);
                                        buttonPanel.revalidate();
                                        buttonPanel.repaint();
                                    }
                                    if (redBox.isSelected()) {
                                        System.out.println(isP1Turn);
                                        isP1Turn = true;

                                        Coin blackC = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                        init.player2Black.add(blackC);
                                        p2.blackCoin++;
                                        Coin redC = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                        init.player2Red.add(redC);
                                        p2.redCoin++;

                                        System.out.println(init.slotMachineBlack);
                                        System.out.println(init.slotMachineRed);

                                        blackCoinNum--;
                                        redCoinNum--;
                                        blackCoinLabel.setText(String.valueOf(blackCoinNum));
                                        redCoinLabel.setText(String.valueOf(redCoinNum));

                                        if (blackCoinNum < 1) {
                                            emptySlotCount++;
                                        }
                                        if (redCoinNum < 1) {
                                            emptySlotCount++;
                                        }

                                        buttonPanel.remove(checkBoxPanel);
                                        if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                        if (!(blackCoinNum < 1 && redCoinNum < 1)) buttonPanel.add(pick3Button);
                                        buttonPanel.add((Component) itemListener);
                                        buttonPanel.revalidate();
                                        buttonPanel.repaint();
                                    }
                                    if (whiteBox.isSelected()) {
                                        System.out.println(isP1Turn);
                                        isP1Turn = true;

                                        Coin blackC = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                        init.player2Black.add(blackC);
                                        p2.blackCoin++;
                                        Coin whiteC = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                        init.player2White.add(whiteC);
                                        p2.whiteCoin++;

                                        System.out.println(init.slotMachineBlack);
                                        System.out.println(init.slotMachineWhite);

                                        blackCoinNum--;
                                        whiteCoinNum--;
                                        blackCoinLabel.setText(String.valueOf(blackCoinNum));
                                        whiteCoinLabel.setText(String.valueOf(whiteCoinNum));

                                        if (blackCoinNum < 1) {
                                            emptySlotCount++;
                                        }
                                        if (whiteCoinNum < 1) {
                                            emptySlotCount++;
                                        }

                                        buttonPanel.remove(checkBoxPanel);
                                        if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                        if (!(blackCoinNum < 1 && whiteCoinNum < 1)) buttonPanel.add(pick3Button);
                                        buttonPanel.add((Component) itemListener);
                                        buttonPanel.revalidate();
                                        buttonPanel.repaint();
                                    }
                                }
                                if (blueBox.isSelected()) {
                                    if (greenBox.isSelected()) {
                                        System.out.println(isP1Turn);
                                        isP1Turn = true;

                                        Coin blueC = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                        init.player2Blue.add(blueC);
                                        p2.blueCoin++;
                                        Coin greenC = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                        init.player2Green.add(greenC);
                                        p2.greenCoin++;

                                        System.out.println(init.slotMachineBlue);
                                        System.out.println(init.slotMachineGreen);

                                        blueCoinNum--;
                                        greenCoinNum--;
                                        blueCoinLabel.setText(String.valueOf(blueCoinNum));
                                        greenCoinLabel.setText(String.valueOf(greenCoinNum));

                                        if (blueCoinNum < 1) {
                                            emptySlotCount++;
                                        }
                                        if (greenCoinNum < 1) {
                                            emptySlotCount++;
                                        }

                                        buttonPanel.remove(checkBoxPanel);
                                        if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                        if (!(blueCoinNum < 1 && greenCoinNum < 1)) buttonPanel.add(pick3Button);
                                        buttonPanel.add((Component) itemListener);
                                        buttonPanel.revalidate();
                                        buttonPanel.repaint();
                                    }
                                    if (redBox.isSelected()) {
                                        System.out.println(isP1Turn);
                                        isP1Turn = true;

                                        Coin blueC = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                        init.player2Blue.add(blueC);
                                        p2.blueCoin++;
                                        Coin redC = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                        init.player2Red.add(redC);
                                        p2.redCoin++;

                                        System.out.println(init.slotMachineBlue);
                                        System.out.println(init.slotMachineRed);

                                        blueCoinNum--;
                                        redCoinNum--;
                                        blueCoinLabel.setText(String.valueOf(blueCoinNum));
                                        redCoinLabel.setText(String.valueOf(redCoinNum));

                                        if (blueCoinNum < 1) {
                                            emptySlotCount++;
                                        }
                                        if (redCoinNum < 1) {
                                            emptySlotCount++;
                                        }

                                        buttonPanel.remove(checkBoxPanel);
                                        buttonPanel.add(pick2Button);
                                        if (!(blueCoinNum < 1 && redCoinNum < 1)) buttonPanel.add(pick3Button);
                                        buttonPanel.add((Component) itemListener);
                                        buttonPanel.revalidate();
                                        buttonPanel.repaint();
                                    }
                                    if (whiteBox.isSelected()) {
                                        System.out.println(isP1Turn);
                                        isP1Turn = true;

                                        Coin blueC = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                        init.player2Blue.add(blueC);
                                        p2.blueCoin++;
                                        Coin whiteC = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                        init.player2White.add(whiteC);
                                        p2.whiteCoin++;

                                        System.out.println(init.slotMachineBlue);
                                        System.out.println(init.slotMachineWhite);

                                        blueCoinNum--;
                                        whiteCoinNum--;
                                        blueCoinLabel.setText(String.valueOf(blueCoinNum));
                                        whiteCoinLabel.setText(String.valueOf(whiteCoinNum));

                                        if (blueCoinNum < 1) {
                                            emptySlotCount++;
                                        }
                                        if (whiteCoinNum < 1) {
                                            emptySlotCount++;
                                        }

                                        buttonPanel.remove(checkBoxPanel);
                                        if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                        if (!(blueCoinNum < 1 && whiteCoinNum < 1)) buttonPanel.add(pick3Button);
                                        buttonPanel.add((Component) itemListener);
                                        buttonPanel.revalidate();
                                        buttonPanel.repaint();
                                    }
                                }
                                if (greenBox.isSelected()) {
                                    if (redBox.isSelected()) {
                                        System.out.println(isP1Turn);
                                        isP1Turn = true;

                                        Coin greenC = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                        init.player2Green.add(greenC);
                                        p2.greenCoin++;
                                        Coin redC = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                        init.player2Red.add(redC);
                                        p2.redCoin++;

                                        System.out.println(init.slotMachineGreen);
                                        System.out.println(init.slotMachineRed);

                                        greenCoinNum--;
                                        redCoinNum--;
                                        greenCoinLabel.setText(String.valueOf(greenCoinNum));
                                        redCoinLabel.setText(String.valueOf(redCoinNum));

                                        if (greenCoinNum < 1) {
                                            emptySlotCount++;
                                        }
                                        if (redCoinNum < 1) {
                                            emptySlotCount++;
                                        }

                                        buttonPanel.remove(checkBoxPanel);
                                        if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                        if (!(greenCoinNum < 1 && redCoinNum < 1)) buttonPanel.add(pick3Button);
                                        buttonPanel.add((Component) itemListener);
                                        buttonPanel.revalidate();
                                        buttonPanel.repaint();
                                    }
                                    if (whiteBox.isSelected()) {
                                        System.out.println(isP1Turn);
                                        isP1Turn = true;

                                        Coin greenC = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                        init.player2Green.add(greenC);
                                        p2.greenCoin++;
                                        Coin whiteC = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                        init.player2White.add(whiteC);
                                        p2.whiteCoin++;

                                        System.out.println(init.slotMachineGreen);
                                        System.out.println(init.slotMachineWhite);

                                        greenCoinNum--;
                                        whiteCoinNum--;
                                        greenCoinLabel.setText(String.valueOf(greenCoinNum));
                                        whiteCoinLabel.setText(String.valueOf(whiteCoinNum));

                                        if (greenCoinNum < 1) {
                                            emptySlotCount++;
                                        }
                                        if (whiteCoinNum < 1) {
                                            emptySlotCount++;
                                        }

                                        buttonPanel.remove(checkBoxPanel);
                                        if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                        if (!(greenCoinNum < 1 && whiteCoinNum < 1)) buttonPanel.add(pick3Button);
                                        buttonPanel.add((Component) itemListener);
                                        buttonPanel.revalidate();
                                        buttonPanel.repaint();
                                    }
                                }
                                if (redBox.isSelected()) {
                                    if (whiteBox.isSelected()) {
                                        System.out.println(isP1Turn);
                                        isP1Turn = true;

                                        Coin whiteC = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                        init.player2White.add(whiteC);
                                        p2.whiteCoin++;
                                        Coin redC = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                        init.player2Red.add(redC);
                                        p2.redCoin++;

                                        System.out.println(init.slotMachineWhite);
                                        System.out.println(init.slotMachineRed);

                                        whiteCoinNum--;
                                        redCoinNum--;
                                        whiteCoinLabel.setText(String.valueOf(whiteCoinNum));
                                        redCoinLabel.setText(String.valueOf(redCoinNum));

                                        if (whiteCoinNum < 1) {
                                            emptySlotCount++;
                                        }
                                        if (redCoinNum < 1) {
                                            emptySlotCount++;
                                        }

                                        buttonPanel.remove(checkBoxPanel);
                                        if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                        if (!(redCoinNum < 1 && whiteCoinNum < 1)) buttonPanel.add(pick3Button);
                                        buttonPanel.add((Component) itemListener);
                                        buttonPanel.revalidate();
                                        buttonPanel.repaint();
                                    }
                                }


                                //when maxSelection == 3:
                                if (blackBox.isSelected()) {
                                    System.out.println(isP1Turn);
                                    isP1Turn = true;

                                    Coin blackC = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                    init.player2Black.add(blackC);
                                    p2.blackCoin++;

                                    System.out.println(init.slotMachineBlack);

                                    blackCoinNum--;

                                    blackCoinLabel.setText(String.valueOf(blackCoinNum));

                                    if (blackCoinNum < 1) {
                                        emptySlotCount++;
                                    }

                                    buttonPanel.remove(checkBoxPanel);
                                    if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                    if (emptySlotCount < 5) buttonPanel.add(pick3Button);
                                    buttonPanel.add((Component) itemListener);
                                    buttonPanel.revalidate();
                                    buttonPanel.repaint();
                                }
                                if (blueBox.isSelected()) {
                                    System.out.println(isP1Turn);
                                    isP1Turn = true;

                                    Coin blueC = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                    init.player2Blue.add(blueC);
                                    p2.blueCoin++;

                                    System.out.println(init.slotMachineBlue);

                                    blueCoinNum--;

                                    blueCoinLabel.setText(String.valueOf(blueCoinNum));

                                    if (blueCoinNum < 1) {
                                        emptySlotCount++;
                                    }

                                    buttonPanel.remove(checkBoxPanel);
                                    if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                    if (emptySlotCount < 5) buttonPanel.add(pick3Button);
                                    buttonPanel.add((Component) itemListener);
                                    buttonPanel.revalidate();
                                    buttonPanel.repaint();
                                }
                                if (greenBox.isSelected()) {
                                    System.out.println(isP1Turn);
                                    isP1Turn = true;

                                    Coin greenC = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                    init.player2Green.add(greenC);
                                    p2.greenCoin++;

                                    System.out.println(init.slotMachineGreen);

                                    greenCoinNum--;

                                    greenCoinLabel.setText(String.valueOf(greenCoinNum));

                                    if (greenCoinNum < 1) {
                                        emptySlotCount++;
                                    }

                                    buttonPanel.remove(checkBoxPanel);
                                    if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                    if (emptySlotCount < 5) buttonPanel.add(pick3Button);
                                    buttonPanel.add((Component) itemListener);
                                    buttonPanel.revalidate();
                                    buttonPanel.repaint();
                                }
                                if (redBox.isSelected()) {
                                    System.out.println(isP1Turn);
                                    isP1Turn = true;

                                    Coin redC = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                    init.player2Red.add(redC);
                                    p2.redCoin++;

                                    System.out.println(init.slotMachineRed);

                                    redCoinNum--;

                                    redCoinLabel.setText(String.valueOf(redCoinNum));

                                    if (redCoinNum < 1) {
                                        emptySlotCount++;
                                    }

                                    buttonPanel.remove(checkBoxPanel);
                                    if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                    if (emptySlotCount < 5) buttonPanel.add(pick3Button);
                                    buttonPanel.add((Component) itemListener);
                                    buttonPanel.revalidate();
                                    buttonPanel.repaint();
                                }
                                if (whiteBox.isSelected()) {
                                    System.out.println(isP1Turn);
                                    isP1Turn = true;

                                    Coin whiteC = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                    init.player2White.add(whiteC);
                                    p2.whiteCoin++;

                                    System.out.println(init.slotMachineWhite);

                                    whiteCoinNum--;

                                    whiteCoinLabel.setText(String.valueOf(whiteCoinNum));

                                    if (whiteCoinNum < 1) {
                                        emptySlotCount++;
                                    }

                                    buttonPanel.remove(checkBoxPanel);
                                    if (blackCoinNum >= 2 || blueCoinNum >= 2 || greenCoinNum >= 2 || redCoinNum >= 2 || whiteCoinNum >= 2) buttonPanel.add(pick2Button);
                                    if (emptySlotCount < 5) buttonPanel.add(pick3Button);
                                    buttonPanel.add((Component) itemListener);
                                    buttonPanel.revalidate();
                                    buttonPanel.repaint();
                                }
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
    }
    public void drawLevel1() {
        for (int i = 0, j = 0, k = 0; i < 4; i++, j++, k++) {
            try {
                level1Card[k] = init.level1.get(i);
                BufferedImage image = ImageIO.read(getClass().getResource(level1Card[k].getImage1Path()));
                cardLabel1[k] = new JLabel(new ImageIcon(image));
                cardLabel1[k].setBounds(510 + (cardWidth + 10)*j, 65, cardWidth, cardHeight);
                int tmp = k;
                int tempI = i;
                cardLabel1[tmp].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        cardLabel1[tmp].removeMouseListener(this);
                        cardLabel1[tmp].setIcon(null);
                        buyButton = new JButton("Buy");
                        reserveButton = new JButton("Reserve");
                        cancelButton = new JButton("Cancel");
                        buyButton.setFocusable(false);
                        reserveButton.setFocusable(false);
                        cancelButton.setFocusable(false);
                        cardLabel1[tmp].add(buyButton);
                        cardLabel1[tmp].add(reserveButton);
                        cardLabel1[tmp].add(cancelButton);
                        cardLabel1[tmp].setLayout(new FlowLayout());
                        cancelButton.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                super.mouseClicked(e);
                                cardLabel1[tmp].removeAll();
                                try {
                                    reDrawCards();
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                        });
                        buyButton.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                super.mouseClicked(e);
                                if (isP1Turn) {
//                                    System.out.println("P1 Turn");
//                                    System.out.println(p1.blackCoin);
//                                    System.out.println(p1.blueCoin);
//                                    System.out.println(p1.greenCoin);
//                                    System.out.println(p1.redCoin);
//                                    System.out.println(p1.whiteCoin);
//                                    System.out.println("------------");
//                                    System.out.println(level1Card[tmp].blackCoin);
//                                    System.out.println(level1Card[tmp].blueCoin);
//                                    System.out.println(level1Card[tmp].greenCoin);
//                                    System.out.println(level1Card[tmp].redCoin);
//                                    System.out.println(level1Card[tmp].whiteCoin);

                                    if (p1.blackCoin >= level1Card[tmp].blackCoin && p1.blueCoin >= level1Card[tmp].blueCoin && p1.greenCoin >= level1Card[tmp].greenCoin && p1.redCoin >= level1Card[tmp].redCoin && p1.whiteCoin >= level1Card[tmp].whiteCoin) {
                                        System.out.println("YESSSS PLAYER 1");
                                        //add special coins
                                        isP1Turn = false;
                                        init.playerOneHand.add(level1Card[tmp]);
                                        System.out.println(init.level1.get(tempI).toString());
                                        init.level1.remove(tempI);
//                                        System.out.println(level1Card[tmp].point);
//                                        System.out.println("/////");
//                                        System.out.println(level1Card[tmp].blackCoin);
//                                        System.out.println(level1Card[tmp].blueCoin);
//                                        System.out.println(level1Card[tmp].greenCoin);
//                                        System.out.println(level1Card[tmp].redCoin);
//                                        System.out.println(level1Card[tmp].whiteCoin);
//                                        System.out.println("----------");
                                        p1.blackCoin -= level1Card[tmp].blackCoin;
                                        p1.blueCoin -= level1Card[tmp].blueCoin;
                                        p1.greenCoin -= level1Card[tmp].greenCoin;
                                        p1.redCoin -= level1Card[tmp].redCoin;
                                        p1.whiteCoin -= level1Card[tmp].whiteCoin;
                                        p1.score += level1Card[tmp].point;
//                                        if (!init.player1Black.isEmpty()) {
//                                            for (int i = 1; i < level1Card[tmp].blackCoin; i++) {
//                                                init.player1Black.remove(init.player1Black.size() - i);
//                                            }
//                                        }
//                                        if (!init.player1Blue.isEmpty()) {
//                                            for (int i = 1; i < level1Card[tmp].blueCoin; i++) {
//                                                init.player1Blue.remove(init.player1Blue.size() - i);
//                                            }
//                                        }
//                                        if (!init.player1Green.isEmpty()) {
//                                            for (int i = 1; i < level1Card[tmp].greenCoin; i++) {
//                                                init.player1Green.remove(init.player1Green.size() - i);
//                                            }
//                                        }
//                                        if (!init.player1Red.isEmpty()) {
//                                            for (int i = 1; i < level1Card[tmp].redCoin; i++) {
//                                                init.player1Red.remove(init.player1Red.size() - i);
//                                            }
//                                        }
//                                        if (!init.player1White.isEmpty()) {
//                                            for (int i = 1; i < level1Card[tmp].whiteCoin; i++) {
//                                                init.player1White.remove(init.player1White.size() - i);
//                                            }
//                                        }
                                        switch (level1Card[tmp].specialCoin) {
                                            case "black":
                                                p1.SpecialBlackCoin++;
                                                break;
                                            case "blue":
                                                p1.SpecialBlueCoin++;
                                                break;
                                            case "green":
                                                p1.SpecialGreenCoin++;
                                                break;
                                            case "red":
                                                p1.SpecialRedCoin++;
                                                break;
                                            case "white":
                                                p1.SpecialWhiteCoin++;
                                                break;
                                        }
//                                        System.out.println(p1.score);
//                                        System.out.println("////");
//                                        System.out.println(p1.blackCoin);
//                                        System.out.println(p1.blueCoin);
//                                        System.out.println(p1.greenCoin);
//                                        System.out.println(p1.redCoin);
//                                        System.out.println(p1.whiteCoin);
//                                        System.out.println("////");
//                                        System.out.println(p1.SpecialBlackCoin);
//                                        System.out.println(p1.SpecialBlueCoin);
//                                        System.out.println(p1.SpecialGreenCoin);
//                                        System.out.println(p1.SpecialRedCoin);
//                                        System.out.println(p1.SpecialWhiteCoin);
                                        cardLabel1[tmp].removeAll();
                                        try {
                                            reDrawCards();
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                    }
                                }
                                else {
//                                    System.out.println("P2 Turn");
//                                    System.out.println(p2.blackCoin);
//                                    System.out.println(p2.blueCoin);
//                                    System.out.println(p2.greenCoin);
//                                    System.out.println(p2.redCoin);
//                                    System.out.println(p2.whiteCoin);
//                                    System.out.println("------------");
//                                    System.out.println(level1Card[tmp].blackCoin);
//                                    System.out.println(level1Card[tmp].blueCoin);
//                                    System.out.println(level1Card[tmp].greenCoin);
//                                    System.out.println(level1Card[tmp].redCoin);
//                                    System.out.println(level1Card[tmp].whiteCoin);
                                    if (p2.blackCoin >= level1Card[tmp].blackCoin && p2.blueCoin >= level1Card[tmp].blueCoin && p2.greenCoin >= level1Card[tmp].greenCoin && p2.redCoin >= level1Card[tmp].redCoin && p2.whiteCoin >= level1Card[tmp].whiteCoin) {
                                        System.out.println("YESSSS PLAYER 2");
                                        isP1Turn = true;
                                        init.playerTwoHand.add(level1Card[tmp]);
                                        System.out.println(init.level1.get(tempI).toString());
                                        init.level1.remove(tempI);
//                                        System.out.println(level1Card[tmp].point);
//                                        System.out.println("/////");
//                                        System.out.println(level1Card[tmp].blackCoin);
//                                        System.out.println(level1Card[tmp].blueCoin);
//                                        System.out.println(level1Card[tmp].greenCoin);
//                                        System.out.println(level1Card[tmp].redCoin);
//                                        System.out.println(level1Card[tmp].whiteCoin);
//                                        System.out.println("---------");
                                        p2.blackCoin -= level1Card[tmp].blackCoin;
                                        p2.blueCoin -= level1Card[tmp].blueCoin;
                                        p2.greenCoin -= level1Card[tmp].greenCoin;
                                        p2.redCoin -= level1Card[tmp].redCoin;
                                        p2.whiteCoin -= level1Card[tmp].whiteCoin;
                                        p2.score += level1Card[tmp].point;
//                                        if (!init.player2Black.isEmpty()) {
//                                            for (int i = 1; i < level1Card[tmp].blackCoin; i++) {
//                                                init.player2Black.remove(init.player2Black.size() - i);
//                                            }
//                                        }
//                                        if (!init.player2Blue.isEmpty()) {
//                                            for (int i = 1; i < level1Card[tmp].blueCoin; i++) {
//                                                init.player2Blue.remove(init.player2Blue.size() - i);
//                                            }
//                                        }
//                                        if (!init.player2Green.isEmpty()) {
//                                            for (int i = 1; i < level1Card[tmp].greenCoin; i++) {
//                                                init.player2Green.remove(init.player2Green.size() - i);
//                                            }
//                                        }
//                                        if (!init.player2Red.isEmpty()) {
//                                            for (int i = 1; i < level1Card[tmp].redCoin; i++) {
//                                                init.player2Red.remove(init.player2Red.size() - i);
//                                            }
//                                        }
//                                        if (!init.player2White.isEmpty()) {
//                                            for (int i = 1; i < level1Card[tmp].whiteCoin; i++) {
//                                                init.player2White.remove(init.player2White.size() - i);
//                                            }
//                                        }
                                        switch (level1Card[tmp].specialCoin) {
                                            case "black":
                                                p2.SpecialBlackCoin++;
                                                break;
                                            case "blue":
                                                p2.SpecialBlueCoin++;
                                                break;
                                            case "green":
                                                p2.SpecialGreenCoin++;
                                                break;
                                            case "red":
                                                p2.SpecialRedCoin++;
                                                break;
                                            case "white":
                                                p2.SpecialWhiteCoin++;
                                                break;
                                        }
//                                        System.out.println(p2.score);
//                                        System.out.println("////");
//                                        System.out.println(p2.blackCoin);
//                                        System.out.println(p2.blueCoin);
//                                        System.out.println(p2.greenCoin);
//                                        System.out.println(p2.redCoin);
//                                        System.out.println(p2.whiteCoin);
//                                        System.out.println("////");
//                                        System.out.println(p2.SpecialBlackCoin);
//                                        System.out.println(p2.SpecialBlueCoin);
//                                        System.out.println(p2.SpecialGreenCoin);
//                                        System.out.println(p2.SpecialRedCoin);
//                                        System.out.println(p2.SpecialWhiteCoin);
                                        cardLabel1[tmp].removeAll();
                                        try {
                                            reDrawCards();
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                    }
                                }
                            }
                        });
                        reserveButton.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                super.mouseClicked(e);



                            }
                        });
                        cardLabel1[tmp].addMouseListener(this);
                    }
                });
                gamePanel.add(cardLabel1[k]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void drawlevel2() {
        for (int i = init.level2.size() - 1, j = 0, k = 0; i > init.level2.size() - 5; i--, j++, k++) {
            try {
                level2Card[k] = init.level2.get(i);
                BufferedImage image = ImageIO.read(getClass().getResource(level2Card[k].getImage2Path()));
                cardLabel2[k] = new JLabel(new ImageIcon(image));
                cardLabel2[k].setBounds(510 + (cardWidth + 10)*j, 65 + 10 + cardHeight, cardWidth, cardHeight);
                int tmp = k;
                cardLabel2[tmp].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        cardLabel2[tmp].removeMouseListener(this);
                        cardLabel2[tmp].setIcon(null);
                        buyButton = new JButton("Buy");
                        reserveButton = new JButton("Reserve");
                        cancelButton = new JButton("Cancel");
                        buyButton.setFocusable(false);
                        reserveButton.setFocusable(false);
                        cancelButton.setFocusable(false);
                        cardLabel2[tmp].add(buyButton);
                        cardLabel2[tmp].add(reserveButton);
                        cardLabel2[tmp].add(cancelButton);
                        cardLabel2[tmp].setLayout(new FlowLayout());
                        cancelButton.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                super.mouseClicked(e);
                                cardLabel2[tmp].removeAll();
                                cardLabel2[tmp].setIcon(new ImageIcon(image));
                            }
                        });
                        cardLabel2[tmp].addMouseListener(this);
                    }
                });
                gamePanel.add(cardLabel2[k]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void drawlevel3() {
        for (int i = init.level3.size() - 1, j = 0, k = 0; i > init.level3.size() - 5; i--, j++, k++) {
            try {
                level3Card[k] = init.level3.get(i);
                BufferedImage image = ImageIO.read(getClass().getResource(level3Card[k].getImage3Path()));
                cardLabel3[k] = new JLabel(new ImageIcon(image));
                cardLabel3[k].setBounds(510 + (cardWidth + 10)*j, 65 + 2 * (10 + cardHeight), cardWidth, cardHeight);
                int tmp = k;
                cardLabel3[tmp].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        cardLabel3[tmp].removeMouseListener(this);
                        cardLabel3[tmp].setIcon(null);
                        buyButton = new JButton("Buy");
                        reserveButton = new JButton("Reserve");
                        cancelButton = new JButton("Cancel");
                        buyButton.setFocusable(false);
                        reserveButton.setFocusable(false);
                        cancelButton.setFocusable(false);
                        cardLabel3[tmp].add(buyButton);
                        cardLabel3[tmp].add(reserveButton);
                        cardLabel3[tmp].add(cancelButton);
                        cardLabel3[tmp].setLayout(new FlowLayout());
                        cancelButton.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                super.mouseClicked(e);
                                cardLabel3[tmp].removeAll();
                                cardLabel3[tmp].setIcon(new ImageIcon(image));
                            }
                        });
                        cardLabel3[tmp].addMouseListener(this);
                    }
                });
                gamePanel.add(cardLabel3[k]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void drawPrizeClaws() {
        for (int i = init.prizeClaw.size() - 1, j = 0, k = 0; i > init.prizeClaw.size() - 4; i--, j++, k++) {
            try {
                prizeClawCard = init.prizeClaw.get(i);
                BufferedImage image = ImageIO.read(getClass().getResource(prizeClawCard.getPrizeClawPath()));
                prizeClawLabel[k] = new JLabel(new ImageIcon(image));
                prizeClawLabel[k].setBounds(560 + (cardWidth + 10)*j, 65 + 3 * (10 + cardHeight), cardWidth, cardHeight);
                int tmp = k;
                prizeClawLabel[tmp].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        prizeClawLabel[tmp].removeMouseListener(this);
                        prizeClawLabel[tmp].setIcon(null);
                        buyButton = new JButton("Buy");
                        cancelButton = new JButton("Cancel");
                        buyButton.setFocusable(false);
                        cancelButton.setFocusable(false);
                        prizeClawLabel[tmp].add(buyButton);
                        prizeClawLabel[tmp].add(cancelButton);
                        prizeClawLabel[tmp].setLayout(new FlowLayout());
                        cancelButton.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                super.mouseClicked(e);
                                prizeClawLabel[tmp].removeAll();
                                prizeClawLabel[tmp].setIcon(new ImageIcon(image));
                            }
                        });
                        prizeClawLabel[tmp].addMouseListener(this);
                    }
                });
                gamePanel.add(prizeClawLabel[k]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void drawCoins() throws IOException {
        try {
            BufferedImage image1 = ImageIO.read(getClass().getResource("./coins/black coin.png"));
            blackCoinLabel = new JLabel(new ImageIcon(image1));
            blackCoinLabel.setBounds(500, 700, 94, 94);
            blackCoinLabel.setText(String.valueOf(blackCoinNum));
            blackCoinLabel.setHorizontalTextPosition(JLabel.CENTER);
            blackCoinLabel.setVerticalTextPosition(JLabel.CENTER);
            blackCoinLabel.setForeground(Color.WHITE);
            blackCoinLabel.setFont(font);
            gamePanel.add(blackCoinLabel);


            BufferedImage image2 = ImageIO.read(getClass().getResource("./coins/blue coin.png"));
            blueCoinLabel = new JLabel(new ImageIcon(image2));
            blueCoinLabel.setBounds(500 + 94 + 2, 700, 94, 94);
            blueCoinLabel.setText(String.valueOf(blackCoinNum));
            blueCoinLabel.setHorizontalTextPosition(JLabel.CENTER);
            blueCoinLabel.setVerticalTextPosition(JLabel.CENTER);
            blueCoinLabel.setForeground(Color.WHITE);
            blueCoinLabel.setFont(font);
            gamePanel.add(blueCoinLabel);


            BufferedImage image3 = ImageIO.read(getClass().getResource("./coins/green coin.png"));
            greenCoinLabel = new JLabel(new ImageIcon(image3));
            greenCoinLabel.setBounds(500 + 2*94 + 4, 700, 94, 94);
            greenCoinLabel.setText(String.valueOf(greenCoinNum));
            greenCoinLabel.setHorizontalTextPosition(JLabel.CENTER);
            greenCoinLabel.setVerticalTextPosition(JLabel.CENTER);
            greenCoinLabel.setForeground(Color.WHITE);
            greenCoinLabel.setFont(font);
            gamePanel.add(greenCoinLabel);


            BufferedImage image4 = ImageIO.read(getClass().getResource("./coins/red coin.png"));
            redCoinLabel = new JLabel(new ImageIcon(image4));
            redCoinLabel.setBounds(500 + 3*94 + 6, 700, 94, 94);
            redCoinLabel.setText(String.valueOf(redCoinNum));
            redCoinLabel.setHorizontalTextPosition(JLabel.CENTER);
            redCoinLabel.setVerticalTextPosition(JLabel.CENTER);
            redCoinLabel.setForeground(Color.WHITE);
            redCoinLabel.setFont(font);
            gamePanel.add(redCoinLabel);


            BufferedImage image5 = ImageIO.read(getClass().getResource("./coins/white coin.png"));
            whiteCoinLabel = new JLabel(new ImageIcon(image5));
            whiteCoinLabel.setBounds(500 + 4*94 + 8, 700, 94, 94);
            whiteCoinLabel.setText(String.valueOf(whiteCoinNum));
            whiteCoinLabel.setHorizontalTextPosition(JLabel.CENTER);
            whiteCoinLabel.setVerticalTextPosition(JLabel.CENTER);
            whiteCoinLabel.setForeground(Color.BLACK);
            whiteCoinLabel.setFont(font);
            gamePanel.add(whiteCoinLabel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void reDrawCards() throws IOException {
        for (int i = 0, j = 0, k = 0; i < 4; i++, j++, k++) {
            try {
                level1Card[k] = init.level1.get(i);
                BufferedImage image = ImageIO.read(getClass().getResource(level1Card[k].getImage1Path()));
                cardLabel1[k].setIcon(new ImageIcon(image));
                cardLabel1[k].setBounds(510 + (cardWidth + 10)*j, 65, cardWidth, cardHeight);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
