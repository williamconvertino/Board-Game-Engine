## API Changes

**Update Property Pop-Up information on gameboard.**

```java
  public void updatePropertyPopups() {
    for (int i = 0; i < gameData.getBoard().getTiles().size(); i++) {
      allPropInfoPopups.set(i, new PropertyInfoPopUp(gameData.getBoard().getTileAtIndex(i), myBuilder, myLanguage));
    }
  } 
```

This was added because the property popup did not reflect the new changes in ownership.

**Refactor API to get custom player names**

```java
  @Deprecated
  public List<Node> getTextAreaInfo() {
    List<Node> textAreaList = new ArrayList<>();
    textAreaList.add(myTextAreaVBox.getChildren().get(1));
    textAreaList.add(myTextAreaVBox.getChildren().get(3));
    textAreaList.add(myTextAreaVBox.getChildren().get(5));
    textAreaList.add(myTextAreaVBox.getChildren().get(7));
    return textAreaList;
  }

  public List<Node> getPlayerNameTextAreaInfo() {
    List<Node> textAreaList = new ArrayList<>();
    for (Node nodeCheck : myTextAreaVBox.getChildren()) {
        if (nodeCheck.getId() != null && nodeCheck.getId().contains(ENTER_NAME)) {
          textAreaList.add(nodeCheck);
        }
    }
    return textAreaList;
  }
```

This was refactored to allow better access of other elements in myTextAreaVBox. Before it was using magic values to access the TextField elements.

**Add A Color Selector for custom player colors**
```java
    public List<Node> getPlayerColors() {
        List<Node> colorsComboBoxes = new ArrayList<>();
        for (Node nodeCheck : myColorSelectionVBox.getChildren()) {
            if (nodeCheck.getId() != null && nodeCheck.getId().contains(SELECT_COLOR)) {
                colorsComboBoxes.add(nodeCheck);
            }
        }
        return colorsComboBoxes;
    }
```

This is added to get the players colors and add them to the game data.

**Refactor Start and DisplayManager**
```java
  /**
   * Get GameData for testing
   * @return myGameData
   */
  public GameData getGameData() {
    return myGameData;
  }
```

This is added to get the game data for testing purposes.
Now the gameData isn't initialized until after the user starts the game

**Complete Method for Change Theme** 
```java
/**
   * Loops through all the available displays, changes theme.
   * All future added displays will also be of said theme.
   *
   * @param theme the theme
   */
  public void changeTheme(String theme) {
    selectedTheme = STYLESHEETS.get(theme);
    for (Display display : allDisplays) {
      Scene scene = display.getScene();
      scene.getStylesheets().remove(0);
      scene.getStylesheets().add(getClass().getResource(selectedTheme).toExternalForm());
    }
  }
```

Completed the method to change the theme. Before, it is empty. Theme change function now supported.

**Add User Profile Functionality**
```java
  /**
   * The profile popup can be accessed
   */
  public Popup getPopup();
```

We needed to add a player profiles functionality this method can get the popup for update, sign in, and register.

**Check and edit Player Login status**
```java
  /**
   * Set Logged In
   */
  public void setLoggedIn(boolean loggedIn) {
    userLoggedIn = loggedIn;
  }

  /**
   * Check Logged In
   */
  public boolean checkLoggedIn() {
    return userLoggedIn;
  }
```

The display manager holds the boolean of whether or not a player is logged in and these methods manipulate the data

**Get Profile Manager and use it in the start menu**
```java
  /**
   * Return Profile Manager
   */
  public ProfileManager getProfileManager() {
    return myProfileManager;
  }
```

The display manager stores the Profile Manager and the start menu buttons will need to use the Profile Manager


**Added getTile, getTileIndex, getAllTilesOfType, and getClosestTileOfType to BoardManager**

