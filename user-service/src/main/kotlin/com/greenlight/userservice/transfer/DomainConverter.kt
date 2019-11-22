package com.greenlight.userservice.transfer

interface DomainConverter<T> {
    fun convert(): T
}
