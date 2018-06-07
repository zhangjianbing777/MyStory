package com.nmys.story.controller.admin;

import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.page.Page;
import com.blade.mvc.annotation.Param;
import com.blade.mvc.annotation.Route;
import com.blade.mvc.http.HttpMethod;
import com.blade.mvc.http.Request;
import com.blade.mvc.ui.RestResponse;
import com.nmys.story.controller.BaseController;
import com.nmys.story.exception.TipException;
import com.nmys.story.extension.Commons;
import com.nmys.story.model.dto.Types;
import com.nmys.story.model.entity.Attach;
import com.nmys.story.service.SiteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Description:后台附件管理
 * Author:70kg
 * Param
 * Return
 * Date 2018/5/17 10:24
 */
@Slf4j
@Controller
@RequestMapping("admin/attach")
public class AttachController extends BaseController {

    public static final String CLASSPATH = new File(AttachController.class.getResource("/").getPath()).getPath() + File.separatorChar;

    @Inject
    private SiteService siteService;

    /**
     * 附件页面
     *
     * @param request
     * @param page
     * @param limit
     * @return
     */
    @Route(value = "", method = HttpMethod.GET)
    public String index(Request request, @Param(defaultValue = "1") int page,
                        @Param(defaultValue = "12") int limit) {

        Attach       attach     = new Attach();
        Page<Attach> attachPage = attach.page(page, limit);
        request.attribute("attachs", attachPage);
        request.attribute(Types.ATTACH_URL, Commons.site_option(Types.ATTACH_URL, Commons.site_url()));
        return "admin/attach";
    }


    public RestResponse delete(@Param Integer id, HttpServletRequest request) {
        try {
            Attach attach = new Attach().find(id);
            if (null == attach) {
                return RestResponse.fail("不存在该附件");
            }
            String fkey = attach.getFkey();
            siteService.cleanCache(Types.C_STATISTICS);
            String             filePath = CLASSPATH.substring(0, CLASSPATH.length() - 1) + fkey;
            java.nio.file.Path path     = Paths.get(filePath);
            log.info("Delete attach: [{}]", filePath);
            if (Files.exists(path)) {
                Files.delete(path);
            }
            attach.delete(id);
        } catch (Exception e) {
            String msg = "附件删除失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                log.error(msg, e);
            }
            return RestResponse.fail(msg);
        }
        return RestResponse.ok();
    }

}
