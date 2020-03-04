package com.greenlight.common.transfer

interface DomainConverter<T> {
    fun convert(): T
}
