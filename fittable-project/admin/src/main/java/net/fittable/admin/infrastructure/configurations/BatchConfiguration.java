package net.fittable.admin.infrastructure.configurations;

import net.fittable.admin.infrastructure.batch.UsedReservationsUpdateJob;
import net.fittable.admin.infrastructure.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

//@Configuration
@EnableAsync
@EnableScheduling
public class BatchConfiguration {

    @Autowired
    private SessionRepository sessionRepository;

    @Bean
    public UsedReservationsUpdateJob usedReservationsUpdateJob() {
        return new UsedReservationsUpdateJob(this.sessionRepository);
    }
}
