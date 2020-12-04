package ua.site.prototype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import ua.site.prototype.entities.UserEntity;
import ua.site.prototype.service.UserService;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String indexPage(Model model) {

        UserEntity userEntity = userService.findFirstByName("Alex");

        model.addAttribute("isCurrentDate", userService.isCurrentDate(userEntity));
        model.addAttribute("currentDate", userEntity.getCurrentDate());
        model.addAttribute("user", userEntity);

        return "index";
    }

    @PostMapping(value = "/add-photos", produces = "application/json")
    @ResponseBody
    public String addPhotos(@RequestBody String[] imagesBase64) {
        userService.uploadImages("Alex", imagesBase64);

        return "ok";
    }
}
