package com.onlineoffice.conf;

import com.zhuozhengsoft.pageoffice.poserver.Server;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description :初始化Bean
 * ---------------------------------
 * @Author : SG.Y
 * @Date : 2019/5/9 15:40
 */
@Configuration
@ConfigurationProperties
public class BeanInitConf {

    // PageOffice配置
    private String posyspath;

    /***
     * PageOffice 注册
     * @return
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        Server poserver = new Server();
        //设置PageOffice注册成功后,license.lic文件存放的目录
        poserver.setSysPath(posyspath);
        ServletRegistrationBean srb = new ServletRegistrationBean(poserver);
        // 下面是把资源文件暴露出来，必须配置，否则页面访问不了
        srb.addUrlMappings("/poserver.zz", "/posetup.exe", "/pageoffice.js", "/jquery.min.js", "/pobstyle.css", "/sealsetup.exe");
        return srb;
    }

    public void setPosyspath(String posyspath) {
        this.posyspath = posyspath;
    }

}
