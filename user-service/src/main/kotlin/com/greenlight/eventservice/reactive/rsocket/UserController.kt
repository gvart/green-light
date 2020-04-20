//package com.greenlight.eventservice.reactive.rsocket
//
//import com.greenlight.eventservice.service.UserProfileService
//import com.greenlight.eventservice.transfer.IdRequest
//import com.greenlight.eventservice.transfer.IdResponse
//import org.springframework.messaging.handler.annotation.MessageMapping
//import org.springframework.stereotype.Controller
//
//@Controller
//class UserController(private val userService: UserProfileService) {
//
//    @MessageMapping("user/find")
//    suspend fun findById(request: IdRequest): IdResponse {
//        return IdResponse(userService.findOne(request.id).id!!)
//
//    }
//}