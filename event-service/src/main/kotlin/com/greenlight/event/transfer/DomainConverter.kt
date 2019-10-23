package com.greenlight.event.transfer

interface DomainConverter<T> {
    fun convert(): T
}
