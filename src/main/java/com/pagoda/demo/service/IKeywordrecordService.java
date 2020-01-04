package com.pagoda.demo.service;

import com.pagoda.demo.entity.Keywordrecord;

import java.util.List;

public interface IKeywordrecordService {
    List<Keywordrecord> findKeyWordList(Keywordrecord keywordrecord);

}
