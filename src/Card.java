public class Card {
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