package com.mangel9development.astutemusicplayer.model.Aggregation;

/**
 * An aggregant represents an element that can be grouped together using an {@link Aggregator}.
 */
public interface Aggregant{
    /**
     * @return the name of this Aggregant. This is the name that is displayed in the UI.
     */
    String getName();
}
