```mermaid
sequenceDiagram
  Client->>+CircularResource: GET /circular
  CircularResource->>+CircularController: readAll()
  CircularController->>+ReadAllCircularUseCase: execute()
  ReadAllCircularUseCase->>+CircularRepository: readAll()
  CircularRepository->>+CircularEntityRepository: findAll()
  CircularEntityRepository-->>-CircularRepository: e
  CircularRepository-->>-ReadAllCircularUseCase: d
  ReadAllCircularUseCase-->>-CircularController: c
  CircularController-->>-CircularResource: b
  CircularResource-->>-Client: application/json
```
