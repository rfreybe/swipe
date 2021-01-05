package de.fhe.ai.pme.swipe.storage;

import androidx.room.TypeConverter;

import de.fhe.ai.pme.swipe.model.Color;
import de.fhe.ai.pme.swipe.model.Rating;

/*
    TypeConverter class used for storing custom data types in the Room database
    Room doesn't support object references
 */
public class Converters {
    @TypeConverter
    public static Color stringToColor(String string) {
        switch(string) {
            case ("blue"):
                return Color.blue;
            case ("red"):
                return Color.red;
            case ("green"):
                return Color.green;
            case ("orange"):
                return Color.orange;
            case ("yellow"):
                return Color.yellow;
            default:
                return Color.grey;
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
                return Rating.green;
            case("yellow"):
                return Rating.yellow;
            default:
                return Rating.red;
        }
    }

    @TypeConverter
    public static String ratingToString(Rating rating) {
        return rating.toString();
    }
}
