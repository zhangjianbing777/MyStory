package com.nmys.story.service.impl;

import com.nmys.story.mapper.OptionMapper;
import com.nmys.story.model.entity.Options;
import com.nmys.story.service.IOptionService;
import io.lettuce.core.output.KeyValueStreamingChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void saveOrUpdateOptions(Map<String,String> options) {
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
}
