package com.pagoda.demo.entity;

import com.pagoda.demo.annotation.MyAnnot;
import com.pagoda.demo.annotation.MyAnnotAge;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

public class User {

    private String name;

    private Integer age;

    @MyAnnotAge
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    @MyAnnot
    public void setName(String name) {
        this.name = name;
    }

    public static User getUser(int age, String name){
        User user = new User();
        user.setAge(age);
        user.setName(name);
        //获取所有方法
        Method[] methods = User.class.getMethods();
        for (Method method : methods) {
            try {
                //如果方法上存在该注解，则赋值
                if (method.isAnnotationPresent(MyAnnot.class)){
                    MyAnnot myAnnot = method.getAnnotation(MyAnnot.class);
//                    if (Objects.isNull(user.getName())){
                        System.out.println(method.invoke(user,myAnnot.value()));
//                    }
                }
                if (method.isAnnotationPresent(MyAnnotAge.class)){
                    MyAnnotAge myAnnotAge = method.getAnnotation(MyAnnotAge.class);
                    int maxValue = myAnnotAge.MaxValue();
                    int minValue = myAnnotAge.minValue();
//                    Integer age = (Integer) method.invoke(method.getName());
                    Integer realAge = (Integer) method.invoke(user);
                    if (Objects.nonNull(realAge) && ( realAge > maxValue || realAge <= minValue )){
                        throw new Exception("该用户年龄是"+user.getAge()+",不符合规则");
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        return user;
    }

    public static void main(String[] args) {
        User user = User.getUser(10,"wulx");
        System.out.println(user.getName()+"========="+user.getAge());
    }
}
