package web.infrastructure.schedule;

import java.util.Date;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.Timeout;
import jakarta.ejb.TimerConfig;
import jakarta.ejb.TimerService;
import jakarta.enterprise.concurrent.ManagedExecutorService;
import jakarta.inject.Inject;
import web.application.chat.IChat;
import web.application.watch.service.IWatch;
import web.infrastructure.builder.Built;
import web.infrastructure.websocket.chat.ChatService;

@Singleton
@Startup
public class Timer {

    @Resource
    private ManagedExecutorService mExecutorService;

    @Resource
    TimerService tservice; 
    
    @Inject @Built
    IWatch watch;
    
    @Inject
    IChat chat;
    
    @PostConstruct    
    public void start() {
        parsing();
        tservice.createIntervalTimer(new Date(), 10000, new TimerConfig()); 
    }
  
    @Timeout
    public void timeout() {
        ChatService.broadcast(chat.getRecommendation(watch.getRandom().getName()));
    }

    private void parsing(){
        watch.updateAll();
    }

    @Schedule(second="*", minute="*", hour="*/12")
    public void parsingTimer(){
        mExecutorService.execute(() -> {
            parsing();
        });
    }
}
