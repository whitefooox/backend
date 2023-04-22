package web.controller.websocket.chat;

import java.util.Date;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.Timeout;
import jakarta.ejb.TimerConfig;
import jakarta.ejb.TimerService;
import jakarta.inject.Inject;
import web.model.chat.IChat;
import web.model.chat.Message;
import web.model.watch.IWatch;

@Singleton
@Startup
public class Timer {

    @Resource
    TimerService tservice; 

    @Inject
    IWatch watch;

    @Inject
    IChat chat;
    
    @PostConstruct    
    public void start() {
        watch.updateAll();
        tservice.createIntervalTimer(new Date(), 10000, new TimerConfig()); 
    }
  
    @Timeout
    public void timeout() {
        ChatService.broadcast(chat.getRecommendation(watch.getRandom().getName()));
    }

    @Schedule(second="*", minute="*", hour="*/12")
    public void parsing(){
        watch.updateAll();
    }
}
