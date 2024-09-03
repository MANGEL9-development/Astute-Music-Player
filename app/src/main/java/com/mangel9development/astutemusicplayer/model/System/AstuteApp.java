package com.mangel9development.astutemusicplayer.model.System;

import com.mangel9development.astutemusicplayer.model.Library;
import com.mangel9development.astutemusicplayer.model.System.Settings.Settings;

/**
 * This is the core class for this app
 */
public class AstuteApp{
    /**
     * This class is responsible for loading the app at startup.<br/>
     * This is the current loading process:
     * TODO: finish the loading process
     * <ul>
     *     <li>Load Library
     *      <ul>
     *          <li>Load songs</li> // This also loads Artists, Albums, and Genres.
     *          <li>Load playlists</li>
     *      </ul>
     *     </li>
     *     <li>Load Settings</li>
     * </ul>
     * Note: ordered lists represent steps that are completed in a sequence, while unordered
     *  lists represent steps that can be completed concurrently
     */
    public static class Loader{
        /**
         * This is the number of completed steps in the loading process. Every time a loading
         * step has been completed, this number is incremented by 1. This number can be used in
         * the loading activity to animate the loading bar.<br />
         * This value should never be greater than {@link #TOTAL_LOADING_STEPS}
         */
        private static int loadingStepsCompleted;
        /**
         * This is the total number of steps in the loading process.
         * <br />
         * 1 is a placeholder, and needs to be replaced by the true amount of steps once that is
         * figured out.
         */
        private static final int TOTAL_LOADING_STEPS=1;

        /**
         * Begins the loading process
         */
        public static void loadApp(){
            Library.loadLibrary();
            Settings.loadSettings();
        }



    }
}
