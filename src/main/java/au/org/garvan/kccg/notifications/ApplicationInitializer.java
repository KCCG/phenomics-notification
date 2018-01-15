package au.org.garvan.kccg.notifications;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Created by ahmed on 27/11/17.
 */
@Component
public class ApplicationInitializer {
    protected static final Logger log = LoggerFactory.getLogger(ApplicationInitializer.class);

    @EventListener({ContextRefreshedEvent.class})
    public void init() {
    }
}

