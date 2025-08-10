package com.stardew.model.NPCs;

public class PromptBuilder {
    private final String npcName;
    private final String season;
    private final String weather;
    private final int friendshipLevel;
    private final String occupation;
    private final String playerAction;
    private final String dayAndTime;
    private final String playerInput;

    public PromptBuilder(String npcName, String season, String weather, int friendshipLevel, String occupation, String playerAction, String dayAndTime, String playerInput) {
        this.npcName = npcName;
        this.season = season;
        this.weather = weather;
        this.friendshipLevel = friendshipLevel;
        this.occupation = occupation;
        this.playerAction = playerAction;
        this.dayAndTime = dayAndTime;
        this.playerInput = playerInput;
    }


    public String build() {
        return String.format(
            "You are role-playing as an NPC named %s.\n" +
                "Current game conditions:\n" +
                "- Season: %s\n" +
                "- Weather: %s\n" +
                "- Friendship level with player: %d (1 = distant, 5 = very close)\n" +
                "- NPC's occupation: %s\n" +
                "- Player's recent activity: %s\n" +
                "- Day and time in the game: %s\n\n" +
                "Rules:\n" +
                "1. Respond with a short, natural dialogue (maximum 2 sentences).\n" +
                "2. Match your tone to the friendship level:\n" +
                "   - Level 1-2: Slightly formal or reserved.\n" +
                "   - Level 3-4: Warm and friendly.\n" +
                "   - Level 5: Very close, casual, and personal.\n" +
                "3. Reference the season, weather, or your occupation naturally in the conversation.\n" +
                "4. Make the dialogue creative and varied every time (avoid repeating phrases).\n" +
                "5. Keep language simple and understandable.\n" +
                "6. Output only the dialogue, without explanations.\n" +
                "7. Consider the player's recent activity when responding.\n\n" +
                "Player says: %s",
            npcName, season, weather, friendshipLevel, occupation, playerAction, dayAndTime, playerInput
        );
    }
}
