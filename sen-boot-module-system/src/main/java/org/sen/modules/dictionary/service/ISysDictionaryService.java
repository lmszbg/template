package org.sen.modules.dictionary.service;

import org.sen.common.api.vo.Result;
import org.sen.modules.dictionary.entity.SysDictionary;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sen
 * @since 2019-12-13
 */
public interface ISysDictionaryService extends IService<SysDictionary> {

    Result addDiction(SysDictionary sysDictionary);
}
