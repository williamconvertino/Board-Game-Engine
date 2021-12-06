Team member: William Convertino wac20
Participant from Other Team: Alexis Cruz-Ayala adc94

## Part 1

1. We have each part of our data (ei. houses, players, tiles, etc.) in their 
own classes, and each of the classes has exclusive rights to their own data. This 
makes it easy to keep our logic contained within our classes, and makes adding child 
classes quite easy.
2. Our API is built on our data classes and the larger gamedata class, which houses
the aggregation of the different pieces of data. This makes it easy to work with
child classes, as any classes that want to act on our data need to do so with our
public methods in the data classes.
3. More than anything, we kept flexibility in how our data classes obtained their information.
This means that we don't have child classes of our property or card classes, but rather have
one larger class capable of constructing any type of card/property given a text file.
4. We have some exceptions that deal with unwanted turn actions (such as rolling the die to many times,
or ending the turn without rolling), and these should log as a warning, but otherwise not cause any errors.
We also have more major exceptions, such as files not loading, or improperly formatted variation files, which
currently are only displayed in our log. We might want to improve this error handling system to
allow for more of a user response, as well as possible safeguards in the case of an exception occurring.
## Part 2
1. Our API is easy to learn because it keeps all the functionality of specific datatypes within their respective classes. 
While some of the initialization may be difficult to understand, using the data once it has been initialized is quite intuitive.
We also have extensive documentation, and intuitively named functions that should allow for easy use of our API
2. Again, our API has good documentation, and our public methods are all well named. This means that, even if the code itself is
somewhat complex, usage of our methods should be very readable and easy to use.
3. Beyond the points already discussed, we also have a lot of exception handling that should prevent users from
misusing the API. If they want to use a method improperly, they must first set up a system of error handling, which
mitigates a lot of the risks involved with using our API.
4. I think that our API is good insofar as it allows users to use our data with ease. There are very few cases in which
our data classes require a complex understanding to operate, and everything is documented in a way that makes
it accessible to anyone who wants to use it. Overall, I think the points above all contribute to why
I think it is a good API design. I think there are certainly areas in which we could improve the design, in some parts
of the code more than others, but the more base level data is quite reusable
 and intuitive to use.