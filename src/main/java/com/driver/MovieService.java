package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    public void addMovie(Movie movie){
        movieRepository.addMovie(movie);
    }
    public void addDirector(Director director){
        movieRepository.addDirector(director);
    }
    public void addMovieDirectorPair(String movie, String director){
        movieRepository.addMovieDirectorPair(movie,director);
    }
    public Movie findMovie(String movieName){
        return movieRepository.findMovie(movieName);
    }
    public Director findDirector(String directorName){
        return movieRepository.findDirector(directorName);
    }
    public List<String> movieByDirectorName(String directorName){
        return movieRepository.movieByDirectorName(directorName);
    }
    public List<String> findAllMovies(){
        return movieRepository.findAllMovies();
    }
    public void deleteDirectorByName(String directorName){
        movieRepository.deleteDirectorByName(directorName);
    }
    public void deleteAllDirectors(){
        movieRepository.deleteAllDirectors();
    }

}
