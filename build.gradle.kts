import com.expediagroup.graphql.plugin.gradle.graphql

plugins {
    application
    id("org.jetbrains.kotlin.jvm") version "1.3.72"
    id("com.expediagroup.graphql") version "4.0.0-alpha.1"
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.3.72")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.3.72")
    implementation("com.expediagroup:graphql-kotlin-spring-client:4.0.0-alpha.1")
}

application {
    mainClassName = "com.expediagroup.graphql.client.ApplicationKt"
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}

graphql {
    client {
        endpoint = "http://localhost:8080/graphql"
        packageName = "com.expediagroup.graphql.generated"
    }
}
