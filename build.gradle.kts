plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

sourceSets {
    main {
        java.srcDir("src/main/java")
    }

    test {
        java.srcDir("src/test/java")
    }
}

dependencies {
    // General
    implementation("com.google.guava:guava:31.1-jre")

    // Lombok
    annotationProcessor("org.projectlombok:lombok:1.18.26")
    compileOnly("org.projectlombok:lombok:1.18.26")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.26")
    testCompileOnly("org.projectlombok:lombok:1.18.26")

    // Logging
    implementation("org.apache.logging.log4j:log4j-api:2.20.0")
    implementation("org.apache.logging.log4j:log4j-core:2.20.0")

    // Testing
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
    testImplementation("org.hamcrest:hamcrest:2.2")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}