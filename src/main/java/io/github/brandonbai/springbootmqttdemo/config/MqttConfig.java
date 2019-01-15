package io.github.brandonbai.springbootmqttdemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;

import java.util.List;

@Configuration
public class MqttConfig {

    @Value("${mqtt.clientId}")
    private String clientId;

    @Value("${mqtt.username}")
    private String username;

    @Value("${mqtt.password}")
    private String password;

    @Value("${mqtt.cleanSession}")
    private Boolean cleanSession;

    @Value("${mqtt.async}")
    private Boolean async;

    @Value("${mqtt.defaultQos}")
    private Integer defaultQos;

    @Value("${mqtt.completionTimeout}")
    private Integer completionTimeout;

    @Value("${mqtt.keepAliveInterval}")
    private Integer keepAliveInterval;

    @Value("${mqtt.serverUri}")
    private String serverUri;

    private DefaultMqttPahoClientFactory clientFactory() {

        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();

        factory.setPassword(username);
        factory.setUserName(password);
        factory.setCleanSession(cleanSession);
        factory.setKeepAliveInterval(keepAliveInterval);
        factory.setServerURIs(serverUri);

        return factory;
    }

    @Bean
    public MqttPahoMessageHandler mqttHandler() {

        MqttPahoMessageHandler handler = new MqttPahoMessageHandler(clientId, clientFactory());

        handler.setAsync(async);
        handler.setDefaultQos(defaultQos);
        handler.setCompletionTimeout(completionTimeout);

        return handler;
    }

}
