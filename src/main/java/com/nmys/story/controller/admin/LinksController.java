package com.nmys.story.controller.admin;

import com.nmys.story.controller.BaseController;
import com.nmys.story.mapper.MetaMapper;
import com.nmys.story.model.bo.RestResponseBo;
import com.nmys.story.model.dto.Types;
import com.nmys.story.model.entity.Metas;
import com.nmys.story.service.IMetaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Description:链接控制层
 * Author:70kg
 * Param
 * Return
 * Date 2018/5/14 12:34
 */
@Controller
@RequestMapping("admin/links")
public class LinksController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LinksController.class);

    @Autowired
    private IMetaService metasService;

    @Autowired
    private MetaMapper metaMapper;

    @GetMapping(value = "")
    public String index(HttpServletRequest request) {
        // 根据类型来获取链接
        List<Metas> metas = metasService.getMetasByType(Types.LINK);
        request.setAttribute("links", metas);
        return "admin/links";
    }


    /**
     * Description:保存友链
     * Author:70kg
     * Param [title, url, logo, mid, sort]
     * Return com.nmys.story.model.bo.RestResponseBo
     * Date 2018/5/14 12:39
     */
    @PostMapping(value = "save")
    @ResponseBody
    public RestResponseBo saveLink(@RequestParam String title,
                                   @RequestParam String url,
                                   @RequestParam String logo,
                                   @RequestParam Integer mid,
                                   @RequestParam(value = "sort", defaultValue = "0") int sort) {
        try {
            Metas metas = new Metas();
            metas.setName(title);
            metas.setSlug(url);
            metas.setDescription(logo);
            metas.setSort(sort);
            metas.setType(Types.LINK);
            if (null != mid) {
                metas.setMid(mid);
                metaMapper.updateMeta(metas);
            } else {
                metaMapper.saveMeta(metas);
            }
        } catch (Exception e) {
            String msg = "友链保存失败";
            LOGGER.error(msg, e);
            return RestResponseBo.fail(msg);
        }
        return RestResponseBo.ok();
    }


    /**
     * Description:删除友链
     * Author:70kg
     * Param [mid]
     * Return com.nmys.story.model.bo.RestResponseBo
     * Date 2018/5/14 12:39
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public RestResponseBo delete(@RequestParam int mid) {
        try {
            metaMapper.delMetaById(mid);
        } catch (Exception e) {
            String msg = "友链删除失败";
            LOGGER.error(msg, e);
            return RestResponseBo.fail(msg);
        }
        return RestResponseBo.ok();
    }

}
