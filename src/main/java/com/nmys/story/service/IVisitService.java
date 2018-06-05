package com.nmys.story.service;

import com.nmys.story.model.entity.Visit;

public interface IVisitService {

    Visit getCountById(Integer id);

    void updateCountById(Integer id);

}
