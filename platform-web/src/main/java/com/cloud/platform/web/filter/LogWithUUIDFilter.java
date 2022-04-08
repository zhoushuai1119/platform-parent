package com.cloud.platform.web.filter;

import com.cloud.platform.web.properties.CloudWebProperties;
import org.slf4j.MDC;

import javax.servlet.*;
import java.io.IOException;
import java.util.UUID;

/**
 * @description:
 * @author: zhou shuai
 * @date: 2022/3/29 20:06
 * @version: v1
 */
public class LogWithUUIDFilter implements Filter {

    private CloudWebProperties cloudWebProperties;

    public LogWithUUIDFilter(final CloudWebProperties monsterWebProperties) {
        this.cloudWebProperties = monsterWebProperties;
    }

    public void init(final FilterConfig filterConfig) {
    }

    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        if (this.cloudWebProperties.isEnableLogWithUUID()) {
            try {
                String uuid = UUID.randomUUID().toString();
                //uuid = uuid.substring(uuid.lastIndexOf("-") + 1);
                MDC.put("UUID", uuid.toUpperCase());
            }
            catch (Exception ex) {}
        }
        chain.doFilter(request, response);
        try {
            MDC.remove("UUID");
        }
        catch (Exception ex2) {}
    }

    public void destroy() {

    }

}

