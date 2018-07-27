package com.nmys.story.model.dto;

import lombok.Data;

import java.util.Map;

/**
 * description
 *
 * @author 70KG
 * @date 2018/7/27
 */
@Data
public class IpBean {

    /**响应码**/
    private String code;

    /**数据封装**/
    private Map<String, String> data;

}