```java

/**
 * Returns the first tile found with the given name.
 *
 * @param tileName the name of the desired tile.
 * @return the first tile found with the given name.
 * @throws TileNotFoundException if the tile cannot be found.
 */
public TileModel getTile(String tileName) throws TileNotFoundException {
    return findNextClosestTile(0, tileName);
    }

/**
 * Returns the index of the first tile found with the given name.
 *
 * @param tileName the name of the desired tile.
 * @return the index of the first tile found with the given name.
 * @throws TileNotFoundException if the tile cannot be found.
 */
public int getTileIndex(String tileName) throws TileNotFoundException {
    Player playerSim = new Player("");
    playerSim.setLocation(0);
    return findNextClosestTileIndex(playerSim, tileName);
    }

/**
 * Returns a list of all the tiles with the specified type.
 *
 * @param type the type of tile to return.
 * @return a list of all the tiles with the specified type.
 */
public List<TileModel> getAllTilesOfType(String type) {
    List<TileModel> tiles = new ArrayList<>(getTiles());
    tiles.removeIf(e->!e.getMyType().equals(type));
    return tiles;
    }

/**
 * Returns the closest tile to the given player of the given type.
 *
 * @param player the player to use for reference.
 * @param type the type of tile to search for.
 * @return the closest tile to the given player of the given type.
 * @throws InvalidFileFormatException if the tile type cannot be found.
 */
public TileModel getClosestTileOfType(Player player, String type)
    throws InvalidFileFormatException {
    List<TileModel> possibleTiles = getAllTilesOfType(type);
    TileModel desiredTile = possibleTiles.stream().reduce( (a,b) ->
    {
    try {
    return
    getDistance(player.getLocation(), findNextClosestTileIndex(player, a.getName()))
< getDistance(player.getLocation(), findNextClosestTileIndex(player, b.getName()))
    ? a : b;

    } catch (TileNotFoundException e) {
    return null;
    }
    }
    ).get();

    if (desiredTile == null) {
    throw new InvalidFileFormatException();
    }
    return desiredTile;
    }


```

These methods are all utility functions for the BoardManager to help retrieve data in different ways.
I implemented them mostly for their use in testing, but they could also have useful applications in the actual code.

**added getRandomCard, getCard, and getCards to Deck class**
```java

/**
     * Returns a random card from this deck.
     *
     * @return a random card from this deck.
     */
    public Card getRandomCard() {
        Random rd = new Random();
        return myCardList.get(rd.nextInt(myCardList.size()));
    }

    /**
     *  Finds and returns the card with the given name. If the card cannot be found,
     *  throws a CardNotFoundException.
     *
     * @param cardName the name of the desired card.
     * @return the card with the specified name.
     * @throws CardNotFoundException if the card cannot be found.
     */
    public Card getCard(String cardName) throws CardNotFoundException {

        for (Card c: myCardList) {
            if (c.getName().equals(cardName)) {
                return c;
            }
        }
        throw new CardNotFoundException(cardName);
    }

    /**
     * Returns all the cards in this deck.
     *
     * @return an immutable list containing all the cards in this deck.
     */
    public List<Card> getCards() {
        return ImmutTool.getImmutableList(myCardList);
    }

```

These methods allow for additional ways to access the data in the deck.
I implemented them mostly for testing purposes, but they also have potential applications
in the program itself.

**Added displayCard method to DisplayCommunicator** 

```java

/**
   *  Displays the specified card.
   *
   * @param card the card to display.
   */
  public void displayCard(Card card) {
    //Todo: replace with display
    System.out.println(String.format("Card drawn: [%s]", card.getDescription()));
  }
```

Our cards previously printed all the values directly. Now they call this method, which handles the card display.


**Added a screen to see the players that had lost**
```java
public void goLossScreen() {
allDisplays.add(new LossScreen(this, languageResource, selectedTheme, myGameData));
int size = allDisplays.size();
currDisplay = allDisplays.get(size - 1);
myStage.setScene(currDisplay.getScene());
}
```
Created a new method that moves the current scene to the loss screen when the person clicks the bottom 'See Players who Lost'


**Refactor GameBoardDisplay for a Bottom section pt. 1** 
```java
public GameBoardDisplay(Stage stage, DisplayManager displayManager, ResourceBundle language,
Map<EVENT_NAMES, TMEvent> eventMap, GameData gameData, String theme){
        myStyle=theme;
        myUIBuilder=new UIBuilder(language);
        myLanguage=language;
        myStage=stage;
        myGameData=gameData;
        myEventMap=eventMap;
        myDisplayManager=displayManager;
        theTop=new Top(this,myDisplayManager,myLanguage);
        theRight=new Right(this,myLanguage,eventMap,gameData);
        theLeft=new Left(myLanguage,eventMap,gameData);
        theBottom=new Bottom(myLanguage,myDisplayManager);
        theBoard=new GameBoard(myDisplayManager,myLanguage,gameData,eventMap,theme);

        theGameBoard=new BorderPane();
        theGameBoard.setCenter(theBoard.getComponent());
        theGameBoard.setBottom(theBottom.getComponent());
        theGameBoard.setRight(theRight.getComponent());
        theGameBoard.setLeft(theLeft.getComponent());
        theGameBoard.setTop(theTop.getTopComponent());

        makeScene();
        }
```

Added bottom component, so that I could add an additional botton to see the players who lost.


**Add bottom class for bottom component of gameboard display**
```java
public Bottom(ResourceBundle language, DisplayManager displayManager) {
myDisplayManager = displayManager;
bottomComponent = new VBox();
myLangResource = language;
myUIBuilder = new UIBuilder(myLangResource);
}
```

Added bottom component, so that I could add an additional botton to see the players who lost.

