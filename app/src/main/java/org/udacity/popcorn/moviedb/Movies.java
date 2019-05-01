package org.udacity.popcorn.moviedb;

import com.google.gson.annotations.SerializedName;

import java.util.List;

class Movies {
    @SerializedName("results")
    private List<Movie> list;

    public final int count() {
        return list.size();
    }

    public final Movie get(int i) {
        return list.get(i);
    }
}
