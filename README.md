## 1. maven dependencies

The maven dependencies are same with server project.

Just copy those dependencies from server project to client project.

## 2. proto file

The proto file is the contract between client and server.

Just copy the proto file from server project to client project.
So that, the client can communicate with the server apis with codes 
generated from the proto file.

## 3. client

### 3.1 channel (make connection)

The grpc library provides channel, that the client
can connect the service and access the apis using channel.

`ManagedChannelBuilder.forAddress("localhost", 9091)`

with no security method `usePlaintext()`

### 3.2 stub (access apis)

#### 3.2.1 The stub is generated from proto file.

Using `newBlockingStub()`:

Whenever the client sends the request, the client will wait for the server 
response. It means that the client cannot do further process.

Using `newFutureStub()`:

It is completable feature concept.

Using `newStub()`:

It is a synchronous call. Once the client makes a request to the api,
it will not wait for the response from the server. It will do the further process.
Once the server sends the response it will capture the response from the server.

#### 3.2.2 construct the login request using builder class

`User.LoginRequest loginRequest = User.LoginRequest.newBuilder().setUsername("nick").setPassword("npass").build();`

#### 3.2.3 send request and get response

`ser.APIResponse apiResponse = userBlockingStub.login(loginRequest);`



## Ref

[grpc server java](https://github.com/ppdouble/grpc-server-java)

[grpc client java](https://github.com/ppdouble/grpc-client-java)

[gRPC Java Client App Implementation](https://www.youtube.com/watch?v=J0AMX9YpdLk)

[grpc sample on github](https://github.com/techtter/grpc)



