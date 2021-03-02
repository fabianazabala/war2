package com.epam.war.service.card;

import com.epam.war.domain.Card;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class HighestCardsFinder implements CardsFinder {

  /**
   * Finds the highest cards from the submitted collection.
   *
   * @return List of highest valued cards, or all the cards if none has a different value.
   */
  @Override
  public List<Card> findCard(Collection<Card> cards) {
    return Optional.ofNullable(cards.stream()
        .collect(Collectors.groupingBy(c -> c.getValue().getValue(),
            () -> new TreeMap<Integer, List<Card>>(Comparator.reverseOrder()),
            Collectors.toList())))
        .map(TreeMap::firstEntry)
        .map(Map.Entry::getValue)
        .orElse(Collections.emptyList());
  }
}
