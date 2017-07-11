package service;

import entity.User;
/**
 * Created by zzy on 2017/5/4.
 */
public abstract class AuthService {
    public abstract  boolean isLogin();

    public abstract  String login(User user);

    public abstract  String logout();

    public abstract String register(User user);

    protected abstract void allow_passport(User user);

}
