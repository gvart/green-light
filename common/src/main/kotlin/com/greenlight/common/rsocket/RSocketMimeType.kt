package com.greenlight.common.rsocket

import io.rsocket.metadata.WellKnownMimeType

class RSocketMimeType {
    companion object {
        const val RSOCKET_ROUTING = "message/x.rsocket.routing.v0"
    }
}