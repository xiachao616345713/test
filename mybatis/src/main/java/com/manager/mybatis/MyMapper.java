package com.manager.mybatis;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author chao
 * @since 2018-04-10
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
