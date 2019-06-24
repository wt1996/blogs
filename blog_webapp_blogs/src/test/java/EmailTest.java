import cn.wangtao.batch.service.EmailService;
import cn.wangtao.pojo.cms.SysNotify;
import cn.wangtao.run.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName EmailTest
 * @Auth 桃子
 * @Date 2019-6-19 11:03
 * @Version 1.0
 * @Description
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class EmailTest {

    @Autowired
    private EmailService jms;

    @Test
    public void testSendEmail(){
        String receiver = "wangtao_610@126.com";
        try {
            System.out.println("邮件发送开始");
            SysNotify sysNotify = new SysNotify();
            sysNotify.setNotifyMessage("拉啊啊啊啊啊啊啊");
            sysNotify.setNotifyTopic("测试邮件");
            List list=new ArrayList();
            list.add(receiver);
            list.add(receiver);
            list.add(receiver);
            jms.sendMail(list,null);
            System.out.println("邮件发送成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
