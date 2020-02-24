package com.greenlight.userservice.client.rsocket

import com.greenlight.userservice.domain.User
import io.rsocket.client.LoadBalancedRSocketMono

class NotificationClient {

    private enum class NotificationType {
        EMAIL;
    }

    fun sendEmailNotification(user: User) {
        sendNotification(NotificationType.EMAIL, user)
    }

    private fun sendNotification(notificationClient: NotificationType, user: User) {

    }
}