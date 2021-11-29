package ooga.util.parsers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import ooga.display.tileviews.TileView;
import ooga.model.data.properties.Property;
import ooga.model.data.tilemodels.TileModel;


/**
 * Parser class responsible for parsing .board file
 *
 * @author Casey Goldstein
 *
 * @since 0.0.1
 */
public class BoardParser {

  /**
   * Creates BoardParser
   */
  public BoardParser(){
  }

  /**
   * Takes board file path and map of TileModels and returns a ordered list of tileModels
   * based off board configuration
   *
   * @param boardFilePath
   * @param tileMap
   * @return
   * @throws IOException
   */
  public List<TileModel> parseBoard(String boardFilePath, Map<String,TileModel> tileMap) throws IOException {
    List<TileModel> boardTileModels = new ArrayList<>();

    File file=new File(boardFilePath);
    FileReader fileReader =new FileReader("data/variations/monopoly_original/board/monopoly_original.board");
    BufferedReader bufferedReader = new BufferedReader(fileReader);
    String line;
    while((line = bufferedReader.readLine())!=null)
    {
        boardTileModels.add(tileMap.get(line));
      }

    return boardTileModels;
  }

}
