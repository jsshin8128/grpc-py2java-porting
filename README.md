# grpc-py2java-porting

경희대학교 풀스택서비스네트워킹 수업 과제입니다.

## 프로젝트 설명

Python으로 작성된 gRPC 코드를 Java로 포팅한 프로젝트입니다.

---

## gRPC 통신 패턴별 구현 가이드 (Python 기준)

### 1. Unary (일반 RPC)

#### 1단계: 원격 호출 함수 작성

원격으로 호출할 함수를 작성합니다.

#### 2단계: 프로토콜 버퍼 구성

원격 호출 함수 관련 서비스와 메시지를 정의합니다. `proto` 파일은 protocol buffers 표준을 따릅니다.

#### 3단계: gRPC 클래스 자동 생성

`grpc_tools.protoc`를 통해 클래스 파일들을 생성합니다.

- **메시지 클래스 파일**: proto 파일의 메시지에 대응하는 코드
- **클라이언트/서버 클래스 파일**: proto 파일의 클라이언트용 코드와 서버용 코드

#### 4단계: gRPC 서버 작성

1. `grpc/futures` 모듈을 import 합니다.
2. protoc가 생성한 클래스를 import 합니다.
3. 원격 호출될 함수들을 import 합니다.
4. protoc가 생성한 `Servicer` 클래스를 base class로 하여 원격 호출될 함수들을 멤버로 갖는 서버 클래스를 생성합니다.
5. 서버 클래스에 원격 호출될 함수에 대한 RPC 함수를 작성합니다.
6. `grpc.server`를 생성합니다.
7. `add_CalculatorServicer_to_server()`를 사용하여 `grpc.server`에 4번의 Servicer를 추가합니다.
8. `grpc.server`의 통신 포트를 열고, `start()`로 서버를 실행합니다.
9. `grpc.server`가 유지되도록 프로그램 실행을 유지합니다.

#### 5단계: gRPC Client 작성

1. `grpc` 모듈을 import 합니다.
2. protoc가 생성한 클래스를 import 합니다.
3. gRPC 통신 채널을 생성합니다.
4. protoc가 생성한 클라이언트용 코드(stub)를 채널을 사용하여 실행하여 stub을 생성합니다.
5. protoc가 생성한 메시지용 코드에 맞춰서 원격 함수에 전달할 메시지를 만들고, 전달할 값을 저장합니다.
6. 원격 함수를 stub을 통해 호출합니다.
7. 얻은 결과로 원하는 작업을 수행합니다.

---

### 2. Server Streaming (서버 스트리밍)

#### 1단계: 원격 호출 함수 작성

원격으로 호출할 함수를 작성합니다.

#### 2단계: 프로토콜 버퍼 구성

원격 호출 함수 관련 서비스와 메시지를 정의합니다. `proto` 파일은 protocol buffers 표준을 따릅니다.

**서버 스트리밍의 경우**, 반환 타입에 `stream` 키워드를 사용합니다 (예: `returns (stream Message)`).

#### 3단계: gRPC 클래스 자동 생성

`grpc_tools.protoc`를 통해 클래스 파일들을 생성합니다.

- **메시지 클래스 파일**: proto 파일의 메시지에 대응하는 코드
- **클라이언트/서버 클래스 파일**: proto 파일의 클라이언트용 코드와 서버용 코드

#### 4단계: gRPC 서버 작성

1. `grpc/futures` 모듈을 import 합니다.
2. protoc가 생성한 클래스를 import 합니다.
3. 원격 호출될 함수들을 import 합니다.
4. protoc가 생성한 `Servicer` 클래스를 base class로 하여 원격 호출될 함수들을 멤버로 갖는 서버 클래스를 생성합니다.
5. 서버 클래스에 원격 호출될 함수에 대한 RPC 함수를 작성합니다. **서버 스트리밍의 경우**, 함수 내에서 `yield`를 사용하여 여러 메시지를 순차적으로 반환합니다.
6. `grpc.server`를 생성합니다.
7. `add_ServerStreamingServicer_to_server()`를 사용하여 `grpc.server`에 4번의 Servicer를 추가합니다.
8. `grpc.server`의 통신 포트를 열고, `start()`로 서버를 실행합니다.
9. `grpc.server`가 유지되도록 프로그램 실행을 유지합니다.

