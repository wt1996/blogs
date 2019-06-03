package cn.wangtao.baseConfig;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;

/**
 * @ClassName MapperScannerConfig
 * @Auth 桃子
 * @Date 2019-5-20 18:30
 * @Version 1.0
 * @Description
 **/
@Configuration
@AutoConfigureAfter(SqlSessionFactory.class)
public class MapperScannerConfig {

    private String baseMapperPackage="cn.wangtao.mapper";

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage(baseMapperPackage);//每张表对应的XXMapper.java interface类型的Java文件
        Properties properties = new Properties();
        properties.setProperty("mappers", "cn.wangtao.baseEntity.BaseMapperEntity");
        properties.setProperty("notEmpty", "false");
        properties.setProperty("IDENTITY", "MYSQL");
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }
}
