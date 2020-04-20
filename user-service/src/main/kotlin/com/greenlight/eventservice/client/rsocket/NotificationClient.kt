package com.greenlight.eventservice.client.rsocket

import com.greenlight.eventservice.domain.UserProfile

class NotificationClient {

    private enum class NotificationType {
        EMAIL;
    }

    fun sendEmailNotification(userProfile: UserProfile) {
        sendNotification(NotificationType.EMAIL, userProfile)
    }

    private fun sendNotification(notificationClient: NotificationType, userProfile: UserProfile) {

    }
}