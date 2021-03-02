package com.epam.war.service.input;

import com.epam.war.domain.DeckSize;
import com.epam.war.service.screen.Usage;

public class InputHandler {

  private final Usage usage;

  public InputHandler(Usage usage) {
    this.usage = usage;
  }

  public Input handleArguments(String[] args) {
    if (hasInvalidNumberOfArguments(args)) {
      usage.message();
      System.exit(1);
    }

    boolean invalidPlayerNumber = isInvalidFirstParameter(args[0]);
    boolean invalidDeckSize = isInvalidSecondParameter(args[1]);

    if (invalidPlayerNumber && invalidDeckSize) {
      System.out.printf("Values were corrected to %s players on a %s deck",
          Input.DEFAULT_PLAYER_NUMBER, Input.DEFAULT_DECK_SIZE);
      return Input.create();
    } else if (invalidPlayerNumber) {
      System.out.printf("Number of players set to %s, you provided unparseable input %s",
          Input.DEFAULT_PLAYER_NUMBER, args[0]);
      return Input.create(DeckSize.fromCode(args[1]));
    }

    int playerNumber = correctFirstParameter(Integer.parseInt(args[0]));
    if (invalidDeckSize) {
      System.out.printf("Deck set to %s, you provided unrecognized value: %s",
          Input.DEFAULT_DECK_SIZE, args[1]);
      return Input.create(playerNumber);
    } else {
      return Input.create(playerNumber, DeckSize.fromCode(args[1]));
    }
  }

  private int correctFirstParameter(int parameter) {
    int newValue = parameter;
    if (parameter <= 0) {
      newValue = 1;
    }

    if (parameter > 5) {
      newValue = 5;
    }

    if (newValue != parameter) {
      System.out.printf("Number of players corrected to %s, you provided %s%n",
          newValue,
          parameter);
    }
    return newValue;
  }


  private boolean hasInvalidNumberOfArguments(String[] args) {
    return args.length != 2;
  }

  private boolean isInvalidFirstParameter(String parameter) {
    try {
      Integer.parseInt(parameter);
      return false;
    } catch (NumberFormatException e) {
      return true;
    }
  }

  private boolean isInvalidSecondParameter(String parameter) {
    return !(parameter.equalsIgnoreCase("small")
        || parameter.equalsIgnoreCase("large"));
  }
}
