SpringBoot + Visual Studio Code
================================
Visual Studio Code(VSCode)에서 SpringBoot Hot Code Replace(HCR) 사용을 위한 설정입니다.

# 준비사항
- Visual Studio Code (1.8 이상)
- SptringBoot 3.x
- Java 17 이상

## VScode 확장 추가
- Extension Pack for Java
- Spring Boot Extension Pack
- Gradle Extension Pack (선택)
- Material Icon Theme (선택)
- indent-rainbow (선택)

**Gradle for Java** 확장의 buildServer 기능을 사용하면 HCR이 작동하지 않습니다. 해당 프로젝트에서 이슈로 관리되고 있으니 나중에 수정될 수 있습니다.  
참고로 buildServer 활성화 할 경우 *Force Java Compilation -> Incremental*을 수동으로 실행하면 되긴 합니다만 불편.  
buildServer를 사용하는 경우 gradle과 같이 *build* 디렉토리를 사용합니다.
buildServer를 중지하는 경우'Language Support for Java' 확장의 *JDT LS*를 사용하며 빌드 할때 *bin* 디렉토리를 사용합니다.   
buildServer는 gradle build task와 같고 약간의 빌드 성능향상이 있습니다. 

## SpringBoot 설정
build.gradle에 spring-boot-devtools 를 추가합니다.  
```developmentOnly 'org.springframework.boot:spring-boot-devtools```

application.properties를 수정합니다.
```ini
spring.devtools.livereload.enabled=true
# ThymeLeaf를 사용하는 경우
spring.thymeleaf.cache=false 

spring.devtools.restart.enabled=true
spring.devtools.restart.additional-paths=src/main/java
spring.devtools.restart.poll-interval=2s
spring.devtools.restart.quiet-period=1s
spring.devtools.restart.exclude=target/**

logging.level.com.example=debug
```

## VSCode 설정
.vscode 디렉토리아래 settings.json에 추가합니다.
```json
{
  "java.compile.nullAnalysis.mode": "automatic",
  "java.autobuild.enabled": true,
  "java.project.referencedLibraries": [
      "bin/main", 
      "bin/test"
  ],
  "java.gradle.buildServer.enabled": "off", // Temporary. Check for extension updates
}
```

SpringBoot Dashboard에서 Debug를 실행후 개발하시면 됩니다. Gradle BootRun과 JDT LS에서 실행된 코드결과에 차이가 있었지만 원인은 찾지 못했습니다.  
개인적 소감은 Eclipse보단 편하고 IntelliJ IDEA보단 불편합니다.

# 참고문서
* [Enable incremental build with Gradle build server to support hot code replace](https://github.com/microsoft/vscode-gradle/issues/1449)