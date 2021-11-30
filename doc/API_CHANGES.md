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

