import cn.wangtao.pojo.article.Article;
import org.junit.Test;

/**
 * @ClassName TestLombok
 * @Auth 桃子
 * @Date 2019-5-14 11:43
 * @Version 1.0
 * @Description
 **/
public class TestLombok {
    @Test
    public void test1(){
        Article article = new Article();
        article.setContent("这是一个测试");
        System.out.println(article);
    }
}
