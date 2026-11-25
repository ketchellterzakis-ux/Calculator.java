# JavaFX Calculator

A desktop calculator with a graphical interface, built in Java with JavaFX. It was built as a lab for [your course name] at Montclair State University.

## What it does

- Adds, subtracts, multiplies, and divides decimal numbers
- Supports chaining operations (for example, `2 + 3 * 4` calculates step by step as you press each operator)
- Backspace (`<--`) removes the last digit typed
- `CE` clears the current entry, and `C` clears everything
- `+/-` flips the sign of the number being typed
- Only allows one decimal point per number
- Pressing a number after a result starts a new calculation

## How it works

- `Calculator` sets up the window with a `BorderPane`: the display on top and the buttons in the center.
- `KeyGrid` extends `GridPane` and builds the button layout with nested loops. Each button's label is stored in its `userData`, and one handler method, `processButtonPress`, uses a `switch` to decide what each button does.
- The display text is bound to a `SimpleStringProperty`, so the screen updates automatically whenever the calculator's value changes.

## How to run it

1. Install a JDK (21 or newer) and JavaFX. IntelliJ's JavaFX project template sets this up for you.
2. Download `Calculator.java`.
3. Run the `Calculator` class from your IDE.

## Known limitations

- Results display as decimals (for example, `4.0` instead of `4`).
- Dividing by zero shows `Infinity`.

## What I learned

- Building a graphical interface with JavaFX layouts (`BorderPane`, `GridPane`, `HBox`)
- Handling button clicks with event handlers
- Using property binding to keep the display in sync with the data
- Using `switch` statements and `String` methods to manage input
