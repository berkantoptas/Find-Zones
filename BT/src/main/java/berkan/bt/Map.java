package berkan.bt;

public class Map implements MapInterface{

    private int mapWidth;
    private int mapHeight;
    public static String[][] map;

    @Override
    public void SetSize(Dimension dim) {
        mapWidth = dim.width;
        mapHeight = dim.height;
        map = new String[mapHeight][mapWidth];
    }

    @Override
    public void GetSize(Dimension dim) {
        //System.out.println("Map Width: " + dim.width + "\n" + "Map Height: " + dim.height + "\n");
        dim.width = this.mapWidth;
        dim.height = this.mapHeight;
    }

    @Override
    public void SetBorder(int x, int y) throws Exception {
        map[x][y] = "1";
    }

    @Override
    public void ClearBorder(int x, int y) throws Exception {
        map[x][y] = "0";
    }

    @Override
    public boolean IsBorder(int x, int y) throws Exception {
        return map[x][y] == "1";
    }

    @Override
    public void Show() {
        try {
            InitializeMap();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void InitializeMap() throws Exception{
        for(int i = 0 ; i < mapHeight ; i++){
            for(int j = 0 ; j < mapWidth ; j++){
                ClearBorder(i,j);
            }
        }
    }
}
