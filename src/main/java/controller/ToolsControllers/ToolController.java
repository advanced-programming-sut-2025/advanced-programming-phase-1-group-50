package controller.ToolsControllers;

import models.Result;
import models.app.App;
import models.tools.Tool;

public class ToolController {
    public Result ToolsEquip(String input) {
        for(Tool t : App.getGame().getCurrentPlayingPlayer().getBackpack().getTools()){
            if(t.getClass().getName().equals(input)){
                App.getGame().getCurrentPlayingPlayer().setCurrentTool(t);
                return new Result(true,"Tool equipped");
            }
        }
        return new Result(false,"Tool not found");
    }
    public Result showCurrentTool(){
        return new Result(true,"Current Tool : " + App.getGame().getCurrentPlayingPlayer()
                .getCurrentTool().getClass().getSimpleName());
    }
    public Result showAvailableTool(){
        StringBuilder sb = new StringBuilder();
        for(Tool t : App.getGame().getCurrentPlayingPlayer().getBackpack().getTools()){
            sb.append(t.getClass().getName());
            sb.append("\n");

        }
        return new Result(true,"Available Tools : " + sb);
    }
}
