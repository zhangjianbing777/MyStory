package com.nmys.story.controller.admin;

import com.nmys.story.constant.WebConstant;
import com.nmys.story.controller.BaseController;
import com.nmys.story.model.bo.RestResponseBo;
import com.nmys.story.model.dto.Types;
import com.nmys.story.model.entity.Metas;
import com.nmys.story.service.IMetaService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Description:分类管理
 * Author:70kg
 * Param
 * Return
 * Date 2018/5/14 15:35
 */
@Controller
@RequestMapping("admin/category")
@Slf4j(topic = "CategoryController")
public class CategoryController extends BaseController {

    @Autowired
    private IMetaService metasService;

    @GetMapping(value = "")
    public String index(HttpServletRequest request) {
        List<Metas> categories = metasService.getMetaList(Types.CATEGORY, null, WebConstant.MAX_POSTS);
        List<Metas> tags = metasService.getMetaList(Types.TAG, null, WebConstant.MAX_POSTS);
        request.setAttribute("categories", categories);
        request.setAttribute("tags", tags);
        return "admin/category";
    }

    @RequiresPermissions("create")
    @PostMapping(value = "save")
    @ResponseBody
    public RestResponseBo saveCategory(@RequestParam String cname, @RequestParam Integer mid, @RequestParam String metaType) {
        try {
            metasService.saveMeta(metaType, cname, mid);
        } catch (Exception e) {
            String msg = "分类保存失败";
            log.error(msg, e);
            return RestResponseBo.fail(msg);
        }
        return RestResponseBo.ok();
    }

    @RequiresPermissions("delete")
    @PostMapping(value = "delete")
    @ResponseBody
    public RestResponseBo delete(@RequestParam int mid) {
        try {
            metasService.delMetaById(mid);
        } catch (Exception e) {
            String msg = "删除失败";
            log.error(msg, e);
            return RestResponseBo.fail(msg);
        }
        return RestResponseBo.ok();
    }

}
