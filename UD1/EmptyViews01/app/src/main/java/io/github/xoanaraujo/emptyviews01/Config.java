package io.github.xoanaraujo.emptyviews01;

import android.content.SharedPreferences;

public class Config {

    private static Config instance;
    private String name , email, age;
    private boolean checkOk;

    private SharedPreferences sp;

    private final String MAIL_KEY = "name";
    private final String NAME_KEY = "mail";
    private final String AGE_KEY = "age";
    private final String CHECKBOX_KEY = "Accept";

    private Config(){

    }

    public int getAge(int noAge){
        int age;
        try{
            age = Integer.parseInt(this.age);
        }catch (NumberFormatException e){
            return  noAge;
        }
        return age;
    }

    public boolean set(String name, String mail, String age, boolean checkOk){
        if(name.trim().equals("")) return false;
        if(!mail.trim().equals("")) if(isValidEmail()) return false;
        if(age.trim().equals("")) return false;

        this.name = name;
        this.email = mail;
        this.age = age;
        this.checkOk = checkOk;
        sp.edit()
                .putString(NAME_KEY, name)
                .putString(MAIL_KEY, mail)
                .putString(AGE_KEY, age)
                .putBoolean(CHECKBOX_KEY, checkOk)
                .apply();
        return true;
    }

    private boolean isValidEmail() {
        return this.email.equals("[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{2,5}");
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean isCheckOk() {
        return checkOk;
    }

    public static Config getInstance(){
        if (instance == null)
            instance = new Config();
        return instance;
    }
}
