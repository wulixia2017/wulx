package com.pagoda.demo.service.Impl;


import com.pagoda.demo.dao.KeywordrecordDao;
import com.pagoda.demo.entity.Keywordrecord;
import com.pagoda.demo.service.IKeywordrecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeywordrecordServiceImpl implements IKeywordrecordService {

    @Autowired
    private KeywordrecordDao keywordrecordDao;


    public List<Keywordrecord> findKeyWordList(Keywordrecord keywordrecord){

        return keywordrecordDao.findKeyWordList(keywordrecord);
    }
}
