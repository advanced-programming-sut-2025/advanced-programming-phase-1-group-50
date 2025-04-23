package models.foraging;

import models.date.Season;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MixedSeeds {
    private static final HashMap<Season, ArrayList<CropType>> seasonSeeds = new HashMap<>();

    static {
        seasonSeeds.put(Season.Spring, new ArrayList<CropType>(Arrays.asList(CropType.Cauliflower, CropType.Parsnip,
                CropType.Potato, CropType.BlueJazz, CropType.Tulip)));
        seasonSeeds.put(Season.Summer, new ArrayList<CropType>(Arrays.asList(CropType.Corn, CropType.HotPepper,
                CropType.Radish, CropType.Wheat, CropType.Poppy, CropType.Sunflower, CropType.SummerSpangle)));
        seasonSeeds.put(Season.Fall, new ArrayList<CropType>(Arrays.asList(CropType.Artichoke, CropType.Corn,
                CropType.Eggplant, CropType.Pumpkin, CropType.Sunflower, CropType.FairyRose)));
        seasonSeeds.put(Season.Winter, new ArrayList<CropType>(Arrays.asList(CropType.Powdermelon)));
    }


    public ArrayList<CropType> getSeasonCrops(Season season) {
        return new ArrayList<>();
    }
}
