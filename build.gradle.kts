import com.expediagroup.graphql.plugin.gradle.graphql
import com.expediagroup.graphql.plugin.generator.GraphQLClientType
import com.expediagroup.graphql.plugin.gradle.tasks.GraphQLDownloadSDLTask
import com.expediagroup.graphql.plugin.gradle.tasks.GraphQLGenerateClientTask

plugins {
    application
    id("org.jetbrains.kotlin.jvm") version "1.3.72"
    id("com.expediagroup.graphql") version "4.0.0-alpha.7"
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.3.72")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.3.72")
    implementation("com.expediagroup:graphql-kotlin-spring-client:4.0.0-alpha.7")
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
        sdlEndpoint = "http://localhost:8080/sdl"
        packageName = "com.example.generated"
        clientType = GraphQLClientType.WEBCLIENT
        queryFileDirectory = "${project.projectDir.absolutePath}/src/main/resources/first"
    }
}

// above graphql extension configuration is equivalent to the below
//val graphqlDownloadSDL by tasks.getting(GraphQLDownloadSDLTask::class) {
//    endpoint.set("http://localhost:8080/sdl")
//}
//
//val graphqlGenerateClient by tasks.getting(GraphQLGenerateClientTask::class) {
//    clientType.set(GraphQLClientType.WEBCLIENT)
//    packageName.set("com.example.generated")
//    schemaFile.set(graphqlDownloadSDL.outputFile)
//    queryFileDirectory.set("${project.projectDir.absolutePath}/src/main/resources/first")
//    dependsOn("graphqlDownloadSDL")
//}

val graphqlDownloadOtherSDL by tasks.registering(GraphQLDownloadSDLTask::class) {
    endpoint.set("http://localhost:8081/sdl")
    dependsOn("graphqlGenerateClient")
}

val graphqlGenerateOtherClient by tasks.registering(GraphQLGenerateClientTask::class) {
    clientType.set(GraphQLClientType.WEBCLIENT)
    packageName.set("com.example.generated2")
    schemaFile.set(graphqlDownloadOtherSDL.get().outputFile)
    queryFileDirectory.set("${project.projectDir.absolutePath}/src/main/resources/second")
    outputDirectory.set(project.layout.buildDirectory.dir("generated/source/graphql/main"))
    dependsOn("graphqlDownloadOtherSDL")
}

tasks {
    compileKotlin {
        dependsOn("graphqlGenerateOtherClient")
    }
}
