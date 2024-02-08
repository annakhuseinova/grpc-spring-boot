package org.annakhuseinova.aggregator.service;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.annakhuseinova.aggregator.dto.RecommendedMovie;
import org.annakhuseinova.aggregator.dto.UserGenre;
import org.annakhuseinova.movie.MovieSearchRequest;
import org.annakhuseinova.movie.MovieSearchResponse;
import org.annakhuseinova.movie.MovieServiceGrpc;
import org.annakhuseinova.movie.UserGenreUpdateRequest;
import org.annakhuseinova.movie.UserResponse;
import org.annakhuseinova.movie.UserSearchRequest;
import org.annakhuseinova.movie.UserServiceGrpc;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMovieService {

    @GrpcClient("user-service")
    private UserServiceGrpc.UserServiceBlockingStub userStub;
    @GrpcClient("movie-service")
    private MovieServiceGrpc.MovieServiceBlockingStub movieStub;

    public List<RecommendedMovie> getUserMovieSuggestions(String loginId){
        UserSearchRequest userSearchRequest = UserSearchRequest.newBuilder().setLoginId(loginId).build();
        UserResponse userResponse = this.userStub.getUserGenre(userSearchRequest);
        org.annakhuseinova.movie.MovieSearchRequest movieSearchRequest = MovieSearchRequest.newBuilder().setGenre(userResponse.getGenre()).build();
        MovieSearchResponse movieSearchResponse = this.movieStub.getMovies(movieSearchRequest);
        return movieSearchResponse.getMovieList()
                .stream()
                .map(movieDto -> new RecommendedMovie(movieDto.getTitle(), movieDto.getYear(), movieDto.getRating()))
                .collect(Collectors.toList());
    }

    public void setUserGenre(UserGenre userGenre){
        UserGenreUpdateRequest userGenreUpdateRequest = UserGenreUpdateRequest.newBuilder()
                .setLoginId(userGenre.getLoginId())
                .setGenre(org.annakhuseinova.common.Genre.valueOf(userGenre.getGenre().toUpperCase()))
                .build();
        UserResponse userResponse = this.userStub.updateUserGenre(userGenreUpdateRequest);
    }
}
