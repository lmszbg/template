package org.sen.modules.dictionary.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.sen.common.api.vo.Result;
import org.sen.modules.dictionary.entity.SysDictionary;
import org.sen.modules.dictionary.model.DictionaryModel;
import org.sen.modules.dictionary.service.ISysDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sen
 * @since 2019-12-13
 */
@RestController
@RequestMapping("/dictionary")
@Api("字典")
@AllArgsConstructor
@CrossOrigin
public class SysDictionaryController {

    @Autowired
    private ISysDictionaryService dictionaryService;


    @PostMapping("addDictionary")
    @ApiOperation("添加新的字典数据")
    public Result addDictionary(@Valid @RequestBody DictionaryModel dictionaryModel){
        Result result = dictionaryService.addDiction(new SysDictionary(){{
            setCode(dictionaryModel.getCode());
            setGroupId(dictionaryModel.getGroupId());
            setVal(dictionaryModel.getVal());
            setName(dictionaryModel.getName());
            setIcon(dictionaryModel.getIcon());
            setPatientId(dictionaryModel.getPatientId());
            setUrl(dictionaryModel.getUrl());
            setSortId(dictionaryModel.getSortId());
            setCompose(dictionaryModel.getCompose());
            setDescribe(dictionaryModel.getDescribe());
            setCreateBy("admin");
            setCreateTime(LocalDateTime.now());
            setUpdateBy("admin");
            setUpdateTime(LocalDateTime.now());
        }});
        return result;
    }


}
