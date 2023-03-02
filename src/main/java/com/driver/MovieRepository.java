package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Repository
public class MovieRepository {

    private HashMap<String, Movie>movieMap;
    private HashMap<String, Director>directorMap;
    private HashMap<String, List<String>>directorMovieMapping;

    public MovieRepository(){
        this.movieMap = new HashMap<String, Movie>();
        this.directorMap = new HashMap<String, Director>();
        this.directorMovieMapping = new HashMap<String, List<String>>();
    }

    public void addMovie(Movie movie){
        movieMap.put(movie.getName(), movie);
    }

    // 2 Add a director
    public void addDirector(Director director){
        directorMap.put(director.getName(),director);
    }

    // 3 Pair an existing movie and director
    public void addMovieDirectorPair(String movie, String director){
        if(movieMap.containsKey(movie) && directorMap.containsKey(director)){
            movieMap.put(movie, movieMap.get(movie));
            directorMap.put(director, directorMap.get(director));

            List<String> currentMovies = new ArrayList<String>();

            if(directorMovieMapping.containsKey(director)) currentMovies = directorMovieMapping.get(director);
            currentMovies.add(movie);

            directorMovieMapping.put(director, currentMovies);
        }
    }

    // 4 Get Movie by movie name
    public Movie findMovie(String movieName){
        return movieMap.get(movieName);
    }

    // 5 Get Director by director name
    public Director findDirector(String directorName){
        return directorMap.get(directorName);
    }

    // 6 Get List of movies name for a given director name
    public List<String> movieByDirectorName(String directorName){
        List<String> moviesList = new ArrayList<>();
        if(directorMovieMapping.containsKey(directorName)) moviesList = directorMovieMapping.get(directorName);
        return moviesList;
    }

    // 7 Get List of all movies added
    public List<String> findAllMovies(){
        return new ArrayList<>(movieMap.keySet());
    }

    // 8 Delete a director and its movies from the records
    public void deleteDirectorByName(String directorName) {
        if (directorMap.containsKey(directorName))
            directorMap.remove(directorName);

        List<String> movies = new ArrayList<>();

        if (directorMovieMapping.containsKey(directorName)) {
            movies = directorMovieMapping.get(directorName);
            directorMovieMapping.remove(directorName);

            for (String movie : movies) {
                if (movieMap.containsKey(movie))
                    movieMap.remove(movie);
            }
        }
    }

    // 9 Delete all directors and all movies by them from the records
    public void deleteAllDirectors(){
        HashSet<String> moviesSet = new HashSet<>();

        for(String director : directorMovieMapping.keySet()){
            for(String movie : directorMovieMapping.get(director)){
                moviesSet.add(movie);
                }
            }
        for(String movie : moviesSet){
            if(movieMap.containsKey(movie))
                movieMap.remove(movie);
            }
        for(String dir : directorMap.keySet())
            directorMap.remove(dir);
    }
}
