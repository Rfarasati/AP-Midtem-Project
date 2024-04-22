public class PrizeClaw {
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
    public String toString() { // A method in order to print arraylist to check
        return point + "-" + blackCoin + "-" + blueCoin + "-" + greenCoin + "-" + redCoin + "-" + whiteCoin;
    }
    public String getPrizeClawPath() {
        return "./prize claw/" + toString() + ".png";
    }

}
