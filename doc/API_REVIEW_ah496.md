## API Review

### Part 1

1. How does your API encapsulate your implementation decisions?
   * It calls private methods which the caller cannot see.
2. What abstractions is your API built on?
   * All displays have a simple update method with no parameters or return values.
3. What about your API's design is intended to be flexible?
   * Update can do whatever it needs to do for different displays.
4. What exceptions (error cases) might occur in your API and how are they addressed?
   * None.

### Part 2

1. How do you justify that your API is easy to learn?
   * It doesn't need parameters and doesn't return anything. It is also very clearly named.
2. How do you justify that API leads to readable code?
   * It performs 2 different actions that corresponds with the display.
3. How do you justify that API is hard to misuse?
   * It doesn't update model (only reads). It does not take any parameters.
4. Why do you think your API design is good (also define what your measure of good is)?
   * It is simple and readable and easy to understand.