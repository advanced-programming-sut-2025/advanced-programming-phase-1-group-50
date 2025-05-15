package controller.PlayersRealtionController;

import models.Result;
import models.app.App;
import models.userInfo.Player;

import java.util.Iterator;
import java.util.Set;

public class PlayersRelationController {

    public Result friendships() {

        StringBuilder message = new StringBuilder("FriendShips:");

        for (Set<Player> key : App.getGame().getRelationsBetweenPlayers().relationNetwork.keySet()) {

            message.append("\n");

            Iterator<Player> iterator = key.iterator();
            Player p1 = iterator.next();
            Player p2 = iterator.next();

            message.append(p1.getUsername()).append(" and ").append(p2.getUsername()).append(":");
            message.append(App.getGame().getRelationsBetweenPlayers().relationNetwork.get(key).toString());

        }

        return new Result(true, message.toString());

    }


}
}
