package com.example.chatapp.config

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig : WebSocketMessageBrokerConfigurer {

    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        registry
            .setApplicationDestinationPrefixes("/app/")

            // TODO: make it work with custom message broker
            .enableSimpleBroker("/topic")
            // .enableStompBrokerRelay("/topic")
            // .setRelayHost("localhost")
            // .setRelayPort(61613)
            // .setClientLogin("guest")
            // .setClientPasscode("guest")
    }

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        // add 2 endpoints with and without SockJS
        registry.addEndpoint("/gs-guide-websocket")
        registry.addEndpoint("/gs-guide-websocket")
            .withSockJS()
    }
}