package com.atgugui.mybatis;

import com.atgugi.project.employee;
import com.atguigu.mapper.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class test {
    @Before
    public  void before(){
        System.out.println("before");
    }
    @Test
    public void test01() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        mapper.deleteById(3);
//        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void test02() throws IOException {

    }
}
