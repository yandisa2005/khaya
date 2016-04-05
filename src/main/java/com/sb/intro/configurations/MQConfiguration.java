//package com.sb.intro.configurations;
//
//import com.ibm.mq.jms.MQConnectionFactory;
//import com.ibm.mq.jms.MQQueue;
//import com.ibm.msg.client.jms.JmsConnectionFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jms.connection.CachingConnectionFactory;
//import org.springframework.jms.listener.AbstractMessageListenerContainer;
//import org.springframework.jms.listener.DefaultMessageListenerContainer;
//
//import javax.annotation.PostConstruct;
//import javax.jms.ConnectionFactory;
//import javax.jms.JMSException;
//import javax.jms.Queue;
//
//@Configuration
//public class MQConfiguration {
//    private static final Logger log = LoggerFactory.getLogger(MQConfiguration.class);
//
//    @Autowired
//    private MQConnectionDetails mqConfig;
//
//    @Bean
//    public Queue receiveQueue() throws JMSException {
//        return new MQQueue(mqConfig.getReceiveQueue());
//    }
//
//    @Bean
//    public AbstractMessageListenerContainer messageListenerContainer() throws JMSException {
//        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory());
//        container.setDestination(receiveQueue());
//        container.setSessionTransacted(true);
//        container.setAutoStartup(false);
//
//        return container;
//    }
//
//    @Bean
//    public ConnectionFactory connectionFactory() throws JMSException {
//        return new CachingConnectionFactory(mqConnectionFactory());
//    }
//
//    @Bean
//    public JmsConnectionFactory mqConnectionFactory() throws JMSException {
//        MQConnectionFactory connectionFactory = new MQConnectionFactory();
//        connectionFactory.setHostName(mqConfig.getHostName());
//        connectionFactory.setPort(mqConfig.getPort());
//        connectionFactory.setQueueManager(mqConfig.getQueueManager());
//        connectionFactory.setChannel(mqConfig.getChannel());
//        connectionFactory.setTransportType(mqConfig.getTransportType());
//
//        return connectionFactory;
//    }
//
//    @PostConstruct
//    public void log() {
//        log.info("*************************MQ Details******************************");
//        log.info("Host Name: {}", mqConfig.getHostName());
//        log.info("Port No: {}", mqConfig.getPort());
//        log.info("Queue Manager: {}", mqConfig.getQueueManager());
//        log.info("Channel: {}", mqConfig.getChannel());
//        log.info("Transport Type: {}", mqConfig.getTransportType());
//    }
//}
