package com.stardew.model.userInfo;

import com.stardew.model.PlayersRelation.RelationWithPlayers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RelationNetwork {
    public final Map<Set<Player>, RelationWithPlayers> relationNetwork = new HashMap<>();
}
