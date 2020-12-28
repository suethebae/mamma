package shop.mammastore.common;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
//�떛湲��넠 �삎�떇
public class LoginManager implements HttpSessionBindingListener {
    private static Hashtable loginUsers = new Hashtable();

    private LoginManager() {//�깮�꽦�옄
        super();
    }

    public static LoginManager getInstance() {
        return LoginManager.LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final LoginManager INSTANCE = new LoginManager();
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) { //留� �삎�깭濡� �떎�젣 濡쒓렇�씤�쑀�� �젙蹂댁� �궎媛믪��옣
        loginUsers.put(event.getSession(), event.getName());
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) { //remove �꽭�뀡�쓣 �옄�룞�쑝濡� �뙆愿�/濡쒓렇�븘�썐�븷�븣
        loginUsers.remove(event.getSession());
    }

    public void removeSession(String id) { //濡쒓렇�븘�썐�떆�궗�� �씠嫄� 遺�瑜대뒗�뜲 �쐞�뿉爰� �옄�룞�쑝濡� �샇異쒕맖 HttpSessionBindingListener �씪�꽌
        Enumeration e = loginUsers.keys();
        HttpSession session = null;
        while(e.hasMoreElements()) {
            session = (HttpSession) e.nextElement();
            if (loginUsers.get(session).equals(id)) {
                session.invalidate();
            }
        }
    }

    public boolean isLogin(String sessionId) {
        boolean isLogin = false;
        Enumeration e = loginUsers.keys();
        String key = "";
        while(e.hasMoreElements()) {
            key = (String) e.nextElement();
            if (sessionId.equals(key)) {
                isLogin = true;
            }
        }
        return isLogin;
    }

    public void setSession(HttpSession session, String id) {
        session.setAttribute(id, this);
    }

    public String getMemberId(HttpSession session) {
        return (String) loginUsers.get(session);
    }
}










