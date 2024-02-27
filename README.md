<div align="center">
<img src="https://github.com/h-beeen/flow-subject/assets/112257466/0f91c167-ca03-46f8-a86b-03348056a034" width="900px">
</div>

<br>
<br>

- (개발 전) 필수 요구사항 기능명세서
    - 사용자는 고정 확장자 파일 업로드를 (bat, cmd, com, cpl, exe, scr, js)를 체크박스 형태로 제한
    - 사용자는 커스텀 확장자를 최대 200개까지 지정해 추가적으로 제한 가능
    - 고정 확장자의 check, unchecked Status는 DB와 동기화 되어야 함
    - 커스텀 확장자의 경우 `추가` 버튼을 눌렀을 때 하단 UI에 추가되며 DB랑 동기화 되어야 함
    - 커스텀 확장자 추가 간 중복 체크
    - 커스텀 확장자는 최대 200개까지 저장 가능하며, 최대 20자리의 확장자까지 허용

- (개발 전) 자체 요구사항 기능 명세서
  - 사용자는 파일 업로드/다운로드 기능을 사용할 수 있으며 용량은 20MB로 제한
  - 사용자는 업로드한 파일을 보관함에서 열람할 수 있으며, 다운로드가 가능함
  - 사용자는 특정 확장자를 필터링해 보관함에서 전체 삭제가 가능함
  - 이미 업로드 된 파일의 확장자를 커스텀 확장자를 추가할 경우, Throw Exception
    - 이 경우, 특정 확장자 전체 삭제 기능을 활용해, 버킷을 비우고 추가 가능
