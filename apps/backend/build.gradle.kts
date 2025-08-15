plugins {
    java
    id("org.springframework.boot") version "3.5.4"
    id("io.spring.dependency-management") version "1.1.7"
}

group =
    "org.giftilist"
version =
    "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion =
            JavaLanguageVersion.of(
                21
            )
    }
}

configurations {
    compileOnly {
        extendsFrom(
            configurations.annotationProcessor.get()
        )
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(
        "org.springframework.boot:spring-boot-starter-actuator"
    )
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0"
    )
    implementation("org.springframework.boot:spring-boot-starter-validation")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("org.postgresql:postgresql")
    testImplementation("org.testcontainers:junit-jupiter:1.19.7")
    testImplementation("org.testcontainers:postgresql:1.19.7")

    runtimeOnly("org.postgresql:postgresql")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("com.h2database:h2")
    implementation(
        "org.springframework.boot:spring-boot-starter-web"
    )
    compileOnly(
        "org.projectlombok:lombok"
    )
    developmentOnly(
        "org.springframework.boot:spring-boot-devtools"
    )
    developmentOnly(
        "org.springframework.boot:spring-boot-docker-compose"
    )
    annotationProcessor(
        "org.projectlombok:lombok"
    )
    testImplementation(
        "org.springframework.boot:spring-boot-starter-test"
    )
    testRuntimeOnly(
        "org.junit.platform:junit-platform-launcher"
    )
}
tasks.test {
    systemProperty("spring.profiles.active", "test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
