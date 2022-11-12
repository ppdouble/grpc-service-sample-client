package nd.pemo.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import nd.pemo.grpcclientsample.User;
import nd.pemo.grpcclientsample.userGrpc;

public class GRPCClient {

    public static void main(String args[]) {

        // create connection
        ManagedChannel managedChannel
                = ManagedChannelBuilder.forAddress("localhost", 9901)
                .usePlaintext()
                .build();

        // access api
        userGrpc.userBlockingStub userBlockingStub = userGrpc.newBlockingStub(managedChannel);

        // construct the login request using builder class
        User.LoginRequest loginRequest
                = User.LoginRequest
                .newBuilder()
                .setUsername("nick")
                .setPassword("ndpass")
                .build();

        // sending request using stub
        // get the response from the User stub
        User.APIResponse apiResponse = userBlockingStub.login(loginRequest);
        System.out.println("Response: " + apiResponse.getResponsemessage());

    }
}
