package ooga.display.screens;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * This class helps the GameCreatorScreen create and write to the user's custom monopoly variation
 *
 * @author Casey Goldstein
 */
public class FolderFactory {

  private final String VARIATION_PATH = "data/variations/";
  private final String CARDS_PATH = "/cards";
  private final String CHANCE_PATH = "/chance";
  private final String COMMUNITY_CHEST_PATH = "/community_chest";
  private final String TILES_PATH = "/tiles";
  private final String CONFIG = "/config";
  private final String BOARD_PATH = "/board";
  private final String PROPERTIES_PATH = "/properties";
  private final String DEFAULT_VARIATION_PATH = "/original";
  private final String BOARD_MANAGER_PROPERTY = "BoardManager=ooga.model.game_handling.board_manager.";
  private final String PLAYER_MANAGER_PROPERTY = "PlayerManager=ooga.model.data.player.";
  private final String DIE_PROPERTY = "Die=ooga.model.die.";
  private final String VARIATION_NAMES_FOLDER = "data/variation_names";

  private File variationFolder;
  private File variationBoard;
  private File variationCards;
  private File variationProperties;
  private File variationConfigFile;
  private File variationBoardFile;
  private File variationTiles;
  private File variationChanceCards;
  private File variationCommunityChestCards;
  private File variationNameFile;

  private final int TILE_SUBSTRING_CUTOFF = 37;
  private final int CHANCE_SUBSTRING_CUTOFF = 38;
  private final int COMMUNITY_CHEST_SUBSTRING_CUTOFF = 47;

  private Path src;
  private Path des;


  /**
   * Creates the folder hierarchy for the variation.
   *
   * @param variationName
   * @throws IOException
   */
  public void makeDirectories(String variationName) throws IOException {
    variationFolder = new File(VARIATION_PATH + variationName.replace(" ", "_"));
    variationFolder.mkdirs();
    variationBoard = new File(VARIATION_PATH + variationName + BOARD_PATH);
    variationBoard.mkdirs();
    variationCards = new File(VARIATION_PATH + variationName + CARDS_PATH);
    variationCards.mkdirs();
    variationProperties = new File(VARIATION_PATH + variationName + PROPERTIES_PATH);
    variationProperties.mkdirs();
    variationBoardFile = new File(
        VARIATION_PATH + variationName + BOARD_PATH + "/" + variationName + BOARD_PATH.replace("/",
            "."));
    variationBoardFile.createNewFile();
    variationConfigFile = new File(
        VARIATION_PATH + variationName + CONFIG + PROPERTIES_PATH.replace("/", "."));
    variationConfigFile.createNewFile();
    variationTiles = new File(VARIATION_PATH + variationName + BOARD_PATH + TILES_PATH);
    variationTiles.mkdirs();
    variationChanceCards = new File(VARIATION_PATH + variationName + CARDS_PATH + CHANCE_PATH);
    variationChanceCards.mkdirs();
    variationCommunityChestCards = new File(
        VARIATION_PATH + variationName + CARDS_PATH + COMMUNITY_CHEST_PATH);
    variationCommunityChestCards.mkdirs();
    variationNameFile = new File(VARIATION_NAMES_FOLDER + "/" + variationName);
    variationNameFile.createNewFile();
    copyTileFiles(variationName);
    copyCardFiles(CHANCE_PATH, CHANCE_SUBSTRING_CUTOFF, variationName);
    copyCardFiles(COMMUNITY_CHEST_PATH, COMMUNITY_CHEST_SUBSTRING_CUTOFF, variationName);
  }

  //copies tile files from original variation to new variation
  private void copyTileFiles(String name) throws IOException {
    String tileName;
    File fileFolder = new File(VARIATION_PATH + DEFAULT_VARIATION_PATH + BOARD_PATH + TILES_PATH);
    for (File file : fileFolder.listFiles()) {
      tileName = file.getPath().substring(TILE_SUBSTRING_CUTOFF);
      new File(VARIATION_PATH + name + BOARD_PATH + TILES_PATH + "/" + tileName).createNewFile();
      src = Paths.get(file.getPath());
      des = Paths.get(VARIATION_PATH + name + BOARD_PATH + TILES_PATH + "/" + tileName);
      Files.copy(src, des, StandardCopyOption.REPLACE_EXISTING);
    }
  }

  //copies configuration file from original variation to new variation
  public void writeConfigFile(String boardmanager, String playermanager, String die)
      throws IOException {
    System.out.println(variationConfigFile);
    FileWriter fw = new FileWriter(variationConfigFile, true);
    fw.write(BOARD_MANAGER_PROPERTY + boardmanager + "\n");
    fw.write(PLAYER_MANAGER_PROPERTY + playermanager + "\n");
    fw.write(DIE_PROPERTY + die);
    fw.close();
  }


  //copies chance and community chest cards from original variation to new variation
  private void copyCardFiles(String cardPath, int pathCutoff, String name) throws IOException {
    String tileName;
    File fileFolder = new File(VARIATION_PATH + DEFAULT_VARIATION_PATH + CARDS_PATH + cardPath);
    for (File file : fileFolder.listFiles()) {
      tileName = file.getPath().substring(pathCutoff);
      new File(VARIATION_PATH + name + CARDS_PATH + cardPath + "/" + tileName).createNewFile();
      src = Paths.get(file.getPath());
      des = Paths.get(VARIATION_PATH + name + CARDS_PATH + cardPath + "/" + tileName);
      Files.copy(src, des, StandardCopyOption.REPLACE_EXISTING);
    }
  }

  //Writes a single tile name to the .board file in the variation folder
  public void writeLineToBoard(String tileName) throws IOException {
    FileWriter fw = new FileWriter(variationBoardFile, true);
    fw.write(tileName + "\n");
    fw.close();
  }
}
