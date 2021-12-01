Summary:

I spoke with Keith Cressman and Drew Peterson on the data design for their projects. They both created UNO, but on different teams, which made it easy to compare and contrast approaches.

They both used JSON files to house all of their data. Specifically, for each variation, they start with a couple rules/parameters for the games, and then have a full deck of cards and their respective properties. They use a JSON library to parse the data into key value pairs.

This approach differed from mine, in that I elected to make everything a text file, with various suffixes based off file type (.property, .card, etc). This gave me full autonomy for how to parse each file - for cards and properties, I simply convert the file into a Properties object with one line, and then can use all getProperty, getKey methods. My board file is simply a list of tile names, which a fileReader can parse line by line.

The issues my fellow data curators had were mainly in dealing with saving states. Because they had one JSON file with all of the players and their properties, they needed to rewrite the file every single time they want to save. I recommended to separate all of the players into their individual file, which allows you to only change the files you need to change.

They also helped me get to the bottom of one issue I have been thinking about - if we do end up implementing a save game feature, I will need to make sure that the files that I create are identical in structure than the config files. That means that I have to have empty player files in the config files, to then populate them later in the game.