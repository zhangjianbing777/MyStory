package com.nmys.story.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nmys.story.constant.WebConstant;
import com.nmys.story.controller.BaseController;
import com.nmys.story.model.entity.Logs;
import com.nmys.story.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author itachi
 * @Title: LogController
 * @Description: 日志管理控制层
 * @date 2018/6/7下午8:16
 */
@Controller
@RequestMapping("admin/logs")
public class LogController extends BaseController {

    @Autowired
    private ILogService logService;

    @GetMapping(value = "")
    public String index(@RequestParam(defaultValue = "1") int page,
                        @RequestParam(defaultValue = "10") int limit,
                        HttpServletRequest request) {
        PageHelper.startPage(page, limit);
        List<Logs> list = logService.searchVisitLogByAction(WebConstant.VISITOR);
        PageInfo<Logs> pageInfo = new PageInfo<>(list);
        request.setAttribute("logs", pageInfo);
        return "admin/log_list";
    }

}
