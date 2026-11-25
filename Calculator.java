import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Calculator extends Application {

    // The text displaying the number being entered and the result
    private Text windowText;

    /*
    1. Create an HBox called textWindow, then call setAlignment() on it and pass in
    Pos.CENTER_RIGHT.
    This will right justify the text like calculators do.
    2. Assign a new Text object to windowText. Then create a new Font and set the
    size to 32 (you can pass
    32 directly into the constructor). Then, set windowText's font to the one you
    just created.
    3. Create a new KeyGrid called grid. We'll define what this does shortly, but
    for now just create it
    4. Recall that binding involves keeping two properties aligned with the same
    data. Typically, in JavaFX
    we bind a Text object's TextProperty to some other property. What this
    accomplishes is that when that
    other property is updated elsewhere in the code, the TextProperty is
    automatically updated as well
    without requiring us to first have access to the Text object from whatever
    class we are in, and secondly
    manually call setText on it to update it. We will use this binding strategy for
    the text being displayed at
    the top of the calculator. KeyGrid already has methods completed for you
    allowing you to access the property
    via a getter in that class (the method windowTextProperty()).
    You've already created windowText, so now bind its textProperty to grid's
    windowTextProperty, then add
    windowText to textWindow (use getChildren().add(...))
    5. Remember that BorderPanes separate and organize nodes by sections. They have
    a top, bottom, left, right,
    and center area that nodes and layouts can be added to using setter methods.
    Set the top of root to textWindow
    and set the center of root to grid.
    6. Write your main method and call launch() and pass in args. This is what
    launches your application.
    ---
    */
    public void start(Stage primaryStage) {
        // TODO: Step 1
        HBox textWindow = new HBox();
        textWindow.setAlignment(Pos.CENTER_RIGHT);

        // TODO: Step 2
        windowText = new Text();
        Font font = new Font(32);
        windowText.setFont(font);

        // TODO: Step 3
        KeyGrid grid = new KeyGrid();

        // TODO: Step 4
        windowText.textProperty().bind(grid.windowTextProperty());
        textWindow.getChildren().add(windowText);

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        // TODO: Step 5
        root.setTop(textWindow);
        root.setCenter(grid);

        Scene scene = new Scene(root, 400, 450, Color.GREEN);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Calculator");
        primaryStage.show();
    }

    // TODO: Step 6
    public static void main(String[] args) {
        launch(args);
    }
}

