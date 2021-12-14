# OOGA Design Final
### Names
Aaric Han, Casey Goldstein, Henry Huynh, William Convertino, Jordan Castleman

## Team Roles and Responsibilities

* Aaric Han - Program UI, Player Profile UI, GameBoard UI, Exceptions UI

* Casey Goldstein - File Parser, Game Variation UI

* Henry Huynh - Program UI, Option UI, Player Information UI, End Screen UI

* William Convertino - Game Flow Backend, Action Sequences

* Jordan Castleman - Game Flow Backend, Player Profile Backend, Trade Manager Backend


## Design goals

#### What Features are Easy to Add
- Trade manager UI can be easily added. The backend is finished and the front end can be added by modifying the TurnChoices class in the right of the game board display package. There would have to also be a new class made with a popup that prompts users to propose and accept trades.


## High-level Design

#### Core Classes
* model
  * data
    * cards
      * `Card.java` (represent the game's commands cards)
    * deck
      * `Deck.java` (store all cards in deck)
      * `DeckManager.java` (keep track of active deck in game)
    * gamedata
      * `GameData.java` (Stores all gamedata for UI and other classes to access)
    * player
      * `Player.java` (A player)
      * `PlayerManager.java` (A collection of players and logic for players)
    * properties
      * `Property.java` (holds property information)
    * tilemodels
      * `PropertyTileModel.java` (takes property information and performs actions when players land on it)
  * die
    * `OriginalDice.java` (Dice used in game)
  * game_handling
    * board_manager
      * `OriginalBoardManager.java` (Manages the flow of the pieces)
    * commands
      * Classes in here take pseudocode and parses it and converts the pseudocode to method calls
    * `FunctionExecutor.java` (Executes functions from cards or property actions)
    * `TurnManager.java` (Manages Player's turns)
  * game_initialization
* util
  * parsers
    * `BoardParser.java` (Parse .board files for the tiles on gameboard)
    * `CardParser.java` (Parse .card files for the cards)
    * `FolderParser.java` (Parse files in folder)
    * `PropertyParser.java` (Parser for in-game property tiles)
    * `TileParser.java` (Parser for the .tile files)
  * `ProfileManager.java` (Back end for player profiles)
* display
  * game_board
    * board
      * `GameBoard.java` (Makes and updates the game board)
    * bottom
      * `Bottom.java` (The win/loss screen button)
    * left
      * `Left.java` (The player in-game information)
    * right
      * `Right.java` (The right panel of the game screen)
      * `TurnChoices.java` (The turn choices buttons players use to play the game)
    * top
      * `Top.java` (Control flow for going to previous screens)
    * `GameBoardDisplay.java` (Makes and updates the game screen)
  * popup
    * `PropertyInfoPopUp.java` (Displays Property tile information on click)
  * resources
    * Files in here are the texts for UI elements and styling for UI elements
  * screens
    * endgame
      * `LossScreen.java` (The screen to show win/loss)
    * `StartMenu.java` (Allows users to start the game)
    * `GameCreatorScreen.java` (Allow users to customize the game)
    * `EnterPlayersScreen.java` (Allows users to customize the players and select a game)
  * ui_tools
    * `UIBuilder.java` (Factory that makes UI components)
  * `Display.java` (The generic display class that screens extend)
  * `DisplayManager.java` ()
* `GameManager.java` (Initializes the game data, event manager, turn manager, and function executor)

## Assumptions that Affect the Design

#### Features Affected by Assumptions
- We assumed that the board size is 11x11 and so smaller boards do not show up in the UI properly.
- We assumed that if people wanted to make a new variation, they would stick with the file formats that we specify. This makes it so our parsers for different game elements only have to parse one type of file.


## Significant differences from Original Plan
- In the display, we wanted the current player's info to show on the bottom panel of the game display but that was not implemented.
- We also wanted the trade functionality but that also was not implemented in the display.

## New Features HowTo

#### Easy to Add Features
To add additional features in the front end, we decided to go with the popup approach. If there is a new piece of functionality, we can make a popup class and when an event triggers, it could show the popup. To add additional features in the backend, we can make a package and add classes to the package.

#### Other Features not yet Done
The approach above could be applied to the trade manager UI. The backend for the trade manager is complete but on the front end, a button needed to be added to the TurnChoices class where it opens a popup with options for trading. The popup's buttons would be linked to the backend methods.
