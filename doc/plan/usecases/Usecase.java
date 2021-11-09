import model.data.collectables.Collectable;
import model.data.collectables.properties.Property;
import model.data.game_data.GameData;
import model.data.game_data.Player;
import model.data.tiles.PropertyTile;
import model.data.tiles.Tile;
import model.die.Die;
import model.game_manager.GameManager;
import model.trade_manager.TradeManager;
import model.turn_manager.GameFunctionManager;
import model.turn_manager.TurnManager;

class Usecases {

  TurnManager myTurnManager;
  GameData myGameData;
  GameFunctionManager myGameFunctionManager;
  Player activePlayer;
  TradeManager myTradeManager;

  //The previous player ended their turn
  void startTurn () {
    activePlayer = myGameData.getNextPlayer();
    myTurnManager.startTurn(activePlayer);
  }

  //When the player chooses to roll the dice, they will press a button on the
  //display that will execute the following command:
  void roll () {
    myTurnManager.roll();
  }


  //An implementation of this roll could look something like this:
  void myTurnManager_roll() {
    Die myDie = new Die() {
      @Override
      public int roll() {
        return 6;
      }
    };

    int roll = myDie.roll();
    if(!activePlayer.isInJail()) {
      myGameFunctionManager.movePlayerFd(activePlayer, roll);
    }
  }


  //If a player initiates a trade, by pressing the trade button on another player, it will cause the
  //event handler to call the following method
  void initiateTrade(Player p) {
    myTradeManager.initTrade(activePlayer, p);
  }

  //The players can click on what they want to offer to signal the event handler to call a method:
  void offerItem(Player p, Collectable c) {
    myTradeManager.toggleTrade(c);
  }

  //And if they want to accept/refuse the offer:
  void acceptOffer/refuseOffer() {
      myTradeManager.acceptTrade();
      myTradeManager.refuseTrade();
  }


  //If a player wants to end their turn:
  void endTurn() {
    if (activePlayer.getMoney() < 0) {
      activePlayer.setStatus(false);
    }
    myTurnManager.endTurn(activePlayer);
  }

  //If a player lands on a space:
  void landsOnSpace () {
    Tile myTile = myGameData.getTiles().get(activePlayer.getLocation());
    myTile.executeLandOn(activePlayer, myGameFunctionManager);
  }

  //An example implementation of a property tile:
  void examplePropertyFile() {

    PropertyTile myPropertyTile = new PropertyTile() {
      @Override
      public void executePassThrough(Player player, GameFunctionManager functionManager) {

      }

      @Override
      public void executeLandOn(Player player, GameFunctionManager functionManager) {
        if (property.getOwner() != player) {
          player.addMoney(-1 * property.getRent());
          property.getOwner().addMoney(property.getRent());
        }
      }
    }

  }

}