#### 5단계: gRPC Client 작성

1. `grpc` 모듈을 import 합니다.
2. protoc가 생성한 클래스를 import 합니다.
3. gRPC 통신 채널을 생성합니다.
4. protoc가 생성한 클라이언트용 코드(stub)를 채널을 사용하여 실행하여 stub을 생성합니다.
5. protoc가 생성한 메시지용 코드에 맞춰서 원격 함수에 전달할 메시지를 만들고, 전달할 값을 저장합니다.
6. 원격 함수를 stub을 통해 호출합니다. **서버 스트리밍의 경우**, 반환값이 이터레이터(iterator)이므로 `for` 루프를 사용하여 스트림으로 받은 여러 응답을 순차적으로 처리합니다.
7. 얻은 결과로 원하는 작업을 수행합니다.

---

### 3. Client Streaming (클라이언트 스트리밍)

#### 1단계: 원격 호출 함수 작성

원격으로 호출할 함수를 작성합니다.

#### 2단계: 프로토콜 버퍼 구성

원격 호출 함수 관련 서비스와 메시지를 정의합니다. `proto` 파일은 protocol buffers 표준을 따릅니다.

**클라이언트 스트리밍의 경우**, 입력 타입에 `stream` 키워드를 사용합니다 (예: `rpc GetServerResponse(stream Message) returns (Number)`).

#### 3단계: gRPC 클래스 자동 생성

`grpc_tools.protoc`를 통해 클래스 파일들을 생성합니다.

- **메시지 클래스 파일**: proto 파일의 메시지에 대응하는 코드
- **클라이언트/서버 클래스 파일**: proto 파일의 클라이언트용 코드와 서버용 코드

#### 4단계: gRPC 서버 작성

1. `grpc/futures` 모듈을 import 합니다.
2. protoc가 생성한 클래스를 import 합니다.
3. 원격 호출될 함수들을 import 합니다.
4. protoc가 생성한 `Servicer` 클래스를 base class로 하여 원격 호출될 함수들을 멤버로 갖는 서버 클래스를 생성합니다.
5. 서버 클래스에 원격 호출될 함수에 대한 RPC 함수를 작성합니다. **클라이언트 스트리밍의 경우**, 함수의 첫 번째 매개변수가 `request_iterator`로 전달되며, `for` 루프를 사용하여 클라이언트로부터 받은 여러 요청을 순차적으로 처리합니다. 모든 요청을 처리한 후 최종 결과를 `return`으로 반환합니다.
6. `grpc.server`를 생성합니다.
7. `add_ClientStreamingServicer_to_server()`를 사용하여 `grpc.server`에 4번의 Servicer를 추가합니다.
8. `grpc.server`의 통신 포트를 열고, `start()`로 서버를 실행합니다.
9. `grpc.server`가 유지되도록 프로그램 실행을 유지합니다.

#### 5단계: gRPC Client 작성

1. `grpc` 모듈을 import 합니다.
2. protoc가 생성한 클래스를 import 합니다.
3. gRPC 통신 채널을 생성합니다.
4. protoc가 생성한 클라이언트용 코드(stub)를 채널을 사용하여 실행하여 stub을 생성합니다.
5. protoc가 생성한 메시지용 코드에 맞춰서 원격 함수에 전달할 메시지들을 생성하는 제너레이터 함수를 작성합니다. `yield`를 사용하여 여러 메시지를 순차적으로 생성합니다.
6. 원격 함수를 stub을 통해 호출합니다. **클라이언트 스트리밍의 경우**, 5번에서 작성한 제너레이터 함수를 인자로 전달하여 여러 요청을 스트림으로 보냅니다. 서버로부터 최종 결과를 받습니다.
7. 얻은 결과로 원하는 작업을 수행합니다.

---

### 4. Bidirectional Streaming (양방향 스트리밍)

#### 1단계: 원격 호출 함수 작성

원격으로 호출할 함수를 작성합니다.

#### 2단계: 프로토콜 버퍼 구성

