package com.pagoda.demo.dto.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel(
        value = "FunctionInput",
        description = ""
)
public class FunctionInput implements Serializable {

    @ApiModelProperty(
            name = "id",
            value = "id",
            dataType = "Long"
    )
    private Integer id;

    @ApiModelProperty(

            name = "level",
            value = "level",
            dataType = "int"
    )
    private Integer level;

    @ApiModelProperty(
            name = "parentId",
            value = "parentId",
            dataType = "int"
    )
    private Integer parentId;

    @ApiModelProperty(
            name = "type",
            value = "type",
            dataType = "String"
    )
    private String type;
    @ApiModelProperty(
            name = "title",
            value = "title",
            dataType = "String"
    )
    private String title;

    @ApiModelProperty(
            name = "keyWordRecord",
            value = "keyWordRecord",
            dataType = "String"
    )
    private String keyWordRecord;
    @ApiModelProperty(
            name = "memberCode",
            value = "memberCode",
            dataType = "String"
    )
    private String memberCode;

    @ApiModelProperty(
            name = "keyword",
            value = "keyword",
            dataType = "Long"
    )
    private Integer keyword;

    public List<Integer> getKeywordList() {
        return keywordList;
    }

    public void setKeywordList(List<Integer> keywordList) {
        this.keywordList = keywordList;
    }

    @ApiModelProperty(
            name = "keywordList",
            value = "keywordList",
            dataType = "List<Integer>"
    )
    private List<Integer> keywordList;

    public Integer getKeyword() {
        return keyword;
    }

    public void setKeyword(Integer keyword) {
        this.keyword = keyword;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeyWordRecord() {
        return keyWordRecord;
    }

    public void setKeyWordRecord(String keyWordRecord) {
        this.keyWordRecord = keyWordRecord;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

}
