package com.nmys.story.shiro;

import com.nmys.story.model.bo.ResponseBo;
import com.nmys.story.model.entity.Users;
import com.nmys.story.utils.MD5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * description
 *
 * @author 70KG
 * @date 2018/8/20
 */
@Controller
@RequestMapping("/userLogin")
public class SecurityController {

    @GetMapping("/login")
    public String login() {
        return "themes/shiro/login";
    }
    @PostMapping("/login")
    @ResponseBody
    public ResponseBo login(String username, String password) {
        // 密码MD5加密
        password = MD5Utils.encrypt(username, password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 获取Subject对象
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return ResponseBo.ok();
        } catch (UnknownAccountException e) {
            return ResponseBo.error(e.getMessage());
        } catch (IncorrectCredentialsException e) {
            return ResponseBo.error(e.getMessage());
        } catch (LockedAccountException e) {
            return ResponseBo.error(e.getMessage());
        } catch (AuthenticationException e) {
            return ResponseBo.error("认证失败！");
        }
    }

    @RequestMapping("/")
    public String redirectIndex() {
        return "redirect:/themes/shiro//index";
    }

    @RequestMapping("/index")
    public String index(Model model) {
        // 登录成后，即可通过Subject获取登录的用户信息
        Users user = (Users) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("user", user);
        return "themes/shiro/index";
    }

}
