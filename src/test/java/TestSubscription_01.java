import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import config.ActionKeywords;
public class TestSubscription_01 {
ActionKeywords keywords;
@Before
public void init() throws IOException
{
keywords=new ActionKeywords();
}
 @Test
public void Subscription_01() throws Throwable {
keywords.openBrowser("Mozilla");
keywords.navigate("https://dev-marketplace.stcs.com.sa/");
keywords.click("mi_Login");
keywords.input("txtbx_UserName","inasr");
keywords.input("txtbx_Password","Inasr@stcs.com18");
keywords.click("btn_LogIn");
keywords.click("mi_Subscriptions");
keywords.click("i_Service", "inasr-2");
keywords.click("btn_Remove", "inasr3");
keywords.click("mi_User");
keywords.click("mi_Logout");
keywords.openBrowser("Mozilla");
keywords.navigate("https://dev-marketplace.stcs.com.sa/");
keywords.click("mi_Login");
keywords.input("txtbx_UserName","inasr");
keywords.input("txtbx_Password","Inasr@stcs.com18");
keywords.click("btn_LogIn");
keywords.click("mi_Subscriptions");
keywords.click("i_Service", "inasr-2");
keywords.click("lnk_AddUsers");
keywords.click("chk_User", "inasr3");
keywords.click("btn_Add");
keywords.checkElementExists("div_User", "inasr3");
keywords.click("mi_User");
keywords.click("mi_Logout");
keywords.navigate("https://dev-marketplace.stcs.com.sa/");
keywords.click("mi_Login");
keywords.input("txtbx_UserName","inasr3");
keywords.input("txtbx_Password","Inasr@stcs.com18");
keywords.click("btn_LogIn");
keywords.checkElementExists("i_Service");
keywords.click("mi_User");
keywords.click("mi_Logout");
}
}
