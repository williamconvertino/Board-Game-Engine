# TestPlan

Describe at least two specific strategies your team discussed to make your APIs more easily testable (such as useful  
parameters and return values, using smaller classes or methods,   
throwing Exceptions, etc.).

### Strategy 1
From the backend, we tentatively speculate that among the obvious ideas of using sensible method names and excellent documentation, we could use smaller classes for APIs that have a very specific and clear role, beyond putting a myriad of different methods in a "god" API that manages everything. In making a more flexible API that be used more effectively, we also will try to approach the API as providing a single or couple tools that can be used to do many different things, rather than providing numerous tools that only work in a specific context. This is similar to the Single Responsibility Principle. And of course generally we would do well to follow the SOLID principles in our design. Having well-designed code like this would be good for testing.

For simpler methods or areas with less complexity in terms of design work,  it would make sense to utilize TDD which would gurantee the robustness of our design's compatiability with tests.


### Strategy 2
In the front-end aspect of testing, we realize that from our past projects we have had an easier time performing view testing by remembering to make sure each of our view objects have, if possible, a node identification that can be queried. So when we design our APIs for the view, we want the class(es) that create all the view objects to assign ID's within the respective methods. So within the createGenericButton(), and as we create any method that would create an button/window/etc.  that should be tested, we would have a bit of code that does objectName.setID(name) which would allow us to much more easily test the view. Additionally, documentation on the methods would make it easier for users to test, in the case that the individual responsible for the viewtesting was not responsible for aspects of the front-end that he is testing.

#

Describe at least three test scenarios each for four project features (at least one of which is negative/sad/error producing), the expected outcome, and how your design supports testing for it (such as methods you can call, specific values you can use as parameters and return values to check, specific expected UI displays or changes that could be checked, etc.).
*A test scenario describes the the expected results from a user's action to initiate a feature in your program and the steps that lead to that result. It is generally believed that easily testable code is better designed so this sets the team's commitment to the project's quality by addressing expectations for both "happy" and "sad" possible input/interactions.*

##  Feature 1: Buying House

### Test Scenario 1 - Buy Error Popup

For the front-end, we can have a scenario where it tests the following: the user clicks on a tile, "the property window"  appears and the user attempts to buy the property. However, an error should appear that says that the user cannot buy the property because they do not have a monopoly.

### Test Scenario 2 - Buy Success Popup

For the front-end, we can have a scenario where it tests the following: the user clicks on a tile, "the property window"  appears and the user attempts to buy the property. A popup should appear that says that the user has successfully bought the property. The property should also be checked to see that it appears visually on the player's list of possessions (whichever way that might be presented)

### Test Scenario 3 - Detecting internal change in the data when user buys a house

In the backend, we should check that all the relevant internal properties for the player changes when they buy a house. We should check if the player has a house in their list of possessions (whichever way that might be maintained), such as a list of Building objects where houses have a price, description, etc. So we should be able to call a getBuildings or similar if not a get method. (It might make sense to have a large hierarchy here, where we have Collectibles as the grandfather, Buildings as the father, and the Houses as a son.)  We should also be able to check if their money has decreased as well.

## Feature 2: Executing dice roll

### Test Scenario 1 - Test that double dice roll event is triggered in view

In the view, when a double is rolled (i.e. dice both result in the same number respectively), some sort of indication that a special event has happened where the player may roll again once more. Detect that a popup like "Doubles! Go again" has appeared. Check that button that allows the user to the roll works and has not been removed from the screen.

### Test Scenario 2 - Test that the rolling doubles three times lands you in jail

In the view, when three doubles are rolled, the player should be immediately sent to jail. This means that on the third double, the movement should not be executed and instead the player should be instantly moved into the jail tile. The check should be on that the third double does not cause execution of movement, and that the two prior doubles do, and that the player token is located on the jail tile.

### Test Scenario 3 - Test that rolling is ineffective in jail unless doubles are achieved

Confirm in the back end and the frontend that rolling is ineffective in jail. A popup should appear that "You are in jail, cannot move", unless in the case where the double occurs, in which case "You are free, you rolled doubles." So we are checking for both neg and positive in this test scenario. Technically we could split this into two tests, one neg and one pos.

## Feature 3: Requesting a trade

### Test Scenario 1 - Trade offer
Confirm that a trade request is possible with the user when it is the beginning of their turn. The popup/frame change for the trade request should appear.

###  Test Scenario 2 - Trade offer select

The user should be able to select any item from the possession in the frame and add that to the offer bar, and then click "Offer" to offer a trade.

### Test Scenario 3 - Trade decline/accept

The player selected by the original player should be able to see that they have been offered a  trade and be able to "Accept" or "Decline" it.

## Feature 4: Auction events

### Test Scenario 1 - All players can bid
All players can bid. A popup should appear with the name of each of the players. They may then enter what they are willing to bid. The ones that bid first should be ones that are first nonvirtually to declare that they would like to bid first.

### Test Scenario 2 - Current bid value should be maintained
The current bid should be maintained by the model. getBid() should return the updated Bid after a user has typed and entered his bid into the textField.

### Test Scenario 3 - Bid value should an integer
Check that a popup error occurs when a bid value that is entered is invalid. For the following cases: it is a string rather than a number, it is a float rather an integer, it is exceeding what the player can afford. 