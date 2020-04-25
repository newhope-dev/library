package library.library.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


@Configuration
public class ShiroConfig {

    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean(name="shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("security") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //拦截器
        Map<String,String> filerChainDefinitionMap=new LinkedHashMap<>();
        /**
         * shiro内置过滤器，可以实现权限的相关拦截器
         *     常用的过滤器：
         *     anon：无需认证可以访问
         *     authc：认证才可访问
         *     perms：
         *     role：
         */
        // filerChainDefinitionMap.put("/hello","authc");
         filerChainDefinitionMap.put("/book/list","anon");
         filerChainDefinitionMap.put("/login","anon");
        filerChainDefinitionMap.put("/register2.html","anon");
        filerChainDefinitionMap.put("/add","anon");
        filerChainDefinitionMap.put("/**/*.js", "anon");

        filerChainDefinitionMap.put("/register.html","anon");
        filerChainDefinitionMap.put("/hello.html","anon");
        filerChainDefinitionMap.put("/testexcel","anon");
        filerChainDefinitionMap.put("/avat.html","anon");
        filerChainDefinitionMap.put("/image","anon");
        filerChainDefinitionMap.put("/**/*.js", "anon");
        filerChainDefinitionMap.put("/**/*.css", "anon");
        filerChainDefinitionMap.put("/**/*.html", "anon");
        filerChainDefinitionMap.put("/**/*.svg", "anon");
        filerChainDefinitionMap.put("/**/*.pdf", "anon");
        filerChainDefinitionMap.put("/**/*.jpg", "anon");
        filerChainDefinitionMap.put("/**/*.png", "anon");
        filerChainDefinitionMap.put("/**/*.ico", "anon");




        //添加自己的过滤器并且取名为jwt
        Map<String, Filter> filterMap=new HashMap<>();
        filterMap.put("jwt",new JwtFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        //过滤链定义，从上向下顺序执行，一般将/**放在最下边
        filerChainDefinitionMap.put("/**","jwt");

        // 未授权界面返回JSON
        shiroFilterFactoryBean.setUnauthorizedUrl("/register.html");
        shiroFilterFactoryBean.setLoginUrl("/register.html");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filerChainDefinitionMap);

        return  shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean(name="security")
    public DefaultWebSecurityManager securityManager(@Qualifier("realm") Realm realm){
        DefaultWebSecurityManager defaultWebSecurityManager=new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }
    /**
     * 创建Realm
     */
  @Bean(name="realm")
    public Realm getRealm(){return new Realm();}
}
