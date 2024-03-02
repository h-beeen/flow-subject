<div align="center">
<img src="https://upload.wikimedia.org/wikipedia/commons/e/e3/Flow_logo_211215.png" width="30%"/><br>
</div>

<br><br>

<h3>🌱 플로우 채용 사전과제</h3>

- **지원자** : 변해빈
- **Contact** : joker7011@naver.com
- **배포 사이트** : [https://flow.h-beeen.xyz/](https://flow.h-beeen.xyz/)
- **소요 시간** : 48시간

<br>


<h3>🔧 Tech Stacks</h3>

- **Framework/Language** : Java17, Spring Boot 3
- **ORM** : MySQL, Spring Data JPA
- **View** : Thymeleaf, JQuery
- **CI/CD** : Docker, Github Actions
- **Infra** : AWS EC2, S3 Bucket, Load Balancer, Route 53

<br>

---

<h3>💁 Project Introduction</h3>

<img align="center" src="https://github.com/h-beeen/flow-subject/assets/112257466/656a4dc4-eded-4efc-9cee-0dc77fd73a3d" width="70%"/>

<br>

- **메인 페이지** : [https://flow.h-beeen.xyz/](https://flow.h-beeen.xyz/)
    - 파일 업로드 : 원하는 파일을 업로드 할 수 있습니다.
    - 파일 관리 : 서버에 업로드한 파일을 삭제하거나 다운로드 할 수 있습니다.
    - 파일 확장자 차단 : 고정 확장자, 커스텀 확장자 추가 및 삭제를 통해 유해 확장자 업로드를 제한할 수 있습니다.

---

<img align="center" src="https://github.com/h-beeen/flow-subject/assets/112257466/d38c0dde-d135-4a23-970c-384c681aaefb" width="70%"/>

<br>

- **파일 업로드 페이지** : [https://flow.h-beeen.xyz/file/new](https://flow.h-beeen.xyz/file/new)
- **예외 처리**
    - 빈 파일은 업로드 할 수 없습니다.
    - 이미 같은 이름의 파일이 업로드되어 있다면, 업로드 할 수 없습니다.
    - 금지된 고정 확장자에 True로 표시되어 있는 파일을 업로드 할 수 없습니다.
    - 금지된 커스텀 확장자에 등록되어 있는 파일을 업로드 할 수 없습니다.

---

<img align="center" src="https://github.com/h-beeen/flow-subject/assets/112257466/e6a05261-70e2-47cd-ae60-5e9ecb44189d" width="70%">

<br>

- **파일 관리 페이지** : [https://flow.h-beeen.xyz/files](https://flow.h-beeen.xyz/files)
  - 서버에 저장된 파일 다운로드 기능
  - 서버에서 파일을 삭제하는 기능


---

<img align="center" src="https://github.com/h-beeen/flow-subject/assets/112257466/57383bfc-7ffe-4fe0-bb89-d9f23aa81b52" width="70%">

<br>

- **파일 확장자 차단 페이지** : [https://flow.h-beeen.xyz/file/restrict](https://flow.h-beeen.xyz/file/restrict)
  - 고정 확장자를 지정할 수 있습니다.
    - 최초 DB 저장은 Unchecked 상태로 Flush.
  - 커스텀 확장자를 지정할 수 있습니다.
- **예외 처리**
  - 파일 확장자는 Empty거나 Null일 수 없습니다.
  - 파일 확장자의 최대 글자는 20자 입니다.
  - 파일 확장자는 영문과 숫자로만 구성되어야 합니다.
  - 고정 확장자에 이미 등록된(T/F 여부 무관) 확장자는 커스텀 확장자에 등록할 수 없습니다.
  - 커스텀 확장자는 대소문자를 구별하지 않고, 모두 소문자로 변환하여 중복을 체크합니다.
  - 이미 등록된 커스텀 확장자를 다시 커스텀 확장자로 등록할 수 없습니다.
  - 커스텀 확장자는 최대 200개까지 등록이 가능합니다.

<br>

---

<h3>Project Structure</h3>

```php
└── upload
    ├── UploadApplication.class
    ├── file
    │   ├── application
    │   │   ├── CustomFileExtensionService.class
    │   │   ├── FileUploadService.class
    │   │   ├── FixedFileExtensionService.class
    │   │   └── dto
    │   │       ├── request
    │   │       │   ├── CustomFileExtensionRequest.class
    │   │       │   ├── FileExtensionIdRequest.class
    │   │       │   └── NewFileRequest.class
    │   │       └── response
    │   │           ├── CustomFileExtensionResponse.class
    │   │           └── FixedFileExtensionResponse.class
    │   ├── domain
    │   │   ├── CustomFileExtension$CustomFileExtensionBuilder.class
    │   │   ├── CustomFileExtension.class
    │   │   ├── FixedFileExtension.class
    │   │   ├── QCustomFileExtension.class
    │   │   ├── QFixedFileExtension.class
    │   │   └── constants
    │   │       └── FileExtensionType.class
    │   ├── exception
    │   │   ├── FileError.class
    │   │   └── FileExtensionError.class
    │   ├── infra
    │   │   ├── FileManager.class
    │   │   └── persistence
    │   │       ├── CustomFileExtensionRepository.class
    │   │       └── FixedFileExtensionRepository.class
    │   └── presentation
    │       ├── rest
    │       │   ├── NewFileController.class
    │       │   └── RestrictFileExtensionController.class
    │       └── view
    │           └── FileExtensionController.class
    └── global
        ├── auditing
        │   ├── BaseEntity.class
        │   └── QBaseEntity.class
        ├── config
        │   ├── AuditingConfig.class
        │   └── S3ObjectStorageConfig.class
        ├── exception
        │   ├── ApiExceptionHandler.class
        │   ├── BusinessException.class
        │   └── error
        │       ├── ErrorCode.class
        │       ├── ErrorResponse.class
        │       └── GlobalError.class
        └── view
            └── presentation
                └── HomeController.class

```

---

감사합니다.
변해빈 드림.
