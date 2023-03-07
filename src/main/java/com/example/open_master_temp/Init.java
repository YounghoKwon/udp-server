package com.example.open_master_temp;

import com.example.open_master_temp.entity.TransferObject;
import com.example.open_master_temp.repository.TransferRepository;
import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Init {

    private final TransferRepository transferRepository;

    @PostConstruct
    void init() {

        long cnt = 0L;
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                final ThreadLocalRandom random = ThreadLocalRandom.current();
                final TransferObject transferObject = new TransferObject(random.nextInt(),
                    LocalDateTime.now());
                transferRepository.save(transferObject);
            }
        };
        timer.schedule(task, 0, 100);

    }
}
