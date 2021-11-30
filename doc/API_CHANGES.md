## API Changes

Template for API changes document (***DELETE BEFORE TURNING IN***):

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