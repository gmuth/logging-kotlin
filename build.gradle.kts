plugins {
    id("org.jetbrains.kotlin.jvm") version "1.7.22"
    id("org.sonarqube") version "4.3.0.3225" // https://plugins.gradle.org/plugin/org.sonarqube
    id("jacoco")
}

group = "de.gmuth"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

// update gradle wrapper
// ./gradlew wrapper --gradle-version 7.6.2

val javaVersion = "11"
val slf4jVersion = "1.7.36"
val androidVersion = "4.1.1.4"

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")

    compileOnly("org.slf4j:slf4j-api:$slf4jVersion") // org.slf4j
    compileOnly("com.google.android:android:$androidVersion") // android.util.Log

    //testRuntimeOnly("org.slf4j:slf4j-simple:$slf4jVersion")
    testRuntimeOnly("ch.qos.logback:logback-classic:1.2.9")
}

// gradlew clean -x test build publishToMavenLocal
defaultTasks("assemble")

tasks.compileKotlin {
    kotlinOptions {
        jvmTarget = javaVersion
    }
}

tasks.compileTestKotlin {
    kotlinOptions {
        jvmTarget = javaVersion
    }
}
tasks.compileJava {
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
}

java {
    withSourcesJar()
}

// ====== analyse code with SonarQube ======

// required for sonarqube code coverage
// https://docs.sonarqube.org/latest/analysis/test-coverage/java-test-coverage
tasks.jacocoTestReport {
    dependsOn(tasks.test)
    // https://stackoverflow.com/questions/67725347/jacoco-fails-on-gradle-7-0-2-and-kotlin-1-5-10
    //version = "0.8.7"
    reports {
        xml.required.set(true)
        csv.required.set(false)
        html.required.set(false)
    }
}

// gradle test jacocoTestReport sonar
// https://docs.sonarqube.org/latest/analysis/scan/sonarscanner-for-gradle/
// configure token with 'publish analysis' permission in file ~/.gradle/gradle.properties:
// systemProp.sonar.login=<token>
sonar {
    properties {
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.projectKey", "gmuth_logging-kotlin")
        property("sonar.organization", "gmuth")
    }
}

tasks.sonar {
    dependsOn(tasks.jacocoTestReport) // for coverage
}