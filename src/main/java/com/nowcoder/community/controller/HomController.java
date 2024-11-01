package com.nowcoder.community.controller;

import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.Page;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.DiscussPostService;
import com.nowcoder.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomController {

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    //定义访问路径
    @RequestMapping(path = "/index1" , method = RequestMethod.GET)
    public String getIndexPage1(Model model, Page page){
        //方法调用前  spingMVC会自动实例化Model以及Page  并将Page放入Model
        //所以 在thymeleaf中可以直接访问Page对象。
        page.setRows(discussPostService.findDiscussPostRows(0));
        page.setPath("/index1");//页面上复用路径
        List<DiscussPost> lists = discussPostService.findDiscussPosts(0, page.getOffset() ,page.getLimit());
        List<Map<String, Object>> disscussPosts = new ArrayList<>();
        for (DiscussPost post : lists) {
            Map<String, Object> map = new HashMap<>();
            map.put("post", post);
            User user = userService.getUserById(post.getUserId());
            disscussPosts.add(map);
        }

        //将展现的结果给Model
        model.addAttribute("dissPosts", disscussPosts);
        return "/index1";
    }

    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String getIndexPage(Model model, Page page) {
        // 方法调用钱,SpringMVC会自动实例化Model和Page,并将Page注入Model.
        // 所以,在thymeleaf中可以直接访问Page对象中的数据.
        page.setRows(discussPostService.findDiscussPostRows(0));
        page.setPath("/index");

        List<DiscussPost> list = discussPostService.findDiscussPosts(0, page.getOffset(), page.getLimit());
        List<Map<String, Object>> discussPosts = new ArrayList<>();
        if (list != null) {
            for (DiscussPost post : list) {
                Map<String, Object> map = new HashMap<>();
                map.put("post", post);
                User user = userService.getUserById(post.getUserId());
                map.put("user", user);
                discussPosts.add(map);
            }
        }
        model.addAttribute("discussPosts", discussPosts);
        return "/index";
    }
}
