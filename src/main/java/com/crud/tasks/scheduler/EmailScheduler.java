package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;


public class EmailScheduler {
    private static final String SUBJECT = "TAsks: Once a day email";
    private static final String TASKS = "tasks";
    private static final String TASK = "task" ;


    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;

    @Scheduled(fixedDelay = 10000)
    public void sendInformationEmail() {
        long size = taskRepository.count();
        String task = size>1 ? TASKS : TASK ;

            simpleEmailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT, "Currently in database you got : " + size +""+ task, ""));

    }
}
