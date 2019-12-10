package org.sen.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * 单数据源配置（jeecg.datasource.open = false时生效）
 *
 */
@Configuration
public class MybatisPlusConfig {

    /**
         *  分页插件
     */
//    @Bean
//    public PaginationInterceptor paginationInterceptor() {
//        // 设置sql的limit为无限制，默认是500
//        return new PaginationInterceptor().setLimit(500);
//    }
    
//    /**
//     * mybatis-plus SQL执行效率插件【生产环境可以关闭】
//     */
//    @Bean
//    public PerformanceInterceptor performanceInterceptor() {
//        return new PerformanceInterceptor();
//    }


}