/*
7. KeyGrid extends GridPane. It is what contains and structures all of the
calculator buttons.
Start by creating the class variables. Create the following variables (all
private):
firstNum (a String)
secondNum (a String)
operator (a String)
result (a Double)
resultShown (a boolean. Set this to false right here in the declaration)
windowTextProperty (a SimpleStringProperty)
8. In the KeyGrid constructor, set firstNum, secondNum, and operator to an
empty String
9. The 2-dimensional String array (nums) holds all of the Strings for the
button labels in the order that they will
appear on the calculator. The nested for loops iterate over each element in
this 2d-array. Inside the
nested loops, do the following:
Create a new button and set its text to nums[j][i]
Set the button's userData to nums[j][i]
Set the button's preferred size to have a width of 100 and height of 80
Set button's action to call processButtonPress
Finally, call add() and add button to the KeyGrid. add() is inherited from
GridPane and takes (node, column_index, row_index)
10. Write getters for firstNum (getFirstNum) and secondNum (getSecondNum).
Instead of returning the String directly,
return the String casted to a double (use Double.parseDouble(...))
11. processButtonPress accepts an ActionEvent parameter. This ActionEvent holds
information about the event that fired.
We want to determine which button was actually pressed that made this method
execute. Start by creating a new Button variable
called source and assign the result of event.getSource() to it. This returns a
generic Object, so cast it to a Button.
12. Remember that we stored the Button's label value in userData, so now we
need to extract that. Since the source variable
from the previous step refers to the button that was pressed, we can pull that
data from it. Create a String called buttonValue
and assign source's userData to it. Use .toString() on userData to convert it
to a String.
13. We'll also want to know which number we are typing in at a given time, and
we can use the operator String for that. If the operator
String is empty (""), then no operator button has been pressed yet. If this is
the case, we must be typing the first number. Otherwise,
if the operator String is NOT empty, we must be typing the second number.
Create a boolean for this purpose. Call it onFirstNum and
set it to true if operator is empty.
14. The first if condition you see under processButtonPress checks whether
buttonValue was a number. Inside this conditional, write another
if-else statement. If onFirstNum is true:
Write another nested if statement that first checks if resultShown is true.
resultShown is set to true in calculate() after a calculation
is done and the result is displayed. If the boolean is true then entering
another number (without entering an operator first)
should start the process from the beginning. In this case, set firstNum to
be an empty String ("") and set resultShown to false.
After that nested if statement, concatenate buttonValue with firstNum and
call setWindowTextContent(...) (which updates the property
bound to the Text in the main application) and pass in firstNum.
Else, follow the same process, but instead with secondNum. Append buttonValue
and set the window text content accordingly.
15. A switch is written on buttonValue. Depending on what it is, we obviously
want our code to execute different behavior. The first
four cases are started, and they represent the operators. When one of these are
pressed, we need to consider two conditions:
- If a second number hasn't been entered yet, pressing different operator
buttons in succession can just change what's stored in the operator variable
- If a second number has already been entered, we need to first perform the
current operation between firstNum and secondNum.
Then we can set operator to whatever was pressed
So, after the "*" case, write an if-else conditional statement such that if
secondNum is empty (""), operator is reassigned to buttonValue and the textWindow
content is set to be operator (to display which button was pressed).
Else, call calculate() (this method is done for you) and assign buttonValue to
the operator class attribute. After the if-else statements, add a break; so
that this case's code doesn't run into the next
(NOTE: Remember that with switch-cases, cases will run into each other unless
you explicitly add break; at the end of it. For these four
cases, we can let this happen since the same code will execute for all of them.
Just ensure that a break; is placed at the very end of the "*" case block)
16. Backspace removes the last digit from the number being typed. Write a
conditional that fires if onFirstNum is true and isn't empty.
In this case, remove the last digit from firstNum and update the window
text content. (Hint: Use .substring() to reassign firstNum)
Otherwise, check if secondNum is empty. If it's not, we can remove a digit,
so do so and update window text content with that updated value.
Don't forget to add break; at the end of the case. Without it, the
next case's code will execute as well.
17. CE clears the current entry, whereas C clears everything. For CE, first
check if onFirstNum is true. If it is, that is the one that should be cleared,
so set it equal to an empty String ("") and update the window text content with
that new blank value.
If onFirstNum was false, do the same process with secondNum. Just like
before, don't forget to add break; at the end of the case.
18. C is much simpler. If this was the key that was pressed, set firstNum,
secondNum, and operator all to empty ("") Strings. Again, don't forget to call
setWindowTextContent to update what is displayed on the front end. Also,
remember to add break; at the end of the case.
19. Scroll down and see the hasDecimal method. As the name suggests, it returns
true if a String contains a decimal point and false if it does not. Use this
in the next case in which the "." key was pressed. Numbers being entered
should, of course, only be allowed to have one decimal point, so the code needs to
first check whether or not one exists in the String before it adds it.
Write a conditional statement to check first if onFirstNum is true. If it is,
firstNum is the number to be evaluated, so check whether it has a decimal point
already by using the helper method hasDecimal(). If that returns false, AND
firstNum is not empty, then one can be added, so append a "." to firstNum and
update
the window text content with the new value. Otherwise, if onFirstNum was false,
write another conditional to verify that secondNum is also not empty AND doesn't
already have a decimal point. If it does not, follow the same process as with
firstNum: Add the "." and set the window text content via the class' method
accordingly.
As always, don't forget to add break; at the end of the case to avoid execution
running past that point.
20. The last button's functionality to address is +/-. This key swaps the sign
of the number being typed. Write an if statement as follows:
If onFirstNum is true AND firstNum is NOT empty,
Check if the first character in the String (use .charAt(0)) is '-'.
If it is, update firstNum to be the String past that point (use .substring(...))
If the first character of firstNum is NOT '-', update firstNum to
be "-" + firstNum
In both cases, update the window text content property with the
class' setter method.
Otherwise, if onFirstNum is false AND secondNum is NOT empty,
Follow the same steps for secondNum
*/
class KeyGrid extends GridPane {

    // TODO: Step 7
    private String firstNum;
    private String secondNum;
    private String operator;
    private Double result;
    private boolean resultShown = false;
    private SimpleStringProperty windowTextProperty = new SimpleStringProperty("");

    public KeyGrid() {
        // TODO: Step 8
        firstNum = "";
        secondNum = "";
        operator = "";

        String[][] nums = {
            {"<--", "CE", "C", "/"},
            {"7", "8", "9", "*"},
            {"4", "5", "6", "-"},
            {"1", "2", "3", "+"},
            { "+/-", "0", ".", "=" }
        };

        for (int i = 0 ; i < nums[i].length ; i++) {
            for (int j = 0 ; j < nums.length ; j++) {
                // TODO: Step 9
                Button button = new Button(nums[j][i]);
                button.setUserData(nums[j][i]);
                button.setPrefSize(100, 80);
                button.setOnAction(this::processButtonPress);
                add(button, i, j);
            }
        }
    }

    // TODO: Step 10
    public double getFirstNum() {
        return Double.parseDouble(firstNum);
    }

    public double getSecondNum() {
        return Double.parseDouble(secondNum);
    }

