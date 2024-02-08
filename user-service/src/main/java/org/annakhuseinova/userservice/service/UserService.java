package org.annakhuseinova.userservice.service;

import io.grpc.stub.StreamObserver;
import org.annakhuseinova.common.Genre;
import net.devh.boot.grpc.server.service.GrpcService;
import org.annakhuseinova.movie.UserGenreUpdateRequest;
import org.annakhuseinova.movie.UserResponse;
import org.annakhuseinova.movie.UserSearchRequest;
import org.annakhuseinova.movie.UserServiceGrpc;
import org.annakhuseinova.userservice.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

/*
*  @GrpcService - marks gRPC services that should be registered with a gRPC server.
*  If spring-boot's auto-configuration is used, then the server will be created automatically
* */
@GrpcService
public class UserService extends UserServiceGrpc.UserServiceImplBase {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void getUserGenre(UserSearchRequest request, StreamObserver<UserResponse> responseObserver) {
        UserResponse.Builder builder = UserResponse.newBuilder();
        this.userRepository.findById(request.getLoginId()).ifPresent(user -> {
            builder.setName(user.getName())
                    .setLoginId(user.getLogin())
                    .setGenre(Genre.valueOf(user.getGenre().toUpperCase()));
        });
        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }

    @Transactional
    @Override
    public void updateUserGenre(UserGenreUpdateRequest request, StreamObserver<UserResponse> responseObserver) {
        UserResponse.Builder builder = UserResponse.newBuilder();
        this.userRepository.findById(request.getLoginId())
                .ifPresent(user -> {
                    builder.setName(user.getName())
                            .setLoginId(user.getLogin())
                            .setGenre(Genre.valueOf(user.getGenre().toUpperCase()));
                });
        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }
}
