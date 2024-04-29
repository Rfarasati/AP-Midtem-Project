public class Card {
    int level;
    int point;
    String specialCoin;
    int blackCoin;
    int blueCoin;
    int greenCoin;
    int redCoin;
    int whiteCoin;

    Card(int level, int point, String specialCoin, int blackCoin, int blueCoin, int greenCoin, int redCoin, int whiteCoin) {
        this.level = level;
        this.point = point;
        this.specialCoin = specialCoin;
        this.blackCoin = blackCoin;
        this.blueCoin = blueCoin;
        this.greenCoin = greenCoin;
        this.redCoin = redCoin;
        this.whiteCoin = whiteCoin;
    }

    public String toString() { // A method in order to print arraylist to check
        return level + "-" + point + "-" + specialCoin + "-" + blackCoin + "-" + blueCoin + "-" + greenCoin + "-" + redCoin + "-" + whiteCoin;
    }
    public String getImage1Path() {
        return "./level 1/" + toString() + ".png";
    }
    public String getImage2Path() {
        return "./level 2/" + toString() + ".png";
    }
    public String getImage3Path() {
        return "./level 3/" + toString() + ".png";
    }
}
