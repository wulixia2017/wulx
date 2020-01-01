package com.pagoda.demo.dao;

import com.pagoda.demo.entity.Member;
import org.apache.ibatis.annotations.Param;

public interface MemberDao {
    Member getMember(@Param("id")int id);
}
