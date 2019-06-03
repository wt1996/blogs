import cn.wangtao.pojo.user.SysUser;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName Test1
 * @Auth 桃子
 * @Date 2019-5-21 10:26
 * @Version 1.0
 * @Description
 **/
public class Test1 {

    @Test
    public void test1(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String time = dateFormat.format(new Date());
        System.out.println(time);
        System.out.println("23:30".compareTo(time));
        System.out.println("16:54".compareTo(time));
        System.out.println("16:55".compareTo(time));
    }
    SysUser user =new SysUser();
    @Test
    public void test2(){
        if("16:52".compareTo(new SimpleDateFormat("HH:mm").format(new Date()))<=0){
            user.setAge(user.getAge()+3);
        }
        System.out.println(user);
    }


}
