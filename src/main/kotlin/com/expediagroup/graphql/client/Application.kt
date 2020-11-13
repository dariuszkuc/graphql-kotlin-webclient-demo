package com.expediagroup.graphql.client

import com.example.generated.ConferenceQuery
import com.example.generated.GreetingsQuery
import kotlinx.coroutines.runBlocking
import org.springframework.web.reactive.function.client.WebClient

fun main() {
    runBlocking {
        val builder = WebClient.builder()
            .defaultHeader("X-API-KEY", "s3cr3t")

        val client = GraphQLWebClient(url = "http://localhost:8080/graphql")

        val greetingsQuery = GreetingsQuery(client)
        val greeting = greetingsQuery.execute() {
            header("Accept-Language", "fr")
        }
        println(greeting.data?.schedule)
        println("---")

        val conferenceQuery = ConferenceQuery(client)
        val result = conferenceQuery.execute(variables = ConferenceQuery.Variables(namePrefix = "G"))
        println(result.data?.conference)
        println(result.data?.schedule)
        println(result.data?.people)
    }
}
