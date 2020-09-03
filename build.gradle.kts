plugins {
    application
    id("org.jetbrains.kotlin.jvm") version "1.3.72"
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.3.72")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.3.72")
}

application {
    mainClassName = "com.expediagroup.graphql.client.ApplicationKt"
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}
