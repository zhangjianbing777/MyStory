package com.nmys.story.model.entity;

import lombok.Data;

/**
 * 附件
 * <p>
 * Created by 70kg
 */
@Data
public class Attach {

    private Integer id;
    private String fname;
    private String ftype;
    private String fkey;
    private Integer author_id;
    private Integer created;

}
