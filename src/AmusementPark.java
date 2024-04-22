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
    Player p1;
    Player p2;
    boolean isP1Turn = true;
    Card level1Card;
    Card level2Card;
    Card level3Card;
    PrizeClaw prizeClawCard;
    int cardWidth = 108; //ratio should 1/1.4
    int cardHeight = 144;
    JLabel blackCoinLabel;
    JLabel blueCoinLabel;
    JLabel redCoinLabel;
    JLabel goldCoinLabel;
    JLabel whiteCoinLabel;

    AmusementPark() throws IOException {
        startGame();

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        //frame.setSize(500,300);
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

                buttonPanel.add(radioPanel);

                frame.revalidate();
                frame.repaint();
            }
        });
    }
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
                            if (isP1Turn) {
                                if (blackBox.isSelected()) {
                                    if (blueBox.isSelected()) {
                                        if (greenBox.isSelected()) {
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
                                        }
                                        if (redBox.isSelected()) {
                                            Coin blackC = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                            init.player1Black.add(blackC);
                                            p1.blackCoin++;
                                            Coin blueC = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                            init.player1Blue.add(blueC);
                                            p1.blueCoin++;
                                            Coin redC = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                            init.player1Red.add(redC);
                                            p1.redCoin++;
                                        }
                                        if (whiteBox.isSelected()) {
                                            Coin blackC = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                            init.player1Black.add(blackC);
                                            p1.blackCoin++;
                                            Coin blueC = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                            init.player1Blue.add(blueC);
                                            p1.blueCoin++;
                                            Coin whiteC = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                            init.player1White.add(whiteC);
                                            p1.whiteCoin++;
                                        }
                                    }
                                    if (greenBox.isSelected()) {
                                        if (redBox.isSelected()) {
                                            Coin blackC = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                            init.player1Black.add(blackC);
                                            p1.blackCoin++;
                                            Coin greenC = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                            init.player1Green.add(greenC);
                                            p1.greenCoin++;
                                            Coin redC = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                            init.player1Red.add(redC);
                                            p1.redCoin++;
                                        }
                                        if (whiteBox.isSelected()) {
                                            Coin blackC = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                            init.player1Black.add(blackC);
                                            p1.blackCoin++;
                                            Coin greenC = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                            init.player1Green.add(greenC);
                                            p1.greenCoin++;
                                            Coin whiteC = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                            init.player1White.add(whiteC);
                                            p1.whiteCoin++;
                                        }
                                    }
                                    if (redBox.isSelected()) {
                                        if (whiteBox.isSelected()) {
                                            Coin blackC = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                            init.player1Black.add(blackC);
                                            p1.blackCoin++;
                                            Coin redC = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                            init.player1Red.add(redC);
                                            p1.redCoin++;
                                            Coin whiteC = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                            init.player1White.add(whiteC);
                                            p1.whiteCoin++;
                                        }
                                    }
                                }
                                if (blueBox.isSelected()) {
                                    if (greenBox.isSelected()) {
                                        if (redBox.isSelected()) {
                                            Coin blueC = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                            init.player1Blue.add(blueC);
                                            p1.blueCoin++;
                                            Coin greenC = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                            init.player1Green.add(greenC);
                                            p1.greenCoin++;
                                            Coin redC = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                            init.player1Red.add(redC);
                                            p1.redCoin++;
                                        }
                                        if (whiteBox.isSelected()) {
                                            Coin blueC = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                            init.player1Blue.add(blueC);
                                            p1.blueCoin++;
                                            Coin greenC = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                            init.player1Green.add(greenC);
                                            p1.greenCoin++;
                                            Coin whiteC = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                            init.player1White.add(whiteC);
                                            p1.whiteCoin++;
                                        }
                                    }
                                    if (redBox.isSelected()) {
                                        if (whiteBox.isSelected()) {
                                            Coin blueC = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                            init.player1Blue.add(blueC);
                                            p1.blueCoin++;
                                            Coin redC = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                            init.player1Red.add(redC);
                                            p1.redCoin++;
                                            Coin whiteC = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                            init.player1White.add(whiteC);
                                            p1.whiteCoin++;
                                        }
                                    }
                                }
                                if (greenBox.isSelected()) {
                                    if (redBox.isSelected()) {
                                        if (whiteBox.isSelected()) {
                                            Coin greenC = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                            init.player1Green.add(greenC);
                                            p1.greenCoin++;
                                            Coin redC = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                            init.player1Red.add(redC);
                                            p1.redCoin++;
                                            Coin whiteC = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                            init.player1White.add(whiteC);
                                            p1.whiteCoin++;
                                        }
                                    }
                                }
                            }
                            else {
                                if (blackBox.isSelected()) {
                                    if (blueBox.isSelected()) {
                                        if (greenBox.isSelected()) {
                                            Coin blackC = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                            init.player2Black.add(blackC);
                                            p2.blackCoin++;
                                            Coin blueC = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                            init.player2Blue.add(blueC);
                                            p2.blueCoin++;
                                            Coin greenC = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                            init.player2Green.add(greenC);
                                            p2.greenCoin++;
                                        }
                                        if (redBox.isSelected()) {
                                            Coin blackC = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                            init.player2Black.add(blackC);
                                            p2.blackCoin++;
                                            Coin blueC = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                            init.player2Blue.add(blueC);
                                            p2.blueCoin++;
                                            Coin redC = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                            init.player2Red.add(redC);
                                            p2.redCoin++;
                                        }
                                        if (whiteBox.isSelected()) {
                                            Coin blackC = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                            init.player2Black.add(blackC);
                                            p2.blackCoin++;
                                            Coin blueC = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                            init.player2Blue.add(blueC);
                                            p2.blueCoin++;
                                            Coin whiteC = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                            init.player2White.add(whiteC);
                                            p2.whiteCoin++;
                                        }
                                    }
                                    if (greenBox.isSelected()) {
                                        if (redBox.isSelected()) {
                                            Coin blackC = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                            init.player2Black.add(blackC);
                                            p2.blackCoin++;
                                            Coin greenC = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                            init.player2Green.add(greenC);
                                            p2.greenCoin++;
                                            Coin redC = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                            init.player2Red.add(redC);
                                            p2.redCoin++;
                                        }
                                        if (whiteBox.isSelected()) {
                                            Coin blackC = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                            init.player2Black.add(blackC);
                                            p2.blackCoin++;
                                            Coin greenC = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                            init.player2Green.add(greenC);
                                            p2.greenCoin++;
                                            Coin whiteC = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                            init.player2White.add(whiteC);
                                            p2.whiteCoin++;
                                        }
                                    }
                                    if (redBox.isSelected()) {
                                        if (whiteBox.isSelected()) {
                                            Coin blackC = init.slotMachineBlack.remove(init.slotMachineBlack.size() - 1);
                                            init.player2Black.add(blackC);
                                            p2.blackCoin++;
                                            Coin redC = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                            init.player2Red.add(redC);
                                            p2.redCoin++;
                                            Coin whiteC = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                            init.player2White.add(whiteC);
                                            p2.whiteCoin++;
                                        }
                                    }
                                }
                                if (blueBox.isSelected()) {
                                    if (greenBox.isSelected()) {
                                        if (redBox.isSelected()) {
                                            Coin blueC = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                            init.player2Blue.add(blueC);
                                            p2.blueCoin++;
                                            Coin greenC = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                            init.player2Green.add(greenC);
                                            p2.greenCoin++;
                                            Coin redC = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                            init.player2Red.add(redC);
                                            p2.redCoin++;
                                        }
                                        if (whiteBox.isSelected()) {
                                            Coin blueC = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                            init.player2Blue.add(blueC);
                                            p2.blueCoin++;
                                            Coin greenC = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                            init.player2Green.add(greenC);
                                            p2.greenCoin++;
                                            Coin whiteC = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                            init.player2White.add(whiteC);
                                            p2.whiteCoin++;
                                        }
                                    }
                                    if (redBox.isSelected()) {
                                        if (whiteBox.isSelected()) {
                                            Coin blueC = init.slotMachineBlue.remove(init.slotMachineBlue.size() - 1);
                                            init.player2Blue.add(blueC);
                                            p2.blueCoin++;
                                            Coin redC = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                            init.player2Red.add(redC);
                                            p2.redCoin++;
                                            Coin whiteC = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                            init.player2White.add(whiteC);
                                            p2.whiteCoin++;
                                        }
                                    }
                                }
                                if (greenBox.isSelected()) {
                                    if (redBox.isSelected()) {
                                        if (whiteBox.isSelected()) {
                                            Coin greenC = init.slotMachineGreen.remove(init.slotMachineGreen.size() - 1);
                                            init.player2Green.add(greenC);
                                            p2.greenCoin++;
                                            Coin redC = init.slotMachineRed.remove(init.slotMachineRed.size() - 1);
                                            init.player2Red.add(redC);
                                            p2.redCoin++;
                                            Coin whiteC = init.slotMachineWhite.remove(init.slotMachineWhite.size() - 1);
                                            init.player2White.add(whiteC);
                                            p2.whiteCoin++;
                                        }
                                    }
                                }
                            }
                            isP1Turn = !isP1Turn;
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
        for (int i = init.level1.size() - 1, j = 0, k = 0; i > init.level1.size() - 5; i--, j++, k++) {
            try {
                level1Card = init.level1.get(i);
                BufferedImage image = ImageIO.read(getClass().getResource(level1Card.getImage1Path()));
                cardLabel1[k] = new JLabel(new ImageIcon(image));
                cardLabel1[k].setBounds(510 + (cardWidth + 10)*j, 65, cardWidth, cardHeight);
                int tmp = k;
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
                                cardLabel1[tmp].setIcon(new ImageIcon(image));
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
                level2Card = init.level2.get(i);
                BufferedImage image = ImageIO.read(getClass().getResource(level2Card.getImage2Path()));
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
                level3Card = init.level3.get(i);
                BufferedImage image = ImageIO.read(getClass().getResource(level3Card.getImage3Path()));
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
            gamePanel.add(blackCoinLabel);
            BufferedImage image2 = ImageIO.read(getClass().getResource("./coins/blue coin.png"));
            blackCoinLabel = new JLabel(new ImageIcon(image2));
            blackCoinLabel.setBounds(500 + 94 + 2, 700, 94, 94);
            gamePanel.add(blackCoinLabel);
            BufferedImage image3 = ImageIO.read(getClass().getResource("./coins/green coin.png"));
            blackCoinLabel = new JLabel(new ImageIcon(image3));
            blackCoinLabel.setBounds(500 + 2*94 + 4, 700, 94, 94);
            gamePanel.add(blackCoinLabel);
            BufferedImage image4 = ImageIO.read(getClass().getResource("./coins/red coin.png"));
            blackCoinLabel = new JLabel(new ImageIcon(image4));
            blackCoinLabel.setBounds(500 + 3*94 + 6, 700, 94, 94);
            gamePanel.add(blackCoinLabel);
            BufferedImage image5 = ImageIO.read(getClass().getResource("./coins/white coin.png"));
            blackCoinLabel = new JLabel(new ImageIcon(image5));
            blackCoinLabel.setBounds(500 + 4*94 + 8, 700, 94, 94);
            gamePanel.add(blackCoinLabel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
