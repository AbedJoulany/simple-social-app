package com.example.ex4.controllers;

//import com.example.ex4.beans.SessionBean;

import com.example.ex4.beans.UsersBean;
import com.example.ex4.classes.UserMsg;
import com.example.ex4.repo.User;
import com.example.ex4.repo.UserRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Resource(name = "ActiveUsersBean")
    private UsersBean usersList = new UsersBean();

    /* @Resource(name = "sessionBeanExample")
     private User sessionBean;*/
    @Resource(name = "sessionUser")
    private User sessionBean;

    /* inject via its type the User repo bean - a singleton */
    @Autowired
    private UserRepository repository;

    private UserRepository getRepo() {
        return repository;
    }

    @GetMapping("/")
    public String main(User user, Model model, HttpServletRequest request) {

        System.out.println("session id in login " + request.getSession().getId());
        if ((request.getSession().getAttribute("status")) == null || (!((boolean) request.getSession().getAttribute("status")))) {
            return "login";
        }
        return "redirect:/chatRoom";
    }

    @GetMapping("/chatRoom")
    public String enterChatRoom(@Valid User user, BindingResult result, Model model, HttpServletRequest request) {

        if ((request.getSession().getAttribute("status")) == null || (!((boolean) request.getSession().getAttribute("status")))) {
            return "redirect:/";
        }
        if (getRepo().findByUserName(sessionBean.getUserName()) != null) {
            model.addAttribute("currentUser", sessionBean);
            return "chatRoom";
        }
        return "chatRoom";
    }

    @PostMapping("/chatRoom")
    public String addUser(@Valid User user, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            return "login";
        }

        System.out.println("search in repo " + getRepo().findByUserName(user.getUserName()));

        // ****** first time **************
        if (getRepo().findByUserName(user.getUserName()) == null) {
            getRepo().save(user);
        }
        // ******* not first time *****************
        else {
            User u = getRepo().findByUserName(user.getUserName());
            u.setIsActive(true);
            getRepo().save(u);
        }
        sessionBean = getRepo().findByUserName(user.getUserName());
        model.addAttribute("currentUser", sessionBean);

        List<User> activeUsers = getRepo().findByIsActive(true);
        model.addAttribute("users", activeUsers);
        request.getSession().setAttribute("status", true);

        return "chatRoom";
    }

    @PostMapping("/getActiveUsers")
    public @ResponseBody
    String getActiveUsers() {
        Gson gson = new Gson();
        String json;
        List<UserMsg> userMsgList = new ArrayList<UserMsg>();

        for (User u : getRepo().findByIsActive(true)) {
            userMsgList.add(new UserMsg(u.getUserName(), " "));
        }
        json = gson.toJson(userMsgList);

        return json;
    }

    @PostMapping("/logout")
    public String logoutUser(@RequestParam("userName") String userName, User user, Model model, HttpServletRequest request) {

        User u = getRepo().findByUserName(userName);
        u.setIsActive(false);
        getRepo().save(u);

        request.getSession().invalidate();
        return "redirect:/";
    }
}
