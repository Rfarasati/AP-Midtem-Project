public class Player {
    int score;
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
