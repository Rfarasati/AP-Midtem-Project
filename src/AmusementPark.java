import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
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
    JLabel[] reservesLabel1 = new JLabel[3];
    JLabel[] reservesLabel2 = new JLabel[3];
    JLabel[] prizeClawLabel = new JLabel[3];
    JLabel player1Label = new JLabel();
    JLabel player2Label = new JLabel();
    JLabel p1Score = new JLabel();
    JLabel p2Score = new JLabel();
    JLabel p1Reserved = new JLabel();
    JLabel p2Reserved = new JLabel();
    JLabel p1NormalBlack = new JLabel();
    JLabel p1SpecialBlack = new JLabel();
    JLabel p2NormalBlack = new JLabel();
    JLabel p2SpecialBlack = new JLabel();
    JLabel p1NormalBlue = new JLabel();
    JLabel p1SpecialBlue = new JLabel();
    JLabel p2NormalBlue = new JLabel();
    JLabel p2SpecialBlue = new JLabel();
    JLabel p1NormalGreen = new JLabel();
    JLabel p1SpecialGreen = new JLabel();
    JLabel p2NormalGreen = new JLabel();
    JLabel p2SpecialGreen = new JLabel();
    JLabel p1NormalRed = new JLabel();
    JLabel p1SpecialRed = new JLabel();
    JLabel p2NormalRed = new JLabel();
    JLabel p2SpecialRed = new JLabel();
    JLabel p1NormalWhite = new JLabel();
    JLabel p1SpecialWhite = new JLabel();
    JLabel p2NormalWhite = new JLabel();
    JLabel p2SpecialWhite = new JLabel();
    JLabel p1Gold = new JLabel();
    JLabel p2Gold = new JLabel();
    JLabel endGame1 = new JLabel();
    JLabel endGame2 = new JLabel();
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
    Card[] reserves1 = new Card[3];
    Card[] reserves2 = new Card[3];
    PrizeClaw[] prizeClawCard = new PrizeClaw[3];
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
    int goldCoinNum = 5;

    AmusementPark() throws IOException {
        startGame();

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
//        frame.setSize(1200,1000);
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
        drawLevel2();
        drawLevel3();
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
        drawScoreboard1();
        drawScoreboard2();
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
                            if (blackCoinNum == 4) {
                                System.out.println(isP1Turn);
                                isP1Turn = false;

                                Coin blackC1 = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                init.player1Black.add(blackC1);
                                p1.blackCoin++;
                                Coin blackC2 = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                init.player1Black.add(blackC2);
                                p1.blackCoin++;

                                blackCoinNum -= 2;
                                
                                if (p1.getTotalCoin() > 10) {
                                    p1.blackCoin--;
                                    blackCoinNum++;
                                    
                                    if (p1.getTotalCoin() > 10) {
                                        p1.blackCoin--;
                                        blackCoinNum++;
                                    }
                                }

                                updateNormalBlack1();

                                System.out.println(init.slotMachineBlack);
                                
                                

                                blackCoinLabel.setText(String.valueOf(blackCoinNum));

                                if (blackCoinNum < 1) {
                                    emptySlotCount++;
                                }

                                buttonPanel.remove(radioPanel);
                                if (blackCoinNum >= 4 || blueCoinNum >= 4 || greenCoinNum >= 4 || redCoinNum >= 4 || whiteCoinNum >= 4)
                                    buttonPanel.add(pick2Button);
                                if (blackCoinNum > 0 || blueCoinNum > 0 || greenCoinNum > 0 || redCoinNum > 0 || whiteCoinNum > 0)
                                    buttonPanel.add(pick3Button);
                                blackBtn.addActionListener(this);
                                buttonPanel.revalidate();
                                buttonPanel.repaint();
                            }
                        }
                    });
                    blueBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (blueCoinNum == 4) {
                                System.out.println(isP1Turn);
                                isP1Turn = false;

                                Coin blueC1 = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                init.player1Blue.add(blueC1);
                                p1.blueCoin++;
                                Coin blueC2 = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                init.player1Blue.add(blueC2);
                                p1.blueCoin++;

                                blueCoinNum -= 2;

                                if (p1.getTotalCoin() > 10) {
                                    p1.blueCoin--;
                                    blueCoinNum++;

                                    if (p1.getTotalCoin() > 10) {
                                        p1.blueCoin--;
                                        blueCoinNum++;
                                    }
                                }

                                updateNormalBlue1();

                                System.out.println(init.slotMachineBlue);
                                

                                blueCoinLabel.setText(String.valueOf(blueCoinNum));

                                if (blueCoinNum < 1) {
                                    emptySlotCount++;
                                }

                                buttonPanel.remove(radioPanel);
                                if (blackCoinNum >= 4 || blueCoinNum >= 4 || greenCoinNum >= 4 || redCoinNum >= 4 || whiteCoinNum >= 4)
                                    buttonPanel.add(pick2Button);
                                if (blackCoinNum > 0 || blueCoinNum > 0 || greenCoinNum > 0 || redCoinNum > 0 || whiteCoinNum > 0)
                                    buttonPanel.add(pick3Button);
                                blueBtn.addActionListener(this);
                                buttonPanel.revalidate();
                                buttonPanel.repaint();
                            }
                        }
                    });
                    greenBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (greenCoinNum == 4) {
                                System.out.println(isP1Turn);
                                isP1Turn = false;

                                Coin greenC1 = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                init.player1Green.add(greenC1);
                                p1.greenCoin++;
                                Coin greenC2 = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                init.player1Green.add(greenC2);
                                p1.greenCoin++;

                                greenCoinNum -= 2;
                                if (p1.getTotalCoin() > 10) {
                                    p1.greenCoin--;
                                    greenCoinNum++;

                                    if (p1.getTotalCoin() > 10) {
                                        p1.greenCoin--;
                                        greenCoinNum++;
                                    }
                                }
                                updateNormalGreen1();

                                System.out.println(init.slotMachineGreen);


                                greenCoinLabel.setText(String.valueOf(greenCoinNum));

                                if (greenCoinNum < 1) {
                                    emptySlotCount++;
                                }

                                buttonPanel.remove(radioPanel);
                                if (blackCoinNum >= 4 || blueCoinNum >= 4 || greenCoinNum >= 4 || redCoinNum >= 4 || whiteCoinNum >= 4)
                                    buttonPanel.add(pick2Button);
                                if (blackCoinNum > 0 || blueCoinNum > 0 || greenCoinNum > 0 || redCoinNum > 0 || whiteCoinNum > 0)
                                    buttonPanel.add(pick3Button);
                                greenBtn.addActionListener(this);
                                buttonPanel.revalidate();
                                buttonPanel.repaint();
                            }
                        }
                    });
                    redBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (redCoinNum == 4) {
                                System.out.println(isP1Turn);
                                isP1Turn = false;

                                Coin redC1 = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                init.player1Red.add(redC1);
                                p1.redCoin++;
                                Coin redC2 = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                init.player1Red.add(redC2);
                                p1.redCoin++;

                                redCoinNum -= 2;
                                if (p1.getTotalCoin() > 10) {
                                    p1.redCoin--;
                                    redCoinNum++;

                                    if (p1.getTotalCoin() > 10) {
                                        p1.redCoin--;
                                        redCoinNum++;
                                    }
                                }
                                updateNormalRed1();

                                System.out.println(init.slotMachineRed);

                                

                                redCoinLabel.setText(String.valueOf(redCoinNum));

                                if (redCoinNum < 1) {
                                    emptySlotCount++;
                                }

                                buttonPanel.remove(radioPanel);
                                if (blackCoinNum >= 4 || blueCoinNum >= 4 || greenCoinNum >= 4 || redCoinNum >= 4 || whiteCoinNum >= 4)
                                    buttonPanel.add(pick2Button);
                                if (blackCoinNum > 0 || blueCoinNum > 0 || greenCoinNum > 0 || redCoinNum > 0 || whiteCoinNum > 0)
                                    buttonPanel.add(pick3Button);
                                redBtn.addActionListener(this);
                                buttonPanel.revalidate();
                                buttonPanel.repaint();
                            }
                        }
                    });
                    whiteBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (whiteCoinNum == 4 ) {
                                System.out.println(isP1Turn);
                                isP1Turn = false;

                                Coin whiteC1 = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                init.player1White.add(whiteC1);
                                p1.whiteCoin++;
                                Coin whiteC2 = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                init.player1White.add(whiteC2);
                                p1.whiteCoin++;

                                whiteCoinNum -= 2;

                                if (p1.getTotalCoin() > 10) {
                                    p1.whiteCoin--;
                                    whiteCoinNum++;

                                    if (p1.getTotalCoin() > 10) {
                                        p1.whiteCoin--;
                                        whiteCoinNum++;
                                    }
                                }

                                updateNormalWhite1();

                                System.out.println(init.slotMachineWhite);


                                whiteCoinLabel.setText(String.valueOf(whiteCoinNum));

                                if (whiteCoinNum < 1) {
                                    emptySlotCount++;
                                }

                                buttonPanel.remove(radioPanel);
                                if (blackCoinNum >= 4 || blueCoinNum >= 4 || greenCoinNum >= 4 || redCoinNum >= 4 || whiteCoinNum >= 4)
                                    buttonPanel.add(pick2Button);
                                if (blackCoinNum > 0 || blueCoinNum > 0 || greenCoinNum > 0 || redCoinNum > 0 || whiteCoinNum > 0)
                                    buttonPanel.add(pick3Button);
                                whiteBtn.addActionListener(this);
                                buttonPanel.revalidate();
                                buttonPanel.repaint();
                            }
                        }
                    });
                }
                
                else {
                    blackBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (blackCoinNum == 4 ) {
                                System.out.println(isP1Turn);
                                isP1Turn = true;

                                Coin blackC1 = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                init.player2Black.add(blackC1);
                                p2.blackCoin++;
                                Coin blackC2 = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                init.player2Black.add(blackC2);
                                p2.blackCoin++;

                                blackCoinNum -= 2;
                                if (p2.getTotalCoin() > 10) {
                                    p2.blackCoin--;
                                    blackCoinNum++;

                                    if (p2.getTotalCoin() > 10) {
                                        p2.blackCoin--;
                                        blackCoinNum++;
                                    }
                                }
                                
                                updateNormalBlack2();

                                System.out.println(init.slotMachineBlack);

                                

                                blackCoinLabel.setText(String.valueOf(blackCoinNum));

                                if (blackCoinNum < 1) {
                                    emptySlotCount++;
                                }

                                buttonPanel.remove(radioPanel);
                                if (blackCoinNum >= 4 || blueCoinNum >= 4 || greenCoinNum >= 4 || redCoinNum >= 4 || whiteCoinNum >= 4)
                                    buttonPanel.add(pick2Button);
                                if (blackCoinNum > 0 || blueCoinNum > 0 || greenCoinNum > 0 || redCoinNum > 0 || whiteCoinNum > 0)
                                    buttonPanel.add(pick3Button);
                                blackBtn.addActionListener(this);
                                buttonPanel.revalidate();
                                buttonPanel.repaint();
                            }
                        }
                    });
                    blueBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (blueCoinNum == 4) {
                                System.out.println(isP1Turn);
                                isP1Turn = true;

                                Coin blueC1 = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                init.player2Blue.add(blueC1);
                                p2.blueCoin++;
                                Coin blueC2 = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                init.player2Blue.add(blueC2);
                                p2.blueCoin++;

                                blueCoinNum -= 2;
                                

                                System.out.println(init.slotMachineBlue);

                                if (p2.getTotalCoin() > 10) {
                                    p2.blueCoin--;
                                    blueCoinNum++;

                                    if (p2.getTotalCoin() > 10) {
                                        p2.blueCoin--;
                                        blueCoinNum++;
                                    }
                                }
                                updateNormalBlue2();

                                blueCoinLabel.setText(String.valueOf(blueCoinNum));

                                if (blueCoinNum < 1) {
                                    emptySlotCount++;
                                }

                                buttonPanel.remove(radioPanel);
                                if (blackCoinNum >= 4 || blueCoinNum >= 4 || greenCoinNum >= 4 || redCoinNum >= 4 || whiteCoinNum >= 4)
                                    buttonPanel.add(pick2Button);
                                if (blackCoinNum > 0 || blueCoinNum > 0 || greenCoinNum > 0 || redCoinNum > 0 || whiteCoinNum > 0)
                                    buttonPanel.add(pick3Button);
                                blueBtn.addActionListener(this);
                                buttonPanel.revalidate();
                                buttonPanel.repaint();
                            }
                        }
                    });
                    greenBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (greenCoinNum == 4 ) {
                                System.out.println(isP1Turn);
                                isP1Turn = true;

                                Coin greenC1 = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                init.player2Green.add(greenC1);
                                p2.greenCoin++;
                                Coin greenC2 = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                init.player2Green.add(greenC2);
                                p2.greenCoin++;
                                
                                greenCoinNum -= 2;

                                if (p2.getTotalCoin() > 10) {
                                    p2.greenCoin--;
                                    greenCoinNum++;

                                    if (p2.getTotalCoin() > 10) {
                                        p2.greenCoin--;
                                        greenCoinNum++;
                                    }
                                }

                                updateNormalGreen2();

                                System.out.println(init.slotMachineGreen);
                                

                                greenCoinLabel.setText(String.valueOf(greenCoinNum));

                                if (greenCoinNum < 1) {
                                    emptySlotCount++;
                                }

                                buttonPanel.remove(radioPanel);
                                if (blackCoinNum >= 4 || blueCoinNum >= 4 || greenCoinNum >= 4 || redCoinNum >= 4 || whiteCoinNum >= 4)
                                    buttonPanel.add(pick2Button);
                                if (blackCoinNum > 0 || blueCoinNum > 0 || greenCoinNum > 0 || redCoinNum > 0 || whiteCoinNum > 0)
                                    buttonPanel.add(pick3Button);
                                greenBtn.addActionListener(this);
                                buttonPanel.revalidate();
                                buttonPanel.repaint();
                            }
                        }
                    });
                    redBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (redCoinNum == 4 ) {
                                System.out.println(isP1Turn);
                                isP1Turn = true;

                                Coin redC1 = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                init.player2Red.add(redC1);
                                p2.redCoin++;
                                Coin redC2 = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                init.player2Red.add(redC2);
                                p2.redCoin++;

                                redCoinNum -= 2;
                                

                                System.out.println(init.slotMachineRed);

                                if (p2.getTotalCoin() > 10) {
                                    p2.redCoin--;
                                    redCoinNum++;

                                    if (p2.getTotalCoin() > 10) {
                                        p2.redCoin--;
                                        redCoinNum++;
                                    }
                                }

                                updateNormalRed2();
                                
                                redCoinLabel.setText(String.valueOf(redCoinNum));

                                if (redCoinNum < 1) {
                                    emptySlotCount++;
                                }

                                buttonPanel.remove(radioPanel);
                                if (blackCoinNum >= 4 || blueCoinNum >= 4 || greenCoinNum >= 4 || redCoinNum >= 4 || whiteCoinNum >= 4)
                                    buttonPanel.add(pick2Button);
                                if (blackCoinNum > 0 || blueCoinNum > 0 || greenCoinNum > 0 || redCoinNum > 0 || whiteCoinNum > 0)
                                    buttonPanel.add(pick3Button);
                                redBtn.addActionListener(this);
                                buttonPanel.revalidate();
                                buttonPanel.repaint();
                            }
                        }
                    });
                    whiteBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (whiteCoinNum == 4 ) {
                                System.out.println(isP1Turn);
                                isP1Turn = true;

                                Coin whiteC1 = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                init.player2White.add(whiteC1);
                                p2.whiteCoin++;
                                Coin whiteC2 = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                init.player2White.add(whiteC2);
                                p2.whiteCoin++;

                                whiteCoinNum -= 2;

                                if (p2.getTotalCoin() > 10) {
                                    p2.whiteCoin--;
                                    whiteCoinNum++;

                                    if (p2.getTotalCoin() > 10) {
                                        p2.whiteCoin--;
                                        whiteCoinNum++;
                                    }
                                }
                                updateNormalWhite2();

                                System.out.println(init.slotMachineWhite);
                                

                                whiteCoinLabel.setText(String.valueOf(whiteCoinNum));

                                if (whiteCoinNum < 1) {
                                    emptySlotCount++;
                                }

                                buttonPanel.remove(radioPanel);
                                if (blackCoinNum >= 4 || blueCoinNum >= 4 || greenCoinNum >= 4 || redCoinNum >= 4 || whiteCoinNum >= 4)
                                    buttonPanel.add(pick2Button);
                                if (blackCoinNum > 0 || blueCoinNum > 0 || greenCoinNum > 0 || redCoinNum > 0 || whiteCoinNum > 0)
                                    buttonPanel.add(pick3Button);
                                whiteBtn.addActionListener(this);
                                buttonPanel.revalidate();
                                buttonPanel.repaint();
                            }
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

                                            greenCoinNum--;
                                            blackCoinNum--;
                                            blueCoinNum--;


                                            if (p1.getTotalCoin() > 10) {
                                                p1.blackCoin--;
                                                blackCoinNum++;

                                                if (p1.getTotalCoin() > 10) {
                                                    p1.blueCoin--;
                                                    blueCoinNum++;

                                                    if (p1.getTotalCoin() > 10) {
                                                        p1.greenCoin--;
                                                        greenCoinNum++;
                                                    }
                                                }
                                            }


                                            updateNormalBlack1();
                                            updateNormalBlue1();
                                            updateNormalGreen1();

                                            System.out.println(init.slotMachineBlack);
                                            System.out.println(init.slotMachineBlue);
                                            System.out.println(init.slotMachineGreen);


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


                                            redCoinNum--;
                                            blackCoinNum--;
                                            blueCoinNum--;


                                            if (p1.getTotalCoin() > 10) {
                                                p1.blackCoin--;
                                                blackCoinNum++;

                                                if (p1.getTotalCoin() > 10) {
                                                    p1.blueCoin--;
                                                    blueCoinNum++;

                                                    if (p1.getTotalCoin() > 10) {
                                                        p1.greenCoin--;
                                                        redCoinNum++;
                                                    }
                                                }
                                            }



                                            updateNormalBlack1();
                                            updateNormalBlue1();
                                            updateNormalRed1();

                                            System.out.println(init.slotMachineBlack);
                                            System.out.println(init.slotMachineBlue);
                                            System.out.println(init.slotMachineRed);

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

                                            whiteCoinNum--;
                                            blackCoinNum--;
                                            blueCoinNum--;


                                            if (p1.getTotalCoin() > 10) {
                                                p1.blackCoin--;
                                                blackCoinNum++;

                                                if (p1.getTotalCoin() > 10) {
                                                    p1.blueCoin--;
                                                    blueCoinNum++;

                                                    if (p1.getTotalCoin() > 10) {
                                                        p1.whiteCoin--;
                                                        whiteCoinNum++;
                                                    }
                                                }
                                            }

                                            updateNormalBlack1();
                                            updateNormalBlue1();
                                            updateNormalWhite1();

                                            System.out.println(init.slotMachineBlack);
                                            System.out.println(init.slotMachineBlue);
                                            System.out.println(init.slotMachineWhite);


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

                                            greenCoinNum--;
                                            blackCoinNum--;
                                            redCoinNum--;


                                            if (p1.getTotalCoin() > 10) {
                                                p1.blackCoin--;
                                                blackCoinNum++;

                                                if (p1.getTotalCoin() > 10) {
                                                    p1.greenCoin--;
                                                    greenCoinNum++;

                                                    if (p1.getTotalCoin() > 10) {
                                                        p1.redCoin--;
                                                        redCoinNum++;
                                                    }
                                                }
                                            }

                                            updateNormalBlack1();
                                            updateNormalGreen1();
                                            updateNormalRed1();

                                            System.out.println(init.slotMachineBlack);
                                            System.out.println(init.slotMachineRed);
                                            System.out.println(init.slotMachineGreen);

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

                                            greenCoinNum--;
                                            blackCoinNum--;
                                            redCoinNum--;


                                            if (p1.getTotalCoin() > 10) {
                                                p1.blackCoin--;
                                                blackCoinNum++;

                                                if (p1.getTotalCoin() > 10) {
                                                    p1.greenCoin--;
                                                    greenCoinNum++;

                                                    if (p1.getTotalCoin() > 10) {
                                                        p1.whiteCoin--;
                                                        whiteCoinNum++;
                                                    }
                                                }
                                            }

                                            updateNormalBlack1();
                                            updateNormalGreen1();
                                            updateNormalWhite1();

                                            System.out.println(init.slotMachineBlack);
                                            System.out.println(init.slotMachineWhite);
                                            System.out.println(init.slotMachineGreen);


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

                                            whiteCoinNum--;
                                            blackCoinNum--;
                                            redCoinNum--;


                                            if (p1.getTotalCoin() > 10) {
                                                p1.blackCoin--;
                                                blackCoinNum++;

                                                if (p1.getTotalCoin() > 10) {
                                                    p1.redCoin--;
                                                    redCoinNum++;

                                                    if (p1.getTotalCoin() > 10) {
                                                        p1.whiteCoin--;
                                                        whiteCoinNum++;
                                                    }
                                                }
                                            }

                                            updateNormalBlack1();
                                            updateNormalRed1();
                                            updateNormalWhite1();

                                            System.out.println(init.slotMachineBlack);
                                            System.out.println(init.slotMachineRed);
                                            System.out.println(init.slotMachineWhite);


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


                                            greenCoinNum--;
                                            blueCoinNum--;
                                            redCoinNum--;


                                            if (p1.getTotalCoin() > 10) {
                                                p1.blueCoin--;
                                                blueCoinNum++;

                                                if (p1.getTotalCoin() > 10) {
                                                    p1.greenCoin--;
                                                    greenCoinNum++;

                                                    if (p1.getTotalCoin() > 10) {
                                                        p1.redCoin--;
                                                        redCoinNum++;
                                                    }
                                                }
                                            }

                                            updateNormalBlue1();
                                            updateNormalGreen1();
                                            updateNormalRed1();

                                            System.out.println(init.slotMachineRed);
                                            System.out.println(init.slotMachineBlue);
                                            System.out.println(init.slotMachineGreen);

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


                                            greenCoinNum--;
                                            blueCoinNum--;
                                            whiteCoinNum--;


                                            if (p1.getTotalCoin() > 10) {
                                                p1.blueCoin--;
                                                blueCoinNum++;

                                                if (p1.getTotalCoin() > 10) {
                                                    p1.greenCoin--;
                                                    greenCoinNum++;

                                                    if (p1.getTotalCoin() > 10) {
                                                        p1.whiteCoin--;
                                                        whiteCoinNum++;
                                                    }
                                                }
                                            }

                                            updateNormalBlue1();
                                            updateNormalGreen1();
                                            updateNormalWhite1();

                                            System.out.println(init.slotMachineWhite);
                                            System.out.println(init.slotMachineBlue);
                                            System.out.println(init.slotMachineGreen);

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

                                            whiteCoinNum--;
                                            blueCoinNum--;
                                            redCoinNum--;


                                            if (p1.getTotalCoin() > 10) {
                                                p1.blueCoin--;
                                                blueCoinNum++;

                                                if (p1.getTotalCoin() > 10) {
                                                    p1.whiteCoin--;
                                                    whiteCoinNum++;

                                                    if (p1.getTotalCoin() > 10) {
                                                        p1.redCoin--;
                                                        redCoinNum++;
                                                    }
                                                }
                                            }

                                            updateNormalBlue1();
                                            updateNormalWhite1();
                                            updateNormalRed1();

                                            System.out.println(init.slotMachineWhite);
                                            System.out.println(init.slotMachineBlue);
                                            System.out.println(init.slotMachineRed);

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

                                            greenCoinNum--;
                                            whiteCoinNum--;
                                            redCoinNum--;


                                            if (p1.getTotalCoin() > 10) {
                                                p1.greenCoin--;
                                                greenCoinNum++;

                                                if (p1.getTotalCoin() > 10) {
                                                    p1.redCoin--;
                                                    redCoinNum++;

                                                    if (p1.getTotalCoin() > 10) {
                                                        p1.whiteCoin--;
                                                        whiteCoinNum++;
                                                    }
                                                }
                                            }

                                            updateNormalWhite1();
                                            updateNormalGreen1();
                                            updateNormalRed1();

                                            System.out.println(init.slotMachineRed);
                                            System.out.println(init.slotMachineWhite);
                                            System.out.println(init.slotMachineGreen);

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

                                        blueCoinNum--;
                                        blackCoinNum--;


                                        if (p1.getTotalCoin() > 10) {
                                            p1.blackCoin--;
                                            blackCoinNum++;

                                            if (p1.getTotalCoin() > 10) {
                                                p1.blueCoin--;
                                                blueCoinNum++;
                                            }
                                        }

                                        updateNormalBlack1();
                                        updateNormalBlue1();

                                        System.out.println(init.slotMachineBlack);
                                        System.out.println(init.slotMachineBlue);

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


                                        greenCoinNum--;
                                        blackCoinNum--;


                                        if (p1.getTotalCoin() > 10) {
                                            p1.blackCoin--;
                                            blackCoinNum++;

                                            if (p1.getTotalCoin() > 10) {
                                                p1.greenCoin--;
                                                greenCoinNum++;
                                            }
                                        }

                                        updateNormalBlack1();
                                        updateNormalGreen1();

                                        System.out.println(init.slotMachineBlack);
                                        System.out.println(init.slotMachineGreen);

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

                                        redCoinNum--;
                                        blackCoinNum--;


                                        if (p1.getTotalCoin() > 10) {
                                            p1.blackCoin--;
                                            blackCoinNum++;

                                            if (p1.getTotalCoin() > 10) {
                                                p1.redCoin--;
                                                redCoinNum++;
                                            }
                                        }


                                        updateNormalBlack1();
                                        updateNormalRed1();

                                        System.out.println(init.slotMachineBlack);
                                        System.out.println(init.slotMachineRed);

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

                                        whiteCoinNum--;
                                        blackCoinNum--;


                                        if (p1.getTotalCoin() > 10) {
                                            p1.blackCoin--;
                                            blackCoinNum++;

                                            if (p1.getTotalCoin() > 10) {
                                                p1.whiteCoin--;
                                                whiteCoinNum++;
                                            }
                                        }

                                        updateNormalBlack1();
                                        updateNormalWhite1();

                                        System.out.println(init.slotMachineBlack);
                                        System.out.println(init.slotMachineWhite);

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

                                        blueCoinNum--;
                                        greenCoinNum--;


                                        if (p1.getTotalCoin() > 10) {
                                            p1.greenCoin--;
                                            greenCoinNum++;

                                            if (p1.getTotalCoin() > 10) {
                                                p1.blueCoin--;
                                                blueCoinNum++;
                                            }
                                        }

                                        updateNormalBlue1();
                                        updateSpecialGreen1();

                                        System.out.println(init.slotMachineBlue);
                                        System.out.println(init.slotMachineGreen);

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

                                        blueCoinNum--;
                                        redCoinNum--;


                                        if (p1.getTotalCoin() > 10) {
                                            p1.redCoin--;
                                            redCoinNum++;

                                            if (p1.getTotalCoin() > 10) {
                                                p1.blueCoin--;
                                                blueCoinNum++;
                                            }
                                        }

                                        updateNormalBlue1();
                                        updateNormalRed1();

                                        System.out.println(init.slotMachineBlue);
                                        System.out.println(init.slotMachineRed);


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

                                        blueCoinNum--;
                                        whiteCoinNum--;


                                        if (p1.getTotalCoin() > 10) {
                                            p1.whiteCoin--;
                                            whiteCoinNum++;

                                            if (p1.getTotalCoin() > 10) {
                                                p1.blueCoin--;
                                                blueCoinNum++;
                                            }
                                        }

                                        updateNormalBlue1();
                                        updateSpecialWhite1();

                                        System.out.println(init.slotMachineBlue);
                                        System.out.println(init.slotMachineWhite);

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

                                        greenCoinNum--;
                                        redCoinNum--;


                                        if (p1.getTotalCoin() > 10) {
                                            p1.greenCoin--;
                                            greenCoinNum++;

                                            if (p1.getTotalCoin() > 10) {
                                                p1.redCoin--;
                                                redCoinNum++;
                                            }
                                        }

                                        updateNormalGreen1();
                                        updateNormalRed1();

                                        System.out.println(init.slotMachineGreen);
                                        System.out.println(init.slotMachineRed);

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

                                        greenCoinNum--;
                                        whiteCoinNum--;


                                        if (p1.getTotalCoin() > 10) {
                                            p1.greenCoin--;
                                            greenCoinNum++;

                                            if (p1.getTotalCoin() > 10) {
                                                p1.whiteCoin--;
                                                whiteCoinNum++;
                                            }
                                        }

                                        updateNormalGreen1();
                                        updateNormalWhite1();

                                        System.out.println(init.slotMachineGreen);
                                        System.out.println(init.slotMachineWhite);

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

                                        whiteCoinNum--;
                                        redCoinNum--;


                                        if (p1.getTotalCoin() > 10) {
                                            p1.whiteCoin--;
                                            whiteCoinNum++;

                                            if (p1.getTotalCoin() > 10) {
                                                p1.redCoin--;
                                                redCoinNum++;
                                            }
                                        }

                                        updateNormalRed1();
                                        updateNormalWhite1();

                                        System.out.println(init.slotMachineWhite);
                                        System.out.println(init.slotMachineRed);


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

                                    blackCoinNum--;


                                    if (p1.getTotalCoin() > 10) {
                                        p1.blackCoin--;
                                        blackCoinNum++;
                                    }

                                    updateNormalBlack1();

                                    System.out.println(init.slotMachineBlack);

                                    
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

                                    blueCoinNum--;


                                    if (p1.getTotalCoin() > 10) {
                                        p1.blueCoin--;
                                        blueCoinNum++;
                                    }

                                    updateNormalBlue1();

                                    System.out.println(init.slotMachineBlue);


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

                                    greenCoinNum--;


                                    if (p1.getTotalCoin() > 10) {
                                        p1.greenCoin--;
                                        greenCoinNum++;
                                    }

                                    updateNormalGreen1();

                                    System.out.println(init.slotMachineGreen);


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

                                    redCoinNum--;


                                    if (p1.getTotalCoin() > 10) {
                                        p1.redCoin--;
                                        redCoinNum++;
                                    }

                                    updateNormalRed1();

                                    System.out.println(init.slotMachineRed);


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

                                    whiteCoinNum--;


                                    if (p1.getTotalCoin() > 10) {
                                        p1.whiteCoin--;
                                        whiteCoinNum++;
                                    }

                                    updateNormalWhite1();

                                    System.out.println(init.slotMachineWhite);


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


                                            greenCoinNum--;
                                            blackCoinNum--;
                                            blueCoinNum--;


                                            if (p2.getTotalCoin() > 10) {
                                                p2.blackCoin--;
                                                blackCoinNum++;

                                                if (p2.getTotalCoin() > 10) {
                                                    p2.blueCoin--;
                                                    blueCoinNum++;

                                                    if (p2.getTotalCoin() > 10) {
                                                        p2.greenCoin--;
                                                        greenCoinNum++;
                                                    }
                                                }
                                            }
                                            
                                            updateNormalBlack2();
                                            updateNormalBlue2();
                                            updateNormalGreen2();

                                            System.out.println(init.slotMachineBlack);
                                            System.out.println(init.slotMachineBlue);
                                            System.out.println(init.slotMachineGreen);
                                            
                                            
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

                                            redCoinNum--;
                                            blackCoinNum--;
                                            blueCoinNum--;


                                            if (p2.getTotalCoin() > 10) {
                                                p2.blackCoin--;
                                                blackCoinNum++;

                                                if (p2.getTotalCoin() > 10) {
                                                    p2.blueCoin--;
                                                    blueCoinNum++;

                                                    if (p2.getTotalCoin() > 10) {
                                                        p2.redCoin--;
                                                        redCoinNum++;
                                                    }
                                                }
                                            }

                                            updateNormalBlack2();
                                            updateNormalBlue2();
                                            updateNormalRed2();

                                            System.out.println(init.slotMachineBlack);
                                            System.out.println(init.slotMachineBlue);
                                            System.out.println(init.slotMachineRed);


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


                                            whiteCoinNum--;
                                            blackCoinNum--;
                                            blueCoinNum--;


                                            if (p2.getTotalCoin() > 10) {
                                                p2.blackCoin--;
                                                blackCoinNum++;

                                                if (p2.getTotalCoin() > 10) {
                                                    p2.blueCoin--;
                                                    blueCoinNum++;

                                                    if (p2.getTotalCoin() > 10) {
                                                        p2.whiteCoin--;
                                                        whiteCoinNum++;
                                                    }
                                                }
                                            }

                                            updateNormalBlack2();
                                            updateNormalBlue2();
                                            updateNormalWhite2();

                                            System.out.println(init.slotMachineBlack);
                                            System.out.println(init.slotMachineBlue);
                                            System.out.println(init.slotMachineWhite);


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


                                            greenCoinNum--;
                                            blackCoinNum--;
                                            redCoinNum--;


                                            if (p2.getTotalCoin() > 10) {
                                                p2.blackCoin--;
                                                blackCoinNum++;

                                                if (p2.getTotalCoin() > 10) {
                                                    p2.redCoin--;
                                                    redCoinNum++;

                                                    if (p2.getTotalCoin() > 10) {
                                                        p2.greenCoin--;
                                                        greenCoinNum++;
                                                    }
                                                }
                                            }

                                            updateNormalBlack2();
                                            updateNormalGreen2();
                                            updateNormalRed2();

                                            System.out.println(init.slotMachineBlack);
                                            System.out.println(init.slotMachineRed);
                                            System.out.println(init.slotMachineGreen);


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


                                            greenCoinNum--;
                                            blackCoinNum--;
                                            whiteCoinNum--;


                                            if (p2.getTotalCoin() > 10) {
                                                p2.blackCoin--;
                                                blackCoinNum++;

                                                if (p2.getTotalCoin() > 10) {
                                                    p2.whiteCoin--;
                                                    whiteCoinNum++;

                                                    if (p2.getTotalCoin() > 10) {
                                                        p2.greenCoin--;
                                                        greenCoinNum++;
                                                    }
                                                }
                                            }

                                            updateNormalBlack2();
                                            updateNormalGreen2();
                                            updateNormalWhite2();

                                            System.out.println(init.slotMachineBlack);
                                            System.out.println(init.slotMachineWhite);
                                            System.out.println(init.slotMachineGreen);


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

                                            whiteCoinNum--;
                                            blackCoinNum--;
                                            redCoinNum--;


                                            if (p2.getTotalCoin() > 10) {
                                                p2.blackCoin--;
                                                blackCoinNum++;

                                                if (p2.getTotalCoin() > 10) {
                                                    p2.redCoin--;
                                                    redCoinNum++;

                                                    if (p2.getTotalCoin() > 10) {
                                                        p2.whiteCoin--;
                                                        whiteCoinNum++;
                                                    }
                                                }
                                            }

                                            updateNormalBlack2();
                                            updateNormalRed2();
                                            updateNormalWhite2();

                                            System.out.println(init.slotMachineBlack);
                                            System.out.println(init.slotMachineRed);
                                            System.out.println(init.slotMachineWhite);


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

                                            greenCoinNum--;
                                            blueCoinNum--;
                                            redCoinNum--;


                                            if (p2.getTotalCoin() > 10) {
                                                p2.blueCoin--;
                                                blueCoinNum++;

                                                if (p2.getTotalCoin() > 10) {
                                                    p2.redCoin--;
                                                    redCoinNum++;

                                                    if (p2.getTotalCoin() > 10) {
                                                        p2.greenCoin--;
                                                        greenCoinNum++;
                                                    }
                                                }
                                            }

                                            updateNormalBlue2();
                                            updateNormalGreen2();
                                            updateNormalRed2();

                                            System.out.println(init.slotMachineRed);
                                            System.out.println(init.slotMachineBlue);
                                            System.out.println(init.slotMachineGreen);


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

                                            greenCoinNum--;
                                            blueCoinNum--;
                                            whiteCoinNum--;


                                            if (p2.getTotalCoin() > 10) {
                                                p2.blueCoin--;
                                                blueCoinNum++;

                                                if (p2.getTotalCoin() > 10) {
                                                    p2.whiteCoin--;
                                                    whiteCoinNum++;

                                                    if (p2.getTotalCoin() > 10) {
                                                        p2.greenCoin--;
                                                        greenCoinNum++;
                                                    }
                                                }
                                            }

                                            updateNormalBlue2();
                                            updateNormalGreen2();
                                            updateNormalWhite2();

                                            System.out.println(init.slotMachineWhite);
                                            System.out.println(init.slotMachineBlue);
                                            System.out.println(init.slotMachineGreen);


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

                                            redCoinNum--;
                                            blueCoinNum--;
                                            whiteCoinNum--;


                                            if (p2.getTotalCoin() > 10) {
                                                p2.blueCoin--;
                                                blueCoinNum++;

                                                if (p2.getTotalCoin() > 10) {
                                                    p2.whiteCoin--;
                                                    whiteCoinNum++;

                                                    if (p2.getTotalCoin() > 10) {
                                                        p2.redCoin--;
                                                        redCoinNum++;
                                                    }
                                                }
                                            }

                                            updateNormalBlue2();
                                            updateNormalRed2();
                                            updateNormalWhite2();

                                            System.out.println(init.slotMachineWhite);
                                            System.out.println(init.slotMachineBlue);
                                            System.out.println(init.slotMachineRed);


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


                                            redCoinNum--;
                                            greenCoinNum--;
                                            whiteCoinNum--;


                                            if (p2.getTotalCoin() > 10) {
                                                p2.greenCoin--;
                                                greenCoinNum++;

                                                if (p2.getTotalCoin() > 10) {
                                                    p2.whiteCoin--;
                                                    whiteCoinNum++;

                                                    if (p2.getTotalCoin() > 10) {
                                                        p2.redCoin--;
                                                        redCoinNum++;
                                                    }
                                                }
                                            }

                                            updateNormalGreen2();
                                            updateNormalRed2();
                                            updateNormalWhite2();

                                            System.out.println(init.slotMachineWhite);
                                            System.out.println(init.slotMachineRed);
                                            System.out.println(init.slotMachineGreen);


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

                                        blackCoinNum--;
                                        blueCoinNum--;


                                        if (p2.getTotalCoin() > 10) {
                                            p2.blueCoin--;
                                            blueCoinNum++;

                                            if (p2.getTotalCoin() > 10) {
                                                p2.blackCoin--;
                                                blackCoinNum++;
                                            }
                                        }

                                        updateNormalBlack2();
                                        updateNormalBlue2();

                                        System.out.println(init.slotMachineBlack);
                                        System.out.println(init.slotMachineBlue);


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

                                        blackCoinNum--;
                                        greenCoinNum--;


                                        if (p2.getTotalCoin() > 10) {
                                            p2.greenCoin--;
                                            greenCoinNum++;

                                            if (p2.getTotalCoin() > 10) {
                                                p2.blackCoin--;
                                                blackCoinNum++;
                                            }
                                        }

                                        updateNormalBlack2();
                                        updateNormalGreen2();

                                        System.out.println(init.slotMachineBlack);
                                        System.out.println(init.slotMachineGreen);


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

                                        blackCoinNum--;
                                        redCoinNum--;


                                        if (p2.getTotalCoin() > 10) {
                                            p2.redCoin--;
                                            redCoinNum++;

                                            if (p2.getTotalCoin() > 10) {
                                                p2.blackCoin--;
                                                blackCoinNum++;
                                            }
                                        }

                                        updateNormalBlack2();
                                        updateNormalRed2();

                                        System.out.println(init.slotMachineBlack);
                                        System.out.println(init.slotMachineRed);

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

                                        blackCoinNum--;
                                        whiteCoinNum--;


                                        if (p2.getTotalCoin() > 10) {
                                            p2.whiteCoin--;
                                            whiteCoinNum++;

                                            if (p2.getTotalCoin() > 10) {
                                                p2.blackCoin--;
                                                blackCoinNum++;
                                            }
                                        }

                                        updateNormalBlack2();
                                        updateNormalWhite2();

                                        System.out.println(init.slotMachineBlack);
                                        System.out.println(init.slotMachineWhite);


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

                                        blueCoinNum--;
                                        greenCoinNum--;


                                        if (p2.getTotalCoin() > 10) {
                                            p2.blueCoin--;
                                            blueCoinNum++;

                                            if (p2.getTotalCoin() > 10) {
                                                p2.greenCoin--;
                                                greenCoinNum++;
                                            }
                                        }

                                        updateNormalBlue2();
                                        updateNormalGreen2();

                                        System.out.println(init.slotMachineBlue);
                                        System.out.println(init.slotMachineGreen);

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

                                        blueCoinNum--;
                                        redCoinNum--;


                                        if (p2.getTotalCoin() > 10) {
                                            p2.blueCoin--;
                                            blueCoinNum++;

                                            if (p2.getTotalCoin() > 10) {
                                                p2.redCoin--;
                                                redCoinNum++;
                                            }
                                        }

                                        updateNormalBlue2();
                                        updateNormalRed2();

                                        System.out.println(init.slotMachineBlue);
                                        System.out.println(init.slotMachineRed);


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

                                        blueCoinNum--;
                                        whiteCoinNum--;


                                        if (p2.getTotalCoin() > 10) {
                                            p2.blueCoin--;
                                            blueCoinNum++;

                                            if (p2.getTotalCoin() > 10) {
                                                p2.whiteCoin--;
                                                whiteCoinNum++;
                                            }
                                        }

                                        updateNormalBlue2();
                                        updateNormalWhite2();

                                        System.out.println(init.slotMachineBlue);
                                        System.out.println(init.slotMachineWhite);


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

                                        redCoinNum--;
                                        greenCoinNum--;


                                        if (p2.getTotalCoin() > 10) {
                                            p2.redCoin--;
                                            redCoinNum++;

                                            if (p2.getTotalCoin() > 10) {
                                                p2.greenCoin--;
                                                greenCoinNum++;
                                            }
                                        }

                                        updateNormalGreen2();
                                        updateNormalRed2();

                                        System.out.println(init.slotMachineGreen);
                                        System.out.println(init.slotMachineRed);


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

                                        whiteCoinNum--;
                                        greenCoinNum--;


                                        if (p2.getTotalCoin() > 10) {
                                            p2.whiteCoin--;
                                            whiteCoinNum++;

                                            if (p2.getTotalCoin() > 10) {
                                                p2.greenCoin--;
                                                greenCoinNum++;
                                            }
                                        }

                                        updateNormalGreen2();
                                        updateNormalWhite2();

                                        System.out.println(init.slotMachineGreen);
                                        System.out.println(init.slotMachineWhite);

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

                                        redCoinNum--;
                                        whiteCoinNum--;


                                        if (p2.getTotalCoin() > 10) {
                                            p2.whiteCoin--;
                                            whiteCoinNum++;

                                            if (p2.getTotalCoin() > 10) {
                                                p2.redCoin--;
                                                redCoinNum++;
                                            }
                                        }

                                        updateNormalWhite2();
                                        updateNormalRed2();

                                        System.out.println(init.slotMachineWhite);
                                        System.out.println(init.slotMachineRed);


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
                                    isP1Turn = true;

                                    Coin blackC = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                    init.player2Black.add(blackC);
                                    p2.blackCoin++;

                                    blackCoinNum--;

                                    if (p2.getTotalCoin() > 10) {
                                        p2.blackCoin--;
                                        blackCoinNum++;
                                    }

                                    updateNormalBlack2();

                                    System.out.println(init.slotMachineBlack);


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

                                    blueCoinNum--;

                                    if (p2.getTotalCoin() > 10) {
                                        p2.blueCoin--;
                                        blueCoinNum++;
                                    }

                                    updateNormalBlue2();

                                    System.out.println(init.slotMachineBlue);


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

                                    greenCoinNum--;

                                    if (p2.getTotalCoin() > 10) {
                                        p2.greenCoin--;
                                        greenCoinNum++;
                                    }

                                    updateNormalGreen2();

                                    System.out.println(init.slotMachineGreen);


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

                                    redCoinNum--;

                                    if (p2.getTotalCoin() > 10) {
                                        p2.redCoin--;
                                        redCoinNum++;
                                    }

                                    updateNormalRed2();

                                    System.out.println(init.slotMachineRed);


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

                                    updateNormalWhite2();

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
                                    redrawCards1();
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
                                    int black = p1.blackCoin;
                                    int blackSw = 0;
                                    int tmpGold1 = 0;
                                    int blue = p1.blueCoin;
                                    int blueSw = 0;
                                    int tmpGold2 = 0;
                                    int green = p1.greenCoin;
                                    int greenSw = 0;
                                    int tmpGold3 = 0;
                                    int red = p1.redCoin;
                                    int redSw = 0;
                                    int tmpGold4 = 0;
                                    int white = p1.whiteCoin;
                                    int whiteSw = 0;
                                    int tmpGold5 = 0;
                                    if (p1.blackCoin >= level1Card[tmp].blackCoin) {
                                        blackSw = 1;
                                    }
                                    else if (level1Card[tmp].blackCoin > 0 && (p1.blackCoin + p1.SpecialBlackCoin) >= level1Card[tmp].blackCoin) {
                                        blackSw = 2;
                                        black = p1.blackCoin + p1.SpecialBlackCoin;
                                    }
                                    else if (level1Card[tmp].blackCoin > 0 && (p1.blackCoin + p1.SpecialBlackCoin) - level1Card[tmp].blackCoin < 0) {
                                        if (level1Card[tmp].blackCoin - (p1.blackCoin + p1.SpecialBlackCoin) <= p1.goldCoin) {
                                            black = p1.blackCoin + p1.SpecialBlackCoin + (level1Card[tmp].blackCoin - (p1.blackCoin + p1.SpecialBlackCoin));
                                            p1.goldCoin -= level1Card[tmp].blackCoin - (p1.blackCoin + p1.SpecialBlackCoin);
                                            tmpGold1 = level1Card[tmp].blackCoin - (p1.blackCoin + p1.SpecialBlackCoin);
                                            blackSw = 3;
                                            goldCoinNum += level1Card[tmp].blackCoin - (p1.blackCoin + p1.SpecialBlackCoin);
                                            updateGold1();
                                            goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                        }
                                    }
                                    
                                    
                                    if (p1.blueCoin >= level1Card[tmp].blueCoin) {
                                        blueSw = 1;
                                    }
                                    else if (level1Card[tmp].blueCoin > 0 && (p1.blueCoin + p1.SpecialBlueCoin) >= level1Card[tmp].blueCoin) {
                                        blueSw = 2;
                                        blue = p1.blueCoin + p1.SpecialBlueCoin;
                                    }
                                    else if (level1Card[tmp].blueCoin > 0 && (p1.blueCoin + p1.SpecialBlueCoin) - level1Card[tmp].blueCoin < 0) {
                                        if (level1Card[tmp].blueCoin - (p1.blueCoin + p1.SpecialBlueCoin) <= p1.goldCoin) {
                                            blue = p1.blueCoin + p1.SpecialBlueCoin + (level1Card[tmp].blueCoin - (p1.blueCoin + p1.SpecialBlueCoin));
                                            p1.goldCoin -= level1Card[tmp].blueCoin - (p1.blueCoin + p1.SpecialBlueCoin);
                                            tmpGold2 = level1Card[tmp].blueCoin - (p1.blueCoin + p1.SpecialBlueCoin);
                                            blueSw = 3;
                                            goldCoinNum += level1Card[tmp].blueCoin - (p1.blueCoin + p1.SpecialBlueCoin);
                                            updateGold1();
                                            goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                        }
                                    }


                                    if (p1.greenCoin >= level1Card[tmp].greenCoin) {
                                        greenSw = 1;
                                    }
                                    else if (level1Card[tmp].greenCoin > 0 && (p1.greenCoin + p1.SpecialGreenCoin) >= level1Card[tmp].greenCoin) {
                                        greenSw = 2;
                                        green = p1.greenCoin + p1.SpecialGreenCoin;
                                    }
                                    else if (level1Card[tmp].greenCoin > 0 && (p1.greenCoin + p1.SpecialGreenCoin) - level1Card[tmp].greenCoin < 0) {
                                        if (level1Card[tmp].greenCoin - (p1.greenCoin + p1.SpecialGreenCoin) <= p1.goldCoin) {
                                            green = p1.greenCoin + p1.SpecialGreenCoin + (level1Card[tmp].greenCoin - (p1.greenCoin + p1.SpecialGreenCoin));
                                            p1.goldCoin -= level1Card[tmp].greenCoin - (p1.greenCoin + p1.SpecialGreenCoin);
                                            tmpGold3 = level1Card[tmp].greenCoin - (p1.greenCoin + p1.SpecialGreenCoin);
                                            greenSw = 3;
                                            goldCoinNum += level1Card[tmp].greenCoin - (p1.greenCoin + p1.SpecialGreenCoin);
                                            updateGold1();
                                            goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                        }
                                    }
                                    
                                    
                                    if (p1.redCoin >= level1Card[tmp].redCoin) {
                                        redSw = 1;
                                    }
                                    else if (level1Card[tmp].redCoin > 0 && (p1.redCoin + p1.SpecialRedCoin) >= level1Card[tmp].redCoin) {
                                        redSw = 2;
                                        red = p1.redCoin + p1.SpecialRedCoin;
                                    }
                                    else if (level1Card[tmp].redCoin > 0 && (p1.redCoin + p1.SpecialRedCoin) - level1Card[tmp].redCoin < 0) {
                                        if (level1Card[tmp].redCoin - (p1.redCoin + p1.SpecialRedCoin) <= p1.goldCoin) {
                                            red = p1.redCoin + p1.SpecialRedCoin + (level1Card[tmp].redCoin - (p1.redCoin + p1.SpecialRedCoin));
                                            p1.goldCoin -= level1Card[tmp].redCoin - (p1.redCoin + p1.SpecialRedCoin);
                                            tmpGold4 = level1Card[tmp].redCoin - (p1.redCoin + p1.SpecialRedCoin);
                                            redSw = 3;
                                            goldCoinNum += level1Card[tmp].redCoin - (p1.redCoin + p1.SpecialRedCoin);
                                            updateGold1();
                                            goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                        }
                                    }
                                    
                                    
                                    if (p1.whiteCoin >= level1Card[tmp].whiteCoin) {
                                        whiteSw = 1;
                                    }
                                    else if (level1Card[tmp].whiteCoin > 0 && (p1.whiteCoin + p1.SpecialWhiteCoin) >= level1Card[tmp].whiteCoin) {
                                        whiteSw = 2;
                                        white = p1.whiteCoin + p1.SpecialWhiteCoin;
                                    }
                                    else if (level1Card[tmp].whiteCoin > 0 && (p1.whiteCoin + p1.SpecialWhiteCoin) - level1Card[tmp].whiteCoin < 0) {
                                        if (level1Card[tmp].whiteCoin - (p1.whiteCoin + p1.SpecialWhiteCoin) <= p1.goldCoin) {
                                            white = p1.whiteCoin + p1.SpecialWhiteCoin + (level1Card[tmp].whiteCoin - (p1.whiteCoin + p1.SpecialWhiteCoin));
                                            p1.goldCoin -= level1Card[tmp].whiteCoin - (p1.whiteCoin + p1.SpecialWhiteCoin);
                                            tmpGold5 = level1Card[tmp].whiteCoin - (p1.whiteCoin + p1.SpecialWhiteCoin);
                                            whiteSw = 3;
                                            goldCoinNum += level1Card[tmp].whiteCoin - (p1.whiteCoin + p1.SpecialWhiteCoin);
                                            updateGold1();
                                            goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                        }
                                    }

                                    if (black >= level1Card[tmp].blackCoin && blue >= level1Card[tmp].blueCoin && green >= level1Card[tmp].greenCoin && red >= level1Card[tmp].redCoin && white >= level1Card[tmp].whiteCoin) {
                                        System.out.println("YESSSS PLAYER 1");
                                        //add gold coins
                                        isP1Turn = false;
                                        init.playerOneHand.add(level1Card[tmp]);
//                                        System.out.println(init.level1.get(tempI).toString());
                                        init.level1.remove(tempI);
                                        
                                        switch (blackSw) {
                                            case 1 : p1.blackCoin -= level1Card[tmp].blackCoin;
                                            blackCoinNum += level1Card[tmp].blackCoin;
                                            blackCoinLabel.setText(String.valueOf(blackCoinNum));
                                            break;
                                            case 2 : p1.blackCoin -= level1Card[tmp].blackCoin - p1.SpecialBlackCoin;
                                            blackCoinNum += level1Card[tmp].blackCoin - p1.SpecialBlackCoin;
                                            blackCoinLabel.setText(String.valueOf(blackCoinNum));
                                            break;
                                            case 3 : p1.blackCoin -= level1Card[tmp].blackCoin - p1.SpecialBlackCoin - tmpGold1;
                                                blackCoinNum += level1Card[tmp].blackCoin - p1.SpecialBlackCoin - tmpGold1;
                                                blackCoinLabel.setText(String.valueOf(blackCoinNum));
                                                goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                            break;
                                        }
                                        switch (blueSw) {
                                            case 1 : p1.blueCoin -= level1Card[tmp].blueCoin;
                                                blueCoinNum += level1Card[tmp].blueCoin;
                                                blueCoinLabel.setText(String.valueOf(blueCoinNum));
                                                break;
                                            case 2 : p1.blueCoin -= level1Card[tmp].blueCoin - p1.SpecialBlueCoin;
                                                blueCoinNum += level1Card[tmp].blueCoin - p1.SpecialBlueCoin;
                                                blueCoinLabel.setText(String.valueOf(blueCoinNum));
                                                break;
                                            case 3 : p1.blueCoin -= level1Card[tmp].blueCoin - p1.SpecialBlueCoin - tmpGold1;
                                                blueCoinNum += level1Card[tmp].blueCoin - p1.SpecialBlueCoin - tmpGold1;
                                                blueCoinLabel.setText(String.valueOf(blueCoinNum));
                                                goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                                break;
                                        }
                                        switch (greenSw) {
                                            case 1 : p1.greenCoin -= level1Card[tmp].greenCoin;
                                                greenCoinNum += level1Card[tmp].greenCoin;
                                                greenCoinLabel.setText(String.valueOf(greenCoinNum));
                                                break;
                                            case 2 : p1.greenCoin -= level1Card[tmp].greenCoin - p1.SpecialGreenCoin;
                                                greenCoinNum += level1Card[tmp].greenCoin - p1.SpecialGreenCoin;
                                                greenCoinLabel.setText(String.valueOf(greenCoinNum));
                                                break;
                                            case 3 : p1.greenCoin -= level1Card[tmp].greenCoin - p1.SpecialGreenCoin - tmpGold1;
                                                greenCoinNum += level1Card[tmp].greenCoin - p1.SpecialGreenCoin - tmpGold1;
                                                greenCoinLabel.setText(String.valueOf(greenCoinNum));
                                                goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                                break;
                                        }
                                        switch (redSw) {
                                            case 1 : p1.redCoin -= level1Card[tmp].redCoin;
                                                redCoinNum += level1Card[tmp].redCoin;
                                                redCoinLabel.setText(String.valueOf(redCoinNum));
                                                break;
                                            case 2 : p1.redCoin -= level1Card[tmp].redCoin - p1.SpecialRedCoin;
                                                redCoinNum += level1Card[tmp].redCoin - p1.SpecialRedCoin;
                                                redCoinLabel.setText(String.valueOf(redCoinNum));
                                                break;
                                            case 3 : p1.redCoin -= level1Card[tmp].redCoin - p1.SpecialRedCoin - tmpGold1;
                                                redCoinNum += level1Card[tmp].redCoin - p1.SpecialRedCoin - tmpGold1;
                                                redCoinLabel.setText(String.valueOf(redCoinNum));
                                                goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                                break;
                                        }
                                        switch (whiteSw) {
                                            case 1 : p1.whiteCoin -= level1Card[tmp].whiteCoin;
                                                whiteCoinNum += level1Card[tmp].whiteCoin;
                                                whiteCoinLabel.setText(String.valueOf(whiteCoinNum));
                                                break;
                                            case 2 : p1.whiteCoin -= level1Card[tmp].whiteCoin - p1.SpecialWhiteCoin;
                                                whiteCoinNum += level1Card[tmp].whiteCoin - p1.SpecialWhiteCoin;
                                                whiteCoinLabel.setText(String.valueOf(whiteCoinNum));
                                                break;
                                            case 3 : p1.whiteCoin -= level1Card[tmp].whiteCoin - p1.SpecialWhiteCoin - tmpGold1;
                                                whiteCoinNum += level1Card[tmp].whiteCoin - p1.SpecialWhiteCoin - tmpGold1;
                                                whiteCoinLabel.setText(String.valueOf(whiteCoinNum));
                                                goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                                break;
                                        }
                                        
                                        p1.score += level1Card[tmp].point;

                                        updateScore1();

                                        updateNormalBlack1();
                                        updateNormalBlue1();
                                        updateNormalGreen1();
                                        updateNormalRed1();
                                        updateNormalWhite1();

                                        switch (level1Card[tmp].specialCoin) {
                                            case "black":
                                                p1.SpecialBlackCoin++;
                                                updateSpecialBlack1();
                                                break;
                                            case "blue":
                                                p1.SpecialBlueCoin++;
                                                updateSpecialBlue1();
                                                break;
                                            case "green":
                                                p1.SpecialGreenCoin++;
                                                updateSpecialGreen1();
                                                break;
                                            case "red":
                                                p1.SpecialRedCoin++;
                                                updateSpecialRed1();
                                                break;
                                            case "white":
                                                p1.SpecialWhiteCoin++;
                                                updateSpecialWhite1();
                                                break;
                                        }
                                        cardLabel1[tmp].removeAll();
                                        try {
                                            redrawCards1();
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                    }
                                }
                                else {
                                    int black = p2.blackCoin;
                                    int blackSw = 0;
                                    int tmpGold1 = 0;
                                    int blue = p2.blueCoin;
                                    int blueSw = 0;
                                    int tmpGold2 = 0;
                                    int green = p2.greenCoin;
                                    int greenSw = 0;
                                    int tmpGold3 = 0;
                                    int red = p2.redCoin;
                                    int redSw = 0;
                                    int tmpGold4 = 0;
                                    int white = p2.whiteCoin;
                                    int whiteSw = 0;
                                    int tmpGold5 = 0;
                                    if (p2.blackCoin >= level1Card[tmp].blackCoin) {
                                        blackSw = 1;
                                    }
                                    else if (level1Card[tmp].blackCoin > 0 && (p2.blackCoin + p2.SpecialBlackCoin) >= level1Card[tmp].blackCoin) {
                                        blackSw = 2;
                                        black = p2.blackCoin + p2.SpecialBlackCoin;
                                    }
                                    else if (level1Card[tmp].blackCoin > 0 && (p2.blackCoin + p2.SpecialBlackCoin) - level1Card[tmp].blackCoin < 0) {
                                        if (level1Card[tmp].blackCoin - (p2.blackCoin + p2.SpecialBlackCoin) <= p2.goldCoin) {
                                            black = p2.blackCoin + p2.SpecialBlackCoin + (level1Card[tmp].blackCoin - (p2.blackCoin + p2.SpecialBlackCoin));
                                            p2.goldCoin -= level1Card[tmp].blackCoin - (p2.blackCoin + p2.SpecialBlackCoin);
                                            tmpGold1 = level1Card[tmp].blackCoin - (p2.blackCoin + p2.SpecialBlackCoin);
                                            blackSw = 3;
                                            goldCoinNum += level1Card[tmp].blackCoin - (p2.blackCoin + p2.SpecialBlackCoin);
                                            updateGold1();
                                            goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                        }
                                    }


                                    if (p2.blueCoin >= level1Card[tmp].blueCoin) {
                                        blueSw = 1;
                                    }
                                    else if (level1Card[tmp].blueCoin > 0 && (p2.blueCoin + p2.SpecialBlueCoin) >= level1Card[tmp].blueCoin) {
                                        blueSw = 2;
                                        blue = p2.blueCoin + p2.SpecialBlueCoin;
                                    }
                                    else if (level1Card[tmp].blueCoin > 0 && (p2.blueCoin + p2.SpecialBlueCoin) - level1Card[tmp].blueCoin < 0) {
                                        if (level1Card[tmp].blueCoin - (p2.blueCoin + p2.SpecialBlueCoin) <= p2.goldCoin) {
                                            blue = p2.blueCoin + p2.SpecialBlueCoin + (level1Card[tmp].blueCoin - (p2.blueCoin + p2.SpecialBlueCoin));
                                            p2.goldCoin -= level1Card[tmp].blueCoin - (p2.blueCoin + p2.SpecialBlueCoin);
                                            tmpGold2 = level1Card[tmp].blueCoin - (p2.blueCoin + p2.SpecialBlueCoin);
                                            blueSw = 3;
                                            goldCoinNum += level1Card[tmp].blueCoin - (p2.blueCoin + p2.SpecialBlueCoin);
                                            updateGold1();
                                            goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                        }
                                    }


                                    if (p2.greenCoin >= level1Card[tmp].greenCoin) {
                                        greenSw = 1;
                                    }
                                    else if (level1Card[tmp].greenCoin > 0 && (p2.greenCoin + p2.SpecialGreenCoin) >= level1Card[tmp].greenCoin) {
                                        greenSw = 2;
                                        green = p2.greenCoin + p2.SpecialGreenCoin;
                                    }
                                    else if (level1Card[tmp].greenCoin > 0 && (p2.greenCoin + p2.SpecialGreenCoin) - level1Card[tmp].greenCoin < 0) {
                                        if (level1Card[tmp].greenCoin - (p2.greenCoin + p2.SpecialGreenCoin) <= p2.goldCoin) {
                                            green = p2.greenCoin + p2.SpecialGreenCoin + (level1Card[tmp].greenCoin - (p2.greenCoin + p2.SpecialGreenCoin));
                                            p2.goldCoin -= level1Card[tmp].greenCoin - (p2.greenCoin + p2.SpecialGreenCoin);
                                            tmpGold3 = level1Card[tmp].greenCoin - (p2.greenCoin + p2.SpecialGreenCoin);
                                            greenSw = 3;
                                            goldCoinNum += level1Card[tmp].greenCoin - (p2.greenCoin + p2.SpecialGreenCoin);
                                            updateGold1();
                                            goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                        }
                                    }


                                    if (p2.redCoin >= level1Card[tmp].redCoin) {
                                        redSw = 1;
                                    }
                                    else if (level1Card[tmp].redCoin > 0 && (p2.redCoin + p2.SpecialRedCoin) >= level1Card[tmp].redCoin) {
                                        redSw = 2;
                                        red = p2.redCoin + p2.SpecialRedCoin;
                                    }
                                    else if (level1Card[tmp].redCoin > 0 && (p2.redCoin + p2.SpecialRedCoin) - level1Card[tmp].redCoin < 0) {
                                        if (level1Card[tmp].redCoin - (p2.redCoin + p2.SpecialRedCoin) <= p2.goldCoin) {
                                            red = p2.redCoin + p2.SpecialRedCoin + (level1Card[tmp].redCoin - (p2.redCoin + p2.SpecialRedCoin));
                                            p2.goldCoin -= level1Card[tmp].redCoin - (p2.redCoin + p2.SpecialRedCoin);
                                            tmpGold4 = level1Card[tmp].redCoin - (p2.redCoin + p2.SpecialRedCoin);
                                            redSw = 3;
                                            goldCoinNum += level1Card[tmp].redCoin - (p2.redCoin + p2.SpecialRedCoin);
                                            updateGold1();
                                            goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                        }
                                    }


                                    if (p2.whiteCoin >= level1Card[tmp].whiteCoin) {
                                        whiteSw = 1;
                                    }
                                    else if (level1Card[tmp].whiteCoin > 0 && (p2.whiteCoin + p2.SpecialWhiteCoin) >= level1Card[tmp].whiteCoin) {
                                        whiteSw = 2;
                                        white = p2.whiteCoin + p2.SpecialWhiteCoin;
                                    }
                                    else if (level1Card[tmp].whiteCoin > 0 && (p2.whiteCoin + p2.SpecialWhiteCoin) - level1Card[tmp].whiteCoin < 0) {
                                        if (level1Card[tmp].whiteCoin - (p2.whiteCoin + p2.SpecialWhiteCoin) <= p2.goldCoin) {
                                            white = p2.whiteCoin + p2.SpecialWhiteCoin + (level1Card[tmp].whiteCoin - (p2.whiteCoin + p2.SpecialWhiteCoin));
                                            p2.goldCoin -= level1Card[tmp].whiteCoin - (p2.whiteCoin + p2.SpecialWhiteCoin);
                                            tmpGold5 = level1Card[tmp].whiteCoin - (p2.whiteCoin + p2.SpecialWhiteCoin);
                                            whiteSw = 3;
                                            goldCoinNum += level1Card[tmp].whiteCoin - (p2.whiteCoin + p2.SpecialWhiteCoin);
                                            updateGold1();
                                            goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                        }
                                    }

                                    if (black >= level1Card[tmp].blackCoin && blue >= level1Card[tmp].blueCoin && green >= level1Card[tmp].greenCoin && red >= level1Card[tmp].redCoin && white >= level1Card[tmp].whiteCoin) {
                                        System.out.println("YESSSS PLAYER 2");
                                        isP1Turn = true;
                                        init.playerTwoHand.add(level1Card[tmp]);
//                                        System.out.println(init.level1.get(tempI).toString());
                                        init.level1.remove(tempI);
                                        switch (blackSw) {
                                            case 1 : p2.blackCoin -= level1Card[tmp].blackCoin;
                                                blackCoinNum += level1Card[tmp].blackCoin;
                                                blackCoinLabel.setText(String.valueOf(blackCoinNum));
                                                break;
                                            case 2 : p2.blackCoin -= level1Card[tmp].blackCoin - p2.SpecialBlackCoin;
                                                blackCoinNum += level1Card[tmp].blackCoin - p2.SpecialBlackCoin;
                                                blackCoinLabel.setText(String.valueOf(blackCoinNum));
                                                break;
                                            case 3 : p2.blackCoin -= level1Card[tmp].blackCoin - p2.SpecialBlackCoin - tmpGold1;
                                                blackCoinNum += level1Card[tmp].blackCoin - p2.SpecialBlackCoin - tmpGold1;
                                                blackCoinLabel.setText(String.valueOf(blackCoinNum));
                                                goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                                break;
                                        }
                                        switch (blueSw) {
                                            case 1 : p2.blueCoin -= level1Card[tmp].blueCoin;
                                                blueCoinNum += level1Card[tmp].blueCoin;
                                                blueCoinLabel.setText(String.valueOf(blueCoinNum));
                                                break;
                                            case 2 : p2.blueCoin -= level1Card[tmp].blueCoin - p2.SpecialBlueCoin;
                                                blueCoinNum += level1Card[tmp].blueCoin - p2.SpecialBlueCoin;
                                                blueCoinLabel.setText(String.valueOf(blueCoinNum));
                                                break;
                                            case 3 : p2.blueCoin -= level1Card[tmp].blueCoin - p2.SpecialBlueCoin - tmpGold1;
                                                blueCoinNum += level1Card[tmp].blueCoin - p2.SpecialBlueCoin - tmpGold1;
                                                blueCoinLabel.setText(String.valueOf(blueCoinNum));
                                                goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                                break;
                                        }
                                        switch (greenSw) {
                                            case 1 : p2.greenCoin -= level1Card[tmp].greenCoin;
                                                greenCoinNum += level1Card[tmp].greenCoin;
                                                greenCoinLabel.setText(String.valueOf(greenCoinNum));
                                                break;
                                            case 2 : p2.greenCoin -= level1Card[tmp].greenCoin - p2.SpecialGreenCoin;
                                                greenCoinNum += level1Card[tmp].greenCoin - p2.SpecialGreenCoin;
                                                greenCoinLabel.setText(String.valueOf(greenCoinNum));
                                                break;
                                            case 3 : p2.greenCoin -= level1Card[tmp].greenCoin - p2.SpecialGreenCoin - tmpGold1;
                                                greenCoinNum += level1Card[tmp].greenCoin - p2.SpecialGreenCoin - tmpGold1;
                                                greenCoinLabel.setText(String.valueOf(greenCoinNum));
                                                goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                                break;
                                        }
                                        switch (redSw) {
                                            case 1 : p2.redCoin -= level1Card[tmp].redCoin;
                                                redCoinNum += level1Card[tmp].redCoin;
                                                redCoinLabel.setText(String.valueOf(redCoinNum));
                                                break;
                                            case 2 : p2.redCoin -= level1Card[tmp].redCoin - p2.SpecialRedCoin;
                                                redCoinNum += level1Card[tmp].redCoin - p2.SpecialRedCoin;
                                                redCoinLabel.setText(String.valueOf(redCoinNum));
                                                break;
                                            case 3 : p2.redCoin -= level1Card[tmp].redCoin - p2.SpecialRedCoin - tmpGold1;
                                                redCoinNum += level1Card[tmp].redCoin - p2.SpecialRedCoin - tmpGold1;
                                                redCoinLabel.setText(String.valueOf(redCoinNum));
                                                goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                                break;
                                        }
                                        switch (whiteSw) {
                                            case 1 : p2.whiteCoin -= level1Card[tmp].whiteCoin;
                                                whiteCoinNum += level1Card[tmp].whiteCoin;
                                                whiteCoinLabel.setText(String.valueOf(whiteCoinNum));
                                                break;
                                            case 2 : p2.whiteCoin -= level1Card[tmp].whiteCoin - p2.SpecialWhiteCoin;
                                                whiteCoinNum += level1Card[tmp].whiteCoin - p2.SpecialWhiteCoin;
                                                whiteCoinLabel.setText(String.valueOf(whiteCoinNum));
                                                break;
                                            case 3 : p2.whiteCoin -= level1Card[tmp].whiteCoin - p2.SpecialWhiteCoin - tmpGold1;
                                                whiteCoinNum += level1Card[tmp].whiteCoin - p2.SpecialWhiteCoin - tmpGold1;
                                                whiteCoinLabel.setText(String.valueOf(whiteCoinNum));
                                                goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                                break;
                                        }

                                        updateScore2();

                                        updateNormalBlack2();
                                        updateNormalBlue2();
                                        updateNormalGreen2();
                                        updateNormalRed2();
                                        updateNormalWhite2();
                                        
                                        switch (level1Card[tmp].specialCoin) {
                                            case "black":
                                                p2.SpecialBlackCoin++;
                                                updateSpecialBlack2();
                                                break;
                                            case "blue":
                                                p2.SpecialBlueCoin++;
                                                updateSpecialBlue2();
                                                break;
                                            case "green":
                                                p2.SpecialGreenCoin++;
                                                updateSpecialGreen2();
                                                break;
                                            case "red":
                                                p2.SpecialRedCoin++;
                                                updateSpecialRed2();
                                                break;
                                            case "white":
                                                p2.SpecialWhiteCoin++;
                                                updateSpecialWhite2();
                                                break;
                                        }
                                        cardLabel1[tmp].removeAll();
                                        try {
                                            redrawCards1();
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
                                if (isP1Turn) {
                                    if (++p1.reserveCards <= 3) {
                                        isP1Turn = false;
                                        init.playerOneReserves.add(level1Card[tmp]);
                                        System.out.println(init.playerOneReserves.toString());
                                        init.level1.remove(tempI);

                                        cardLabel1[tmp].removeAll();

                                        try {
                                            redrawCards1();
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }

                                        if(goldCoinNum > 0) {
                                            goldCoinNum--;
                                            p1.goldCoin++;
                                            goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                            updateGold1();
                                        }

                                        updateReserved1();

                                        if (p1.reserveCards == 1) {
                                            newJLabels1();
                                        }
                                        try {
                                            drawReserves1();
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                    }
                                }
                                else {
                                    if (++p2.reserveCards <= 3) {
                                        isP1Turn = true;
                                        init.playerTwoReserves.add(level1Card[tmp]);
                                        init.level1.remove(tempI);
                                        System.out.println(init.playerTwoReserves.toString());
                                        cardLabel1[tmp].removeAll();

                                        try {
                                            redrawCards1();
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }

                                        if(goldCoinNum > 0) {
                                            goldCoinNum--;
                                            p2.goldCoin++;
                                            goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                            updateGold2();
                                        }

                                        updateReserved2();

                                        if (p2.reserveCards == 1) {
                                            newJLabels2();
                                        }
                                        try {
                                            drawReserves2();
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                    }
                                }
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
    public void drawLevel2() {
        for (int i = 0, j = 0, k = 0; i < 4; i++, j++, k++) {
            try {
                level2Card[k] = init.level2.get(i);
                BufferedImage image = ImageIO.read(getClass().getResource(level2Card[k].getImage2Path()));
                cardLabel2[k] = new JLabel(new ImageIcon(image));
                cardLabel2[k].setBounds(510 + (cardWidth + 10)*j, 65 + 10 + cardHeight, cardWidth, cardHeight);
                int tmp = k;
                int tempI = i;
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
                                try {
                                    redrawCards2();
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
                                    int black = p1.blackCoin;
                                    int blackSw = 0;
                                    int tmpGold1 = 0;
                                    int blue = p1.blueCoin;
                                    int blueSw = 0;
                                    int tmpGold2 = 0;
                                    int green = p1.greenCoin;
                                    int greenSw = 0;
                                    int tmpGold3 = 0;
                                    int red = p1.redCoin;
                                    int redSw = 0;
                                    int tmpGold4 = 0;
                                    int white = p1.whiteCoin;
                                    int whiteSw = 0;
                                    int tmpGold5 = 0;
                                    if (p1.blackCoin >= level2Card[tmp].blackCoin) {
                                        blackSw = 1;
                                    }
                                    else if (level2Card[tmp].blackCoin > 0 && (p1.blackCoin + p1.SpecialBlackCoin) >= level2Card[tmp].blackCoin) {
                                        blackSw = 2;
                                        black = p1.blackCoin + p1.SpecialBlackCoin;
                                    }
                                    else if (level2Card[tmp].blackCoin > 0 && (p1.blackCoin + p1.SpecialBlackCoin) - level2Card[tmp].blackCoin < 0) {
                                        if (level2Card[tmp].blackCoin - (p1.blackCoin + p1.SpecialBlackCoin) <= p1.goldCoin) {
                                            black = p1.blackCoin + p1.SpecialBlackCoin + (level2Card[tmp].blackCoin - (p1.blackCoin + p1.SpecialBlackCoin));
                                            p1.goldCoin -= level2Card[tmp].blackCoin - (p1.blackCoin + p1.SpecialBlackCoin);
                                            tmpGold1 = level2Card[tmp].blackCoin - (p1.blackCoin + p1.SpecialBlackCoin);
                                            blackSw = 3;
                                            goldCoinNum += level2Card[tmp].blackCoin - (p1.blackCoin + p1.SpecialBlackCoin);
                                            updateGold1();
                                            goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                        }
                                    }


                                    if (p1.blueCoin >= level2Card[tmp].blueCoin) {
                                        blueSw = 1;
                                    }
                                    else if (level2Card[tmp].blueCoin > 0 && (p1.blueCoin + p1.SpecialBlueCoin) >= level2Card[tmp].blueCoin) {
                                        blueSw = 2;
                                        blue = p1.blueCoin + p1.SpecialBlueCoin;
                                    }
                                    else if (level2Card[tmp].blueCoin > 0 && (p1.blueCoin + p1.SpecialBlueCoin) - level2Card[tmp].blueCoin < 0) {
                                        if (level2Card[tmp].blueCoin - (p1.blueCoin + p1.SpecialBlueCoin) <= p1.goldCoin) {
                                            blue = p1.blueCoin + p1.SpecialBlueCoin + (level2Card[tmp].blueCoin - (p1.blueCoin + p1.SpecialBlueCoin));
                                            p1.goldCoin -= level2Card[tmp].blueCoin - (p1.blueCoin + p1.SpecialBlueCoin);
                                            tmpGold2 = level2Card[tmp].blueCoin - (p1.blueCoin + p1.SpecialBlueCoin);
                                            blueSw = 3;
                                            goldCoinNum += level2Card[tmp].blueCoin - (p1.blueCoin + p1.SpecialBlueCoin);
                                            updateGold1();
                                            goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                        }
                                    }


                                    if (p1.greenCoin >= level2Card[tmp].greenCoin) {
                                        greenSw = 1;
                                    }
                                    else if (level2Card[tmp].greenCoin > 0 && (p1.greenCoin + p1.SpecialGreenCoin) >= level2Card[tmp].greenCoin) {
                                        greenSw = 2;
                                        green = p1.greenCoin + p1.SpecialGreenCoin;
                                    }
                                    else if (level2Card[tmp].greenCoin > 0 && (p1.greenCoin + p1.SpecialGreenCoin) - level2Card[tmp].greenCoin < 0) {
                                        if (level2Card[tmp].greenCoin - (p1.greenCoin + p1.SpecialGreenCoin) <= p1.goldCoin) {
                                            green = p1.greenCoin + p1.SpecialGreenCoin + (level2Card[tmp].greenCoin - (p1.greenCoin + p1.SpecialGreenCoin));
                                            p1.goldCoin -= level2Card[tmp].greenCoin - (p1.greenCoin + p1.SpecialGreenCoin);
                                            tmpGold3 = level2Card[tmp].greenCoin - (p1.greenCoin + p1.SpecialGreenCoin);
                                            greenSw = 3;
                                            goldCoinNum += level2Card[tmp].greenCoin - (p1.greenCoin + p1.SpecialGreenCoin);
                                            updateGold1();
                                            goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                        }
                                    }


                                    if (p1.redCoin >= level2Card[tmp].redCoin) {
                                        redSw = 1;
                                    }
                                    else if (level2Card[tmp].redCoin > 0 && (p1.redCoin + p1.SpecialRedCoin) >= level2Card[tmp].redCoin) {
                                        redSw = 2;
                                        red = p1.redCoin + p1.SpecialRedCoin;
                                    }
                                    else if (level2Card[tmp].redCoin > 0 && (p1.redCoin + p1.SpecialRedCoin) - level2Card[tmp].redCoin < 0) {
                                        if (level2Card[tmp].redCoin - (p1.redCoin + p1.SpecialRedCoin) <= p1.goldCoin) {
                                            red = p1.redCoin + p1.SpecialRedCoin + (level2Card[tmp].redCoin - (p1.redCoin + p1.SpecialRedCoin));
                                            p1.goldCoin -= level2Card[tmp].redCoin - (p1.redCoin + p1.SpecialRedCoin);
                                            tmpGold4 = level2Card[tmp].redCoin - (p1.redCoin + p1.SpecialRedCoin);
                                            redSw = 3;
                                            goldCoinNum += level2Card[tmp].redCoin - (p1.redCoin + p1.SpecialRedCoin);
                                            updateGold1();
                                            goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                        }
                                    }


                                    if (p1.whiteCoin >= level2Card[tmp].whiteCoin) {
                                        whiteSw = 1;
                                    }
                                    else if (level2Card[tmp].whiteCoin > 0 && (p1.whiteCoin + p1.SpecialWhiteCoin) >= level2Card[tmp].whiteCoin) {
                                        whiteSw = 2;
                                        white = p1.whiteCoin + p1.SpecialWhiteCoin;
                                    }
                                    else if (level2Card[tmp].whiteCoin > 0 && (p1.whiteCoin + p1.SpecialWhiteCoin) - level2Card[tmp].whiteCoin < 0) {
                                        if (level2Card[tmp].whiteCoin - (p1.whiteCoin + p1.SpecialWhiteCoin) <= p1.goldCoin) {
                                            white = p1.whiteCoin + p1.SpecialWhiteCoin + (level2Card[tmp].whiteCoin - (p1.whiteCoin + p1.SpecialWhiteCoin));
                                            p1.goldCoin -= level2Card[tmp].whiteCoin - (p1.whiteCoin + p1.SpecialWhiteCoin);
                                            tmpGold5 = level2Card[tmp].whiteCoin - (p1.whiteCoin + p1.SpecialWhiteCoin);
                                            whiteSw = 3;
                                            goldCoinNum += level2Card[tmp].whiteCoin - (p1.whiteCoin + p1.SpecialWhiteCoin);
                                            updateGold1();
                                            goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                        }
                                    }

                                    if (black >= level2Card[tmp].blackCoin && blue >= level2Card[tmp].blueCoin && green >= level2Card[tmp].greenCoin && red >= level2Card[tmp].redCoin && white >= level2Card[tmp].whiteCoin) {
                                        System.out.println("YESSSS PLAYER 1");
                                        //add special coins
                                        isP1Turn = false;
                                        init.playerOneHand.add(level2Card[tmp]);
                                        System.out.println(init.level2.get(tempI).toString());
                                        init.level2.remove(tempI);
                                        switch (blackSw) {
                                            case 1 : p1.blackCoin -= level2Card[tmp].blackCoin;
                                                blackCoinNum += level2Card[tmp].blackCoin;
                                                blackCoinLabel.setText(String.valueOf(blackCoinNum));
                                                break;
                                            case 2 : p1.blackCoin -= level2Card[tmp].blackCoin - p1.SpecialBlackCoin;
                                                blackCoinNum += level2Card[tmp].blackCoin - p1.SpecialBlackCoin;
                                                blackCoinLabel.setText(String.valueOf(blackCoinNum));
                                                break;
                                            case 3 : p1.blackCoin -= level2Card[tmp].blackCoin - p1.SpecialBlackCoin - tmpGold1;
                                                blackCoinNum += level2Card[tmp].blackCoin - p1.SpecialBlackCoin - tmpGold1;
                                                blackCoinLabel.setText(String.valueOf(blackCoinNum));
                                                goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                                break;
                                        }
                                        switch (blueSw) {
                                            case 1 : p1.blueCoin -= level2Card[tmp].blueCoin;
                                                blueCoinNum += level2Card[tmp].blueCoin;
                                                blueCoinLabel.setText(String.valueOf(blueCoinNum));
                                                break;
                                            case 2 : p1.blueCoin -= level2Card[tmp].blueCoin - p1.SpecialBlueCoin;
                                                blueCoinNum += level2Card[tmp].blueCoin - p1.SpecialBlueCoin;
                                                blueCoinLabel.setText(String.valueOf(blueCoinNum));
                                                break;
                                            case 3 : p1.blueCoin -= level2Card[tmp].blueCoin - p1.SpecialBlueCoin - tmpGold1;
                                                blueCoinNum += level2Card[tmp].blueCoin - p1.SpecialBlueCoin - tmpGold1;
                                                blueCoinLabel.setText(String.valueOf(blueCoinNum));
                                                goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                                break;
                                        }
                                        switch (greenSw) {
                                            case 1 : p1.greenCoin -= level2Card[tmp].greenCoin;
                                                greenCoinNum += level2Card[tmp].greenCoin;
                                                greenCoinLabel.setText(String.valueOf(greenCoinNum));
                                                break;
                                            case 2 : p1.greenCoin -= level2Card[tmp].greenCoin - p1.SpecialGreenCoin;
                                                greenCoinNum += level2Card[tmp].greenCoin - p1.SpecialGreenCoin;
                                                greenCoinLabel.setText(String.valueOf(greenCoinNum));
                                                break;
                                            case 3 : p1.greenCoin -= level2Card[tmp].greenCoin - p1.SpecialGreenCoin - tmpGold1;
                                                greenCoinNum += level2Card[tmp].greenCoin - p1.SpecialGreenCoin - tmpGold1;
                                                greenCoinLabel.setText(String.valueOf(greenCoinNum));
                                                goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                                break;
                                        }
                                        switch (redSw) {
                                            case 1 : p1.redCoin -= level2Card[tmp].redCoin;
                                                redCoinNum += level2Card[tmp].redCoin;
                                                redCoinLabel.setText(String.valueOf(redCoinNum));
                                                break;
                                            case 2 : p1.redCoin -= level2Card[tmp].redCoin - p1.SpecialRedCoin;
                                                redCoinNum += level2Card[tmp].redCoin - p1.SpecialRedCoin;
                                                redCoinLabel.setText(String.valueOf(redCoinNum));
                                                break;
                                            case 3 : p1.redCoin -= level2Card[tmp].redCoin - p1.SpecialRedCoin - tmpGold1;
                                                redCoinNum += level2Card[tmp].redCoin - p1.SpecialRedCoin - tmpGold1;
                                                redCoinLabel.setText(String.valueOf(redCoinNum));
                                                goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                                break;
                                        }
                                        switch (whiteSw) {
                                            case 1 : p1.whiteCoin -= level2Card[tmp].whiteCoin;
                                                whiteCoinNum += level2Card[tmp].whiteCoin;
                                                whiteCoinLabel.setText(String.valueOf(whiteCoinNum));
                                                break;
                                            case 2 : p1.whiteCoin -= level2Card[tmp].whiteCoin - p1.SpecialWhiteCoin;
                                                whiteCoinNum += level2Card[tmp].whiteCoin - p1.SpecialWhiteCoin;
                                                whiteCoinLabel.setText(String.valueOf(whiteCoinNum));
                                                break;
                                            case 3 : p1.whiteCoin -= level2Card[tmp].whiteCoin - p1.SpecialWhiteCoin - tmpGold1;
                                                whiteCoinNum += level2Card[tmp].whiteCoin - p1.SpecialWhiteCoin - tmpGold1;
                                                whiteCoinLabel.setText(String.valueOf(whiteCoinNum));
                                                goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                                break;
                                        }
                                        p1.score += level2Card[tmp].point;

                                        updateScore1();

                                        updateNormalBlack1();
                                        updateNormalBlue1();
                                        updateNormalGreen1();
                                        updateNormalRed1();
                                        updateNormalWhite1();
                                        
                                        switch (level2Card[tmp].specialCoin) {
                                            case "black":
                                                p1.SpecialBlackCoin++;
                                                updateSpecialBlack1();
                                                break;
                                            case "blue":
                                                p1.SpecialBlueCoin++;
                                                updateSpecialBlue1();
                                                break;
                                            case "green":
                                                p1.SpecialGreenCoin++;
                                                updateSpecialGreen1();
                                                break;
                                            case "red":
                                                p1.SpecialRedCoin++;
                                                updateSpecialRed1();
                                                break;
                                            case "white":
                                                p1.SpecialWhiteCoin++;
                                                updateSpecialWhite1();
                                                break;
                                        }
                                        cardLabel2[tmp].removeAll();
                                        try {
                                            redrawCards2();
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                    }
                                }
                                else {
                                    int black = p2.blackCoin;
                                    int blackSw = 0;
                                    int tmpGold1 = 0;
                                    int blue = p2.blueCoin;
                                    int blueSw = 0;
                                    int tmpGold2 = 0;
                                    int green = p2.greenCoin;
                                    int greenSw = 0;
                                    int tmpGold3 = 0;
                                    int red = p2.redCoin;
                                    int redSw = 0;
                                    int tmpGold4 = 0;
                                    int white = p2.whiteCoin;
                                    int whiteSw = 0;
                                    int tmpGold5 = 0;
                                    if (p2.blackCoin >= level2Card[tmp].blackCoin) {
                                        blackSw = 1;
                                    }
                                    else if (level2Card[tmp].blackCoin > 0 && (p2.blackCoin + p2.SpecialBlackCoin) >= level2Card[tmp].blackCoin) {
                                        blackSw = 2;
                                        black = p2.blackCoin + p2.SpecialBlackCoin;
                                    }
                                    else if (level2Card[tmp].blackCoin > 0 && (p2.blackCoin + p2.SpecialBlackCoin) - level2Card[tmp].blackCoin < 0) {
                                        if (level2Card[tmp].blackCoin - (p2.blackCoin + p2.SpecialBlackCoin) <= p2.goldCoin) {
                                            black = p2.blackCoin + p2.SpecialBlackCoin + (level2Card[tmp].blackCoin - (p2.blackCoin + p2.SpecialBlackCoin));
                                            p2.goldCoin -= level2Card[tmp].blackCoin - (p2.blackCoin + p2.SpecialBlackCoin);
                                            tmpGold1 = level2Card[tmp].blackCoin - (p2.blackCoin + p2.SpecialBlackCoin);
                                            blackSw = 3;
                                            goldCoinNum += level2Card[tmp].blackCoin - (p2.blackCoin + p2.SpecialBlackCoin);
                                            updateGold1();
                                            goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                        }
                                    }


                                    if (p2.blueCoin >= level2Card[tmp].blueCoin) {
                                        blueSw = 1;
                                    }
                                    else if (level2Card[tmp].blueCoin > 0 && (p2.blueCoin + p2.SpecialBlueCoin) >= level2Card[tmp].blueCoin) {
                                        blueSw = 2;
                                        blue = p2.blueCoin + p2.SpecialBlueCoin;
                                    }
                                    else if (level2Card[tmp].blueCoin > 0 && (p2.blueCoin + p2.SpecialBlueCoin) - level2Card[tmp].blueCoin < 0) {
                                        if (level2Card[tmp].blueCoin - (p2.blueCoin + p2.SpecialBlueCoin) <= p2.goldCoin) {
                                            blue = p2.blueCoin + p2.SpecialBlueCoin + (level2Card[tmp].blueCoin - (p2.blueCoin + p2.SpecialBlueCoin));
                                            p2.goldCoin -= level2Card[tmp].blueCoin - (p2.blueCoin + p2.SpecialBlueCoin);
                                            tmpGold2 = level2Card[tmp].blueCoin - (p2.blueCoin + p2.SpecialBlueCoin);
                                            blueSw = 3;
                                            goldCoinNum += level2Card[tmp].blueCoin - (p2.blueCoin + p2.SpecialBlueCoin);
                                            updateGold1();
                                            goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                        }
                                    }


                                    if (p2.greenCoin >= level2Card[tmp].greenCoin) {
                                        greenSw = 1;
                                    }
                                    else if (level2Card[tmp].greenCoin > 0 && (p2.greenCoin + p2.SpecialGreenCoin) >= level2Card[tmp].greenCoin) {
                                        greenSw = 2;
                                        green = p2.greenCoin + p2.SpecialGreenCoin;
                                    }
                                    else if (level2Card[tmp].greenCoin > 0 && (p2.greenCoin + p2.SpecialGreenCoin) - level2Card[tmp].greenCoin < 0) {
                                        if (level2Card[tmp].greenCoin - (p2.greenCoin + p2.SpecialGreenCoin) <= p2.goldCoin) {
                                            green = p2.greenCoin + p2.SpecialGreenCoin + (level2Card[tmp].greenCoin - (p2.greenCoin + p2.SpecialGreenCoin));
                                            p2.goldCoin -= level2Card[tmp].greenCoin - (p2.greenCoin + p2.SpecialGreenCoin);
                                            tmpGold3 = level2Card[tmp].greenCoin - (p2.greenCoin + p2.SpecialGreenCoin);
                                            greenSw = 3;
                                            goldCoinNum += level2Card[tmp].greenCoin - (p2.greenCoin + p2.SpecialGreenCoin);
                                            updateGold1();
                                            goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                        }
                                    }


                                    if (p2.redCoin >= level2Card[tmp].redCoin) {
                                        redSw = 1;
                                    }
                                    else if (level2Card[tmp].redCoin > 0 && (p2.redCoin + p2.SpecialRedCoin) >= level2Card[tmp].redCoin) {
                                        redSw = 2;
                                        red = p2.redCoin + p2.SpecialRedCoin;
                                    }
                                    else if (level2Card[tmp].redCoin > 0 && (p2.redCoin + p2.SpecialRedCoin) - level2Card[tmp].redCoin < 0) {
                                        if (level2Card[tmp].redCoin - (p2.redCoin + p2.SpecialRedCoin) <= p2.goldCoin) {
                                            red = p2.redCoin + p2.SpecialRedCoin + (level2Card[tmp].redCoin - (p2.redCoin + p2.SpecialRedCoin));
                                            p2.goldCoin -= level2Card[tmp].redCoin - (p2.redCoin + p2.SpecialRedCoin);
                                            tmpGold4 = level2Card[tmp].redCoin - (p2.redCoin + p2.SpecialRedCoin);
                                            redSw = 3;
                                            goldCoinNum += level2Card[tmp].redCoin - (p2.redCoin + p2.SpecialRedCoin);
                                            updateGold1();
                                            goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                        }
                                    }


                                    if (p2.whiteCoin >= level2Card[tmp].whiteCoin) {
                                        whiteSw = 1;
                                    }
                                    else if (level2Card[tmp].whiteCoin > 0 && (p2.whiteCoin + p2.SpecialWhiteCoin) >= level2Card[tmp].whiteCoin) {
                                        whiteSw = 2;
                                        white = p2.whiteCoin + p2.SpecialWhiteCoin;
                                    }
                                    else if (level2Card[tmp].whiteCoin > 0 && (p2.whiteCoin + p2.SpecialWhiteCoin) - level2Card[tmp].whiteCoin < 0) {
                                        if (level2Card[tmp].whiteCoin - (p2.whiteCoin + p2.SpecialWhiteCoin) <= p2.goldCoin) {
                                            white = p2.whiteCoin + p2.SpecialWhiteCoin + (level2Card[tmp].whiteCoin - (p2.whiteCoin + p2.SpecialWhiteCoin));
                                            p2.goldCoin -= level2Card[tmp].whiteCoin - (p2.whiteCoin + p2.SpecialWhiteCoin);
                                            tmpGold5 = level2Card[tmp].whiteCoin - (p2.whiteCoin + p2.SpecialWhiteCoin);
                                            whiteSw = 3;
                                            goldCoinNum += level2Card[tmp].whiteCoin - (p2.whiteCoin + p2.SpecialWhiteCoin);
                                            updateGold1();
                                            goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                        }
                                    }

                                    if (black >= level2Card[tmp].blackCoin && blue >= level2Card[tmp].blueCoin && green >= level2Card[tmp].greenCoin && red >= level2Card[tmp].redCoin && white >= level2Card[tmp].whiteCoin) {
                                        System.out.println("YESSSS PLAYER 2");
                                        isP1Turn = true;
                                        init.playerTwoHand.add(level2Card[tmp]);
                                        System.out.println(init.level2.get(tempI).toString());
                                        init.level2.remove(tempI);
                                        switch (blackSw) {
                                            case 1 : p2.blackCoin -= level2Card[tmp].blackCoin;
                                                blackCoinNum += level2Card[tmp].blackCoin;
                                                blackCoinLabel.setText(String.valueOf(blackCoinNum));
                                                break;
                                            case 2 : p2.blackCoin -= level2Card[tmp].blackCoin - p2.SpecialBlackCoin;
                                                blackCoinNum += level2Card[tmp].blackCoin - p2.SpecialBlackCoin;
                                                blackCoinLabel.setText(String.valueOf(blackCoinNum));
                                                break;
                                            case 3 : p2.blackCoin -= level2Card[tmp].blackCoin - p2.SpecialBlackCoin - tmpGold1;
                                                blackCoinNum += level2Card[tmp].blackCoin - p2.SpecialBlackCoin - tmpGold1;
                                                blackCoinLabel.setText(String.valueOf(blackCoinNum));
                                                goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                                break;
                                        }
                                        switch (blueSw) {
                                            case 1 : p2.blueCoin -= level2Card[tmp].blueCoin;
                                                blueCoinNum += level2Card[tmp].blueCoin;
                                                blueCoinLabel.setText(String.valueOf(blueCoinNum));
                                                break;
                                            case 2 : p2.blueCoin -= level2Card[tmp].blueCoin - p2.SpecialBlueCoin;
                                                blueCoinNum += level2Card[tmp].blueCoin - p2.SpecialBlueCoin;
                                                blueCoinLabel.setText(String.valueOf(blueCoinNum));
                                                break;
                                            case 3 : p2.blueCoin -= level2Card[tmp].blueCoin - p2.SpecialBlueCoin - tmpGold1;
                                                blueCoinNum += level2Card[tmp].blueCoin - p2.SpecialBlueCoin - tmpGold1;
                                                blueCoinLabel.setText(String.valueOf(blueCoinNum));
                                                goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                                break;
                                        }
                                        switch (greenSw) {
                                            case 1 : p2.greenCoin -= level2Card[tmp].greenCoin;
                                                greenCoinNum += level2Card[tmp].greenCoin;
                                                greenCoinLabel.setText(String.valueOf(greenCoinNum));
                                                break;
                                            case 2 : p2.greenCoin -= level2Card[tmp].greenCoin - p2.SpecialGreenCoin;
                                                greenCoinNum += level2Card[tmp].greenCoin - p2.SpecialGreenCoin;
                                                greenCoinLabel.setText(String.valueOf(greenCoinNum));
                                                break;
                                            case 3 : p2.greenCoin -= level2Card[tmp].greenCoin - p2.SpecialGreenCoin - tmpGold1;
                                                greenCoinNum += level2Card[tmp].greenCoin - p2.SpecialGreenCoin - tmpGold1;
                                                greenCoinLabel.setText(String.valueOf(greenCoinNum));
                                                goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                                break;
                                        }
                                        switch (redSw) {
                                            case 1 : p2.redCoin -= level2Card[tmp].redCoin;
                                                redCoinNum += level2Card[tmp].redCoin;
                                                redCoinLabel.setText(String.valueOf(redCoinNum));
                                                break;
                                            case 2 : p2.redCoin -= level2Card[tmp].redCoin - p2.SpecialRedCoin;
                                                redCoinNum += level2Card[tmp].redCoin - p2.SpecialRedCoin;
                                                redCoinLabel.setText(String.valueOf(redCoinNum));
                                                break;
                                            case 3 : p2.redCoin -= level2Card[tmp].redCoin - p2.SpecialRedCoin - tmpGold1;
                                                redCoinNum += level2Card[tmp].redCoin - p2.SpecialRedCoin - tmpGold1;
                                                redCoinLabel.setText(String.valueOf(redCoinNum));
                                                goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                                break;
                                        }
                                        switch (whiteSw) {
                                            case 1 : p2.whiteCoin -= level2Card[tmp].whiteCoin;
                                                whiteCoinNum += level2Card[tmp].whiteCoin;
                                                whiteCoinLabel.setText(String.valueOf(whiteCoinNum));
                                                break;
                                            case 2 : p2.whiteCoin -= level2Card[tmp].whiteCoin - p2.SpecialWhiteCoin;
                                                whiteCoinNum += level2Card[tmp].whiteCoin - p2.SpecialWhiteCoin;
                                                whiteCoinLabel.setText(String.valueOf(whiteCoinNum));
                                                break;
                                            case 3 : p2.whiteCoin -= level2Card[tmp].whiteCoin - p2.SpecialWhiteCoin - tmpGold1;
                                                whiteCoinNum += level2Card[tmp].whiteCoin - p2.SpecialWhiteCoin - tmpGold1;
                                                whiteCoinLabel.setText(String.valueOf(whiteCoinNum));
                                                goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                                break;
                                        }
                                        p2.score += level2Card[tmp].point;

                                        updateScore2();

                                        updateNormalBlack2();
                                        updateNormalBlue2();
                                        updateNormalGreen2();
                                        updateNormalRed2();
                                        updateNormalWhite2();
                                        
                                        switch (level2Card[tmp].specialCoin) {
                                            case "black":
                                                p2.SpecialBlackCoin++;
                                                updateSpecialBlack2();
                                                break;
                                            case "blue":
                                                p2.SpecialBlueCoin++;
                                                updateSpecialBlue2();
                                                break;
                                            case "green":
                                                p2.SpecialGreenCoin++;
                                                updateSpecialGreen2();
                                                break;
                                            case "red":
                                                p2.SpecialRedCoin++;
                                                updateSpecialRed2();
                                                break;
                                            case "white":
                                                p2.SpecialWhiteCoin++;
                                                updateSpecialWhite2();
                                                break;
                                        }
                                        cardLabel2[tmp].removeAll();
                                        try {
                                            redrawCards2();
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
                                if (isP1Turn) {
                                    if (++p1.reserveCards <= 3) {
                                        isP1Turn = false;
                                        init.playerOneReserves.add(level2Card[tmp]);
                                        init.level2.remove(tempI);

                                        cardLabel2[tmp].removeAll();

                                        try {
                                            redrawCards2();
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }

                                        if(goldCoinNum > 0) {
                                            goldCoinNum--;
                                            p1.goldCoin++;
                                            goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                            updateGold1();
                                        }

                                        updateReserved1();

                                        if (p1.reserveCards == 1) {
                                            newJLabels1();
                                        }
                                        try {
                                            drawReserves1();
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                    }
                                }
                                else {
                                    if (++p2.reserveCards <= 3) {
                                        isP1Turn = true;
                                        init.playerTwoReserves.add(level2Card[tmp]);
                                        init.level2.remove(tempI);

                                        cardLabel2[tmp].removeAll();

                                        try {
                                            redrawCards2();
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }

                                        if(goldCoinNum > 0) {
                                            goldCoinNum--;
                                            p2.goldCoin++;
                                            goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                            updateGold2();
                                        }

                                        updateReserved2();

                                        if (p2.reserveCards == 1) {
                                            newJLabels2();
                                        }
                                        try {
                                            drawReserves2();
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                    }
                                }
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
    public void drawLevel3() {
        for (int i = 0, j = 0, k = 0; i < 4; i++, j++, k++) {
            try {
                level3Card[k] = init.level3.get(i);
                BufferedImage image = ImageIO.read(getClass().getResource(level3Card[k].getImage3Path()));
                cardLabel3[k] = new JLabel(new ImageIcon(image));
                cardLabel3[k].setBounds(510 + (cardWidth + 10)*j, 65 + 2 * (10 + cardHeight), cardWidth, cardHeight);
                int tmp = k;
                int tempI = i;
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
                                try {
                                    redrawCards3();
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
                                    int black = p1.blackCoin;
                                    int blackSw = 0;
                                    int tmpGold1 = 0;
                                    int blue = p1.blueCoin;
                                    int blueSw = 0;
                                    int tmpGold2 = 0;
                                    int green = p1.greenCoin;
                                    int greenSw = 0;
                                    int tmpGold3 = 0;
                                    int red = p1.redCoin;
                                    int redSw = 0;
                                    int tmpGold4 = 0;
                                    int white = p1.whiteCoin;
                                    int whiteSw = 0;
                                    int tmpGold5 = 0;
                                    if (p1.blackCoin >= level3Card[tmp].blackCoin) {
                                        blackSw = 1;
                                    }
                                    else if (level3Card[tmp].blackCoin > 0 && (p1.blackCoin + p1.SpecialBlackCoin) >= level3Card[tmp].blackCoin) {
                                        blackSw = 2;
                                        black = p1.blackCoin + p1.SpecialBlackCoin;
                                    }
                                    else if (level3Card[tmp].blackCoin > 0 && (p1.blackCoin + p1.SpecialBlackCoin) - level3Card[tmp].blackCoin < 0) {
                                        if (level3Card[tmp].blackCoin - (p1.blackCoin + p1.SpecialBlackCoin) <= p1.goldCoin) {
                                            black = p1.blackCoin + p1.SpecialBlackCoin + (level3Card[tmp].blackCoin - (p1.blackCoin + p1.SpecialBlackCoin));
                                            p1.goldCoin -= level3Card[tmp].blackCoin - (p1.blackCoin + p1.SpecialBlackCoin);
                                            tmpGold1 = level3Card[tmp].blackCoin - (p1.blackCoin + p1.SpecialBlackCoin);
                                            blackSw = 3;
                                            goldCoinNum += level3Card[tmp].blackCoin - (p1.blackCoin + p1.SpecialBlackCoin);
                                            updateGold1();
                                            goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                        }
                                    }


                                    if (p1.blueCoin >= level3Card[tmp].blueCoin) {
                                        blueSw = 1;
                                    }
                                    else if (level3Card[tmp].blueCoin > 0 && (p1.blueCoin + p1.SpecialBlueCoin) >= level3Card[tmp].blueCoin) {
                                        blueSw = 2;
                                        blue = p1.blueCoin + p1.SpecialBlueCoin;
                                    }
                                    else if (level3Card[tmp].blueCoin > 0 && (p1.blueCoin + p1.SpecialBlueCoin) - level3Card[tmp].blueCoin < 0) {
                                        if (level3Card[tmp].blueCoin - (p1.blueCoin + p1.SpecialBlueCoin) <= p1.goldCoin) {
                                            blue = p1.blueCoin + p1.SpecialBlueCoin + (level3Card[tmp].blueCoin - (p1.blueCoin + p1.SpecialBlueCoin));
                                            p1.goldCoin -= level3Card[tmp].blueCoin - (p1.blueCoin + p1.SpecialBlueCoin);
                                            tmpGold2 = level3Card[tmp].blueCoin - (p1.blueCoin + p1.SpecialBlueCoin);
                                            blueSw = 3;
                                            goldCoinNum += level3Card[tmp].blueCoin - (p1.blueCoin + p1.SpecialBlueCoin);
                                            updateGold1();
                                            goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                        }
                                    }


                                    if (p1.greenCoin >= level3Card[tmp].greenCoin) {
                                        greenSw = 1;
                                    }
                                    else if (level3Card[tmp].greenCoin > 0 && (p1.greenCoin + p1.SpecialGreenCoin) >= level3Card[tmp].greenCoin) {
                                        greenSw = 2;
                                        green = p1.greenCoin + p1.SpecialGreenCoin;
                                    }
                                    else if (level3Card[tmp].greenCoin > 0 && (p1.greenCoin + p1.SpecialGreenCoin) - level3Card[tmp].greenCoin < 0) {
                                        if (level3Card[tmp].greenCoin - (p1.greenCoin + p1.SpecialGreenCoin) <= p1.goldCoin) {
                                            green = p1.greenCoin + p1.SpecialGreenCoin + (level3Card[tmp].greenCoin - (p1.greenCoin + p1.SpecialGreenCoin));
                                            p1.goldCoin -= level3Card[tmp].greenCoin - (p1.greenCoin + p1.SpecialGreenCoin);
                                            tmpGold3 = level3Card[tmp].greenCoin - (p1.greenCoin + p1.SpecialGreenCoin);
                                            greenSw = 3;
                                            goldCoinNum += level3Card[tmp].greenCoin - (p1.greenCoin + p1.SpecialGreenCoin);
                                            updateGold1();
                                            goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                        }
                                    }


                                    if (p1.redCoin >= level3Card[tmp].redCoin) {
                                        redSw = 1;
                                    }
                                    else if (level3Card[tmp].redCoin > 0 && (p1.redCoin + p1.SpecialRedCoin) >= level3Card[tmp].redCoin) {
                                        redSw = 2;
                                        red = p1.redCoin + p1.SpecialRedCoin;
                                    }
                                    else if (level3Card[tmp].redCoin > 0 && (p1.redCoin + p1.SpecialRedCoin) - level3Card[tmp].redCoin < 0) {
                                        if (level3Card[tmp].redCoin - (p1.redCoin + p1.SpecialRedCoin) <= p1.goldCoin) {
                                            red = p1.redCoin + p1.SpecialRedCoin + (level3Card[tmp].redCoin - (p1.redCoin + p1.SpecialRedCoin));
                                            p1.goldCoin -= level3Card[tmp].redCoin - (p1.redCoin + p1.SpecialRedCoin);
                                            tmpGold4 = level3Card[tmp].redCoin - (p1.redCoin + p1.SpecialRedCoin);
                                            redSw = 3;
                                            goldCoinNum += level3Card[tmp].redCoin - (p1.redCoin + p1.SpecialRedCoin);
                                            updateGold1();
                                            goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                        }
                                    }


                                    if (p1.whiteCoin >= level3Card[tmp].whiteCoin) {
                                        whiteSw = 1;
                                    }
                                    else if (level3Card[tmp].whiteCoin > 0 && (p1.whiteCoin + p1.SpecialWhiteCoin) >= level3Card[tmp].whiteCoin) {
                                        whiteSw = 2;
                                        white = p1.whiteCoin + p1.SpecialWhiteCoin;
                                    }
                                    else if (level3Card[tmp].whiteCoin > 0 && (p1.whiteCoin + p1.SpecialWhiteCoin) - level3Card[tmp].whiteCoin < 0) {
                                        if (level3Card[tmp].whiteCoin - (p1.whiteCoin + p1.SpecialWhiteCoin) <= p1.goldCoin) {
                                            white = p1.whiteCoin + p1.SpecialWhiteCoin + (level3Card[tmp].whiteCoin - (p1.whiteCoin + p1.SpecialWhiteCoin));
                                            p1.goldCoin -= level3Card[tmp].whiteCoin - (p1.whiteCoin + p1.SpecialWhiteCoin);
                                            tmpGold5 = level3Card[tmp].whiteCoin - (p1.whiteCoin + p1.SpecialWhiteCoin);
                                            whiteSw = 3;
                                            goldCoinNum += level3Card[tmp].whiteCoin - (p1.whiteCoin + p1.SpecialWhiteCoin);
                                            updateGold1();
                                            goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                        }
                                    }

                                    if (black >= level3Card[tmp].blackCoin && blue >= level3Card[tmp].blueCoin && green >= level3Card[tmp].greenCoin && red >= level3Card[tmp].redCoin && white >= level3Card[tmp].whiteCoin) {
                                        System.out.println("YESSSS PLAYER 1");
                                        //add special coins
                                        isP1Turn = false;
                                        init.playerOneHand.add(level3Card[tmp]);
                                        System.out.println(init.level3.get(tempI).toString());
                                        init.level3.remove(tempI);
                                        switch (blackSw) {
                                            case 1 : p1.blackCoin -= level3Card[tmp].blackCoin;
                                                blackCoinNum += level3Card[tmp].blackCoin;
                                                blackCoinLabel.setText(String.valueOf(blackCoinNum));
                                                break;
                                            case 2 : p1.blackCoin -= level3Card[tmp].blackCoin - p1.SpecialBlackCoin;
                                                blackCoinNum += level3Card[tmp].blackCoin - p1.SpecialBlackCoin;
                                                blackCoinLabel.setText(String.valueOf(blackCoinNum));
                                                break;
                                            case 3 : p1.blackCoin -= level3Card[tmp].blackCoin - p1.SpecialBlackCoin - tmpGold1;
                                                blackCoinNum += level3Card[tmp].blackCoin - p1.SpecialBlackCoin - tmpGold1;
                                                blackCoinLabel.setText(String.valueOf(blackCoinNum));
                                                goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                                break;
                                        }
                                        switch (blueSw) {
                                            case 1 : p1.blueCoin -= level3Card[tmp].blueCoin;
                                                blueCoinNum += level3Card[tmp].blueCoin;
                                                blueCoinLabel.setText(String.valueOf(blueCoinNum));
                                                break;
                                            case 2 : p1.blueCoin -= level3Card[tmp].blueCoin - p1.SpecialBlueCoin;
                                                blueCoinNum += level3Card[tmp].blueCoin - p1.SpecialBlueCoin;
                                                blueCoinLabel.setText(String.valueOf(blueCoinNum));
                                                break;
                                            case 3 : p1.blueCoin -= level3Card[tmp].blueCoin - p1.SpecialBlueCoin - tmpGold1;
                                                blueCoinNum += level3Card[tmp].blueCoin - p1.SpecialBlueCoin - tmpGold1;
                                                blueCoinLabel.setText(String.valueOf(blueCoinNum));
                                                goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                                break;
                                        }
                                        switch (greenSw) {
                                            case 1 : p1.greenCoin -= level3Card[tmp].greenCoin;
                                                greenCoinNum += level3Card[tmp].greenCoin;
                                                greenCoinLabel.setText(String.valueOf(greenCoinNum));
                                                break;
                                            case 2 : p1.greenCoin -= level3Card[tmp].greenCoin - p1.SpecialGreenCoin;
                                                greenCoinNum += level3Card[tmp].greenCoin - p1.SpecialGreenCoin;
                                                greenCoinLabel.setText(String.valueOf(greenCoinNum));
                                                break;
                                            case 3 : p1.greenCoin -= level3Card[tmp].greenCoin - p1.SpecialGreenCoin - tmpGold1;
                                                greenCoinNum += level3Card[tmp].greenCoin - p1.SpecialGreenCoin - tmpGold1;
                                                greenCoinLabel.setText(String.valueOf(greenCoinNum));
                                                goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                                break;
                                        }
                                        switch (redSw) {
                                            case 1 : p1.redCoin -= level3Card[tmp].redCoin;
                                                redCoinNum += level3Card[tmp].redCoin;
                                                redCoinLabel.setText(String.valueOf(redCoinNum));
                                                break;
                                            case 2 : p1.redCoin -= level3Card[tmp].redCoin - p1.SpecialRedCoin;
                                                redCoinNum += level3Card[tmp].redCoin - p1.SpecialRedCoin;
                                                redCoinLabel.setText(String.valueOf(redCoinNum));
                                                break;
                                            case 3 : p1.redCoin -= level3Card[tmp].redCoin - p1.SpecialRedCoin - tmpGold1;
                                                redCoinNum += level3Card[tmp].redCoin - p1.SpecialRedCoin - tmpGold1;
                                                redCoinLabel.setText(String.valueOf(redCoinNum));
                                                goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                                break;
                                        }
                                        switch (whiteSw) {
                                            case 1 : p1.whiteCoin -= level3Card[tmp].whiteCoin;
                                                whiteCoinNum += level3Card[tmp].whiteCoin;
                                                whiteCoinLabel.setText(String.valueOf(whiteCoinNum));
                                                break;
                                            case 2 : p1.whiteCoin -= level3Card[tmp].whiteCoin - p1.SpecialWhiteCoin;
                                                whiteCoinNum += level3Card[tmp].whiteCoin - p1.SpecialWhiteCoin;
                                                whiteCoinLabel.setText(String.valueOf(whiteCoinNum));
                                                break;
                                            case 3 : p1.whiteCoin -= level3Card[tmp].whiteCoin - p1.SpecialWhiteCoin - tmpGold1;
                                                whiteCoinNum += level3Card[tmp].whiteCoin - p1.SpecialWhiteCoin - tmpGold1;
                                                whiteCoinLabel.setText(String.valueOf(whiteCoinNum));
                                                goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                                break;
                                        }
                                        p1.score += level3Card[tmp].point;

                                        updateScore1();

                                        updateNormalBlack1();
                                        updateNormalBlue1();
                                        updateNormalGreen1();
                                        updateNormalRed1();
                                        updateNormalWhite1();
                                        
                                        switch (level3Card[tmp].specialCoin) {
                                            case "black":
                                                p1.SpecialBlackCoin++;
                                                updateSpecialBlack1();
                                                break;
                                            case "blue":
                                                p1.SpecialBlueCoin++;
                                                updateSpecialBlue1();
                                                break;
                                            case "green":
                                                p1.SpecialGreenCoin++;
                                                updateSpecialGreen1();
                                                break;
                                            case "red":
                                                p1.SpecialRedCoin++;
                                                updateSpecialRed1();
                                                break;
                                            case "white":
                                                p1.SpecialWhiteCoin++;
                                                updateSpecialWhite1();
                                                break;
                                        }
                                        cardLabel3[tmp].removeAll();
                                        try {
                                            redrawCards3();
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                    }
                                }
                                else {
                                    int black = p2.blackCoin;
                                    int blackSw = 0;
                                    int tmpGold1 = 0;
                                    int blue = p2.blueCoin;
                                    int blueSw = 0;
                                    int tmpGold2 = 0;
                                    int green = p2.greenCoin;
                                    int greenSw = 0;
                                    int tmpGold3 = 0;
                                    int red = p2.redCoin;
                                    int redSw = 0;
                                    int tmpGold4 = 0;
                                    int white = p2.whiteCoin;
                                    int whiteSw = 0;
                                    int tmpGold5 = 0;
                                    if (p2.blackCoin >= level3Card[tmp].blackCoin) {
                                        blackSw = 1;
                                    }
                                    else if (level3Card[tmp].blackCoin > 0 && (p2.blackCoin + p2.SpecialBlackCoin) >= level3Card[tmp].blackCoin) {
                                        blackSw = 2;
                                        black = p2.blackCoin + p2.SpecialBlackCoin;
                                    }
                                    else if (level3Card[tmp].blackCoin > 0 && (p2.blackCoin + p2.SpecialBlackCoin) - level3Card[tmp].blackCoin < 0) {
                                        if (level3Card[tmp].blackCoin - (p2.blackCoin + p2.SpecialBlackCoin) <= p2.goldCoin) {
                                            black = p2.blackCoin + p2.SpecialBlackCoin + (level3Card[tmp].blackCoin - (p2.blackCoin + p2.SpecialBlackCoin));
                                            p2.goldCoin -= level3Card[tmp].blackCoin - (p2.blackCoin + p2.SpecialBlackCoin);
                                            tmpGold1 = level3Card[tmp].blackCoin - (p2.blackCoin + p2.SpecialBlackCoin);
                                            blackSw = 3;
                                            goldCoinNum += level3Card[tmp].blackCoin - (p2.blackCoin + p2.SpecialBlackCoin);
                                            updateGold1();
                                            goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                        }
                                    }


                                    if (p2.blueCoin >= level3Card[tmp].blueCoin) {
                                        blueSw = 1;
                                    }
                                    else if (level3Card[tmp].blueCoin > 0 && (p2.blueCoin + p2.SpecialBlueCoin) >= level3Card[tmp].blueCoin) {
                                        blueSw = 2;
                                        blue = p2.blueCoin + p2.SpecialBlueCoin;
                                    }
                                    else if (level3Card[tmp].blueCoin > 0 && (p2.blueCoin + p2.SpecialBlueCoin) - level3Card[tmp].blueCoin < 0) {
                                        if (level3Card[tmp].blueCoin - (p2.blueCoin + p2.SpecialBlueCoin) <= p2.goldCoin) {
                                            blue = p2.blueCoin + p2.SpecialBlueCoin + (level3Card[tmp].blueCoin - (p2.blueCoin + p2.SpecialBlueCoin));
                                            p2.goldCoin -= level3Card[tmp].blueCoin - (p2.blueCoin + p2.SpecialBlueCoin);
                                            tmpGold2 = level3Card[tmp].blueCoin - (p2.blueCoin + p2.SpecialBlueCoin);
                                            blueSw = 3;
                                            goldCoinNum += level3Card[tmp].blueCoin - (p2.blueCoin + p2.SpecialBlueCoin);
                                            updateGold1();
                                            goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                        }
                                    }


                                    if (p2.greenCoin >= level3Card[tmp].greenCoin) {
                                        greenSw = 1;
                                    }
                                    else if (level3Card[tmp].greenCoin > 0 && (p2.greenCoin + p2.SpecialGreenCoin) >= level3Card[tmp].greenCoin) {
                                        greenSw = 2;
                                        green = p2.greenCoin + p2.SpecialGreenCoin;
                                    }
                                    else if (level3Card[tmp].greenCoin > 0 && (p2.greenCoin + p2.SpecialGreenCoin) - level3Card[tmp].greenCoin < 0) {
                                        if (level3Card[tmp].greenCoin - (p2.greenCoin + p2.SpecialGreenCoin) <= p2.goldCoin) {
                                            green = p2.greenCoin + p2.SpecialGreenCoin + (level3Card[tmp].greenCoin - (p2.greenCoin + p2.SpecialGreenCoin));
                                            p2.goldCoin -= level3Card[tmp].greenCoin - (p2.greenCoin + p2.SpecialGreenCoin);
                                            tmpGold3 = level3Card[tmp].greenCoin - (p2.greenCoin + p2.SpecialGreenCoin);
                                            greenSw = 3;
                                            goldCoinNum += level3Card[tmp].greenCoin - (p2.greenCoin + p2.SpecialGreenCoin);
                                            updateGold1();
                                            goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                        }
                                    }


                                    if (p2.redCoin >= level3Card[tmp].redCoin) {
                                        redSw = 1;
                                    }
                                    else if (level3Card[tmp].redCoin > 0 && (p2.redCoin + p2.SpecialRedCoin) >= level3Card[tmp].redCoin) {
                                        redSw = 2;
                                        red = p2.redCoin + p2.SpecialRedCoin;
                                    }
                                    else if (level3Card[tmp].redCoin > 0 && (p2.redCoin + p2.SpecialRedCoin) - level3Card[tmp].redCoin < 0) {
                                        if (level3Card[tmp].redCoin - (p2.redCoin + p2.SpecialRedCoin) <= p2.goldCoin) {
                                            red = p2.redCoin + p2.SpecialRedCoin + (level3Card[tmp].redCoin - (p2.redCoin + p2.SpecialRedCoin));
                                            p2.goldCoin -= level3Card[tmp].redCoin - (p2.redCoin + p2.SpecialRedCoin);
                                            tmpGold4 = level3Card[tmp].redCoin - (p2.redCoin + p2.SpecialRedCoin);
                                            redSw = 3;
                                            goldCoinNum += level3Card[tmp].redCoin - (p2.redCoin + p2.SpecialRedCoin);
                                            updateGold1();
                                            goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                        }
                                    }


                                    if (p2.whiteCoin >= level3Card[tmp].whiteCoin) {
                                        whiteSw = 1;
                                    }
                                    else if (level3Card[tmp].whiteCoin > 0 && (p2.whiteCoin + p2.SpecialWhiteCoin) >= level3Card[tmp].whiteCoin) {
                                        whiteSw = 2;
                                        white = p2.whiteCoin + p2.SpecialWhiteCoin;
                                    }
                                    else if (level3Card[tmp].whiteCoin > 0 && (p2.whiteCoin + p2.SpecialWhiteCoin) - level3Card[tmp].whiteCoin < 0) {
                                        if (level3Card[tmp].whiteCoin - (p2.whiteCoin + p2.SpecialWhiteCoin) <= p2.goldCoin) {
                                            white = p2.whiteCoin + p2.SpecialWhiteCoin + (level3Card[tmp].whiteCoin - (p2.whiteCoin + p2.SpecialWhiteCoin));
                                            p2.goldCoin -= level3Card[tmp].whiteCoin - (p2.whiteCoin + p2.SpecialWhiteCoin);
                                            tmpGold5 = level3Card[tmp].whiteCoin - (p2.whiteCoin + p2.SpecialWhiteCoin);
                                            whiteSw = 3;
                                            goldCoinNum += level3Card[tmp].whiteCoin - (p2.whiteCoin + p2.SpecialWhiteCoin);
                                            updateGold1();
                                            goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                        }
                                    }

                                    if (black >= level3Card[tmp].blackCoin && blue >= level3Card[tmp].blueCoin && green >= level3Card[tmp].greenCoin && red >= level3Card[tmp].redCoin && white >= level3Card[tmp].whiteCoin){
//                                        System.out.println("YESSSS PLAYER 2");
                                        isP1Turn = true;
                                        init.playerTwoHand.add(level3Card[tmp]);
//                                        System.out.println(init.level3.get(tempI).toString());
                                        init.level3.remove(tempI);
                                        switch (blackSw) {
                                            case 1 : p2.blackCoin -= level3Card[tmp].blackCoin;
                                                blackCoinNum += level3Card[tmp].blackCoin;
                                                blackCoinLabel.setText(String.valueOf(blackCoinNum));
                                                break;
                                            case 2 : p2.blackCoin -= level3Card[tmp].blackCoin - p2.SpecialBlackCoin;
                                                blackCoinNum += level3Card[tmp].blackCoin - p2.SpecialBlackCoin;
                                                blackCoinLabel.setText(String.valueOf(blackCoinNum));
                                                break;
                                            case 3 : p2.blackCoin -= level3Card[tmp].blackCoin - p2.SpecialBlackCoin - tmpGold1;
                                                blackCoinNum += level3Card[tmp].blackCoin - p2.SpecialBlackCoin - tmpGold1;
                                                blackCoinLabel.setText(String.valueOf(blackCoinNum));
                                                goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                                break;
                                        }
                                        switch (blueSw) {
                                            case 1 : p2.blueCoin -= level3Card[tmp].blueCoin;
                                                blueCoinNum += level3Card[tmp].blueCoin;
                                                blueCoinLabel.setText(String.valueOf(blueCoinNum));
                                                break;
                                            case 2 : p2.blueCoin -= level3Card[tmp].blueCoin - p2.SpecialBlueCoin;
                                                blueCoinNum += level3Card[tmp].blueCoin - p2.SpecialBlueCoin;
                                                blueCoinLabel.setText(String.valueOf(blueCoinNum));
                                                break;
                                            case 3 : p2.blueCoin -= level3Card[tmp].blueCoin - p2.SpecialBlueCoin - tmpGold1;
                                                blueCoinNum += level3Card[tmp].blueCoin - p2.SpecialBlueCoin - tmpGold1;
                                                blueCoinLabel.setText(String.valueOf(blueCoinNum));
                                                goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                                break;
                                        }
                                        switch (greenSw) {
                                            case 1 : p2.greenCoin -= level3Card[tmp].greenCoin;
                                                greenCoinNum += level3Card[tmp].greenCoin;
                                                greenCoinLabel.setText(String.valueOf(greenCoinNum));
                                                break;
                                            case 2 : p2.greenCoin -= level3Card[tmp].greenCoin - p2.SpecialGreenCoin;
                                                greenCoinNum += level3Card[tmp].greenCoin - p2.SpecialGreenCoin;
                                                greenCoinLabel.setText(String.valueOf(greenCoinNum));
                                                break;
                                            case 3 : p2.greenCoin -= level3Card[tmp].greenCoin - p2.SpecialGreenCoin - tmpGold1;
                                                greenCoinNum += level3Card[tmp].greenCoin - p2.SpecialGreenCoin - tmpGold1;
                                                greenCoinLabel.setText(String.valueOf(greenCoinNum));
                                                goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                                break;
                                        }
                                        switch (redSw) {
                                            case 1 : p2.redCoin -= level3Card[tmp].redCoin;
                                                redCoinNum += level3Card[tmp].redCoin;
                                                redCoinLabel.setText(String.valueOf(redCoinNum));
                                                break;
                                            case 2 : p2.redCoin -= level3Card[tmp].redCoin - p2.SpecialRedCoin;
                                                redCoinNum += level3Card[tmp].redCoin - p2.SpecialRedCoin;
                                                redCoinLabel.setText(String.valueOf(redCoinNum));
                                                break;
                                            case 3 : p2.redCoin -= level3Card[tmp].redCoin - p2.SpecialRedCoin - tmpGold1;
                                                redCoinNum += level3Card[tmp].redCoin - p2.SpecialRedCoin - tmpGold1;
                                                redCoinLabel.setText(String.valueOf(redCoinNum));
                                                goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                                break;
                                        }
                                        switch (whiteSw) {
                                            case 1 : p2.whiteCoin -= level3Card[tmp].whiteCoin;
                                                whiteCoinNum += level3Card[tmp].whiteCoin;
                                                whiteCoinLabel.setText(String.valueOf(whiteCoinNum));
                                                break;
                                            case 2 : p2.whiteCoin -= level3Card[tmp].whiteCoin - p2.SpecialWhiteCoin;
                                                whiteCoinNum += level3Card[tmp].whiteCoin - p2.SpecialWhiteCoin;
                                                whiteCoinLabel.setText(String.valueOf(whiteCoinNum));
                                                break;
                                            case 3 : p2.whiteCoin -= level3Card[tmp].whiteCoin - p2.SpecialWhiteCoin - tmpGold1;
                                                whiteCoinNum += level3Card[tmp].whiteCoin - p2.SpecialWhiteCoin - tmpGold1;
                                                whiteCoinLabel.setText(String.valueOf(whiteCoinNum));
                                                goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                                break;
                                        }
                                        p2.score += level3Card[tmp].point;

                                        updateScore2();

                                        updateNormalBlack2();
                                        updateNormalBlue2();
                                        updateNormalGreen2();
                                        updateNormalRed2();
                                        updateNormalWhite2();
                                        
                                        switch (level3Card[tmp].specialCoin) {
                                            case "black":
                                                p2.SpecialBlackCoin++;
                                                updateSpecialBlack2();
                                                break;
                                            case "blue":
                                                p2.SpecialBlueCoin++;
                                                updateSpecialBlue2();
                                                break;
                                            case "green":
                                                p2.SpecialGreenCoin++;
                                                updateSpecialGreen2();
                                                break;
                                            case "red":
                                                p2.SpecialRedCoin++;
                                                updateSpecialRed2();
                                                break;
                                            case "white":
                                                p2.SpecialWhiteCoin++;
                                                updateSpecialWhite2();
                                                break;
                                        }
                                        cardLabel3[tmp].removeAll();
                                        try {
                                            redrawCards3();
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
                                if (isP1Turn) {
                                    if (++p1.reserveCards <= 3) {
                                        isP1Turn = false;
                                        init.playerOneReserves.add(level3Card[tmp]);
                                        init.level3.remove(tempI);

                                        cardLabel3[tmp].removeAll();

                                        try {
                                            redrawCards3();
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }

                                        if(goldCoinNum > 0) {
                                            goldCoinNum--;
                                            p1.goldCoin++;
                                            goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                            updateGold1();
                                        }

                                        updateReserved1();

                                        if (p1.reserveCards == 1) {
                                            newJLabels1();
                                        }
                                        try {
                                            drawReserves1();
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                    }
                                }
                                else {
                                    if (++p2.reserveCards <= 3) {
                                        isP1Turn = true;
                                        init.playerTwoReserves.add(level3Card[tmp]);
                                        init.level3.remove(tempI);

                                        cardLabel3[tmp].removeAll();

                                        try {
                                            redrawCards3();
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }

                                        if(goldCoinNum > 0) {
                                            goldCoinNum--;
                                            p2.goldCoin++;
                                            goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                            updateGold2();
                                        }

                                        updateReserved2();

                                        if (p2.reserveCards == 1) {
                                            newJLabels2();
                                        }
                                        try {
                                            drawReserves2();
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                    }
                                }
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
        for (int i = 0, j = 0, k = 0; i < init.prizeClaw.size(); i++, j++, k++) {
            try {
                prizeClawCard[k] = init.prizeClaw.get(i);
                BufferedImage image = ImageIO.read(getClass().getResource(prizeClawCard[k].getPrizeClawPath()));
                prizeClawLabel[k] = new JLabel(new ImageIcon(image));
                prizeClawLabel[k].setBounds(560 + (cardWidth + 10)*j, 65 + 3 * (10 + cardHeight), cardWidth, cardHeight);
                int tmp = k;
                int tempI = i;
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
                                try {
                                    redrawPrizeClaws();
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                        });
                        buyButton.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                super.mouseClicked(e);
                                if (!isP1Turn) {
                                    if (p1.SpecialBlackCoin >= prizeClawCard[tmp].blackCoin && p1.SpecialBlueCoin >= prizeClawCard[tmp].blueCoin && p1.SpecialGreenCoin >= prizeClawCard[tmp].greenCoin && p1.SpecialRedCoin >= prizeClawCard[tmp].redCoin && p1.SpecialWhiteCoin >= prizeClawCard[tmp].whiteCoin) {
                                        init.prizeClaw.remove(tempI);
                                        p2.score += prizeClawCard[tmp].point;

                                        updateScore1();

                                        prizeClawLabel[tmp].removeAll();
                                        try {
                                            redrawPrizeClaws();
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                    }
                                }
                                else {
                                    if (p2.SpecialBlackCoin >= prizeClawCard[tmp].blackCoin && p2.SpecialBlueCoin >= prizeClawCard[tmp].blueCoin && p2.SpecialGreenCoin >= prizeClawCard[tmp].greenCoin && p2.SpecialRedCoin >= prizeClawCard[tmp].redCoin && p2.SpecialWhiteCoin >= prizeClawCard[tmp].whiteCoin) {
                                        init.prizeClaw.remove(tempI);
                                        p2.score += prizeClawCard[tmp].point;

                                        updateScore2();

                                        prizeClawLabel[tmp].removeAll();
                                        try {
                                            redrawPrizeClaws();
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                    }
                                }
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
            blackCoinLabel.setBounds(450, 700, 94, 94);
            blackCoinLabel.setText(String.valueOf(blackCoinNum));
            blackCoinLabel.setHorizontalTextPosition(JLabel.CENTER);
            blackCoinLabel.setVerticalTextPosition(JLabel.CENTER);
            blackCoinLabel.setForeground(Color.WHITE);
            blackCoinLabel.setFont(font);
            gamePanel.add(blackCoinLabel);


            BufferedImage image2 = ImageIO.read(getClass().getResource("./coins/blue coin.png"));
            blueCoinLabel = new JLabel(new ImageIcon(image2));
            blueCoinLabel.setBounds(450 + 94 + 2, 700, 94, 94);
            blueCoinLabel.setText(String.valueOf(blackCoinNum));
            blueCoinLabel.setHorizontalTextPosition(JLabel.CENTER);
            blueCoinLabel.setVerticalTextPosition(JLabel.CENTER);
            blueCoinLabel.setForeground(Color.WHITE);
            blueCoinLabel.setFont(font);
            gamePanel.add(blueCoinLabel);


            BufferedImage image3 = ImageIO.read(getClass().getResource("./coins/green coin.png"));
            greenCoinLabel = new JLabel(new ImageIcon(image3));
            greenCoinLabel.setBounds(450 + 2*94 + 4, 700, 94, 94);
            greenCoinLabel.setText(String.valueOf(greenCoinNum));
            greenCoinLabel.setHorizontalTextPosition(JLabel.CENTER);
            greenCoinLabel.setVerticalTextPosition(JLabel.CENTER);
            greenCoinLabel.setForeground(Color.WHITE);
            greenCoinLabel.setFont(font);
            gamePanel.add(greenCoinLabel);


            BufferedImage image4 = ImageIO.read(getClass().getResource("./coins/red coin.png"));
            redCoinLabel = new JLabel(new ImageIcon(image4));
            redCoinLabel.setBounds(450 + 3*94 + 6, 700, 94, 94);
            redCoinLabel.setText(String.valueOf(redCoinNum));
            redCoinLabel.setHorizontalTextPosition(JLabel.CENTER);
            redCoinLabel.setVerticalTextPosition(JLabel.CENTER);
            redCoinLabel.setForeground(Color.WHITE);
            redCoinLabel.setFont(font);
            gamePanel.add(redCoinLabel);


            BufferedImage image5 = ImageIO.read(getClass().getResource("./coins/white coin.png"));
            whiteCoinLabel = new JLabel(new ImageIcon(image5));
            whiteCoinLabel.setBounds(450 + 4*94 + 8, 700, 94, 94);
            whiteCoinLabel.setText(String.valueOf(whiteCoinNum));
            whiteCoinLabel.setHorizontalTextPosition(JLabel.CENTER);
            whiteCoinLabel.setVerticalTextPosition(JLabel.CENTER);
            whiteCoinLabel.setForeground(Color.BLACK);
            whiteCoinLabel.setFont(font);
            gamePanel.add(whiteCoinLabel);
            
            BufferedImage image6 = ImageIO.read(getClass().getResource("./coins/gold coin.png"));
            goldCoinLabel = new JLabel(new ImageIcon(image6));
            goldCoinLabel.setBounds(450 + 5*94 + 8, 700, 94, 94);
            goldCoinLabel.setText(String.valueOf(goldCoinNum));
            goldCoinLabel.setHorizontalTextPosition(JLabel.CENTER);
            goldCoinLabel.setVerticalTextPosition(JLabel.CENTER);
            goldCoinLabel.setForeground(Color.BLACK);
            goldCoinLabel.setFont(font);
            gamePanel.add(goldCoinLabel);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void drawReserves1() throws IOException {
        for (int i = 0, j = 0, k = 0; i < init.playerOneReserves.size(); i++, j++, k++) {
            try {
                reserves1[k] = init.playerOneReserves.get(i);
                System.out.println(reserves1[k].toString());
                BufferedImage image;
                switch (reserves1[k].level) {
                    case 1 : image = ImageIO.read(Objects.requireNonNull(getClass().getResource(reserves1[k].getImage1Path())));
                        reservesLabel1[k].setIcon(new ImageIcon(image));
                    break;
                    case 2 : image = ImageIO.read(Objects.requireNonNull(getClass().getResource(reserves1[k].getImage2Path())));
                        reservesLabel1[k].setIcon(new ImageIcon(image));
                        break;
                    case 3 : image = ImageIO.read(Objects.requireNonNull(getClass().getResource(reserves1[k].getImage3Path())));
                        reservesLabel1[k].setIcon(new ImageIcon(image));
                    break;
                }
                reservesLabel1[k].setBounds(50 + (cardWidth + 10) * j, 600, cardWidth, cardHeight);
                int tmp = k;
                int tempI = i;
                reservesLabel1[k].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        reservesLabel1[tmp].removeMouseListener(this);
                        reservesLabel1[tmp].setIcon(null);
                        buyButton.setFocusable(false);
                        cancelButton.setFocusable(false);
                        reservesLabel1[tmp].add(buyButton);
                        reservesLabel1[tmp].add(cancelButton);
                        reservesLabel1[tmp].setLayout(new FlowLayout());

                        cancelButton.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                super.mouseClicked(e);
                                if (isP1Turn) {
                                    isP1Turn = false;
                                    init.playerOneReserves.remove(tempI);
                                    switch (reserves1[tmp].level) {
                                        case 1 : init.level1.add(reserves1[tmp]);
                                        break;
                                        case 2 : init.level2.add(reserves1[tmp]);
                                        break;
                                        case 3 : init.level3.add(reserves1[tmp]);
                                        break;
                                    }

                                    reservesLabel1[tmp].removeAll();
                                    try {
                                        redrawReserves1();
                                        redrawCards1();
                                        redrawCards2();
                                        redrawCards3();
                                        gamePanel.repaint();
                                    } catch (IOException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                }
                            }
                        });

                        buyButton.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                super.mouseClicked(e);
                                if (isP1Turn) {
                                    int black = p1.blackCoin;
                                    int blackSw = 0;
                                    int tmpGold1 = 0;
                                    int blue = p1.blueCoin;
                                    int blueSw = 0;
                                    int tmpGold2 = 0;
                                    int green = p1.greenCoin;
                                    int greenSw = 0;
                                    int tmpGold3 = 0;
                                    int red = p1.redCoin;
                                    int redSw = 0;
                                    int tmpGold4 = 0;
                                    int white = p1.whiteCoin;
                                    int whiteSw = 0;
                                    int tmpGold5 = 0;
                                    if (p1.blackCoin >= reserves1[tmp].blackCoin) {
                                        blackSw = 1;
                                    }
                                    else if (reserves1[tmp].blackCoin > 0 && (p1.blackCoin + p1.SpecialBlackCoin) >= reserves1[tmp].blackCoin) {
                                        blackSw = 2;
                                        black = p1.blackCoin + p1.SpecialBlackCoin;
                                    }
                                    else if (reserves1[tmp].blackCoin > 0 && (p1.blackCoin + p1.SpecialBlackCoin) - reserves1[tmp].blackCoin < 0) {
                                        if (reserves1[tmp].blackCoin - (p1.blackCoin + p1.SpecialBlackCoin) <= p1.goldCoin) {
                                            black = p1.blackCoin + p1.SpecialBlackCoin + (reserves1[tmp].blackCoin - (p1.blackCoin + p1.SpecialBlackCoin));
                                            p1.goldCoin -= reserves1[tmp].blackCoin - (p1.blackCoin + p1.SpecialBlackCoin);
                                            tmpGold1 = reserves1[tmp].blackCoin - (p1.blackCoin + p1.SpecialBlackCoin);
                                            blackSw = 3;
                                            goldCoinNum += reserves1[tmp].blackCoin - (p1.blackCoin + p1.SpecialBlackCoin);
                                            updateGold1();
                                            goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                        }
                                    }


                                    if (p1.blueCoin >= reserves1[tmp].blueCoin) {
                                        blueSw = 1;
                                    }
                                    else if (reserves1[tmp].blueCoin > 0 && (p1.blueCoin + p1.SpecialBlueCoin) >= reserves1[tmp].blueCoin) {
                                        blueSw = 2;
                                        blue = p1.blueCoin + p1.SpecialBlueCoin;
                                    }
                                    else if (reserves1[tmp].blueCoin > 0 && (p1.blueCoin + p1.SpecialBlueCoin) - reserves1[tmp].blueCoin < 0) {
                                        if (reserves1[tmp].blueCoin - (p1.blueCoin + p1.SpecialBlueCoin) <= p1.goldCoin) {
                                            blue = p1.blueCoin + p1.SpecialBlueCoin + (reserves1[tmp].blueCoin - (p1.blueCoin + p1.SpecialBlueCoin));
                                            p1.goldCoin -= reserves1[tmp].blueCoin - (p1.blueCoin + p1.SpecialBlueCoin);
                                            tmpGold2 = reserves1[tmp].blueCoin - (p1.blueCoin + p1.SpecialBlueCoin);
                                            blueSw = 3;
                                            goldCoinNum += reserves1[tmp].blueCoin - (p1.blueCoin + p1.SpecialBlueCoin);
                                            updateGold1();
                                            goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                        }
                                    }


                                    if (p1.greenCoin >= reserves1[tmp].greenCoin) {
                                        greenSw = 1;
                                    }
                                    else if (reserves1[tmp].greenCoin > 0 && (p1.greenCoin + p1.SpecialGreenCoin) >= reserves1[tmp].greenCoin) {
                                        greenSw = 2;
                                        green = p1.greenCoin + p1.SpecialGreenCoin;
                                    }
                                    else if (reserves1[tmp].greenCoin > 0 && (p1.greenCoin + p1.SpecialGreenCoin) - reserves1[tmp].greenCoin < 0) {
                                        if (reserves1[tmp].greenCoin - (p1.greenCoin + p1.SpecialGreenCoin) <= p1.goldCoin) {
                                            green = p1.greenCoin + p1.SpecialGreenCoin + (reserves1[tmp].greenCoin - (p1.greenCoin + p1.SpecialGreenCoin));
                                            p1.goldCoin -= reserves1[tmp].greenCoin - (p1.greenCoin + p1.SpecialGreenCoin);
                                            tmpGold3 = reserves1[tmp].greenCoin - (p1.greenCoin + p1.SpecialGreenCoin);
                                            greenSw = 3;
                                            goldCoinNum += reserves1[tmp].greenCoin - (p1.greenCoin + p1.SpecialGreenCoin);
                                            updateGold1();
                                            goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                        }
                                    }


                                    if (p1.redCoin >= reserves1[tmp].redCoin) {
                                        redSw = 1;
                                    }
                                    else if (reserves1[tmp].redCoin > 0 && (p1.redCoin + p1.SpecialRedCoin) >= reserves1[tmp].redCoin) {
                                        redSw = 2;
                                        red = p1.redCoin + p1.SpecialRedCoin;
                                    }
                                    else if (reserves1[tmp].redCoin > 0 && (p1.redCoin + p1.SpecialRedCoin) - reserves1[tmp].redCoin < 0) {
                                        if (reserves1[tmp].redCoin - (p1.redCoin + p1.SpecialRedCoin) <= p1.goldCoin) {
                                            red = p1.redCoin + p1.SpecialRedCoin + (reserves1[tmp].redCoin - (p1.redCoin + p1.SpecialRedCoin));
                                            p1.goldCoin -= reserves1[tmp].redCoin - (p1.redCoin + p1.SpecialRedCoin);
                                            tmpGold4 = reserves1[tmp].redCoin - (p1.redCoin + p1.SpecialRedCoin);
                                            redSw = 3;
                                            goldCoinNum += reserves1[tmp].redCoin - (p1.redCoin + p1.SpecialRedCoin);
                                            updateGold1();
                                            goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                        }
                                    }


                                    if (p1.whiteCoin >= reserves1[tmp].whiteCoin) {
                                        whiteSw = 1;
                                    }
                                    else if (reserves1[tmp].whiteCoin > 0 && (p1.whiteCoin + p1.SpecialWhiteCoin) >= reserves1[tmp].whiteCoin) {
                                        whiteSw = 2;
                                        white = p1.whiteCoin + p1.SpecialWhiteCoin;
                                    }
                                    else if (reserves1[tmp].whiteCoin > 0 && (p1.whiteCoin + p1.SpecialWhiteCoin) - reserves1[tmp].whiteCoin < 0) {
                                        if (reserves1[tmp].whiteCoin - (p1.whiteCoin + p1.SpecialWhiteCoin) <= p1.goldCoin) {
                                            white = p1.whiteCoin + p1.SpecialWhiteCoin + (reserves1[tmp].whiteCoin - (p1.whiteCoin + p1.SpecialWhiteCoin));
                                            p1.goldCoin -= reserves1[tmp].whiteCoin - (p1.whiteCoin + p1.SpecialWhiteCoin);
                                            tmpGold5 = reserves1[tmp].whiteCoin - (p1.whiteCoin + p1.SpecialWhiteCoin);
                                            whiteSw = 3;
                                            goldCoinNum += reserves1[tmp].whiteCoin - (p1.whiteCoin + p1.SpecialWhiteCoin);
                                            updateGold1();
                                            goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                        }
                                    }
                                    if (black >= reserves1[tmp].blackCoin && blue >= reserves1[tmp].blueCoin && green >= reserves1[tmp].greenCoin && red >= reserves1[tmp].redCoin && white >= reserves1[tmp].whiteCoin) {
                                        System.out.println("sucessssss");
                                        //add special coins
                                        isP1Turn = false;
                                        init.playerOneHand.add(reserves1[tmp]);
//                                        System.out.println(init.reserves.get(tempI).toString());
                                        init.playerOneReserves.remove(tempI);
                                        System.out.println(init.playerOneReserves.toString());
                                        switch (blackSw) {
                                            case 1 : p1.blackCoin -= reserves1[tmp].blackCoin;
                                                blackCoinNum += reserves1[tmp].blackCoin;
                                                blackCoinLabel.setText(String.valueOf(blackCoinNum));
                                                break;
                                            case 2 : p1.blackCoin -= reserves1[tmp].blackCoin - p1.SpecialBlackCoin;
                                                blackCoinNum += reserves1[tmp].blackCoin - p1.SpecialBlackCoin;
                                                blackCoinLabel.setText(String.valueOf(blackCoinNum));
                                                break;
                                            case 3 : p1.blackCoin -= reserves1[tmp].blackCoin - p1.SpecialBlackCoin - tmpGold1;
                                                blackCoinNum += reserves1[tmp].blackCoin - p1.SpecialBlackCoin - tmpGold1;
                                                blackCoinLabel.setText(String.valueOf(blackCoinNum));
                                                goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                                break;
                                        }
                                        switch (blueSw) {
                                            case 1 : p1.blueCoin -= reserves1[tmp].blueCoin;
                                                blueCoinNum += reserves1[tmp].blueCoin;
                                                blueCoinLabel.setText(String.valueOf(blueCoinNum));
                                                break;
                                            case 2 : p1.blueCoin -= reserves1[tmp].blueCoin - p1.SpecialBlueCoin;
                                                blueCoinNum += reserves1[tmp].blueCoin - p1.SpecialBlueCoin;
                                                blueCoinLabel.setText(String.valueOf(blueCoinNum));
                                                break;
                                            case 3 : p1.blueCoin -= reserves1[tmp].blueCoin - p1.SpecialBlueCoin - tmpGold1;
                                                blueCoinNum += reserves1[tmp].blueCoin - p1.SpecialBlueCoin - tmpGold1;
                                                blueCoinLabel.setText(String.valueOf(blueCoinNum));
                                                goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                                break;
                                        }
                                        switch (greenSw) {
                                            case 1 : p1.greenCoin -= reserves1[tmp].greenCoin;
                                                greenCoinNum += reserves1[tmp].greenCoin;
                                                greenCoinLabel.setText(String.valueOf(greenCoinNum));
                                                break;
                                            case 2 : p1.greenCoin -= reserves1[tmp].greenCoin - p1.SpecialGreenCoin;
                                                greenCoinNum += reserves1[tmp].greenCoin - p1.SpecialGreenCoin;
                                                greenCoinLabel.setText(String.valueOf(greenCoinNum));
                                                break;
                                            case 3 : p1.greenCoin -= reserves1[tmp].greenCoin - p1.SpecialGreenCoin - tmpGold1;
                                                greenCoinNum += reserves1[tmp].greenCoin - p1.SpecialGreenCoin - tmpGold1;
                                                greenCoinLabel.setText(String.valueOf(greenCoinNum));
                                                goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                                break;
                                        }
                                        switch (redSw) {
                                            case 1 : p1.redCoin -= reserves1[tmp].redCoin;
                                                redCoinNum += reserves1[tmp].redCoin;
                                                redCoinLabel.setText(String.valueOf(redCoinNum));
                                                break;
                                            case 2 : p1.redCoin -= reserves1[tmp].redCoin - p1.SpecialRedCoin;
                                                redCoinNum += reserves1[tmp].redCoin - p1.SpecialRedCoin;
                                                redCoinLabel.setText(String.valueOf(redCoinNum));
                                                break;
                                            case 3 : p1.redCoin -= reserves1[tmp].redCoin - p1.SpecialRedCoin - tmpGold1;
                                                redCoinNum += reserves1[tmp].redCoin - p1.SpecialRedCoin - tmpGold1;
                                                redCoinLabel.setText(String.valueOf(redCoinNum));
                                                goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                                break;
                                        }
                                        switch (whiteSw) {
                                            case 1 : p1.whiteCoin -= reserves1[tmp].whiteCoin;
                                                whiteCoinNum += reserves1[tmp].whiteCoin;
                                                whiteCoinLabel.setText(String.valueOf(whiteCoinNum));
                                                break;
                                            case 2 : p1.whiteCoin -= reserves1[tmp].whiteCoin - p1.SpecialWhiteCoin;
                                                whiteCoinNum += reserves1[tmp].whiteCoin - p1.SpecialWhiteCoin;
                                                whiteCoinLabel.setText(String.valueOf(whiteCoinNum));
                                                break;
                                            case 3 : p1.whiteCoin -= reserves1[tmp].whiteCoin - p1.SpecialWhiteCoin - tmpGold1;
                                                whiteCoinNum += reserves1[tmp].whiteCoin - p1.SpecialWhiteCoin - tmpGold1;
                                                whiteCoinLabel.setText(String.valueOf(whiteCoinNum));
                                                goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                                break;
                                        }
                                        p1.score += reserves1[tmp].point;

                                        updateScore1();

                                        updateNormalBlack1();
                                        updateNormalBlue1();
                                        updateNormalGreen1();
                                        updateNormalRed1();
                                        updateNormalWhite1();

                                        switch (reserves1[tmp].specialCoin) {
                                            case "black":
                                                p1.SpecialBlackCoin++;
                                                updateSpecialBlack1();
                                                break;
                                            case "blue":
                                                p1.SpecialBlueCoin++;
                                                updateSpecialBlue1();
                                                break;
                                            case "green":
                                                p1.SpecialGreenCoin++;
                                                updateSpecialGreen1();
                                                break;
                                            case "red":
                                                p1.SpecialRedCoin++;
                                                updateSpecialRed1();
                                                break;
                                            case "white":
                                                p1.SpecialWhiteCoin++;
                                                updateSpecialWhite1();
                                                break;
                                        }
                                        reservesLabel1[tmp].removeAll();
                                        try {
                                            redrawReserves1();
                                            redrawCards1();
                                            redrawCards2();
                                            redrawCards3();
                                            gamePanel.repaint();
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                    }
                                }
                            }
                        });
                        reservesLabel1[tmp].addMouseListener(this);
                    }
                });
                gamePanel.add(reservesLabel1[tmp]);
                gamePanel.repaint();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void drawReserves2() throws IOException {
        for (int i = 0, j = 0, k = 0; i < init.playerTwoReserves.size(); i++, j++, k++) {
            try {
                reserves2[k] = init.playerTwoReserves.get(i);
                BufferedImage image;
                switch (reserves2[k].level) {
                    case 1 : image = ImageIO.read(Objects.requireNonNull(getClass().getResource(reserves2[k].getImage1Path())));
                        reservesLabel2[k].setIcon(new ImageIcon(image));
                        break;
                    case 2 : image = ImageIO.read(Objects.requireNonNull(getClass().getResource(reserves2[k].getImage2Path())));
                        reservesLabel2[k].setIcon(new ImageIcon(image));
                        break;
                    case 3 : image = ImageIO.read(Objects.requireNonNull(getClass().getResource(reserves2[k].getImage3Path())));
                        reservesLabel2[k].setIcon(new ImageIcon(image));
                        break;
                }
                    reservesLabel2[k].setBounds(1050 + (cardWidth + 10) * j, 600, cardWidth, cardHeight);
                    int tmp = k;
                    int tempI = i;
                    reservesLabel2[k].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            super.mouseClicked(e);
                            reservesLabel2[tmp].removeMouseListener(this);
                            reservesLabel2[tmp].setIcon(null);
                            buyButton.setFocusable(false);
                            cancelButton.setFocusable(false);
                            reservesLabel2[tmp].add(buyButton);
                            reservesLabel2[tmp].add(cancelButton);
                            reservesLabel2[tmp].setLayout(new FlowLayout());

                            cancelButton.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    super.mouseClicked(e);
                                    if (!isP1Turn) {
                                        isP1Turn = true;
                                        init.playerTwoReserves.remove(tempI);
                                        switch (reserves2[tmp].level) {
                                            case 1 : init.level1.add(reserves2[tmp]);
                                                break;
                                            case 2 : init.level2.add(reserves2[tmp]);
                                                break;
                                            case 3 : init.level3.add(reserves2[tmp]);
                                                break;
                                        }

                                        reservesLabel2[tmp].removeAll();
                                        try {
                                            redrawReserves2();
                                            redrawCards1();
                                            redrawCards2();
                                            redrawCards3();
                                            gamePanel.repaint();
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                    }
                                }
                            });

                            buyButton.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    super.mouseClicked(e);
                                    if (!isP1Turn) {
                                        int black = p2.blackCoin;
                                        int blackSw = 0;
                                        int tmpGold1 = 0;
                                        int blue = p2.blueCoin;
                                        int blueSw = 0;
                                        int tmpGold2 = 0;
                                        int green = p2.greenCoin;
                                        int greenSw = 0;
                                        int tmpGold3 = 0;
                                        int red = p2.redCoin;
                                        int redSw = 0;
                                        int tmpGold4 = 0;
                                        int white = p2.whiteCoin;
                                        int whiteSw = 0;
                                        int tmpGold5 = 0;
                                        if (p2.blackCoin >= reserves2[tmp].blackCoin) {
                                            blackSw = 1;
                                        }
                                        else if (reserves2[tmp].blackCoin > 0 && (p2.blackCoin + p2.SpecialBlackCoin) >= reserves2[tmp].blackCoin) {
                                            blackSw = 2;
                                            black = p2.blackCoin + p2.SpecialBlackCoin;
                                        }
                                        else if (reserves2[tmp].blackCoin > 0 && (p2.blackCoin + p2.SpecialBlackCoin) - reserves2[tmp].blackCoin < 0) {
                                            if (reserves2[tmp].blackCoin - (p2.blackCoin + p2.SpecialBlackCoin) <= p2.goldCoin) {
                                                black = p2.blackCoin + p2.SpecialBlackCoin + (reserves2[tmp].blackCoin - (p2.blackCoin + p2.SpecialBlackCoin));
                                                p2.goldCoin -= reserves2[tmp].blackCoin - (p2.blackCoin + p2.SpecialBlackCoin);
                                                tmpGold1 = reserves2[tmp].blackCoin - (p2.blackCoin + p2.SpecialBlackCoin);
                                                blackSw = 3;
                                                goldCoinNum += reserves2[tmp].blackCoin - (p2.blackCoin + p2.SpecialBlackCoin);
                                                updateGold1();
                                                goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                            }
                                        }


                                        if (p2.blueCoin >= reserves2[tmp].blueCoin) {
                                            blueSw = 1;
                                        }
                                        else if (reserves2[tmp].blueCoin > 0 && (p2.blueCoin + p2.SpecialBlueCoin) >= reserves2[tmp].blueCoin) {
                                            blueSw = 2;
                                            blue = p2.blueCoin + p2.SpecialBlueCoin;
                                        }
                                        else if (reserves2[tmp].blueCoin > 0 && (p2.blueCoin + p2.SpecialBlueCoin) - reserves2[tmp].blueCoin < 0) {
                                            if (reserves2[tmp].blueCoin - (p2.blueCoin + p2.SpecialBlueCoin) <= p2.goldCoin) {
                                                blue = p2.blueCoin + p2.SpecialBlueCoin + (reserves2[tmp].blueCoin - (p2.blueCoin + p2.SpecialBlueCoin));
                                                p2.goldCoin -= reserves2[tmp].blueCoin - (p2.blueCoin + p2.SpecialBlueCoin);
                                                tmpGold2 = reserves2[tmp].blueCoin - (p2.blueCoin + p2.SpecialBlueCoin);
                                                blueSw = 3;
                                                goldCoinNum += reserves2[tmp].blueCoin - (p2.blueCoin + p2.SpecialBlueCoin);
                                                updateGold1();
                                                goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                            }
                                        }


                                        if (p2.greenCoin >= reserves2[tmp].greenCoin) {
                                            greenSw = 1;
                                        }
                                        else if (reserves2[tmp].greenCoin > 0 && (p2.greenCoin + p2.SpecialGreenCoin) >= reserves2[tmp].greenCoin) {
                                            greenSw = 2;
                                            green = p2.greenCoin + p2.SpecialGreenCoin;
                                        }
                                        else if (reserves2[tmp].greenCoin > 0 && (p2.greenCoin + p2.SpecialGreenCoin) - reserves2[tmp].greenCoin < 0) {
                                            if (reserves2[tmp].greenCoin - (p2.greenCoin + p2.SpecialGreenCoin) <= p2.goldCoin) {
                                                green = p2.greenCoin + p2.SpecialGreenCoin + (reserves2[tmp].greenCoin - (p2.greenCoin + p2.SpecialGreenCoin));
                                                p2.goldCoin -= reserves2[tmp].greenCoin - (p2.greenCoin + p2.SpecialGreenCoin);
                                                tmpGold3 = reserves2[tmp].greenCoin - (p2.greenCoin + p2.SpecialGreenCoin);
                                                greenSw = 3;
                                                goldCoinNum += reserves2[tmp].greenCoin - (p2.greenCoin + p2.SpecialGreenCoin);
                                                updateGold1();
                                                goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                            }
                                        }


                                        if (p2.redCoin >= reserves2[tmp].redCoin) {
                                            redSw = 1;
                                        }
                                        else if (reserves2[tmp].redCoin > 0 && (p2.redCoin + p2.SpecialRedCoin) >= reserves2[tmp].redCoin) {
                                            redSw = 2;
                                            red = p2.redCoin + p2.SpecialRedCoin;
                                        }
                                        else if (reserves2[tmp].redCoin > 0 && (p2.redCoin + p2.SpecialRedCoin) - reserves2[tmp].redCoin < 0) {
                                            if (reserves2[tmp].redCoin - (p2.redCoin + p2.SpecialRedCoin) <= p2.goldCoin) {
                                                red = p2.redCoin + p2.SpecialRedCoin + (reserves2[tmp].redCoin - (p2.redCoin + p2.SpecialRedCoin));
                                                p2.goldCoin -= reserves2[tmp].redCoin - (p2.redCoin + p2.SpecialRedCoin);
                                                tmpGold4 = reserves2[tmp].redCoin - (p2.redCoin + p2.SpecialRedCoin);
                                                redSw = 3;
                                                goldCoinNum += reserves2[tmp].redCoin - (p2.redCoin + p2.SpecialRedCoin);
                                                updateGold1();
                                                goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                            }
                                        }


                                        if (p2.whiteCoin >= reserves2[tmp].whiteCoin) {
                                            whiteSw = 1;
                                        }
                                        else if (reserves2[tmp].whiteCoin > 0 && (p2.whiteCoin + p2.SpecialWhiteCoin) >= reserves2[tmp].whiteCoin) {
                                            whiteSw = 2;
                                            white = p2.whiteCoin + p2.SpecialWhiteCoin;
                                        }
                                        else if (reserves2[tmp].whiteCoin > 0 && (p2.whiteCoin + p2.SpecialWhiteCoin) - reserves2[tmp].whiteCoin < 0) {
                                            if (reserves2[tmp].whiteCoin - (p2.whiteCoin + p2.SpecialWhiteCoin) <= p2.goldCoin) {
                                                white = p2.whiteCoin + p2.SpecialWhiteCoin + (reserves2[tmp].whiteCoin - (p2.whiteCoin + p2.SpecialWhiteCoin));
                                                p2.goldCoin -= reserves2[tmp].whiteCoin - (p2.whiteCoin + p2.SpecialWhiteCoin);
                                                tmpGold5 = reserves2[tmp].whiteCoin - (p2.whiteCoin + p2.SpecialWhiteCoin);
                                                whiteSw = 3;
                                                goldCoinNum += reserves2[tmp].whiteCoin - (p2.whiteCoin + p2.SpecialWhiteCoin);
                                                updateGold1();
                                                goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                            }
                                        }
                                        if (black >= reserves2[tmp].blackCoin && blue >= reserves2[tmp].blueCoin && green >= reserves2[tmp].greenCoin && red >= reserves2[tmp].redCoin && white >= reserves2[tmp].whiteCoin) {
//                                            System.out.println("YESSSS PLAYER 2");
                                            isP1Turn = true;
                                            init.playerTwoHand.add(reserves2[tmp]);
                                            System.out.println(init.level3.get(tempI).toString());
                                            init.playerTwoReserves.remove(tempI);
                                            switch (blackSw) {
                                                case 1 : p2.blackCoin -= reserves2[tmp].blackCoin;
                                                    blackCoinNum += reserves2[tmp].blackCoin;
                                                    blackCoinLabel.setText(String.valueOf(blackCoinNum));
                                                    break;
                                                case 2 : p2.blackCoin -= reserves2[tmp].blackCoin - p2.SpecialBlackCoin;
                                                    blackCoinNum += reserves2[tmp].blackCoin - p2.SpecialBlackCoin;
                                                    blackCoinLabel.setText(String.valueOf(blackCoinNum));
                                                    break;
                                                case 3 : p2.blackCoin -= reserves2[tmp].blackCoin - p2.SpecialBlackCoin - tmpGold1;
                                                    blackCoinNum += reserves2[tmp].blackCoin - p2.SpecialBlackCoin - tmpGold1;
                                                    blackCoinLabel.setText(String.valueOf(blackCoinNum));
                                                    goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                                    break;
                                            }
                                            switch (blueSw) {
                                                case 1 : p2.blueCoin -= reserves2[tmp].blueCoin;
                                                    blueCoinNum += reserves2[tmp].blueCoin;
                                                    blueCoinLabel.setText(String.valueOf(blueCoinNum));
                                                    break;
                                                case 2 : p2.blueCoin -= reserves2[tmp].blueCoin - p2.SpecialBlueCoin;
                                                    blueCoinNum += reserves2[tmp].blueCoin - p2.SpecialBlueCoin;
                                                    blueCoinLabel.setText(String.valueOf(blueCoinNum));
                                                    break;
                                                case 3 : p2.blueCoin -= reserves2[tmp].blueCoin - p2.SpecialBlueCoin - tmpGold1;
                                                    blueCoinNum += reserves2[tmp].blueCoin - p2.SpecialBlueCoin - tmpGold1;
                                                    blueCoinLabel.setText(String.valueOf(blueCoinNum));
                                                    goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                                    break;
                                            }
                                            switch (greenSw) {
                                                case 1 : p2.greenCoin -= reserves2[tmp].greenCoin;
                                                    greenCoinNum += reserves2[tmp].greenCoin;
                                                    greenCoinLabel.setText(String.valueOf(greenCoinNum));
                                                    break;
                                                case 2 : p2.greenCoin -= reserves2[tmp].greenCoin - p2.SpecialGreenCoin;
                                                    greenCoinNum += reserves2[tmp].greenCoin - p2.SpecialGreenCoin;
                                                    greenCoinLabel.setText(String.valueOf(greenCoinNum));
                                                    break;
                                                case 3 : p2.greenCoin -= reserves2[tmp].greenCoin - p2.SpecialGreenCoin - tmpGold1;
                                                    greenCoinNum += reserves2[tmp].greenCoin - p2.SpecialGreenCoin - tmpGold1;
                                                    greenCoinLabel.setText(String.valueOf(greenCoinNum));
                                                    goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                                    break;
                                            }
                                            switch (redSw) {
                                                case 1 : p2.redCoin -= reserves2[tmp].redCoin;
                                                    redCoinNum += reserves2[tmp].redCoin;
                                                    redCoinLabel.setText(String.valueOf(redCoinNum));
                                                    break;
                                                case 2 : p2.redCoin -= reserves2[tmp].redCoin - p2.SpecialRedCoin;
                                                    redCoinNum += reserves2[tmp].redCoin - p2.SpecialRedCoin;
                                                    redCoinLabel.setText(String.valueOf(redCoinNum));
                                                    break;
                                                case 3 : p2.redCoin -= reserves2[tmp].redCoin - p2.SpecialRedCoin - tmpGold1;
                                                    redCoinNum += reserves2[tmp].redCoin - p2.SpecialRedCoin - tmpGold1;
                                                    redCoinLabel.setText(String.valueOf(redCoinNum));
                                                    goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                                    break;
                                            }
                                            switch (whiteSw) {
                                                case 1 : p2.whiteCoin -= reserves2[tmp].whiteCoin;
                                                    whiteCoinNum += reserves2[tmp].whiteCoin;
                                                    whiteCoinLabel.setText(String.valueOf(whiteCoinNum));
                                                    break;
                                                case 2 : p2.whiteCoin -= reserves2[tmp].whiteCoin - p2.SpecialWhiteCoin;
                                                    whiteCoinNum += reserves2[tmp].whiteCoin - p2.SpecialWhiteCoin;
                                                    whiteCoinLabel.setText(String.valueOf(whiteCoinNum));
                                                    break;
                                                case 3 : p2.whiteCoin -= reserves2[tmp].whiteCoin - p2.SpecialWhiteCoin - tmpGold1;
                                                    whiteCoinNum += reserves2[tmp].whiteCoin - p2.SpecialWhiteCoin - tmpGold1;
                                                    whiteCoinLabel.setText(String.valueOf(whiteCoinNum));
                                                    goldCoinLabel.setText(String.valueOf(goldCoinNum));
                                                    break;
                                            }
                                            p2.score += reserves2[tmp].point;

                                            updateScore2();

                                            updateNormalBlack2();
                                            updateNormalBlue2();
                                            updateNormalGreen2();
                                            updateNormalRed2();
                                            updateNormalWhite2();

                                            switch (reserves2[tmp].specialCoin) {
                                                case "black":
                                                    p2.SpecialBlackCoin++;
                                                    updateSpecialBlack2();
                                                    break;
                                                case "blue":
                                                    p2.SpecialBlueCoin++;
                                                    updateSpecialBlue2();
                                                    break;
                                                case "green":
                                                    p2.SpecialGreenCoin++;
                                                    updateSpecialGreen2();
                                                    break;
                                                case "red":
                                                    p2.SpecialRedCoin++;
                                                    updateSpecialRed2();
                                                    break;
                                                case "white":
                                                    p2.SpecialWhiteCoin++;
                                                    updateSpecialWhite2();
                                                    break;
                                            }
                                            reservesLabel2[tmp].removeAll();
                                            try {
                                                redrawReserves2();
                                                redrawCards1();
                                                redrawCards2();
                                                redrawCards3();
                                                gamePanel.repaint();
                                            } catch (IOException ex) {
                                                throw new RuntimeException(ex);
                                            }
                                        }
                                    }
                                }
                            });
                            reservesLabel2[tmp].addMouseListener(this);
                        }
                    });
                gamePanel.add(reservesLabel2[tmp]);
                gamePanel.repaint();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void redrawCards1() throws IOException {
        for (int i = 0, j = 0, k = 0; i < 4; i++, j++, k++) {
            try {
                level1Card[k] = init.level1.get(i);
                BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResource(level1Card[k].getImage1Path())));
                cardLabel1[k].setIcon(new ImageIcon(image));
                cardLabel1[k].setBounds(510 + (cardWidth + 10)*j, 65, cardWidth, cardHeight);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    } 
    public void redrawCards2() throws IOException {
        for (int i = 0, j = 0, k = 0; i < 4; i++, j++, k++) {
            try {
                level2Card[k] = init.level2.get(i);
                BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResource(level2Card[k].getImage2Path())));
                cardLabel2[k].setIcon(new ImageIcon(image));
                cardLabel2[k].setBounds(510 + (cardWidth + 10)*j, 65 + 10 + cardHeight, cardWidth, cardHeight);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    } 
    public void redrawCards3() throws IOException {
        for (int i = 0, j = 0, k = 0; i < 4; i++, j++, k++) {
            try {
                level3Card[k] = init.level3.get(i);
                BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResource(level3Card[k].getImage3Path())));
                cardLabel3[k].setIcon(new ImageIcon(image));
                cardLabel3[k].setBounds(510 + (cardWidth + 10)*j, 65 + 2 * (10 + cardHeight), cardWidth, cardHeight);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void redrawPrizeClaws() throws IOException {
        for (int i = 0, j = 0, k = 0; i < init.prizeClaw.size(); i++, j++, k++) {
            try {
                prizeClawCard[k] = init.prizeClaw.get(i);
                BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResource(prizeClawCard[k].getPrizeClawPath())));
                prizeClawLabel[k].setIcon(new ImageIcon(image));
                prizeClawLabel[k].setBounds(560 + (cardWidth + 10) * j, 65 + 3 * (10 + cardHeight), cardWidth, cardHeight);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void redrawReserves1() throws IOException {
        for (int i = 0, j = 0, k = 0; i < init.playerOneReserves.size(); i++, j++, k++) {
            try {
                    reserves1[k] = init.playerOneReserves.get(i);
                    System.out.println(reserves1[k].toString());
                    BufferedImage image;
                    switch (reserves1[k].level) {
                        case 1 : image = ImageIO.read(Objects.requireNonNull(getClass().getResource(reserves1[k].getImage1Path())));
                            reservesLabel1[k].setIcon(new ImageIcon(image));
                            break;
                        case 2 : image = ImageIO.read(Objects.requireNonNull(getClass().getResource(reserves1[k].getImage2Path())));
                            reservesLabel1[k].setIcon(new ImageIcon(image));
                            break;
                        case 3 : image = ImageIO.read(Objects.requireNonNull(getClass().getResource(reserves1[k].getImage3Path())));
                            reservesLabel1[k].setIcon(new ImageIcon(image));
                            break;
                    }
                    reservesLabel1[k].setBounds(50 + (cardWidth + 10) * j, 600, cardWidth, cardHeight);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }  
    public void redrawReserves2() throws IOException {
        for (int i = 0, j = 0, k = 0; i < init.playerTwoReserves.size(); i++, j++, k++) {
            try {
                if (reserves2[k] != null) {
                reserves2[k] = init.playerTwoReserves.get(i);
                System.out.println(reserves2[k].toString());
                    BufferedImage image;
                    switch (reserves2[k].level) {
                        case 1 : image = ImageIO.read(Objects.requireNonNull(getClass().getResource(reserves2[k].getImage1Path())));
                            reservesLabel2[k].setIcon(new ImageIcon(image));
                            break;
                        case 2 : image = ImageIO.read(Objects.requireNonNull(getClass().getResource(reserves2[k].getImage2Path())));
                            reservesLabel2[k].setIcon(new ImageIcon(image));
                            break;
                        case 3 : image = ImageIO.read(Objects.requireNonNull(getClass().getResource(reserves2[k].getImage3Path())));
                            reservesLabel2[k].setIcon(new ImageIcon(image));
                            break;
                    }
                reservesLabel2[k].setBounds(1050 + (cardWidth + 10) * j, 600, cardWidth, cardHeight);
                }
                else break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void drawScoreboard1() {
        player1Label.setOpaque(true);
        player1Label.setBackground(Color.RED);
        player1Label.setBounds(120, 60, 300, 40);
        player1Label.setText("Player One");
        player1Label.setHorizontalAlignment(JLabel.CENTER);
        player1Label.setFont(font);
        player1Label.setForeground(Color.WHITE);
        gamePanel.add(player1Label);


        p1Score.setOpaque(true);
        p1Score.setBackground(Color.PINK);
        p1Score.setBounds(120, 100, 150, 40);
        p1Score.setText("Score:    " + p1.score);
        p1Score.setHorizontalAlignment(JLabel.CENTER);
        p1Score.setFont(new Font("Arial", Font.BOLD, 15));
        gamePanel.add(p1Score);


        p1Reserved.setOpaque(true);
        p1Reserved.setBackground(Color.CYAN);
        p1Reserved.setBounds(270, 100, 150, 40);
        p1Reserved.setText("Reserved:   " + p1.reserveCards);
        p1Reserved.setHorizontalAlignment(JLabel.CENTER);
        p1Reserved.setFont(new Font("Arial", Font.BOLD, 15));
        gamePanel.add(p1Reserved);


        p1NormalBlack.setOpaque(true);
        p1NormalBlack.setBackground(Color.BLACK);
        p1NormalBlack.setBounds(120, 140, 150, 40);
        p1NormalBlack.setText("Normal Black:   " + p1.blackCoin);
        p1NormalBlack.setForeground(Color.WHITE);
        p1NormalBlack.setHorizontalAlignment(JLabel.CENTER);
        p1NormalBlack.setFont(new Font("Arial", Font.BOLD, 15));
        gamePanel.add(p1NormalBlack);
        p1SpecialBlack.setOpaque(true);
        p1SpecialBlack.setBackground(Color.BLACK);
        p1SpecialBlack.setBounds(270, 140, 150, 40);
        p1SpecialBlack.setText("Special Black:   " + p1.SpecialBlackCoin);
        p1SpecialBlack.setForeground(Color.WHITE);
        p1SpecialBlack.setHorizontalAlignment(JLabel.CENTER);
        p1SpecialBlack.setFont(new Font("Arial", Font.BOLD, 15));
        gamePanel.add(p1SpecialBlack);


        p1NormalBlue.setOpaque(true);
        p1NormalBlue.setBackground(Color.BLUE);
        p1NormalBlue.setBounds(120, 180, 150, 40);
        p1NormalBlue.setText("Normal Blue:   " + p1.blueCoin);
        p1NormalBlue.setForeground(Color.WHITE);
        p1NormalBlue.setHorizontalAlignment(JLabel.CENTER);
        p1NormalBlue.setFont(new Font("Arial", Font.BOLD, 15));
        gamePanel.add(p1NormalBlue);
        p1SpecialBlue.setOpaque(true);
        p1SpecialBlue.setBackground(Color.BLUE);
        p1SpecialBlue.setBounds(270, 180, 150, 40);
        p1SpecialBlue.setText("Special Blue:   " + p1.SpecialBlueCoin);
        p1SpecialBlue.setForeground(Color.WHITE);
        p1SpecialBlue.setHorizontalAlignment(JLabel.CENTER);
        p1SpecialBlue.setFont(new Font("Arial", Font.BOLD, 15));
        gamePanel.add(p1SpecialBlue);


        p1NormalGreen.setOpaque(true);
        p1NormalGreen.setBackground(Color.GREEN);
        p1NormalGreen.setBounds(120, 220, 150, 40);
        p1NormalGreen.setText("Normal Green:   " + p1.greenCoin);
        p1NormalGreen.setForeground(Color.WHITE);
        p1NormalGreen.setHorizontalAlignment(JLabel.CENTER);
        p1NormalGreen.setFont(new Font("Arial", Font.BOLD, 15));
        gamePanel.add(p1NormalGreen);
        p1SpecialGreen.setOpaque(true);
        p1SpecialGreen.setBackground(Color.GREEN);
        p1SpecialGreen.setBounds(270, 220, 150, 40);
        p1SpecialGreen.setText("Special Green:   " + p1.SpecialGreenCoin);
        p1SpecialGreen.setForeground(Color.WHITE);
        p1SpecialGreen.setHorizontalAlignment(JLabel.CENTER);
        p1SpecialGreen.setFont(new Font("Arial", Font.BOLD, 15));
        gamePanel.add(p1SpecialGreen);


        p1NormalRed.setOpaque(true);
        p1NormalRed.setBackground(Color.RED);
        p1NormalRed.setBounds(120, 260, 150, 40);
        p1NormalRed.setText("Normal Red:   " + p1.redCoin);
        p1NormalRed.setForeground(Color.WHITE);
        p1NormalRed.setHorizontalAlignment(JLabel.CENTER);
        p1NormalRed.setFont(new Font("Arial", Font.BOLD, 15));
        gamePanel.add(p1NormalRed);
        p1SpecialRed.setOpaque(true);
        p1SpecialRed.setBackground(Color.RED);
        p1SpecialRed.setBounds(270, 260, 150, 40);
        p1SpecialRed.setText("Special Red:   " + p1.SpecialRedCoin);
        p1SpecialRed.setForeground(Color.WHITE);
        p1SpecialRed.setHorizontalAlignment(JLabel.CENTER);
        p1SpecialRed.setFont(new Font("Arial", Font.BOLD, 15));
        gamePanel.add(p1SpecialRed);


        p1NormalWhite.setOpaque(true);
        p1NormalWhite.setBackground(Color.WHITE);
        p1NormalWhite.setBounds(120, 300, 150, 40);
        p1NormalWhite.setText("Normal White:   " + p1.whiteCoin);
        p1NormalWhite.setForeground(Color.BLACK);
        p1NormalWhite.setHorizontalAlignment(JLabel.CENTER);
        p1NormalWhite.setFont(new Font("Arial", Font.BOLD, 15));
        gamePanel.add(p1NormalWhite);
        p1SpecialWhite.setOpaque(true);
        p1SpecialWhite.setBackground(Color.WHITE);
        p1SpecialWhite.setBounds(270, 300, 150, 40);
        p1SpecialWhite.setText("Special White:   " + p1.SpecialWhiteCoin);
        p1SpecialWhite.setForeground(Color.BLACK);
        p1SpecialWhite.setHorizontalAlignment(JLabel.CENTER);
        p1SpecialWhite.setFont(new Font("Arial", Font.BOLD, 15));
        gamePanel.add(p1SpecialWhite);

        p1Gold.setOpaque(true);
        p1Gold.setBackground(Color.ORANGE);
        p1Gold.setBounds(120, 340, 300, 40);
        p1Gold.setText("Gold Coins:   " + p1.goldCoin);
        p1Gold.setHorizontalAlignment(JLabel.CENTER);
        p1Gold.setFont(new Font("Arial", Font.BOLD, 15));
        p1Gold.setForeground(Color.BLACK);
        gamePanel.add(p1Gold);
    }
    public void drawScoreboard2() {
        player2Label.setOpaque(true);
        player2Label.setBackground(Color.RED);
        player2Label.setBounds(1100, 60, 300, 40);
        player2Label.setText("Player Two");
        player2Label.setHorizontalAlignment(JLabel.CENTER);
        player2Label.setFont(font);
        player2Label.setForeground(Color.WHITE);
        gamePanel.add(player2Label);


        p2Score.setOpaque(true);
        p2Score.setBackground(Color.PINK);
        p2Score.setBounds(1100, 100, 150, 40);
        p2Score.setText("Score:    " + p2.score);
        p2Score.setHorizontalAlignment(JLabel.CENTER);
        p2Score.setFont(new Font("Arial", Font.BOLD, 15));
        gamePanel.add(p2Score);


        p2Reserved.setOpaque(true);
        p2Reserved.setBackground(Color.CYAN);
        p2Reserved.setBounds(1250, 100, 150, 40);
        p2Reserved.setText("Reserved:   " + p2.reserveCards);
        p2Reserved.setHorizontalAlignment(JLabel.CENTER);
        p2Reserved.setFont(new Font("Arial", Font.BOLD, 15));
        gamePanel.add(p2Reserved);


        p2NormalBlack.setOpaque(true);
        p2NormalBlack.setBackground(Color.BLACK);
        p2NormalBlack.setBounds(1100, 140, 150, 40);
        p2NormalBlack.setText("Normal Black:   " + p2.blackCoin);
        p2NormalBlack.setForeground(Color.WHITE);
        p2NormalBlack.setHorizontalAlignment(JLabel.CENTER);
        p2NormalBlack.setFont(new Font("Arial", Font.BOLD, 15));
        gamePanel.add(p2NormalBlack);
        p2SpecialBlack.setOpaque(true);
        p2SpecialBlack.setBackground(Color.BLACK);
        p2SpecialBlack.setBounds(1250, 140, 150, 40);
        p2SpecialBlack.setText("Special Black:   " + p2.SpecialBlackCoin);
        p2SpecialBlack.setForeground(Color.WHITE);
        p2SpecialBlack.setHorizontalAlignment(JLabel.CENTER);
        p2SpecialBlack.setFont(new Font("Arial", Font.BOLD, 15));
        gamePanel.add(p2SpecialBlack);


        p2NormalBlue.setOpaque(true);
        p2NormalBlue.setBackground(Color.BLUE);
        p2NormalBlue.setBounds(1100, 180, 150, 40);
        p2NormalBlue.setText("Normal Blue:   " + p2.blueCoin);
        p2NormalBlue.setForeground(Color.WHITE);
        p2NormalBlue.setHorizontalAlignment(JLabel.CENTER);
        p2NormalBlue.setFont(new Font("Arial", Font.BOLD, 15));
        gamePanel.add(p2NormalBlue);
        p2SpecialBlue.setOpaque(true);
        p2SpecialBlue.setBackground(Color.BLUE);
        p2SpecialBlue.setBounds(1250, 180, 150, 40);
        p2SpecialBlue.setText("Special Blue:   " + p2.SpecialBlueCoin);
        p2SpecialBlue.setForeground(Color.WHITE);
        p2SpecialBlue.setHorizontalAlignment(JLabel.CENTER);
        p2SpecialBlue.setFont(new Font("Arial", Font.BOLD, 15));
        gamePanel.add(p2SpecialBlue);


        p2NormalGreen.setOpaque(true);
        p2NormalGreen.setBackground(Color.GREEN);
        p2NormalGreen.setBounds(1100, 220, 150, 40);
        p2NormalGreen.setText("Normal Green:   " + p2.greenCoin);
        p2NormalGreen.setForeground(Color.WHITE);
        p2NormalGreen.setHorizontalAlignment(JLabel.CENTER);
        p2NormalGreen.setFont(new Font("Arial", Font.BOLD, 15));
        gamePanel.add(p2NormalGreen);
        p2SpecialGreen.setOpaque(true);
        p2SpecialGreen.setBackground(Color.GREEN);
        p2SpecialGreen.setBounds(1250, 220, 150, 40);
        p2SpecialGreen.setText("Special Green:   " + p2.SpecialGreenCoin);
        p2SpecialGreen.setForeground(Color.WHITE);
        p2SpecialGreen.setHorizontalAlignment(JLabel.CENTER);
        p2SpecialGreen.setFont(new Font("Arial", Font.BOLD, 15));
        gamePanel.add(p2SpecialGreen);


        p2NormalRed.setOpaque(true);
        p2NormalRed.setBackground(Color.RED);
        p2NormalRed.setBounds(1100, 260, 150, 40);
        p2NormalRed.setText("Normal Red:   " + p2.redCoin);
        p2NormalRed.setForeground(Color.WHITE);
        p2NormalRed.setHorizontalAlignment(JLabel.CENTER);
        p2NormalRed.setFont(new Font("Arial", Font.BOLD, 15));
        gamePanel.add(p2NormalRed);
        p2SpecialRed.setOpaque(true);
        p2SpecialRed.setBackground(Color.RED);
        p2SpecialRed.setBounds(1250, 260, 150, 40);
        p2SpecialRed.setText("Special Red:   " + p2.SpecialRedCoin);
        p2SpecialRed.setForeground(Color.WHITE);
        p2SpecialRed.setHorizontalAlignment(JLabel.CENTER);
        p2SpecialRed.setFont(new Font("Arial", Font.BOLD, 15));
        gamePanel.add(p2SpecialRed);


        p2NormalWhite.setOpaque(true);
        p2NormalWhite.setBackground(Color.WHITE);
        p2NormalWhite.setBounds(1100, 300, 150, 40);
        p2NormalWhite.setText("Normal White:   " + p2.whiteCoin);
        p2NormalWhite.setForeground(Color.BLACK);
        p2NormalWhite.setHorizontalAlignment(JLabel.CENTER);
        p2NormalWhite.setFont(new Font("Arial", Font.BOLD, 15));
        gamePanel.add(p2NormalWhite);
        p2SpecialWhite.setOpaque(true);
        p2SpecialWhite.setBackground(Color.WHITE);
        p2SpecialWhite.setBounds(1250, 300, 150, 40);
        p2SpecialWhite.setText("Special White:   " + p2.SpecialWhiteCoin);
        p2SpecialWhite.setForeground(Color.BLACK);
        p2SpecialWhite.setHorizontalAlignment(JLabel.CENTER);
        p2SpecialWhite.setFont(new Font("Arial", Font.BOLD, 15));
        gamePanel.add(p2SpecialWhite);

        p2Gold.setOpaque(true);
        p2Gold.setBackground(Color.ORANGE);
        p2Gold.setBounds(1100, 340, 300, 40);
        p2Gold.setText("Gold Coins:   " + p2.goldCoin);
        p2Gold.setHorizontalAlignment(JLabel.CENTER);
        p2Gold.setFont(new Font("Arial", Font.BOLD, 15));
        p2Gold.setForeground(Color.BLACK);
        gamePanel.add(p2Gold);
    }
    public void updateNormalBlack1() {
        p1NormalBlack.setText("Normal Black:   " + p1.blackCoin);
    }
    public void updateSpecialBlack1() {
        p1SpecialBlack.setText("Special Black:   " + p1.SpecialBlackCoin);
    }
    public void updateNormalBlue1() {
        p1NormalBlue.setText("Normal Blue:   " + p1.blueCoin);
    }
    public void updateSpecialBlue1() {
        p1SpecialBlue.setText("Special Blue:   " + p1.SpecialBlueCoin);
    }
    public void updateNormalGreen1() {
        p1NormalGreen.setText("Normal Green:   " + p1.greenCoin);
    }
    public void updateSpecialGreen1() {
        p1SpecialGreen.setText("Special Green:   " + p1.SpecialGreenCoin);
    }
    public void updateNormalRed1() {
        p1NormalRed.setText("Normal Red:   " + p1.redCoin);
    }
    public void updateSpecialRed1() {
        p1SpecialRed.setText("Special Red:   " + p1.SpecialRedCoin);
    }
    public void updateNormalWhite1() {
        p1NormalWhite.setText("Normal White:   " + p1.whiteCoin);
    }
    public void updateSpecialWhite1() {
        p1SpecialWhite.setText("Special White:   " + p1.SpecialWhiteCoin);
    }    public void updateNormalBlack2() {
        p2NormalBlack.setText("Normal Black:   " + p2.blackCoin);
    }
    public void updateSpecialBlack2() {
        p2SpecialBlack.setText("Special Black:   " + p2.SpecialBlackCoin);
    }
    public void updateNormalBlue2() {
        p2NormalBlue.setText("Normal Blue:   " + p2.blueCoin);
    }
    public void updateSpecialBlue2() {
        p2SpecialBlue.setText("Special Blue:   " + p2.SpecialBlueCoin);
    }
    public void updateNormalGreen2() {
        p2NormalGreen.setText("Normal Green:   " + p2.greenCoin);
    }
    public void updateSpecialGreen2() {
        p2SpecialGreen.setText("Special Green:   " + p2.SpecialGreenCoin);
    }
    public void updateNormalRed2() {
        p2NormalRed.setText("Normal Red:   " + p2.redCoin);
    }
    public void updateSpecialRed2() {
        p2SpecialRed.setText("Special Red:   " + p2.SpecialRedCoin);
    }
    public void updateNormalWhite2() {
        p2NormalWhite.setText("Normal White:   " + p2.whiteCoin);
    }
    public void updateSpecialWhite2() {
        p2SpecialWhite.setText("Special White:   " + p2.SpecialWhiteCoin);
    }
    public void updateScore1() {
        p1Score.setText("Score:    " + p1.score);
        if (p1.score >= 1) drawEndGame1();
    }
    public void updateScore2() {
        p2Score.setText("Score:    " + p2.score);
        if (p2.score >= 1) drawEndGame2();
    }
    public void updateReserved1() {
        p1Reserved.setText("Reserved:   " + p1.reserveCards);
    }
    public void updateReserved2() {
        p2Reserved.setText("Reserved:   " + p2.reserveCards);
    }
    public void updateGold1() {
        p1Gold.setText("Gold Coins:   " + p1.goldCoin);
    }
    public void updateGold2() {
        p2Gold.setText("Gold Coins:   " + p2.goldCoin);
    }
    public void newJLabels1() {
        for (int i = 0; i < 3; i++) {
            reservesLabel1[i] = new JLabel();
        }
    }
    public void newJLabels2() {
        for (int i = 0; i < 3; i++) {
            reservesLabel2[i] = new JLabel();
        }
    }
    public void drawEndGame1() {
        endGame1.setOpaque(true);
        endGame1.setBackground(Color.RED);
        endGame1.setBounds(80, 400, 400, 120);
        endGame1.setText("Player one is the winner!");
        endGame1.setHorizontalAlignment(JLabel.CENTER);
        endGame1.setFont(font);
        endGame1.setForeground(Color.WHITE);
        gamePanel.add(endGame1);
        gamePanel.repaint();
    }
    public void drawEndGame2() {
        endGame2.setOpaque(true);
        endGame2.setBackground(Color.RED);
        endGame2.setBounds(80, 400, 400, 120);
        endGame2.setText("Player two is the winner!");
        endGame2.setHorizontalAlignment(JLabel.CENTER);
        endGame2.setFont(font);
        endGame2.setForeground(Color.WHITE);
        gamePanel.add(endGame2);
        gamePanel.repaint();
    }
    
}
