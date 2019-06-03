package cn.wangtao.baseConfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName BeanFactoryImpl
 * @Auth 桃子
 * @Date 2019-5-15 14:44
 * @Version 1.0
 * @Description
 **/
@Configuration
@Slf4j
public class BeanFactoryImpl implements BeanFactoryAware {


  @Override
  public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
    log.info("BeanFactoryAware为：[{}]",beanFactory);
  }
}
