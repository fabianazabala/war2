package com.epam.war.service.card;

import com.epam.war.domain.Deck;
import com.epam.war.domain.Player;
import com.epam.war.service.screen.Dealing;
import java.util.List;

public class RoundRobinCardDealer implements CardDealer {

  private final Dealing dealing;

  public RoundRobinCardDealer(Dealing dealing) {
    this.dealing = dealing;
  }

  @Override
  public void deal(Deck deck, List<Player> playerList) {
    while (deck.hasCards()) {
      playerList.forEach(player -> deck.pickCard().ifPresent(player::takeCard));
    }

    dealing.showScreen(playerList);

  }
}