원격 호출 함수 관련 서비스와 메시지를 정의합니다. `proto` 파일은 protocol buffers 표준을 따릅니다.

**양방향 스트리밍의 경우**, 입력 타입과 반환 타입 모두에 `stream` 키워드를 사용합니다 (예: `rpc GetServerResponse(stream Message) returns (stream Message)`).

#### 3단계: gRPC 클래스 자동 생성

`grpc_tools.protoc`를 통해 클래스 파일들을 생성합니다.

- **메시지 클래스 파일**: proto 파일의 메시지에 대응하는 코드
- **클라이언트/서버 클래스 파일**: proto 파일의 클라이언트용 코드와 서버용 코드

#### 4단계: gRPC 서버 작성

1. `grpc/futures` 모듈을 import 합니다.
2. protoc가 생성한 클래스를 import 합니다.
3. 원격 호출될 함수들을 import 합니다.
4. protoc가 생성한 `Servicer` 클래스를 base class로 하여 원격 호출될 함수들을 멤버로 갖는 서버 클래스를 생성합니다.
5. 서버 클래스에 원격 호출될 함수에 대한 RPC 함수를 작성합니다. **양방향 스트리밍의 경우**, 함수의 첫 번째 매개변수가 `request_iterator`로 전달되며, `for` 루프를 사용하여 클라이언트로부터 받은 여러 요청을 순차적으로 처리하면서, 각 요청에 대해 `yield`를 사용하여 응답을 스트림으로 보냅니다.
6. `grpc.server`를 생성합니다.
7. `add_BidirectionalServicer_to_server()`를 사용하여 `grpc.server`에 4번의 Servicer를 추가합니다.
8. `grpc.server`의 통신 포트를 열고, `start()`로 서버를 실행합니다.
9. `grpc.server`가 유지되도록 프로그램 실행을 유지합니다.

#### 5단계: gRPC Client 작성

1. `grpc` 모듈을 import 합니다.
2. protoc가 생성한 클래스를 import 합니다.
3. gRPC 통신 채널을 생성합니다.
4. protoc가 생성한 클라이언트용 코드(stub)를 채널을 사용하여 실행하여 stub을 생성합니다.
5. protoc가 생성한 메시지용 코드에 맞춰서 원격 함수에 전달할 메시지들을 생성하는 제너레이터 함수를 작성합니다. `yield`를 사용하여 여러 메시지를 순차적으로 생성합니다.
6. 원격 함수를 stub을 통해 호출합니다. **양방향 스트리밍의 경우**, 5번에서 작성한 제너레이터 함수를 인자로 전달하여 여러 요청을 스트림으로 보냅니다. 반환값이 이터레이터(iterator)이므로 `for` 루프를 사용하여 서버로부터 받은 여러 응답을 순차적으로 처리합니다.
7. 얻은 결과로 원하는 작업을 수행합니다.

---

## Python vs Java 주요 차이점

### 1. 코드 생성 도구

| 항목 | Python | Java |
|------|-------|------|
| 도구 | `grpc_tools.protoc` | `protoc` |
| 명령어 | `python -m grpc_tools.protoc` | `protoc --java_out=...` |

### 2. 서버 생성 및 설정

| 항목 | Python | Java |
|------|--------|------|
| 서버 생성 | `grpc.server()` | `ServerBuilder.forPort(port).build()` |
| 서비스 등록 | `add_XXXServicer_to_server(servicer, server)` | `addService(servicer)` |
| 서버 시작 | `server.start()` | `server.start()` |
| 서버 유지 | 무한 루프 또는 `server.wait_for_termination()` | `server.awaitTermination()` |

### 3. 클라이언트 채널 생성

| 항목 | Python | Java |
|------|--------|------|
| 채널 생성 | `grpc.insecure_channel('localhost:50051')` | `ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build()` |
| 채널 관리 | 자동 관리 | 명시적 `shutdown()` 필요 |

### 4. Stub 생성 및 사용

