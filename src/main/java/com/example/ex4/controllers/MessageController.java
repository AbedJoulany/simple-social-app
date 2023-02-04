package com.example.ex4.controllers;

import com.example.ex4.beans.Label;
import com.example.ex4.beans.UsersBean;
import com.example.ex4.classes.UserMsg;
import com.example.ex4.repo.Message;
import com.example.ex4.repo.MessageRepository;
import com.example.ex4.repo.User;
import com.example.ex4.repo.UserRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//import com.example.ex4.beans.SessionBean;


@Controller
public class MessageController {

    @Resource(name = "ActiveUsersBean")
    private UsersBean usersList = new UsersBean();

    /*@Resource(name = "sessionBeanExample")
    private SessionBean sessionBean;*/

    @Resource(name = "sessionUser")
    private User sessionBean;

    @Autowired
    private MessageRepository repository;

    private MessageRepository getRepo() {
        return repository;
    }

    @Autowired
    private UserRepository userRepository;


    // injecting sesssion scope bean
    // check the corresponding code in BeanConfiguration.java
    @Resource(name="sessionScope")
    private Label sessionLabel;



    @PostMapping("/sendMessage")
    public String sendMessage(@RequestParam("message") String msg, @RequestParam("userName") String userName, User user, BindingResult result, ModelMap model , HttpServletRequest request) {

        User u = userRepository.findByUserName(userName);
        System.out.println("session id in login "+request.getSession().getId());

        System.out.println("user in send message"+ u);
        if(u.getIsActive()) {
            model.addAttribute("currentUser", u);
            model.addAttribute("users", userRepository.findByIsActive(true));
            Message message= new Message(msg, u.getId());
            getRepo().save(message);
            System.out.println("user repo in message controller "+ userRepository.findAll());
            return "redirect:/chatRoom";
        }
        model.addAttribute("errorMessage","your session time is out");
        model.addAttribute("errorMessageType","alert alert-danger");
        return "login";
    }

    @PostMapping(value="/json")
    public @ResponseBody
    String getJson() {

        List<Message> messages = getRepo().findTop5ByOrderByIdDesc();
        List<UserMsg> userMsgList = new ArrayList<UserMsg>();
        Gson gson = new Gson();
        String json;
        for(Message m : messages)
        {
            System.out.println(m.getId());
            Optional<User> u = userRepository.findById(m.getUserId());
            userMsgList.add(new UserMsg(u.get().getUserName(),m.getMessage()));
        }

        json = gson.toJson(userMsgList);

        return json;
    }

    @PostMapping(value="/searchByUser/{userName}")
    public @ResponseBody
    String getMessagesByUser(@PathVariable("userName") String userName) {

        User u = userRepository.findByUserName(userName);
        List<Message> messages = getRepo().findByUserId(u.getId());
        System.out.println(messages);
        List<UserMsg> userMsgList = new ArrayList<UserMsg>();
        Gson gson = new Gson();
        String json;
        for(Message m : messages)
        {
            userMsgList.add(new UserMsg(u.getUserName(),m.getMessage()));
        }

        json = gson.toJson(userMsgList);

        return json;
    }

    @PostMapping(value="/searchByText/{text}")
    public @ResponseBody
    String getMessagesByText(@PathVariable("text") String text) {
        List<Message> messages = getRepo().findAllByMessageContaining(text);
        List<UserMsg> userMsgList = new ArrayList<UserMsg>();
        Gson gson = new Gson();
        String json;
        for(Message m : messages)
        {
            Optional<User> u = userRepository.findById(m.getUserId());
            userMsgList.add(new UserMsg(u.get().getUserName(),m.getMessage()));
        }

        json = gson.toJson(userMsgList);

        return json;
    }


}
