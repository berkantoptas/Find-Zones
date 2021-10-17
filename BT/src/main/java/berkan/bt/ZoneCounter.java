package berkan.bt;

import java.util.Random;

public class ZoneCounter implements ZoneCounterInterface{

    private Dimension dim;
    private final int maxWidth = 44;
    private final int minWidth = 5;
    private final int maxHeight = 25;
    private final int minHeight = 5;
    private MapInterface map;
    boolean[][] mapVisit;

    @Override
    public void Init(MapInterface map) throws Exception {
        this.map = map;
        Random r = new Random();
        int randomWidth = r.nextInt(maxWidth - minWidth) + minWidth;
        int randomHeight = r.nextInt(maxHeight - minHeight) + minHeight;
        dim = new Dimension(randomWidth, randomHeight);

        map.SetSize(dim);
        //map.GetSize(dim);
        map.Show();

        //random vertical lines
        for(int i = 0 ; i < dim.width / 10 ; i++){
            int rnd = r.nextInt(dim.width - 3 - 2) + 2;
            for(int j = 0 ; j < dim.height ; j++){
                map.SetBorder(j, rnd);
            }
        }

        //random horizontal lines
        for(int i = 0 ; i < dim.height / 6 ; i++){
            int rnd = r.nextInt(dim.height - 3 - 2) + 2;
            for(int j = 0 ; j < dim.width ; j++){
                map.SetBorder(rnd, j);
            }
        }

        if(dim.width <= 5 || dim.height <= 5)
            return;
        //random diagonal lines
        int rnd = r.nextInt(dim.width - 3 - 2) + 2;
        for(int i = 0 ; i < dim.height ; i++){
            if(rnd <= dim.width - 1)
                map.SetBorder(i , rnd++);
            else
                break;
        }
        rnd = r.nextInt(dim.width - 3 - 2) + 2;
        for(int i = 0 ; i < dim.height ; i++){
            if(rnd >= 0)
                map.SetBorder(i , rnd--);
            else
                break;
        }
    }

    @Override
    public int Solve() throws Exception {
        int zoneCount = 0;
        mapVisit = new boolean[dim.height][dim.width];
        for(int i = 0 ; i < mapVisit.length ; i++){
            for(int j = 0 ; j < mapVisit[i].length ; j++){
                mapVisit[i][j] = false;
            }
        }
        for(int i = 0 ; i < mapVisit.length ; i++){
            for(int j = 0 ; j < mapVisit[i].length ; j++){
                if(!mapVisit[i][j] && !map.IsBorder(i, j)){
                    zoneCount++;
                    SearchZone(mapVisit, i, j);
                }
            }
        }
        return zoneCount;
    }

    private void SearchZone(boolean[][] mapVisit, int i , int j) throws Exception{
        if(dim.CheckWithin(j, i) || mapVisit[i][j] || map.IsBorder(i, j))
            return;
        mapVisit[i][j] = true;
        SearchZone(mapVisit, i+1, j);
        SearchZone(mapVisit, i, j+1);
        SearchZone(mapVisit, i-1, j);
        SearchZone(mapVisit, i, j-1);
    }
}
