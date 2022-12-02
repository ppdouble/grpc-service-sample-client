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

Creates a new ListenableFuture-style stub that supports unary calls on the service.

It is completable feature concept. 

Using `newStub()`:

It is a synchronous call. Once the client makes a request to the api,
it will not wait for the response from the server. It will do the further process.
Once the server sends the response it will capture the response from the server.

#### 3.2.2 construct the login request using builder class

`User.LoginRequest loginRequest = User.LoginRequest.newBuilder().setUsername("nick").setPassword("npass").build();`

#### 3.2.3 send request and get response

`ser.APIResponse apiResponse = userBlockingStub.login(loginRequest);`

## Exception

If the server does not register the service in `addService()`, it will get exception `io.grpc.StatusRuntimeException: UNIMPLEMENTED: Method not found`

```java
Exception in thread "main" io.grpc.StatusRuntimeException: UNIMPLEMENTED: Method not found: user/login
	at io.grpc.stub.ClientCalls.toStatusRuntimeException(ClientCalls.java:240)
	at io.grpc.stub.ClientCalls.getUnchecked(ClientCalls.java:221)
	at io.grpc.stub.ClientCalls.blockingUnaryCall(ClientCalls.java:140)
	at nd.pemo.grpcclientsample.userGrpc$userBlockingStub.login(userGrpc.java:211)
	at nd.pemo.client.GRPCClient.main(GRPCClient.java:31)
```

or from BloomRPC:

```json
{
  "error": "12 UNIMPLEMENTED: Method not found: user/login"
}
```

See `Status status = Status.UNIMPLEMENTED.withDescription("Method not found: " + methodName);`
in `grpc-core-1.27.0.jar!/io/grpc/internal/ServerImpl.class`
## Ref


[grpc server cpp demo](https://github.com/ppdouble/grpc-cpp-server-sample)

[grpc client cpp demo](https://github.com/ppdouble/grpc-cpp-client-sample)

[grpc client with springboot demo](https://github.com/ppdouble/springboot-grpc-client-sample)

[grpc server with springboot demo](https://github.com/ppdouble/springboot-grpc-server-sample)

[grpc client java demo](https://github.com/ppdouble/grpc-service-sample-client)

[grpc server java demo](https://github.com/ppdouble/grpc-service-pemo)

[sprintboot with grpc server. Fork from nils](https://github.com/ppdouble/city-score)

[gRPC Java Client App Implementation](https://www.youtube.com/watch?v=J0AMX9YpdLk)

[grpc sample on github](https://github.com/techtter/grpc)



