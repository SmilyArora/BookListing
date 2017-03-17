package com.smi.android.booklist;

/**
 * Created by Smily on 3/17/2017.
 */

public class Book {
    private String mTitle;
    private String mAuthor;
    private double mRating;

    public Book(String title, String author, double rating){
        mTitle = title;
        mAuthor = author;
        mRating = rating;
    }

    public String getTitle(){
        return mTitle;
    }

    public String getAuthor(){
        return mAuthor;
    }

    public double getRating(){
        return mRating;
    }
}
