package models.mapInfo;

import models.NPCs.NPC;
import models.NPCs.NPCType;
import models.Placeable;

import models.ShippingBin;
import models.app.App;
import models.date.Weather;
import models.foraging.Crop;
import models.foraging.CropType;
import models.foraging.ForagingMineral;
import models.foraging.Tree;
import models.stores.*;
import models.userInfo.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


// need a way from farms to npc village  .....
// doors are completed , but we need ways from each farm to npc village.


public class Map {
    private final static Random rand = new Random();
    private Tile[][] tiles = new Tile[250][200];
    private ArrayList<Farm> farms = new ArrayList<>();
    private NpcVillage npcVillage;
    private final ArrayList<NpcHome> npcHomes = new ArrayList<>();
    private final ArrayList<ShippingBin> shippingBins = new ArrayList<>();

    public Map(ArrayList<Farm> farms) {
        this.farms = farms;
    }

    public Tile findTile(int x, int y) {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (x == i && y == j) {
                    return tiles[i][j];
                }
            }
        }
        return null;
    }

    public Tile findTile(Position position) {
        return findTile(position.getX(), position.getY());
    }

    public void buildMap(ArrayList<Player> players) {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                tiles[i][j] = new Tile(new Position(i, j));
            }
        }

        farms.clear();
        for (Player player : players) {
            player.getFarm().setTilesSymbol(tiles);
            farms.add(player.getFarm());
        }

        this.npcVillage = new NpcVillage(new Rectangle(100, 75, 49, 49),
                new Blacksmith(102, 77, 3, 3),
                new CarpenterShop(106, 81, 3, 3),
                new FishShop(110, 85, 3, 3),
                new JojaMart(114, 89, 3, 3),
                new MarnieRanch(118, 93, 3, 3),
                new PierreGeneralStore(122, 97, 3, 3),
                new StardropSaloon(126, 101, 3, 3));

        NPC abigailNpc = new NPC(NPCType.Abigail);
        NPC sebastianNpc = new NPC(NPCType.Sebastian);
        NPC leahNpc = new NPC(NPCType.Leah);
        NPC robinNpc = new NPC(NPCType.Robin);
        NPC harveyNpc = new NPC(NPCType.Harvey);

        NpcHome abigailHome = new NpcHome(130, 104, 3, 3, abigailNpc);
        npcHomes.add(abigailHome);
        NpcHome harveyHome = new NpcHome(130, 109, 3, 3, harveyNpc);
        npcHomes.add(harveyHome);
        NpcHome robinHome = new NpcHome(130, 114, 3, 3, robinNpc);
        npcHomes.add(robinHome);
        NpcHome leahHome = new NpcHome(130, 119, 3, 3, leahNpc);
        npcHomes.add(leahHome);
        NpcHome sebastianHome = new NpcHome(130, 99, 3, 3, sebastianNpc);
        npcHomes.add(sebastianHome);


        for (Farm farm : farms) {

            for (Placeable placeable : farm.getPlaceables()) {

                for (int i = placeable.getBounds().x; i < placeable.getBounds().x + placeable.getBounds().width; i++) {
                    for (int j = placeable.getBounds().y; j < placeable.getBounds().y + placeable.getBounds().height; j++) {
                        if (!(placeable instanceof Cottage)) {
                            tiles[i][j].setWalkable(false);
                        }
                        tiles[i][j].setSymbol(placeable.getSymbol());
                        tiles[i][j].setPlaceable(placeable);

                    }
                }
            }
        }
        for (int i = this.npcVillage.getRectangle().x; i < this.npcVillage.getRectangle().x + this.npcVillage.getRectangle().width; i++) {
            for (int j = this.npcVillage.getRectangle().y; j < this.npcVillage.getRectangle().y + this.npcVillage.getRectangle().height; j++) {
                tiles[i][j].setSymbol('.');
            }
        }
        for (Placeable p : this.npcVillage.getPlaceables()) {
            for (int i = p.getBounds().x; i < p.getBounds().x + p.getBounds().width; i++) {
                for (int j = p.getBounds().y; j < p.getBounds().y + p.getBounds().height; j++) {
                    tiles[i][j].setWalkable(false);
                    tiles[i][j].setSymbol(p.getSymbol());
                    tiles[i][j].setPlaceable(p);
                }
            }
        }
        for (NpcHome h : npcHomes) {
            for (int i = h.getBounds().x; i < h.getBounds().x + h.getBounds().width; i++) {
                for (int j = h.getBounds().y; j < h.getBounds().y + h.getBounds().height; j++) {
                    tiles[i][j].setSymbol(h.getSymbol());
                    tiles[i][j].setPlaceable(h);
                    tiles[i][j].setWalkable(false);
                }
            }
        }

        setBorderFarmsAndNpcVillage();
        setDoorFarmsAndNpcVillage();
        for (Farm farm : farms) {
            setWalkableDoorTrue(farm.getDoor().getBounds().x, farm.getDoor().getBounds().y,
                    farm.getDoor().getBounds().width, farm.getDoor().getBounds().height);
        }
        for (Door d : npcVillage.getDoors()) {
            setWalkableDoorTrue(d.getBounds().x, d.getBounds().y, d.getBounds().width, d.getBounds().height);
        }


    }

    public ArrayList<NpcHome> getNpcHomes() {
        return npcHomes;
    }


    public void setBorderFarmsAndNpcVillage() {

        for (int i = 0; i < 200; i++) {
            tiles[99][i].setSymbol('+');
            tiles[149][i].setSymbol('+');
        }
        for (int i = 0; i < 250; i++) {
            tiles[i][74].setSymbol('+');
            tiles[i][124].setSymbol('+');
        }

    }

    public void setDoorFarmsAndNpcVillage() {
        farms.get(0).setDoor(new Door(99, 37, 1, 3));

        farms.get(1).setDoor(new Door(149, 37, 1, 3));
        farms.get(2).setDoor(new Door(99, 155, 1, 3));
        farms.get(3).setDoor(new Door(149, 155, 1, 3));
        npcVillage.addDoors(new Door(99, 105, 1, 3));
        npcVillage.addDoors(new Door(149, 105, 1, 3));
        npcVillage.addDoors(new Door(125, 74, 3, 1));
        npcVillage.addDoors(new Door(125, 124, 3, 1));
    }


    public Tile[][] getTiles() {
        return tiles;
    }

    public ArrayList<Farm> getFarms() {
        return farms;

    }

    public NpcVillage getNpcVillage() {
        return npcVillage;
    }

    public void setWalkableDoorTrue(int x, int y, int width, int height) {
        for (int i = x; i < x + width; i++) {
            for (int j = y; j < y + height; j++) {
                tiles[i][j].setWalkable(true);
                tiles[i][j].setSymbol('d');
            }
        }
    }

    public void randomForagingMineralGenerator() {
        for (Farm farm : farms) {
            for (Quarry quarry : farm.getQuarries()) {
                int numberOfForagingMinerals = 2;
                int counter = 0;
                while (counter < numberOfForagingMinerals) {
                    ForagingMineral foragingMineral = ForagingMineral.values()[rand.nextInt(ForagingMineral.values().length)];
                    quarry.addForagingMineral(foragingMineral);
                    counter++;

                }
            }
        }
    }

    public CropType generateRandomCropType() {
        CropType[] cropTypes = CropType.values();
        return cropTypes[rand.nextInt(cropTypes.length)];
    }

    public void generateRandomForagingCrop(Farm farm) {

        int numberOfRandomCrops = 6;
        int counter = 0;
        while (counter < numberOfRandomCrops) {
            int x = rand.nextInt(farm.getRectangle().width) + farm.getRectangle().x;
            int y = rand.nextInt(farm.getRectangle().height) + farm.getRectangle().y;
            Tile tile = findTile(x, y);
            if (tile.getPlaceable() == null) {
                Crop crop = new Crop(generateRandomCropType(), App.getGame().getTime(), null, x, y);
                tile.setPlaceable(crop);
                tile.setWalkable(false);
                tile.setSymbol(crop.getSymbol());
                farm.getCrops().add(crop);
                farm.getPlaceables().add(crop);
            }
            counter++;

        }

    }

    public void generateRandomStoneFarm(Farm farm) {
        int numberOfStone = rand.nextInt(2) + 1;
        int counter = 0;

        while (counter < numberOfStone) {
            int x = rand.nextInt(farm.getRectangle().width) + farm.getRectangle().x;
            int y = rand.nextInt(farm.getRectangle().height) + farm.getRectangle().y;
            Tile tile = findTile(x, y);

            if (tile.getPlaceable() == null) {
                Stone stone = new Stone(x, y);
                tile.setPlaceable(stone);
                tile.setWalkable(false);
                tile.setSymbol(stone.getSymbol());
                farm.getStones().add(stone);
                farm.getPlaceables().add(stone);
            }
            counter++;
        }
    }

    public Tile getTileByDirection(Tile currentTile, Direction direction) {
        return switch (direction) {
            case UP -> findTile(currentTile.getPosition().getX(), currentTile.getPosition().getY() - 1);
            case DOWN -> findTile(currentTile.getPosition().getX(), currentTile.getPosition().getY() + 1);
            case LEFT -> findTile(currentTile.getPosition().getX() - 1, currentTile.getPosition().getY());
            case RIGHT -> findTile(currentTile.getPosition().getX() + 1, currentTile.getPosition().getY());
            case UP_LEFT -> findTile(currentTile.getPosition().getX() - 1, currentTile.getPosition().getY() - 1);
            case UP_RIGHT -> findTile(currentTile.getPosition().getX() + 1, currentTile.getPosition().getY() - 1);
            case DOWN_LEFT -> findTile(currentTile.getPosition().getX() - 1, currentTile.getPosition().getY() + 1);
            case DOWN_RIGHT -> findTile(currentTile.getPosition().getX() + 1, currentTile.getPosition().getY() + 1);
        };
    }

    public ArrayList<ShippingBin> getShippingBins() {
        return shippingBins;
    }

    public boolean addShippingBin(int x, int y) {
        if (!(tiles[x][y].getPlaceable() == null)) {
            return false;
        }

        ShippingBin temp = new ShippingBin(x, y);

        this.shippingBins.add(temp);
        tiles[x][y].setPlaceable(temp);
        tiles[x][y].setPlowed(false);
        tiles[x][y].setWalkable(false);
        tiles[x][y].setSymbol(temp.getSymbol());
        tiles[x][y].setFertilizer(null);

        return true;
    }

    // this method should call every day.
    public void GotThunderByStormyWeather() {
        if (App.getGame().getTime().getWeather().equals(Weather.Stormy)) {
            int x = rand.nextInt(250);
            int y = rand.nextInt(200);
            Tile tile = findTile(x, y);
            if (tile != null) {
                if (tile.getPlaceable() instanceof Tree tree) {
                    tile.setWalkable(true);
                    tile.setGotThunder(true);
                    tile.setFertilizer(null);
                    tile.setPlaceable(null);
                    tile.setSymbol('.');
                    App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(ForagingMineral.Coal, 1);
                }
            }
        }
    }

    public boolean isAroundPlaceable(Player p, Placeable placeable) {
        if (p.getPosition().getX() - 1 >= 0 && p.getPosition().getX() + 1 <= 250 && p.getPosition().getY() - 1 >= 0
                && p.getPosition().getY() + 1 <= 200) {

            for (int i = p.getPosition().getX() - 1; i <= p.getPosition().getX() + 1; i++) {
                for (int j = p.getPosition().getY() - 1; j <= p.getPosition().getY() + 1; j++) {
                    if (tiles[i][j].getPlaceable() != null &&
                            tiles[i][j].getPlaceable().equals(placeable)) {
                        return true;
                    }
                }
            }
            return false;
        }

        return false;
    }
}