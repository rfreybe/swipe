package de.fhe.ai.pme.swipe.storage;

import androidx.room.TypeConverter;

import de.fhe.ai.pme.swipe.model.Folder.Color;
import de.fhe.ai.pme.swipe.model.Card.Rating;

/*
    TypeConverter class used for storing custom data types in the Room database
    Room doesn't support object references
 */
public class Converters {
    @TypeConverter
    public static Color stringToColor(String string) {
        switch(string) {
            case ("blue"):
                return Color.BLUE;
            case ("red"):
                return Color.RED;
            case ("green"):
                return Color.GREEN;
            case ("orange"):
                return Color.ORANGE;
            case ("yellow"):
                return Color.YELLOW;
            default:
                return Color.GREY;
        }
    }

    @TypeConverter
    public static String colorToString(Color color) {
        return color.toString();
    }

    @TypeConverter
    public static Rating stringToRating(String string) {
        switch(string) {
            case("green"):
                return Rating.GREEN;
            case("yellow"):
                return Rating.YELLOW;
            default:
                return Rating.RED;
        }
    }

    @TypeConverter
    public static String ratingToString(Rating rating) {
        return rating.toString();
    }
}
