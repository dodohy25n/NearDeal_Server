# =============================================
# 1. 빌드(Build) 단계
# =============================================
# Java 17 JDK가 설치된 이미지를 기반으로 'builder'라는 별칭의 빌드 환경을 시작합니다.
FROM eclipse-temurin:17-jdk-jammy AS builder

# 컨테이너 내 작업 폴더를 /app으로 지정합니다.
WORKDIR /near-deal-server

# Docker 빌드 캐시를 효율적으로 사용하기 위해, 자주 바뀌지 않는
# Gradle 관련 파일들을 먼저 복사합니다. (별도 레이어링)
COPY gradlew ./
COPY gradle ./gradle
COPY build.gradle settings.gradle ./

RUN chmod +x ./gradlew

# 프로젝트에 필요한 라이브러리(의존성)를 미리 다운로드합니다.
# 소스 코드가 변경되어도 이 부분은 캐시로 남아 빌드 속도를 높여줍니다.
RUN ./gradlew dependencies --no-daemon

# 나머지 모든 소스 코드를 컨테이너 안으로 복사합니다.
COPY . .

# Gradle을 사용하여 프로젝트를 빌드합니다. (테스트는 건너뜁니다)
# 이 명령을 통해 build/libs/ 폴더에 .jar 파일이 생성됩니다.
RUN ./gradlew build --no-daemon -x test

# =============================================
# 2. 실행(Runtime) 단계
# =============================================
# Java 17 JRE(실행 환경)만 포함된 가벼운 이미지를 기반으로 최종 이미지를 만듭니다.
FROM eclipse-temurin:17-jre-jammy

# 컨테이너 내 작업 폴더를 /app으로 지정합니다.
WORKDIR /near-deal-server

# 이전 'builder' 단계의 /app/build/libs/ 폴더에서 생성된 .jar 파일을
# 현재 컨테이너의 /app/app.jar 라는 이름으로 복사해옵니다.
COPY --from=builder /near-deal-server/build/libs/*.jar /near-deal-server/app.jar

EXPOSE 8080
# 컨테이너가 시작될 때 실행할 기본 명령어를 설정합니다.
# "java -jar app.jar" 명령으로 애플리케이션을 실행합니다.
ENTRYPOINT ["java", "-jar", "app.jar"]