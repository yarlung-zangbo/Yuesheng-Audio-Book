package yuesheng.tv;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
//SpringBoot1.4版本之前用的是@SpringApplicationConfiguration(classes = Application.class)
@SpringBootTest(classes = TvApplication.class)
//测试环境使用，用来表示测试环境使用的ApplicationContext将是WebApplicationContext类型的
@WebAppConfiguration
public class T2VimplControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        //MockMvcBuilders.webAppContextSetup(WebApplicationContext context)：指定WebApplicationContext，将会从该上下文获取相应的控制器并得到相应的MockMvc；
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();//建议使用这种
    }

    @Test
    public void getHello() throws Exception {
        String text = "有人问我 我就会讲\n" +
                "但是无人来\n" +
                "我期待 到无奈\n" +
                "有话要讲 得不到装载\n" +
                "我的心情犹像樽盖等被揭开\n" +
                "咀巴却在养青苔\n" +
                "人潮内 愈文静\n" +
                "愈变得 不受理睬\n" +
                "自己要搞出意外\n" +
                "像突然地高歌\n" +
                "任何地方也像开四面台\n" +
                "着最闪的衫 扮十分感慨\n" +
                "有人来拍照 要记住插袋\n" +
                "你当我是浮夸吧\n" +
                "夸张只因我很怕\n" +
                "似木头 似石头的话\n" +
                "得到注意吗\n" +
                "其实怕被忘记\n" +
                "至放大来演吧\n" +
                "很不安 怎去优雅\n" +
                "世上还赞颂沉默吗\n" +
                "不够爆炸\n" +
                "怎么有话题 让我夸\n" +
                "做大娱乐家\n" +
                "那年十八 母校舞会\n" +
                "站着如喽罗\n" +
                "那时候 我含泪\n" +
                "发誓各位 必须看到我\n" +
                "在世间平凡又普通的路太多\n" +
                "屋村你住哪一座\n" +
                "情爱中 工作中\n" +
                "受过的忽视太多\n" +
                "自尊已饱经跌堕\n" +
                "重视能治肚饿\n" +
                "未曾获得过 便知我为何\n" +
                "大动作很多 犯下这些错\n" +
                "搏人们看看我 算病态么\n" +
                "你当我是浮夸吧\n" +
                "夸张只因我很怕\n" +
                "似木头 似石头的话\n" +
                "得到注意吗\n" +
                "其实怕被忘记\n" +
                "至放大来演吧\n" +
                "很不安 怎去优雅\n" +
                "世上还赞颂沉默吗\n" +
                "不够爆炸\n" +
                "怎么有话题 让我夸\n" +
                "做大娱乐家\n" +
                "幸运儿并不多\n" +
                "若然未当过就知我为何\n" +
                "用十倍苦心 做突出一个\n" +
                "正常人够我 富议论性么\n" +
                "你叫我做浮夸吧\n" +
                "加几声嘘声也不怕\n" +
                "我在场 有闷场的话\n" +
                "表演你看吗 够歇斯底里吗\n" +
                "以眼泪淋花吧\n" +
                "一心只想你惊讶\n" +
                "我旧时似未存在吗\n" +
                "加重注码 青筋也现形\n" +
                "话我知 现在存在吗\n" +
                "凝视我 别再只看天花\n" +
                "我非你杯茶 也可尽情地喝吧\n" +
                "别遗忘有人在 为你声沙";
        text = text.replace('\n',' ');
        System.out.println(text);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/T2V/getaudio").contentType("application/json")
                .content("{\"text\":\""+text+"\",\"title\":\"浮夸\"}"))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                        //断言，判断返回代码是否正确//断言，判断返回的值是否正确
        System.out.println(content);
    }
}


