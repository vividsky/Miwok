package com.example.miwok;

public class Word {

    private String hindiTranslation;
    private String englishTranslation;
    private int imageResourceId = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;
    private int mediaResourceId;

    public int getMediaResourceId() {
        return mediaResourceId;
    }

    public Word(String english, String hindi, int imageResourceId, int mediaResourceId) {

        this.hindiTranslation = hindi;
        this.englishTranslation = english;
        this.imageResourceId = imageResourceId;
        this.mediaResourceId = mediaResourceId;

    }
    public Word(String english, String hindi, int mediaResourceId) {

        this.hindiTranslation = hindi;
        this.englishTranslation = english;
        this.mediaResourceId = mediaResourceId;
    }


    public String getHindiTranslation() {
        return this.hindiTranslation;
    }

    public String getDefaultTranslation() {
        return this.englishTranslation;
    }

    public int getImageResourceId() { return imageResourceId; }

    public boolean hasImage() {
        return NO_IMAGE_PROVIDED != this.imageResourceId;
    }

}
