package com.nemanja.moviedb.Fragments;

import com.nemanja.moviedb.Helpers.Constants;
import com.nemanja.moviedb.Data.MoviesList;
import com.nemanja.moviedb.API.TmdbRestClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//not yet implemented
public class SearchMoviesFragment extends MoviesListFragment {
  public static final String TAG = PopularMoviesFragment.class.getName();

  public SearchMoviesFragment() {
  }

  @Override
  protected void loadMovies() {
    super.loadMovies();
    Call<MoviesList> call = TmdbRestClient.getInstance()
      .getPopularMoviesImpl()
      .getPopularMovies(getPage());
    Callback<MoviesList> callback = new Callback<MoviesList>() {
      @Override
      public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
        if (!response.isSuccessful()) {
          retrievalError(Constants.SERVER_ERROR);
          return;
        }
        setTotalPages(response.body().getTotalPages());
        addMovies(response.body().getMovies());
      }

      @Override
      public void onFailure(Call<MoviesList> call, Throwable t) {
        retrievalError(Constants.NETWORK_ERROR);
      }
    };
    call.enqueue(callback);
  }
}

