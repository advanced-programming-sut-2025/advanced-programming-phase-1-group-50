package com.stardew.models.foraging;

import com.stardew.models.date.Season;

import java.util.HashMap;

public enum ForagingTreeSource {
    Acorns(Season.Special),
    MapleSeeds(Season.Special),
    PineCones(Season.Special),
    MahoganySeeds(Season.Special),
    MushroomTreeSeeds(Season.Special),;

    private final Season season;
    private final static HashMap<String, ForagingTreeSource> stringToForagingTreeSource = new HashMap<>();

    static {
        for (ForagingTreeSource value : ForagingTreeSource.values()) {
            stringToForagingTreeSource.put(value.name().toLowerCase(), value);
        }
    }

    ForagingTreeSource(Season season) {
        this.season = season;
    }

    public String getName() {
        return name();
    }

    public Season getSeason() {
        return season;
    }

    public static ForagingTreeSource getForagingTreeSourceByName(String name) {
        if (name == null || name.isEmpty())
            return null;
        return stringToForagingTreeSource.getOrDefault(name.toLowerCase(), null);
    }
}
