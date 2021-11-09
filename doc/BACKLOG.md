## Use Cases

Henry Huynh
Jordan Castleman

Use Cases

Buy House (Frontend)
Preconditions
1. It is the player’s turn.
2. The player has not rolled the dice.
3. The player has monopoly on one or more color groups.
4. The even development rule must be followed

When a player has all the tradable cells in a color group, this player is said to have monopoly on the color group. A player may build house(s) in the property cells in the color groups the player has monopoly on by pressing the Buy House button before he or she rolls the dice. The price of the house is determined by the cell. After buying the house(s), the status of the player is updated and displayed on the game board. The visual indicator of a house should pop up on whatever tile the house(s) has been purchased on.

When the Buy House button is clicked, the Buy House dialog shows up. The player selects the monopoly color group and the number of houses from that dialog. After clicking on OK in the dialog box, the player pays the fee, and the houses are created. All the property cells in the selected color group have the same number of houses.

Alternative Flows: Nothing happens if the player does not have enough money

Changes the board game variation to Monopoly Skyscraper in the settings
Preconditions
Must have not started the game yet
Must be in the settings screen

The settings screen should be available to be accessed through the start menu. From there, the user should be able to click from one of the buttons to change the board game variation. There should be some visual indicator that informs the user that the mode has been changed successfully.

Sell House (Frontend)
Preconditions
It is the player’s turn.
The player has not rolled the dice.
An even development rule must be followed

A player has the ability to sell to the bank for half price that it was bought at when they click on a house. When the user clicks on the house a popup or some frame should appear with an option to sell the house


Move character (Front-end aspect)
Preconditions
The player must have rolled a dice

The screen should put a highlighter/pointer on the token being moved, and follow its movement. We could additionally have a counter that is displayed indicating the total steps to be moved and decrement from it each time the player moves once. We could have the token move up vertically to show its moving. Or instead of tokens, which might be hard to animate, we could just use a pointer with different colors for different players.


5. Land on tile (Front-end aspect)

When a player lands on the tile, a popup/frame on the side of the board window should appear indicating the information on the tile. This is because it would be difficult to display all the information


6. User clicks on tile
   The user should be able to click on a tile to examine the details of it further. This might be a necessary detail as it will be difficult for the user to read the details of the whole board. Generally, the user should be able to click on objects to be able to acquire more information of the game state.


7. User rolls dice (Front-end aspect)
   Replace the button to roll the dice after the button has been clicked with a greyed out label/button that does nothing when clicked

Makes use of ButtonManager/ButtonInitalizer class


Will Convertino

8. User starts turn in jail
   The PlayerManager starts the turn, sees that the player is in jail, and signals the display to show the bail screen. If the player tries to roll, the PlayerManager checks to see if there are doubles. If there are, the player moves forward that many spaces and they are marked as no longer in jail. Otherwise, the roll is saved, and the bail screen is once again prompted. If the player presses the end turn button, and they haven’t rolled 3 times during bail, end the turn. Otherwise, if they try to end the turn, tell them that they must pay bail first. If they do not have enough money to pay bail, they can refuse to pay and will be eliminated from the game.
9. User rolls dice (Backend)
   Use the event handler in the roll die button to call the method roll() in the PlayerManager. It will check to make sure they are able to roll (ei havent already rolled), and if not it will call getRoll from the Die class. Then, if they are not in jail, the PlayerManager will call its own method movePlayerFD(p,roll). This will call the executePassThrough(p,this) method on each of the tiles it passes through, and then executeLandOn(p,this) in the tile it lands on. This will allow the tiles to execute their functions on the player using the methods in PlayerManager.

10. Player lands on GoToJail square
    The player rolls the dice and moves to the square in the same manner as above. When they land on the square, it calls execute. In the GoToJail square, the execute method calls the goToJail method in the PlayerManager. This moves the player to the jail square, and sets their inJail value to true. Then, their turn is ended automatically.


11. Player trade with another player (Front-end-ish) Henry
    Trade button -> initiateTrade
    -> Panel pops up where you can offer stuff
    Display all the items in inventory
    Collectible (Property, get out of jail free card)
    Textfield w/ money amt
    Another popup happens with error if they don’t have that amount of money.
    Offer
    -> Another panel for other player to accept
    Accept
    Accept offer popup appears (with one button “OK”)
    Refuse
    Refuse offer popup appears (with one button “OK”)

