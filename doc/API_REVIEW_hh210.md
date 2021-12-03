API Review (UI/Front-end) - Henry Huynh (hh210)
Partner: lrj11

Part 1: 


I explained to my partner about the UIBuilder class I made, which follows the factory design. It helps produce
a bunch of front-end components like labels and buttons which help make it easy to utilize across multiple displays. The API 
also helps encapsulate certain implementation decisions as it is necessary to test certain front-end aspects, and the API creates node id's for each
created object, making it easier to test in the future. This also applies for the language support in the API, as we want to implement a wide variety of language support.

I justified to my partner that the API design is flexible because we don't necessarily have to use it, we can still rely on the original methods to create labels and buttons etc.

The UIBuilder in addition to providing support for testing, provides support for language, as it grabs the string version of the property file. So when we switch languages in the options menu, we can easily switch languages.


Exceptions occur as a result of not having language support, and I talk about how to address this in part 2.


Part 2:

There are sections of code where we need to make a label, for example, the normal way. For instance we need to make player names based on the user input, so it's impossible to adapt these based on language. 
However, given that these are names, it is sensible that they are not converted based on language. But this is an instance where we need to specify that the API should not be used in comments.

I think my code is quite readable as it is quite clear that it is building UI components, and each of the method names are clear and have java documentation.

My partner critiqued my design by explaining that it is not obvious that language support is required to use the API, and I agreed with this and realized that 
I need to make a comment in my API class regarding the fact that language support is required, so that other people who are not familiar with the API can quickly understand
its specific purpose.

Therefore, I think my API design is good but requires improvement. It's possible that it is misused because I need to edit my API to have comments.

