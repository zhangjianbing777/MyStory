package com.nmys.story.service.impl;

import com.nmys.story.mapper.OptionMapper;
import com.nmys.story.model.bo.BackResponseBo;
import com.nmys.story.model.bo.RestResponseBo;
import com.nmys.story.model.entity.Options;
import com.nmys.story.service.IOptionService;
import com.nmys.story.utils.PropertyUtil;
import io.lettuce.core.output.KeyValueStreamingChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

@Service
public class OptionServiceImpl implements IOptionService {

    @Autowired
    private OptionMapper optionMapper;

    @Override
    public Options getOptionByName(String name) {
        return optionMapper.getOptionByName(name);
    }

    @Override
    public List<Options> getOptions() {
        return optionMapper.getOptions();
    }

    @Override
    public void saveOrUpdateOptions(Map<String, String> options) {
        if (null != options && !options.isEmpty()) {
            BiConsumer<String, String> biConsumer = this::insertOption;
            options.forEach(biConsumer);
        }
    }


    @Transactional
    public void insertOption(String name, String value) {
        Options option = new Options();
        option.setName(name);
        option.setValue(value);
        if (optionMapper.getOptionByName(name) == null) {
            // 新增设置
            optionMapper.saveOption(option);
        } else {
            // 更新设置
            optionMapper.updateByName(option);
        }
    }

    @Override
    public BackResponseBo backup(String bk_type, String bk_path, String expression) throws Exception {
        BackResponseBo backResponse = new BackResponseBo();
        // 命令行用户名以及密码
        String command = PropertyUtil.getProperty("mysqldump_command");
        // 存放sql文件的路径以及格式
        String fPath = bk_path + "/" + new SimpleDateFormat(expression).format(new Date()) + ".sql";
        Runtime rt = Runtime.getRuntime();
        Process child = rt.exec(command);
        InputStream in = child.getInputStream();
        InputStreamReader input = new InputStreamReader(in, "utf8");
        String inStr;
        StringBuffer sb = new StringBuffer("");
        String outStr;
        BufferedReader br = new BufferedReader(input);
        while ((inStr = br.readLine()) != null) {
            sb.append(inStr + "\r\n");
        }
        outStr = sb.toString();
        FileOutputStream fout = new FileOutputStream(fPath);
        OutputStreamWriter writer = new OutputStreamWriter(fout, "utf8");
        writer.write(outStr);
        writer.flush();
        in.close();
        input.close();
        br.close();
        writer.close();
        fout.close();
        backResponse.setSqlPath(fPath);
        System.out.println("==========MYSQL备份成功==========");
        return backResponse;
    }
}
