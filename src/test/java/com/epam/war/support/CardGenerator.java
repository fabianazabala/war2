package com.epam.war.support;

import com.epam.war.domain.Card;
import com.epam.war.domain.CardSuit;
import com.epam.war.domain.CardValue;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CardGenerator {

  public static List<Card> generateSameCard(int cardNumber) {
    return generateSameCard(cardNumber, CardValue.A, CardSuit.DIAMONDS);
  }


  public static List<Card> generateSameCard(int cardNumber, CardValue value, CardSuit suit) {
    return IntStream.range(0, cardNumber)
        .mapToObj(i -> new Card(value, suit))
        .collect(Collectors.toList());
  }
}
