package com.example.licensemicroservice.repository;

import com.example.licensemicroservice.domain.Match;
import com.example.licensemicroservice.domain.Player;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public class PlayerRepository {
    private final List<Player> playerList;

    public PlayerRepository() {
        this.playerList = createPlayerList();
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    private List<Player> createPlayerList() {
        Player rafa = new Player(1, "Rafael", "Nadal", "1");
        Player serena = new Player(2, "Serena", "Williams", "1");
        Player federer = new Player(3, "Roger", "Federer", "2");
        Player coco = new Player(4, "Coco", "Gauff", "3");

        return List.of(rafa, serena, federer, coco);
    }

    public Player findPlayerById(int playerId) {
        Player player = getPlayerList().stream().filter(s -> s.getPlayerId() == playerId).findAny().orElseThrow(
                () ->
                        new EntityNotFoundException(
                                "Player ID %s not found".formatted(playerId)));
        return player;

    }
}