| 항목 | Python | Java |
|------|--------|------|
| Stub 타입 | 단일 stub (동기/비동기 자동 처리) | `BlockingStub` (동기) 또는 `AsyncStub` (비동기) |
| 생성 방법 | `ServiceStub(channel)` | `ServiceGrpc.newBlockingStub(channel)` 또는 `ServiceGrpc.newStub(channel)` |

### 5. 메시지 객체 생성

| 항목 | Python | Java |
|------|--------|------|
| 생성 방식 | 직접 생성 또는 생성자 | Builder 패턴 필수 |
| 예시 | `Request(value=10)` | `Request.newBuilder().setValue(10).build()` |

### 6. 서버 구현 방식

| 항목 | Python | Java |
|------|--------|------|
| 상속 방식 | `Servicer` 클래스를 base class로 사용 | `Servicer` 클래스를 `extends`로 상속 |
| 메서드 구현 | 일반 함수로 구현 | `@Override`로 메서드 오버라이드 |

### 7. 스트리밍 처리 방식

#### Server Streaming

| 항목 | Python | Java |
|------|--------|------|
| 서버 | `yield` 키워드로 여러 메시지 반환 | `StreamObserver<Response>`의 `onNext()` 여러 번 호출 후 `onCompleted()` |
| 클라이언트 | 반환값이 iterator이므로 `for` 루프 사용 | `BlockingStub`: `Iterator<Response>` 사용<br>`AsyncStub`: `StreamObserver<Response>` 구현 |

#### Client Streaming

| 항목 | Python | Java |
|------|--------|------|
| 서버 | `request_iterator`를 `for` 루프로 순회 | `StreamObserver<Request>`를 반환하고, 내부에서 `onNext()`로 요청 수신 |
| 클라이언트 | 제너레이터 함수(`yield` 사용)를 인자로 전달 | `StreamObserver<Request>`를 반환받아 `onNext()`로 여러 요청 전송 |

#### Bidirectional Streaming

| 항목 | Python | Java |
|------|--------|------|
| 서버 | `request_iterator`를 순회하며 `yield`로 응답 | `StreamObserver<Request>`와 `StreamObserver<Response>` 모두 사용 |
| 클라이언트 | 제너레이터 함수로 요청 전송, iterator로 응답 수신 | `StreamObserver<Request>`로 요청 전송, `StreamObserver<Response>`로 응답 수신 |

### 8. Import/패키지 구조

| 항목 | Python | Java |
|------|--------|------|
| gRPC 모듈 | `import grpc`<br>`import grpc.futures` | `import io.grpc.*` |
| 생성된 코드 | `import` 문으로 import | `import` 문으로 import (패키지 구조 따름) |

### 9. 예외 처리

| 항목 | Python | Java |
|------|--------|------|
| 예외 처리 | Python 예외 처리 (`try-except`) | Java 예외 처리 (`try-catch`) 또는 `StatusRuntimeException` |

### 10. 비동기 처리

| 항목 | Python | Java |
|------|--------|------|
| 비동기 지원 | `grpc.futures` 모듈 사용 | `AsyncStub`과 `StreamObserver` 사용 |
| 콜백 방식 | Future 객체 사용 | `StreamObserver` 인터페이스 구현 |

---

## gRPC 통신 패턴별 구현 가이드 (Java 기준)

### 1. Unary (일반 RPC)

#### 1단계: 원격 호출 함수 작성

원격으로 호출할 함수를 작성합니다.

#### 2단계: 프로토콜 버퍼 구성

원격 호출 함수 관련 서비스와 메시지를 정의합니다. `proto` 파일은 protocol buffers 표준을 따릅니다.

#### 3단계: gRPC 클래스 자동 생성

`protoc`를 통해 Java 클래스 파일들을 생성합니다.

- **메시지 클래스 파일**: proto 파일의 메시지에 대응하는 Java 코드
- **클라이언트/서버 클래스 파일**: proto 파일의 클라이언트용 코드와 서버용 코드

#### 4단계: gRPC 서버 작성

