## API Changes

**Template for API changes document (***DELETE BEFORE TURNING IN***):**

```java
    @Deprecated
    public void winGame() {
      this.winGame();
    }
    
    public void winGame() {
      this.loseGame();
    }
```

This has to be changed because winGame should actually lose the game instead for some reason.

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
