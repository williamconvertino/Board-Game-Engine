Team member: Jordan Castleman jlc153
Participant from Other Team: Nate Zelter nrz3

## Part 1

1. Our team's API has classes that are very active, which is something my partner appreciated.  It has a good design in terms of
how data is handed off between the front and backend and allows for easy modifications and is easily readable as well.
2. Objects that hold data (i.e. trade or player object) are made active through their respective manager classes.  Also, a turn manager
class handles player actions rather than the player directly themselves, which makes communication with the frontend much easier.
3. The way our board is configured is done entirely off of configuration files that can be customized.  Properties, cards, tiles, etc.
can all be customized simply by changing the values in a config file; alternatively, a new object file can be added to the folder and
our project will take care of the rest in terms of rendering and playing that new addition.
4. A few examples of them are if you try and buy a house but don't have enough money or have too many houses on that property already or 
if you try and mortgage an already mortgaged property.  Regardless, we have a display communication mechanism that will take an error when its
thrown and display it to the user.

## Part 2
1. There is no inherent and deep "game loop" that is difficult to understand; rather, we operate on players individually to construct
the game process.  Our api consists of very short and well-named methods that are easy to understand.  Moreover, all of our data objects have
their data hidden from other classes, which makes it easy to understand, too.
2. Our api consists of many logical, short, well-named methods- these three factors combined clearly will lead to readable and simple code.
Additionally, like I said before, we do not use a deeply nested or difficult to understand game loop.
3. Because we have very active classes and our data objects hide their attributes well from other classes (i.e. are very isolated
), our api is very easy to work with.  Additionally, to modify the game, one can do such almost entirely through the config files- no code
has to be touched or redone.
4. Our api design is "good" in the sense that it is logical and readable.  It encapsulates all aspects of the game and breaks it down
into very easy-to-understand classes that have a clear relationship with one another.