package org.annakhuseinova.userservice.service;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.annakhuseinova.movie.UserGenreUpdateRequest;
import org.annakhuseinova.movie.UserResponse;
import org.annakhuseinova.movie.UserSearchRequest;
import org.annakhuseinova.movie.UserServiceGrpc;

/*
*  @GrpcService - marks gRPC services that should be registered with a gRPC server.
*  If spring-boot's auto-configuration is used, then the server will be created automatically
* */
@GrpcService
public class UserService extends UserServiceGrpc.UserServiceImplBase {

    @Override
    public void getUserGenre(UserSearchRequest request, StreamObserver<UserResponse> responseObserver) {
        super.getUserGenre(request, responseObserver);
    }

    @Override
    public void updateUserGenre(UserGenreUpdateRequest request, StreamObserver<UserResponse> responseObserver) {
        super.updateUserGenre(request, responseObserver);
    }
}