1. `io.grpc.ServerBuilder`와 `io.grpc.Server`를 import 합니다.
2. protoc가 생성한 클래스를 import 합니다.
3. 원격 호출될 함수들을 import 합니다.
4. protoc가 생성한 `Servicer` 클래스(예: `CalculatorGrpc.CalculatorImplBase`)를 상속받아서 원격 호출될 함수들을 멤버로 갖는 서버 클래스를 생성합니다.
5. 서버 클래스에 원격 호출될 함수에 대한 RPC 메서드를 오버라이드하여 작성합니다. **Unary의 경우**, 요청과 응답을 직접 반환하는 메서드를 구현합니다.
6. `ServerBuilder`를 사용하여 gRPC 서버를 생성합니다.
7. `addService()`를 사용하여 `ServerBuilder`에 4번의 Servicer 인스턴스를 추가합니다.
8. `ServerBuilder`의 포트를 설정하고, `build()`로 서버를 생성한 후, `start()`로 서버를 실행합니다.
9. 서버가 유지되도록 `awaitTermination()`을 사용하여 프로그램 실행을 유지합니다.

#### 5단계: gRPC Client 작성

1. `io.grpc.ManagedChannel`과 `io.grpc.ManagedChannelBuilder`를 import 합니다.
2. protoc가 생성한 클래스를 import 합니다.
3. `ManagedChannelBuilder`를 사용하여 gRPC 통신 채널을 생성합니다.
4. protoc가 생성한 클라이언트용 코드(stub)를 채널을 사용하여 생성합니다 (예: `CalculatorGrpc.newBlockingStub(channel)` 또는 `CalculatorGrpc.newStub(channel)`).
5. protoc가 생성한 메시지용 코드에 맞춰서 원격 함수에 전달할 메시지 객체를 생성하고, 전달할 값을 설정합니다 (예: `Request.newBuilder().setValue(10).build()`).
6. 원격 함수를 stub을 통해 호출합니다 (예: `stub.calculate(request)`).
7. 얻은 결과로 원하는 작업을 수행합니다.

---

### 2. Server Streaming (서버 스트리밍)

#### 1단계: 원격 호출 함수 작성

원격으로 호출할 함수를 작성합니다.

#### 2단계: 프로토콜 버퍼 구성

원격 호출 함수 관련 서비스와 메시지를 정의합니다. `proto` 파일은 protocol buffers 표준을 따릅니다.

**서버 스트리밍의 경우**, 반환 타입에 `stream` 키워드를 사용합니다 (예: `returns (stream Message)`).

#### 3단계: gRPC 클래스 자동 생성

`protoc`를 통해 Java 클래스 파일들을 생성합니다.

- **메시지 클래스 파일**: proto 파일의 메시지에 대응하는 Java 코드
- **클라이언트/서버 클래스 파일**: proto 파일의 클라이언트용 코드와 서버용 코드

#### 4단계: gRPC 서버 작성

1. `io.grpc.ServerBuilder`와 `io.grpc.Server`를 import 합니다.
2. protoc가 생성한 클래스를 import 합니다.
3. 원격 호출될 함수들을 import 합니다.
4. protoc가 생성한 `Servicer` 클래스를 상속받아서 원격 호출될 함수들을 멤버로 갖는 서버 클래스를 생성합니다.
5. 서버 클래스에 원격 호출될 함수에 대한 RPC 메서드를 오버라이드하여 작성합니다. **서버 스트리밍의 경우**, `StreamObserver<Response>`를 매개변수로 받아서, `onNext()`를 여러 번 호출하여 여러 메시지를 순차적으로 전송합니다. 모든 메시지를 전송한 후 `onCompleted()`를 호출합니다.
6. `ServerBuilder`를 사용하여 gRPC 서버를 생성합니다.
7. `addService()`를 사용하여 `ServerBuilder`에 4번의 Servicer 인스턴스를 추가합니다.
8. `ServerBuilder`의 포트를 설정하고, `build()`로 서버를 생성한 후, `start()`로 서버를 실행합니다.
9. 서버가 유지되도록 `awaitTermination()`을 사용하여 프로그램 실행을 유지합니다.

#### 5단계: gRPC Client 작성

