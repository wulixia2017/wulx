package com.pagoda.demo.dao;

import com.pagoda.demo.entity.Keywordrecord;

import java.util.List;

public interface KeywordrecordDao {
    List<Keywordrecord> findKeyWordList(Keywordrecord keywordrecord);
}
