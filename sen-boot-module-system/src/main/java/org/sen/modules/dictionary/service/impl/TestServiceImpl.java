package org.sen.modules.dictionary.service.impl;

import org.sen.modules.dictionary.entity.Test;
import org.sen.modules.dictionary.mapper.TestMapper;
import org.sen.modules.dictionary.service.ITestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sen
 * @since 2019-12-04
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements ITestService {

}
