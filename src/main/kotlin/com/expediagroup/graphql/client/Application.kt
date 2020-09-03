package com.expediagroup.graphql.client

import com.expediagroup.graphql.generated.ConferenceQuery
import com.expediagroup.graphql.generated.GreetingsQuery
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val client = GraphQLWebClient(url = "http://localhost:8080/graphql")

        val greetingsQuery = GreetingsQuery(client)
        val greeting = greetingsQuery.execute()
        println(greeting.data?.schedule)
        println("---")

        val conferenceQuery = ConferenceQuery(client)
        val result = conferenceQuery.execute(variables = ConferenceQuery.Variables())
        println(result.data?.conference)
        println(result.data?.schedule)
        println(result.data?.people)
    }
}
