package com.cloud.platform.web.filter;

import com.cloud.platform.common.constants.PlatformCommonConstant;
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

    /**
     * 日志追踪ID
     */
    private final String TRACK_ID = PlatformCommonConstant.TrackId.TRACK_ID;

    public LogWithUUIDFilter(final CloudWebProperties monsterWebProperties) {
        this.cloudWebProperties = monsterWebProperties;
    }

    public void init(final FilterConfig filterConfig) {
    }

    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        try {
            if (this.cloudWebProperties.isEnableLogWithUUID()) {
                String trackId = UUID.randomUUID().toString();
                MDC.put(TRACK_ID, trackId);
            }
            chain.doFilter(request, response);
        } catch (Exception ex) {

        } finally {
            MDC.remove(TRACK_ID);
        }
    }

    public void destroy() {
        MDC.clear();
    }

}

