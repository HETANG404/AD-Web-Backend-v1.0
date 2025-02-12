package sg.edu.nus.adproject.dangerous;

public class Dangerous {
    public void dangerousMethod(String userInput) {
        String query = "SELECT * FROM users WHERE name = '" + userInput + "'";  // SQL 注入漏洞
        // 省略数据库执行代码
    }
}
