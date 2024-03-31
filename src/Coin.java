public class Coin {
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
