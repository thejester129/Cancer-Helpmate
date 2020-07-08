package com.example.cancerhelpmate.ui.activities.sleep_aid;

import org.jetbrains.annotations.NotNull;

public class SleepAidLevel {
        private String name;
        private int picture;
        private int sound;

        public SleepAidLevel(@NotNull String name,int picture, int sound) {
            this.name = name;
            this.picture = picture;
            this.sound = sound;
        }

        @NotNull
        public String getName() {
            return name;
        }

        public void setName(@NotNull String name) {
            this.name = name;
        }

        public int getSound() {
            return sound;
        }

        public void setSound(int sound) {
            this.sound = sound;
        }

        public int getPicture() {
            return picture;
        }

        public void setPicture(int picture) {
            this.picture = picture;
        }

}
