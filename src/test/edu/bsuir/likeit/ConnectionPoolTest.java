package test.edu.bsuir.likeit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class ConnectionPoolTest {

    private DataSource datasource = new DataSource();

    @BeforeEach
    public void init() throws Exception {
        PoolProperties p = new PoolProperties();
        p.setUrl("jdbc:mysql://localhost:3306/likeit?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=fa");
        p.setDriverClassName("com.mysql.jdbc.Driver");
        p.setUsername("root");
        p.setPassword("1208");
        p.setJmxEnabled(true);
        p.setTestWhileIdle(false);
        p.setTestOnBorrow(true);
        p.setValidationQuery("SELECT 1");
        p.setTestOnReturn(false);
        p.setValidationInterval(30000);
        p.setTimeBetweenEvictionRunsMillis(30000);
        p.setMaxActive(100);
        p.setInitialSize(10);
        p.setMaxWait(10000);
        p.setRemoveAbandonedTimeout(60);
        p.setMinEvictableIdleTimeMillis(30000);
        p.setMinIdle(10);
        p.setLogAbandoned(true);
        p.setRemoveAbandoned(true);
        p.setJdbcInterceptors(
                "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"+
                        "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
        datasource.setPoolProperties(p);
    }

    @Test
    public void getQuestionTest() throws Exception {
        Connection con = null;
        try {
            con = datasource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from question");
            int cnt = 1;
            while (rs.next()) {
                System.out.println((cnt++)+". :" +rs.getString("theame")+
                        " Question:"+rs.getString("text"));
            }
            rs.close();
            st.close();
        } finally {
            if (con!=null) try {
                con.close();
            }
            catch (Exception ignore) {

            }
        }
    }

    @Test
    public void getAnswerTest() throws Exception {
        Connection con = null;
        try {
            con = datasource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from answers");
            int cnt = 1;
            while (rs.next()) {
                System.out.println((cnt++)+". :" +rs.getString("id")+
                        " Answer:"+rs.getString("text"));
            }
            rs.close();
            st.close();
        } finally {
            if (con!=null) try {
                con.close();
            } catch (Exception ignore) {

            }
        }
    }

    @Test
    public void getUserTest() throws Exception {
        Connection con = null;
        try {
            con = datasource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from user");
            int cnt = 1;
            while (rs.next()) {
                System.out.println((cnt++)+". :" +rs.getString("id")+
                        " User:"+rs.getString("name"));
            }
            rs.close();
            st.close();
        } finally {
            if (con!=null) try {
                con.close();
            } catch (Exception ignore) {

            }
        }
    }
}