package com.stardew.model;

import com.stardew.model.animals.AnimalState;

import java.util.HashMap;
import java.util.Map;

public class AnimalStateToAnimationIDMapper {
    public static Map<AnimalState , AnimationID> chickenStates = new HashMap<>();
    static {
        chickenStates.put(AnimalState.IN_HABITAT , null);
        chickenStates.put(AnimalState.IN_FARM_EATING , AnimationID.chicken_in_farm_eating);
        chickenStates.put(AnimalState.MOVING_UP , AnimationID.chicken_move_up);
        chickenStates.put(AnimalState.MOVING_RIGHT , AnimationID.chicken_move_right);
        chickenStates.put(AnimalState.MOVING_LEFT , AnimationID.chicken_move_left);
        chickenStates.put(AnimalState.MOVING_DOWN , AnimationID.chicken_move_down);

    }
    public static Map<AnimalState , AnimationID> duckStates = new HashMap<>();
    static {
        duckStates.put(AnimalState.IN_HABITAT , null);
        duckStates.put(AnimalState.IN_FARM_EATING , AnimationID.duck_in_farm_eating);
        duckStates.put(AnimalState.MOVING_UP , AnimationID.duck_move_up);
        duckStates.put(AnimalState.MOVING_RIGHT , AnimationID.duck_move_right);
        duckStates.put(AnimalState.MOVING_LEFT , AnimationID.duck_move_left);
        duckStates.put(AnimalState.MOVING_DOWN , AnimationID.duck_move_down);
    }

    public static Map<AnimalState , AnimationID> rabbitStates = new HashMap<>();
    static {
        rabbitStates.put(AnimalState.IN_HABITAT , null);
        rabbitStates.put(AnimalState.IN_FARM_EATING , AnimationID.rabbit_in_farm_eating);
        rabbitStates.put(AnimalState.MOVING_UP , AnimationID.rabbit_move_up);
        rabbitStates.put(AnimalState.MOVING_RIGHT , AnimationID.rabbit_move_right);
        rabbitStates.put(AnimalState.MOVING_LEFT , AnimationID.rabbit_move_left);
        rabbitStates.put(AnimalState.MOVING_DOWN , AnimationID.rabbit_move_down);
    }

    public static Map<AnimalState , AnimationID> dinosaurStates = new HashMap<>();
    static {
        dinosaurStates.put(AnimalState.IN_HABITAT , null);
        dinosaurStates.put(AnimalState.IN_FARM_EATING , AnimationID.dinosaur_in_farm_eating);
        dinosaurStates.put(AnimalState.MOVING_UP , AnimationID.dinosaur_move_up);
        dinosaurStates.put(AnimalState.MOVING_RIGHT , AnimationID.dinosaur_move_right);
        dinosaurStates.put(AnimalState.MOVING_LEFT , AnimationID.dinosaur_move_left);
        dinosaurStates.put(AnimalState.MOVING_DOWN , AnimationID.dinosaur_move_down);
    }

    public static Map<AnimalState , AnimationID> cowStates = new HashMap<>();
    static {
        cowStates.put(AnimalState.IN_HABITAT , null);
        cowStates.put(AnimalState.IN_FARM_EATING , AnimationID.cow_in_farm_eating);
        cowStates.put(AnimalState.MOVING_UP , AnimationID.cow_move_up);
        cowStates.put(AnimalState.MOVING_RIGHT , AnimationID.cow_move_right);
        cowStates.put(AnimalState.MOVING_LEFT , AnimationID.cow_move_left);
        cowStates.put(AnimalState.MOVING_DOWN , AnimationID.cow_move_down);
    }

    public static Map<AnimalState , AnimationID> goatStates = new HashMap<>();
    static {
        goatStates.put(AnimalState.IN_HABITAT , null);
        goatStates.put(AnimalState.IN_FARM_EATING , AnimationID.goat_in_farm_eating);
        goatStates.put(AnimalState.MOVING_UP , AnimationID.goat_move_up);
        goatStates.put(AnimalState.MOVING_RIGHT , AnimationID.goat_move_right);
        goatStates.put(AnimalState.MOVING_LEFT , AnimationID.goat_move_left);
        goatStates.put(AnimalState.MOVING_DOWN , AnimationID.goat_move_down);
    }

    public static Map<AnimalState , AnimationID> sheepStates = new HashMap<>();
    static {
        sheepStates.put(AnimalState.IN_HABITAT , null);
        sheepStates.put(AnimalState.IN_FARM_EATING , AnimationID.sheep_in_farm_eating);
        sheepStates.put(AnimalState.MOVING_UP , AnimationID.sheep_move_up);
        sheepStates.put(AnimalState.MOVING_RIGHT , AnimationID.sheep_move_right);
        sheepStates.put(AnimalState.MOVING_LEFT , AnimationID.sheep_move_left);
        sheepStates.put(AnimalState.MOVING_DOWN , AnimationID.sheep_move_down);
    }

    public static Map<AnimalState , AnimationID> pigStates = new HashMap<>();
    static {
        pigStates.put(AnimalState.IN_HABITAT , null);
        pigStates.put(AnimalState.IN_FARM_EATING , AnimationID.pig_in_farm_eating);
        pigStates.put(AnimalState.MOVING_UP , AnimationID.pig_move_up);
        pigStates.put(AnimalState.MOVING_RIGHT , AnimationID.pig_move_right);
        pigStates.put(AnimalState.MOVING_LEFT , AnimationID.pig_move_left);
        pigStates.put(AnimalState.MOVING_DOWN , AnimationID.pig_move_down);
    }
}
