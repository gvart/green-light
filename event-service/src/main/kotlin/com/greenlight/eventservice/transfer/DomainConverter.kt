package com.greenlight.eventservice.transfer

interface DomainConverter<T> {
    fun convert(): T
}
