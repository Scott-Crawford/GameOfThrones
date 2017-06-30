package com.example.scott.gameofthrones;

import java.util.Date;


public class Episode {
    private int mId;
    private String mTitle;
    private Date mReleaseDate;
    private int mEpisodeNum;
    private int mSeasonNum;
    private String mOverview;
    private double mVoteAverage;


    public Episode(int id, String title, Date releaseDate, int episodeNum, int seasonNum, String overview, double voteAverage) {
        mId = id;
        mTitle = title;
        mReleaseDate = releaseDate;
        mEpisodeNum = episodeNum;
        mSeasonNum = seasonNum;
        mOverview = overview;
        mVoteAverage = voteAverage;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) { mId = id; }

    public Date getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        mReleaseDate = releaseDate;
    }

    public int getEpisodeNum() {
        return mEpisodeNum;
    }

    public void setEpisodeNum(int episodeNum) {
        mEpisodeNum = episodeNum;
    }

    public int getSeasonNum() {
        return mSeasonNum;
    }

    public void setSeasonNum(int seasonNum) {
        mSeasonNum = seasonNum;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    public double getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        mVoteAverage = voteAverage;
    }


}
