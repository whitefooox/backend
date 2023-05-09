package web.infrastructure.message;

import jakarta.annotation.Resource;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSConsumer;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSProducer;
import jakarta.jms.Message;
import jakarta.jms.Queue;
import web.application.authorization.Authorizable;

public class Sender implements Authorizable {
    
  @Resource(mappedName = "jms/WebPool")    
  private ConnectionFactory connectionFactory;

  @Resource(mappedName = "jms/WebQueueAuth")
  private Queue queueAuth;

  @Resource(mappedName = "jms/WebQueueAnime")
  private Queue queueAnime;

  public boolean auth(String login, String password) {
    try {
        JMSContext context = connectionFactory.createContext();
        JMSProducer producer = context.createProducer();     
        JMSConsumer consumer = context.createConsumer(queueAnime);       
        Message message = context.createMessage();
        message.setJMSType("auth");
        message.setStringProperty("login", login);
        message.setStringProperty("password", password);
        producer.send(queueAuth, message);
        Message newMessage = consumer.receive(); 
        Boolean isAuth = newMessage.getBooleanProperty("status");
        return isAuth;
    } catch (Exception e) {
      e.printStackTrace();
        return false;
    }
  }

  @Override
  public boolean reg(String login, String password, String email) {
    try {
      JMSContext context = connectionFactory.createContext();
      JMSProducer producer = context.createProducer();     
      JMSConsumer consumer = context.createConsumer(queueAnime);       
      Message message = context.createMessage();
      message.setJMSType("reg");
      message.setStringProperty("login", login);
      message.setStringProperty("password", password);
      message.setStringProperty("email", email);
      producer.send(queueAuth, message);
      Message newMessage = consumer.receive(); 
      Boolean isReg = newMessage.getBooleanProperty("status");
      return isReg;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean check(String login, String token) {
    try {
      JMSContext context = connectionFactory.createContext();
      JMSProducer producer = context.createProducer();     
      JMSConsumer consumer = context.createConsumer(queueAnime);       
      Message message = context.createMessage();
      message.setJMSType("check");
      message.setStringProperty("login", login);
      message.setStringProperty("token", token);
      producer.send(queueAuth, message);
      Message newMessage = consumer.receive(); 
      Boolean check = newMessage.getBooleanProperty("status");
      return check;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public String getToken(String login) {
    try {
      JMSContext context = connectionFactory.createContext();
      JMSProducer producer = context.createProducer();     
      JMSConsumer consumer = context.createConsumer(queueAnime);       
      Message message = context.createMessage();
      message.setJMSType("token");
      message.setStringProperty("login", login);
      producer.send(queueAuth, message);
      Message newMessage = consumer.receive(); 
      String token = newMessage.getStringProperty("token");
      return token;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }     
}