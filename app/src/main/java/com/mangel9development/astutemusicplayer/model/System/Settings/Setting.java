package com.mangel9development.astutemusicplayer.model.System.Settings;

/**
 * This class defines a setting. Each setting holds a default value that is only to be set by
 * the developer. A setting also holds a set value, this is the value that the user has set.
 * @param <Type> This is the type of setting this class defines. This could be anything from a
 *              boolean (for example, whether the app updates a queue when its playlist is
 *              updated), or a theme.
 */
public class Setting<Type>{
    private final Type DEFAULT_VALUE;
    private Type setValue;

    /*
        TODO: some settings represent numeric values. Those settings often have a range of possible
            values. Find a way to include a minimum and maximum value, but only for numeric
            settings.
     */

    public Setting(Type defaultValue){
        DEFAULT_VALUE=defaultValue;
        setValue=defaultValue;
    }

    /**
     * @return this settings default value
     */
    public Type getDEFAULT_VALUE(){
        return DEFAULT_VALUE;
    }

    /**
     * @return the value of this setting which the user has set. By default, this is set to the
     * default value
     */
    public Type getSetValue(){
        return setValue;
    }

    /**
     * Changes the value
     * @param setValue the new value of this setting
     */
    public void setValue(Type setValue){
        // TODO: this might be where you output the serialization
        this.setValue=setValue;
    }
}
