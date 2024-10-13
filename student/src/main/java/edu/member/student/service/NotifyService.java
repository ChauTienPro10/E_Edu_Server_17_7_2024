package edu.member.student.service;

import edu.member.student.entity.Notify;
import edu.member.student.repository.NotifyRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class NotifyService {
    @Autowired
    NotifyRepository notifyRepository;

    public Notify saveNotify(Notify notify){
        notify.setTime(LocalDateTime.now());
        return notifyRepository.save(notify);
    }
}
