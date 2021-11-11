package ooga.model.data.board;

import java.util.ArrayList;
import java.util.List;
import model.data.game_data.Player;
import model.data.tiles.Tile;

public abstract class Board {

  List<Tile> myTiles;

  public Board () {

    myTiles = new ArrayList();

  }

  public Tile getTile(int index) {
    return myTiles.get(index);
  }


  public void movePlayerFd(Player p, int distance) {

    int tiles_moved = 0;

    while (tiles_moved < distance-1) {
      p.setLocation((p.getLocation() + 1) % myTiles.size());
      myTiles.get(p.getLocation()).executePassThrough(player);
      tiles_moved++;
    }

    myTiles.get(p.getLocation()).executeLandOn(player);
  }

}
