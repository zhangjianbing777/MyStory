package com.nmys.story.service;

import com.nmys.story.model.entity.Metas;

import java.util.List;

/**
 * Description: 友情链接接口
 * author: itachi
 * Date: 2018/5/12 下午6:39
 */
public interface IMetaService {

    /**
     * Description: 根据类型来获取友链
     * author: itachi
     * Date: 2018/5/12 下午6:45
     */
    List<Metas> getMetasByType(String type);

}