1. `io.grpc.ManagedChannel`과 `io.grpc.ManagedChannelBuilder`를 import 합니다.
2. protoc가 생성한 클래스를 import 합니다.
3. `ManagedChannelBuilder`를 사용하여 gRPC 통신 채널을 생성합니다.
4. protoc가 생성한 클라이언트용 코드(stub)를 채널을 사용하여 생성합니다 (예: `ServerStreamingGrpc.newBlockingStub(channel)` 또는 `ServerStreamingGrpc.newStub(channel)`).
5. protoc가 생성한 메시지용 코드에 맞춰서 원격 함수에 전달할 메시지 객체를 생성하고, 전달할 값을 설정합니다.
6. 원격 함수를 stub을 통해 호출합니다. **서버 스트리밍의 경우**, `BlockingStub`을 사용하면 `Iterator<Response>`를 반환받아 `hasNext()`와 `next()`를 사용하여 스트림으로 받은 여러 응답을 순차적으로 처리합니다. 또는 `AsyncStub`을 사용하면 `StreamObserver<Response>`를 구현하여 `onNext()`에서 각 응답을 처리합니다.
7. 얻은 결과로 원하는 작업을 수행합니다.

---

### 3. Client Streaming (클라이언트 스트리밍)

#### 1단계: 원격 호출 함수 작성

원격으로 호출할 함수를 작성합니다.

#### 2단계: 프로토콜 버퍼 구성

원격 호출 함수 관련 서비스와 메시지를 정의합니다. `proto` 파일은 protocol buffers 표준을 따릅니다.

**클라이언트 스트리밍의 경우**, 입력 타입에 `stream` 키워드를 사용합니다 (예: `rpc GetServerResponse(stream Message) returns (Number)`).

#### 3단계: gRPC 클래스 자동 생성

`protoc`를 통해 Java 클래스 파일들을 생성합니다.

- **메시지 클래스 파일**: proto 파일의 메시지에 대응하는 Java 코드
- **클라이언트/서버 클래스 파일**: proto 파일의 클라이언트용 코드와 서버용 코드

#### 4단계: gRPC 서버 작성

1. `io.grpc.ServerBuilder`와 `io.grpc.Server`를 import 합니다.
2. protoc가 생성한 클래스를 import 합니다.
3. 원격 호출될 함수들을 import 합니다.
4. protoc가 생성한 `Servicer` 클래스를 상속받아서 원격 호출될 함수들을 멤버로 갖는 서버 클래스를 생성합니다.
5. 서버 클래스에 원격 호출될 함수에 대한 RPC 메서드를 오버라이드하여 작성합니다. **클라이언트 스트리밍의 경우**, `StreamObserver<Request>`를 반환하는 메서드를 구현하며, 내부에서 `StreamObserver<Response>`를 매개변수로 받아서 처리합니다. 클라이언트로부터 받은 여러 요청을 `StreamObserver<Request>`의 `onNext()`를 통해 순차적으로 처리합니다. 모든 요청을 처리한 후 최종 결과를 `StreamObserver<Response>`의 `onNext()`로 전송하고 `onCompleted()`를 호출합니다.
6. `ServerBuilder`를 사용하여 gRPC 서버를 생성합니다.
7. `addService()`를 사용하여 `ServerBuilder`에 4번의 Servicer 인스턴스를 추가합니다.
8. `ServerBuilder`의 포트를 설정하고, `build()`로 서버를 생성한 후, `start()`로 서버를 실행합니다.
9. 서버가 유지되도록 `awaitTermination()`을 사용하여 프로그램 실행을 유지합니다.

#### 5단계: gRPC Client 작성

1. `io.grpc.ManagedChannel`과 `io.grpc.ManagedChannelBuilder`를 import 합니다.
2. protoc가 생성한 클래스를 import 합니다.
3. `ManagedChannelBuilder`를 사용하여 gRPC 통신 채널을 생성합니다.
4. protoc가 생성한 클라이언트용 코드(stub)를 채널을 사용하여 생성합니다. **클라이언트 스트리밍의 경우**, `AsyncStub`을 사용합니다 (예: `ClientStreamingGrpc.newStub(channel)`).
5. `StreamObserver<Response>`를 구현하여 서버로부터 받을 응답을 처리할 콜백을 작성합니다.
6. 원격 함수를 stub을 통해 호출합니다. **클라이언트 스트리밍의 경우**, `StreamObserver<Request>`를 반환받아서, `onNext()`를 여러 번 호출하여 여러 요청을 스트림으로 보냅니다. 모든 요청을 전송한 후 `onCompleted()`를 호출합니다. 서버로부터 최종 결과는 5번에서 작성한 `StreamObserver<Response>`의 `onNext()`를 통해 받습니다.
7. 얻은 결과로 원하는 작업을 수행합니다.

