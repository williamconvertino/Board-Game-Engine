#Design

Write your planned design without revealing any specific implementation details (like data structures, file formats, etc.).
Start by describing the primary design goals of the project (i.e., where is it most flexible) and the primary architecture of the design 
(i.e., what is closed and what is open) — this should emphasize your game genre's important commonalities and how they 
could be represented by abstractions. Describe your intended design to handle your team's goals for the project 
functionality using APIs to provide services rather than simple classes. Then, for each API you plan to build, 
provide a roughly "one page" high-level design overview using the format below. Note, this file can link to external
image files (such as UML diagrams, CRC cards, scans of hand drawn diagrams, example game screens, etc.).


## General Design

You may find a high-level overview in the UML Diagram that is in the api folder.

The design overview of the project is as follows: In the back-end we have a player model, board model, tile model, etc. which
keeps track of various important information for the game. We have a GameManager and a PlayerManager which act as the two
primary controllers, but it's likely that they will have additional helper classes such as the TradeManager or AuctionManager. 
We have a DisplayManager which acts as an intermediary between the GameManager/PlayerManager and the View. The View class is going to be
assisted by several different classes which form the UI of the class, and we will have utilies/a UI builder class that also helps 
build all the UI required.

We are planning to make classes to deal with cards, properties etc. as abstract as possible. So one idea is to just have a 
collectibles class which is probably going to represent anything at all that the player can claim ownership over, besides money.
This abstraction will be beneficial in the long-run as it will allow for extension should we need to add potentially anything that
extends beyond what we are familiar with. Like for example, if we had a variation where we did not have only cards, properties, money,
but also had powerups for example. Not that we are planning specifically for such a high level of variation, but designing it in that way
allows for the possibility of that should it ever be necessary to do so.

That's one example of a module that we would be open for extension. In regards to the front-end, another example would of course be the UI, and the board itself. We are planning 
to design the UI building tools to support as many additions as possible. We want to be able to easily add modifications to the color scheme, font, language, etc. 
Instead of manually constructing the UI, we will create a bunch of UI tools that will help out in the efficient creation of many different aspects of the view. 
For example, the UI tools to make a card can be used to design both a community chest card and a property card.  