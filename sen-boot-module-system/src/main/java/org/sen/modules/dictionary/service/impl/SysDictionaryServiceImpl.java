package org.sen.modules.dictionary.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.sen.common.api.vo.Result;
import org.sen.modules.dictionary.entity.SysDictionary;
import org.sen.modules.dictionary.mapper.SysDictionaryMapper;
import org.sen.modules.dictionary.service.ISysDictionaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sen
 * @since 2019-12-13
 */
@Service
public class SysDictionaryServiceImpl extends ServiceImpl<SysDictionaryMapper, SysDictionary> implements ISysDictionaryService {

    private SysDictionaryMapper dictionaryMapper;

    @Autowired
    public SysDictionaryServiceImpl(SysDictionaryMapper dictionaryMapper){
        this.dictionaryMapper = dictionaryMapper;
    }

    @Override
    public Result addDiction(SysDictionary sysDictionary){
        List<SysDictionary> isHasList = dictionaryMapper.selectList(new LambdaQueryWrapper<SysDictionary>()
                .eq(SysDictionary::getCode, sysDictionary.getCode())
                .eq(SysDictionary::getGroupId, sysDictionary.getGroupId()));
        if(isHasList.size() != 0){
            return Result.fail5000("已经存在该字典记录,code:"+sysDictionary.getCode()+",groupId:"+sysDictionary.getGroupId());
        }
        dictionaryMapper.insert(sysDictionary);
        return Result.ok(sysDictionary);
    }
}
