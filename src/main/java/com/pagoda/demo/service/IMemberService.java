package com.pagoda.demo.service;

import com.pagoda.demo.entity.Member;

public interface IMemberService {
    Member getMember(int id);

    Member get(int id);
}
