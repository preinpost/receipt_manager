import com.github.gradle.node.npm.task.NpmTask
import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
    java
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.4"
    id("com.github.node-gradle.node") version "3.4.0"
}

group = "soo"
version = "0.0.1-SNAPSHOT"

node {
    version = "16.13.2"
    npmVersion = "9.6.5"

    val projectDir = file("${project.projectDir}/src/main/tailwindcss")
    nodeProjectDir.set(projectDir)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.3")
    implementation("org.springframework.session:spring-session-core")
    implementation("com.h2database:h2:2.1.210")
    implementation("com.github.f4b6a3:uuid-creator:5.3.3")

    // webauthn
    implementation("com.webauthn4j:webauthn4j-core:0.22.0.RELEASE")
    implementation("com.webauthn4j:webauthn4j-metadata:0.22.0.RELEASE")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-cbor:2.15.2")

    implementation("org.springframework.boot:spring-boot-starter-actuator")

    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("org.postgresql:postgresql")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.mybatis.spring.boot:mybatis-spring-boot-starter-test:3.0.3")
}


tasks.withType<Test> {
    useJUnitPlatform()
}


val tailwindBuild = tasks.register<NpmTask>("tailwindBuild") {
    args.set(listOf("run", "build"))
}

tasks.withType<JavaCompile> {
    dependsOn(tailwindBuild)
}