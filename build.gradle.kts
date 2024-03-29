import com.github.gradle.node.npm.task.NpmTask

plugins {
    java
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.4"
    id("com.github.node-gradle.node") version "7.0.2"
}

group = "soo"
version = "0.0.1-SNAPSHOT"

node {
    download = true
    version = "18.17.1"
    npmVersion = ""
    distBaseUrl = "https://nodejs.org/dist"

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
    implementation("com.github.f4b6a3:uuid-creator:5.3.3")
    implementation("org.testcontainers:postgresql")

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

val nodeInstall = tasks.register<NpmTask>("nodeInstall") {
    args.set(listOf("install"))
}

val tailwindBuild = tasks.register<NpmTask>("tailwindBuild") {
    args.set(listOf("run", "build"))
}

val mergeSql = tasks.register("mergeSql") {
    file("src/main/resources/schema.sql").delete()
    file("src/main/resources/schema.sql").appendText("--THIS IS AUTO GENERATED FILE\n--DO NOT EDIT\n\n")

    doLast {
        fileTree("db").forEach {
            val sql = it.readText().replace("\n", "")
            file("src/main/resources/schema.sql").appendText(sql)
        }
    }
}

tasks.withType<JavaCompile> {
    dependsOn(nodeInstall)
    dependsOn(tailwindBuild)
}

tasks.withType<ProcessResources> {
    dependsOn(mergeSql)
}