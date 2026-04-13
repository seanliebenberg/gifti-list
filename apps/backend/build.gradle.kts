plugins {
    java
    id("org.springframework.boot") version "4.0.1"
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

// Temporary security override for transitive Jackson 2 deps pulled by springdoc/swagger.
// Remove once upstream resolves to a patched Jackson 2 line.
configurations.all {
    resolutionStrategy.force(
        "com.fasterxml.jackson.core:jackson-core:2.21.2",
        "com.fasterxml.jackson.core:jackson-databind:2.21.2",
        "com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.21.2",
        "com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.21.2"
    )
}

dependencies {
// override jackson versions to address cves is done in configurations.all
    implementation(enforcedPlatform("com.fasterxml.jackson:jackson-bom:2.21.2"))

    // Message broker rabbitmq
    implementation("org.springframework.boot:spring-boot-starter-amqp")

    // Core
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("io.micrometer:micrometer-registry-prometheus")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // OpenAPI UI (Swagger)
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:3.0.3")

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
    testImplementation("org.springframework.boot:spring-boot-test-autoconfigure:4.0.1")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
    testImplementation("org.testcontainers:junit-jupiter:1.21.4")
    testImplementation("org.testcontainers:postgresql:1.21.4")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    // Make sure tests always use the H2 test profile
    systemProperty("spring.profiles.active", "test")
    useJUnitPlatform()
}