12. Bank auctions off
     Auction preconditions
     f you land on an unowned property, and you decide you don’t want to buy it (or you don’t want to pay full price)
     If you land on an unowned property and can’t afford to pay the full price for it
     A player goes bankrupt to the Bank. When the money is owed to the Bank (and not to another player) all of their properties are auctioned in turn (providing two or more players are still in the game)
     If several players wish to buy houses or hotels but there are not enough left in the Bank, they will be auctioned
     If a player lands on the Auction space in Monopoly: The Mega Edition (more on that below)
     If you’re playing the house rule ‘Forced Auction’, then any time you land on an unowned property an auction begins immediately – you can’t buy one outright.

13. Player mortgages property
    Player rolls dice and moves to the corresponding location and completes the action associated with that location as necessary.  
    Player chooses to mortgage a property by clicking on a property they own and clicking mortgage property.
    Property turns over and the player gets the mortgage amount.

14. Player exits the game
    The player at any point can exit the game by closing the window or hitting the exit game button located within settings.

15. Player lands on another’s monopoly (no buildings)
    Player 1 buys all properties in one monopoly.
    Player 2’s turn begins.  They roll the dice and land on Player 1’s monopoly.
    Player 2 pays double the rent of that property to player 1.

16. Player rolls doubles three times in a row
    Player rolls doubles three times in a row.
    Before any other actions can be completed, the player’s piece is immediately transported to jail.
    A “Jail” banner is posted across the window.
    Player’s turn ends.

17. Player lands on Chance and gets the “Move to Go, collect 200” card
    Player begins their turn and rolls the dice, moving to one of the Chance locations.
    Player draws a “Move to Go, collect 200” card and is immediately transported to Go.
    Player collects 200.
    Alternative variation: Player collects double the payout.

18. Player loses the game by landing on another monopoly
    Player begins their turn and rolls the dice, moving to another player’s monopoly.
    Player has to pay X but has a balance of less than X, therefore the player pays as much as he can.
    Players turn ends and a “You lose” banner appears.
    Player cannot play the game anymore and is prompted to exit.

19. Player wins the game via another landing on their monopoly
    Player 1 begins their turn and rolls the dice, moving to player 2’s monopoly.
    Player 1 cannot pay player 2 the required amount; therefore, player 2 wins.
    Player 1 pays player 2 as much as they can and the balance is updated accordingly.
    Players turn ends and a “You win” banner appears on player 1’s window.
    Player cannot play the game anymore and is prompted

Casey Goldstein

20. User imports configuration file with insufficient information

    Error message pops up stating the particular data missing from file
    
    User has opportunity to select from folder of trusted, usable config files

21. User tries to start game without loading in configuration file

    Error message pops up starting that a configure file was not included.

    User has choice to load file, or continue with default board/settings

22. User imports JSON with incorrect format

    Error message pops up stating that there are typos/erros in JSON file

    Will highlight specific portions of JSON file, asking user to change

23. User hits ‘Save Game’

    Board is converted into 2D array to be written into JSON file

    All player assets are converted to a string array and passed into JSON file

    Properties and their respective owners (and with/without house/hotels) are converted to string array and passed into JSON file

    File Dialog pops up asking User where to keep JSON file

24. Game Starts (with Pseudo Code):

    GameManager will call startTurn(GameData.getNextPlayer())

    Sets myActivePlayer in GameManager to GameData.getCurrentPlayer()

    In GameManager.startTurn(), GameManager will call DisplayManager.setActivePlayer(myActivePlayer)

    Then GameManager.startTurn() will call PlayerManager.startTurn()

    In PlayerManager.startTurn(), will first call checkCircumstances() that sees if GameData.getCurrentPlayer is in jail.

25. Player tries to end turn without rolling dice:

    Error message pops up, saying that player cannot end turn without rolling dice

    Brings Roll Dice button to center of screen for Player to click

    No other event handling will be accessible until Player clicks
    


Aaric Han

26. Make game board class
    1. The GameBoard class will store the GameBoard that is displayed. It will hold display all the cards and allow for certain squares to be clicked on.
27. Make left panel class
    1. The left panel will contain buttons which allow players to view other player's properties and money.
28. Make right panel class
    1. The right panel class will contain options a player can choose from on their turn or display "waiting for turn" in a multiplayer.
29. Make bottom panel class
    1. The bottom panel will contain a list of your own property cards and money values. The properties can be clicked on to show more details.
30. Make top panel class
    1. The top panel class has game settings for load and save. It also has options for pause and play.
31. Make Error popup class
    1. The error popup class will take in a string of what the error is and display it as a popup.
32. Make Card zoom popup class
    1. The card zoom popup class will handle displaying detailed descriptions of the cards.