    private void processButtonPress(ActionEvent event) {
        // TODO: Step 11
        Button source = (Button) event.getSource();

        // TODO: Step 12
        String buttonValue = source.getUserData().toString();

        // TODO: Step 13
        boolean onFirstNum = operator.isEmpty();

        if (Character.isDigit(buttonValue.charAt(0))) {
            // TODO: Step 14
            if (onFirstNum) {
                if (resultShown) {
                    firstNum = "";
                    resultShown = false;
                }
                firstNum = firstNum + buttonValue;
                setWindowTextContent(firstNum);
            } else {
                secondNum = secondNum + buttonValue;
                setWindowTextContent(secondNum);
            }
        } else {
            switch (buttonValue) {
                case "+":
                case "-":
                case "/":
                case "*":
                    // TODO: Step 15
                    if (secondNum.isEmpty()) {
                        operator = buttonValue;
                        setWindowTextContent(operator);
                    } else {
                        calculate();
                        operator = buttonValue;
                    }
                    break;
                case "=":
                    calculate();
                    break;
                case "<--":
                    // Backspace. Removes the last digit from the number being typed.
                    // TODO: Step 16
                    if (onFirstNum && !firstNum.isEmpty()) {
                        firstNum = firstNum.substring(0, firstNum.length() - 1);
                        setWindowTextContent(firstNum);
                    } else if (!secondNum.isEmpty()) {
                        secondNum = secondNum.substring(0, secondNum.length() - 1);
                        setWindowTextContent(secondNum);
                    }
                    break;
                case "CE":
                    // Clear Entry. Clears only the current entry, but keeps the other number in memory.
                    // TODO: Step 17
                    if (onFirstNum) {
                        firstNum = "";
                        setWindowTextContent(firstNum);
                    } else {
                        secondNum = "";
                        setWindowTextContent(secondNum);
                    }
                    break;
                case "C":
                    // Clear. This clears everything from the calculator's memory, including firstNum, secondNum, and operator
                    // TODO: Step 18
                    firstNum = "";
                    secondNum = "";
                    operator = "";
                    setWindowTextContent("");
                    break;
                case ".":
                    // Decimal point. Adds a decimal point only if one does not already exist in whichever number is being entered.
                    // TODO: Step 19
                    if (onFirstNum && !firstNum.isEmpty() && !hasDecimal(firstNum)) {
                        firstNum = firstNum + ".";
                        setWindowTextContent(firstNum);
                    } else if (!onFirstNum && !secondNum.isEmpty() && !hasDecimal(secondNum)) {
                        secondNum = secondNum + ".";
                        setWindowTextContent(secondNum);
                    }
                    break;
                case "+/-":
                    // Positive/negative. Swaps the sign of the current number being typed.
                    // TODO: Step 20
                    if (onFirstNum && !firstNum.isEmpty()) {
                        if (firstNum.charAt(0) == '-') {
                            firstNum = firstNum.substring(1);
                        } else {
                            firstNum = "-" + firstNum;
                        }
                        setWindowTextContent(firstNum);
                    } else if (!onFirstNum && !secondNum.isEmpty()) {
                        if (secondNum.charAt(0) == '-') {
                            secondNum = secondNum.substring(1);
                        } else {
                            secondNum = "-" + secondNum;
                        }
                        setWindowTextContent(secondNum);
                    }
                    break;
            }
        }
    }

    /**
     * Calculates the requested operation between firstNum and secondNum based on
     * what is stored
     * in the operator variable. After calculating, the windowTextContent property
     * is updated with
     * the result, and firstNum is automatically set to be the result so that
     * operations can be chained.
     * This method is done for you.
     */
    private void calculate() {
        if (firstNum.isEmpty() || secondNum.isEmpty() || operator.isEmpty()) {
            return;
        }
        switch (operator) {
            case "+":
                result = getFirstNum() + getSecondNum();
                break;
            case "-":
                result = getFirstNum() - getSecondNum();
                break;
            case "/":
                result = getFirstNum() / getSecondNum();
                break;
            case "*":
                result = getFirstNum() * getSecondNum();
                break;
        }
        firstNum = Double.toString(result);
        setWindowTextContent(firstNum);
        secondNum = "";
    }

    /**
     * Checks whether a String contains a decimal point. This is used before
     * adding a decimal point to a String since they can only contain one.
     * This method is done for you.
     * @param str The String to be evaluated
     * @return A boolean indicating whether the String has a decimal point or not
     */
    private boolean hasDecimal(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '.')
                return true;
        }
        return false;
    }

    /**
     * A getter that returns the StringProperty windowTextProperty. This getter's
     * sole purpose
     * in this code is to expose the property for binding to the Text label in the
     * application.
     * This method is done for you, just ensure that your class attribute is named
     * accordingly.
     * @return StringProperty windowTextProperty,
     */
    public StringProperty windowTextProperty() {
        return windowTextProperty;
    }

    /**
     * A getter that returns the value of windowTextProperty. Not used in this
     * code, but it is
     * good practice to have both a getter and setter for properties.
     * This method is done for you.
     * @return The String stored in windowTextContent
     */
    public String getWindowTextContent() {
        return windowTextProperty.get();
    }

    /**
     * A setter that sets the value stored in windowTextProperty. This property is
     * bound to the
     * Text label in the Application class, so using this method to update the
     * property here
     * automatically updates the text in the application.
     * This method is done for you.
     * @param str The String to set windowTextProperty to
     */
    public void setWindowTextContent(String str) {
        this.windowTextProperty.set(str);
    }
}
