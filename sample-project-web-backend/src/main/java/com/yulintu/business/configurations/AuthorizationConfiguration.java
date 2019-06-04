package com.yulintu.business.configurations;

import com.google.common.base.Strings;
import com.yulintu.thematic.authorizations.TokenAuthorizationHandler;
import com.yulintu.thematic.web.ApiException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;


@Configuration
public class AuthorizationConfiguration {

    @Bean
    public TokenAuthorizationHandler tokenAuthorizationHandler() {

        return (token, request, response, method) -> {

//            if (Strings.isNullOrEmpty(token)) {
//                throw new ApiException(HttpStatus.FORBIDDEN.value(), "令牌为空");
//            }

            // TODO: 其他验证逻辑

            return true;
        };
    }
}