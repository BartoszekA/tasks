package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.controller.TaskController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    private TaskController taskController;

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://bartoszeka.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("preview_text", "Your new Trello card");
        context.setVariable("goodbye_message", "Thank you for using Trello.");
        context.setVariable("company_details", adminConfig.getCompanyDetails());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        context.setVariable("update_message", "On your account there are " + taskController.getTasks().size() +
                " tasks. To see your tasks, please, visit the website.");
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}
