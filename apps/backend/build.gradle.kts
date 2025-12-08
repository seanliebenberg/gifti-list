plugins {
    java
    id("org.springframework.boot") version "3.5.5"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "org.giftilist"
version = "0.0.1-SNAPSHOT"

java {
    toolchain { languageVersion = JavaLanguageVersion.of(21) }
}

configurations {
    compileOnly { extendsFrom(configurations.annotationProcessor.get()) }
}

repositories {
    mavenCentral()
}

dependencies {
    // Core
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // OpenAPI UI (Swagger)
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:3.0.0")

    // DB drivers
    runtimeOnly("org.postgresql:postgresql")
    testRuntimeOnly("com.h2database:h2")

    // Flyway
    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-database-postgresql")

    // Dev experience
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    // Remove as not using Spring’s Compose integration right now
    // developmentOnly("org.springframework.boot:spring-boot-docker-compose")

    // Lombok (optional)
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // Testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.testcontainers:junit-jupiter:1.21.3")
    testImplementation("org.testcontainers:postgresql:1.21.3")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    // Make sure tests always use the H2 test profile
    systemProperty("spring.profiles.active", "test")
    useJUnitPlatform()
}