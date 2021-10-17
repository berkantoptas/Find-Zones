package berkan.bt;

public class Dimension {

    public int width, height;

    public Dimension(int w, int h) {
        width = w;
        height = h;
    }
    public boolean CheckWithin(int x, int y) {
        return !(x >= 0 && x < width && y >= 0 && y < height);
    }
}
