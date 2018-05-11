package com.nmys.story.service.impl;

import com.nmys.story.mapper.OptionMapper;
import com.nmys.story.model.entity.Options;
import com.nmys.story.service.IOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OptionServiceImpl implements IOptionService {

    @Autowired
    private OptionMapper optionMapper;

    @Override
    public Options getOptionByName(String name) {
        return optionMapper.getOptionByName(name);
    }
}
