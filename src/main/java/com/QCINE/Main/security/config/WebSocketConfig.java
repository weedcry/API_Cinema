package com.QCINE.Main.security.config;

import com.QCINE.Main.security.interceptor.HttpHandshakeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;
import org.springframework.messaging.handler.invocation.HandlerMethodReturnValueHandler;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

import java.util.List;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Autowired
    private HttpHandshakeInterceptor handshakeInterceptor;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // TODO Auto-generated method stub
        //for front-end
        //.setInterceptors(handshakeInterceptor)
        registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();
        //for android
        registry.addEndpoint("/ws");

    }


    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        // TODO Auto-generated method stub
        registry.setApplicationDestinationPrefixes("/message_send");
        registry.enableSimpleBroker("/message_receive");
    }


    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
        // TODO Auto-generated method stub

    }


    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        // TODO Auto-generated method stub

    }


    @Override
    public void configureClientOutboundChannel(ChannelRegistration registration) {
        // TODO Auto-generated method stub

    }


    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        // TODO Auto-generated method stub

    }


    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        // TODO Auto-generated method stub

    }


    @Override
    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
        // TODO Auto-generated method stub
        return false;
    }




}