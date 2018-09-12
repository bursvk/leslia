package com.leslia.test.validator;

import com.baidu.unbiz.fluentvalidator.*;
import com.leslia.test.pojo.Student;
import com.leslia.util.validator.LengthValidator;
import com.leslia.util.validator.NotNullValidator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ValidatorTest {

    @Test
    public void test(){
        ComplexResult result= FluentValidator.checkAll().failOver()
                .on("11",new LengthValidator(3,20,"用户名")).when(1==2)
                .on(null,new NotNullValidator("年龄"))
                .doValidate().result(ResultCollectors.toComplex());
       System.out.println(result);
    }

    @Test
    public void test1(){
        Student student=new Student();
        student.setName("");
        student.setAge("");
        ComplexResult result= FluentValidator.checkAll().failOver()
                .on(student.getName(),new LengthValidator(3,20,"用户名"))
                .on(student.getAge(),new NotNullValidator("年龄"))
                .doValidate().result(ResultCollectors.toComplex());
        System.out.println(result);
    }

    @Test
    public void test2(){
        Student student=new Student();
        student.setName("");
        student.setAge(null);
        ValidatorChain chain = new ValidatorChain();
        List validators = new ArrayList<Validator>();
        validators.add(new LengthValidator(3,20,"用户名"));
        validators.add(new NotNullValidator("年龄"));
        chain.setValidators(validators);
        Result result= FluentValidator.checkAll().on(student.getName(),chain)
                .doValidate().result(ResultCollectors.toSimple());
        System.out.println(result);
    }


}
