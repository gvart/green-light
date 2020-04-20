package com.greenlight.eventservice.extensions

import org.springframework.test.web.reactive.server.WebTestClient


fun WebTestClient.ResponseSpec.printResponse(): WebTestClient.ResponseSpec {
    println(this.expectBody().returnResult().toString())
    return this
}