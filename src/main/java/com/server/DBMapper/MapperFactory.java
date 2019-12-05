package com.server.DBMapper;


import org.apache.commons.io.IOUtils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 
* @Title: MapperFactory.java 
* @Package com.server.common 
* @Description: 通过动态代理生成mapper的代理对象，打开关闭session,打开关闭事务
* @author: wjl 
* @date 2019年03月11日 下午7:43:50 
* @version V1.0
 */
public enum MapperFactory {

	YDZ_NPDATABASE{
        private SqlSessionFactory sqlSessionFactory;

        public <T> T createMapper(Class<? extends Mapper> clazz){
            return createMapper(clazz,this);
        }
        //SqlSessionFactory org.apache.ibatis.session.SqlSessionFactoryBuilder.build(InputStream inputStream, String environment)
        protected void createSqlSessionFactory(){
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream,this.name());
        }

        public SqlSessionFactory getSqlSessionFactory(){
            return sqlSessionFactory;
        }
    };

    
    public abstract <T> T createMapper(Class<? extends Mapper> clazz);

    protected abstract void createSqlSessionFactory();

    public abstract SqlSessionFactory getSqlSessionFactory();

    private static InputStream inputStream=null;

    static {
        try {
        	
            inputStream = Resources.getResourceAsStream("conf.xml");
            
            YDZ_NPDATABASE.createSqlSessionFactory();
            
            
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    @SuppressWarnings("unchecked")
    protected static <T> T createMapper(Class<? extends Mapper> clazz, MapperFactory mapperFactory){
        SqlSession sqlSession=mapperFactory.getSqlSessionFactory().openSession();
   
        Mapper mapper=sqlSession.getMapper(clazz);
        return (T) MapperProxy.bind(mapper,sqlSession);
    }

    private static class MapperProxy implements InvocationHandler{
        private Mapper mapper;
        private SqlSession sqlSession;
        /*
         * 构造器传入mapper对象和sqlsession
         */
        private MapperProxy(Mapper mapper, SqlSession sqlSession){
            this.mapper=mapper;
            this.sqlSession=sqlSession;
        }

        private static Mapper bind(Mapper mapper, SqlSession sqlSession){
            return (Mapper) Proxy.newProxyInstance(mapper.getClass().getClassLoader(),mapper.getClass().getInterfaces(),new MapperProxy(mapper,sqlSession));
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
            Object object=null;
            try {
                object = method.invoke(mapper,args);
            }catch (Exception e){ 
                e.printStackTrace();
            }finally {
                sqlSession.commit();
                sqlSession.close();
            }
            return object;
        }
    }
}
