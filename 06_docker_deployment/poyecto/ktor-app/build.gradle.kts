val ktor_version = "2.3.12"
val exposed_version = "0.50.0"
val postgres_version = "42.7.3"


plugins {
    kotlin("jvm") version "1.9.22"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("ch.qos.logback:logback-classic:1.4.14")

    implementation("org.jetbrains.exposed:exposed-core:${exposed_version}")
    implementation("org.jetbrains.exposed:exposed-dao:${exposed_version}")
    implementation("org.jetbrains.exposed:exposed-jdbc:${exposed_version}")

    implementation("org.postgresql:postgresql:$postgres_version")
    implementation("mysql:mysql-connector-java:8.0.33")
}

application {
    mainClass.set("MainKt")
}
