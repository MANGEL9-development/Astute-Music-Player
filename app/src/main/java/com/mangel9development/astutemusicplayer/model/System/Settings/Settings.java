package com.mangel9development.astutemusicplayer.model.System.Settings;

import java.io.Serializable;

/**
 * This class holds all the settings for this app. These settings can be changed by the user and
 * those changes will be saved even when the app is closed.
 */
public class Settings implements Serializable{
    private Settings(){
        // make this class uninstantiable
    }

    /**
     * Loads the settings. This is to be called when the app is loading.
     */
    public static void loadSettings(){
        // TODO: write code to get the settings that were serialized
    }

    /**
     * Saves the settings. This is to be called when settings are changed.
     */
    public static void saveSettings(){
        // TODO: write code to serialize the settings
    }

    /**
     * This holds the settings that affect the playback of music.
     */
    public static class Playback{
        private Playback(){
            // make this class uninstantiable
        }

        public static class Crossfade{
            private Crossfade(){
                // make this class uninstantiable
            }

            public final Setting<Ability> crossfadeAbility=new Setting<>(Ability.DISABLED);
            // TODO: make a setting that defines the crossfade time once you figure out range
            //  settings
        }

        /**
         * This holds the settings that affect the queue.
         */
        public static class Queue{
            private Queue(){
                // make this class uninstantiable
            }

            /**
             * This sets whether the queue is updated when its base media is changed. For example,
             * if the queue is playing from a playlist, any song added/removed from that playlist
             * will be added/removed from the queue.
             */
            public final Setting<Boolean> updateQueueOnBaseMediaChange=new Setting<>(false);
        }

    }



}
