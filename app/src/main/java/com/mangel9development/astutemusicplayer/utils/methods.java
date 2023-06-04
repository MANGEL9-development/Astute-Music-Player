package com.mangel9development.astutemusicplayer.utils;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.Contract;

public class methods{
    private methods(){}

    /**
     * Computes and returns the average of a and b. This is safer because it prevents a sum
     * greater than {@link Integer#MAX_VALUE}.
     * @param a a number
     * @param b another number
     * @return the average of a and b
     */
    public static int safeMid(int a, int b){
        return (a + ((b-a)/2));
    }

    /**
     * Creates a color from a word. The way this algorithm works, the same word will always produce
     * the same color. A different word, no matter how similar, would create a different color. If
     * the string contains multiple words (separated by a space), each word will have half as much
     * "strength" as the next word.
     * @param word a word (or phrase)
     * @return a color in HEX format that was generated from the passed word(s).
     */
    @NonNull
    @Contract(pure = true)
    public static String makeColorFromWord(String word){
        // TODO: make an algorithm that converts the name of the genre to a color

        // NOTE: In English, the "main" adjective is the last one, meaning that the previous
        //  adjective describe the next adjective. For example, "Chill Tropical" describes a
        //  Tropical song that has a chill vibe. This means that the last word should be the
        //  strongest when mixing colors. However, the direction is opposite in most other
        //  languages. So when creating the algorithm, consider adding an option to flip the
        //  order of strength to be more appropriate for other languages.
        return "#000000";
    }
}