---

### 4. Bidirectional Streaming (양방향 스트리밍)

#### 1단계: 원격 호출 함수 작성

원격으로 호출할 함수를 작성합니다.

#### 2단계: 프로토콜 버퍼 구성

원격 호출 함수 관련 서비스와 메시지를 정의합니다. `proto` 파일은 protocol buffers 표준을 따릅니다.

**양방향 스트리밍의 경우**, 입력 타입과 반환 타입 모두에 `stream` 키워드를 사용합니다 (예: `rpc GetServerResponse(stream Message) returns (stream Message)`).

#### 3단계: gRPC 클래스 자동 생성

`protoc`를 통해 Java 클래스 파일들을 생성합니다.

- **메시지 클래스 파일**: proto 파일의 메시지에 대응하는 Java 코드
- **클라이언트/서버 클래스 파일**: proto 파일의 클라이언트용 코드와 서버용 코드

#### 4단계: gRPC 서버 작성

1. `io.grpc.ServerBuilder`와 `io.grpc.Server`를 import 합니다.
2. protoc가 생성한 클래스를 import 합니다.
3. 원격 호출될 함수들을 import 합니다.
4. protoc가 생성한 `Servicer` 클래스를 상속받아서 원격 호출될 함수들을 멤버로 갖는 서버 클래스를 생성합니다.
5. 서버 클래스에 원격 호출될 함수에 대한 RPC 메서드를 오버라이드하여 작성합니다. **양방향 스트리밍의 경우**, `StreamObserver<Request>`를 반환하는 메서드를 구현하며, 내부에서 `StreamObserver<Response>`를 매개변수로 받아서 처리합니다. 클라이언트로부터 받은 여러 요청을 `StreamObserver<Request>`의 `onNext()`를 통해 순차적으로 처리하면서, 각 요청에 대해 `StreamObserver<Response>`의 `onNext()`를 사용하여 응답을 스트림으로 보냅니다. 모든 처리가 완료되면 `onCompleted()`를 호출합니다.
6. `ServerBuilder`를 사용하여 gRPC 서버를 생성합니다.
7. `addService()`를 사용하여 `ServerBuilder`에 4번의 Servicer 인스턴스를 추가합니다.
8. `ServerBuilder`의 포트를 설정하고, `build()`로 서버를 생성한 후, `start()`로 서버를 실행합니다.
9. 서버가 유지되도록 `awaitTermination()`을 사용하여 프로그램 실행을 유지합니다.

#### 5단계: gRPC Client 작성

1. `io.grpc.ManagedChannel`과 `io.grpc.ManagedChannelBuilder`를 import 합니다.
2. protoc가 생성한 클래스를 import 합니다.
3. `ManagedChannelBuilder`를 사용하여 gRPC 통신 채널을 생성합니다.
4. protoc가 생성한 클라이언트용 코드(stub)를 채널을 사용하여 생성합니다. **양방향 스트리밍의 경우**, `AsyncStub`을 사용합니다 (예: `BidirectionalGrpc.newStub(channel)`).
5. `StreamObserver<Response>`를 구현하여 서버로부터 받을 응답을 처리할 콜백을 작성합니다.
6. 원격 함수를 stub을 통해 호출합니다. **양방향 스트리밍의 경우**, `StreamObserver<Request>`를 반환받아서, `onNext()`를 여러 번 호출하여 여러 요청을 스트림으로 보냅니다. 서버로부터 받은 여러 응답은 5번에서 작성한 `StreamObserver<Response>`의 `onNext()`를 통해 순차적으로 처리합니다. 모든 요청을 전송한 후 `onCompleted()`를 호출합니다.
7. 얻은 결과로 원하는 작업을 수행합니다.
