package com.example.demo.constants;

public class Constants {

    public enum YTPlayer {
        ENDED(0),
        PLAYING(1),
        PAUSED(2),
        BUFFERING(3),
        CUED(5);

        private int code;

        YTPlayer(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }
}
