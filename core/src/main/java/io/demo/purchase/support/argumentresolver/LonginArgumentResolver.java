package io.demo.purchase.support.argumentresolver;

import io.demo.purchase.core.domain.error.CoreDomainErrorType;
import io.demo.purchase.core.domain.user.JwtProvider;
import io.demo.purchase.core.domain.user.User;
import io.demo.purchase.core.domain.user.UserReader;
import io.demo.purchase.support.CustomException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Arrays;

@Component
public class LonginArgumentResolver implements HandlerMethodArgumentResolver {

    private final JwtProvider jwtProvider;
    private final UserReader userReader;

    public LonginArgumentResolver(JwtProvider jwtProvider, UserReader userReader) {
        this.jwtProvider = jwtProvider;
        this.userReader = userReader;
    }


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthorizedUser.class)
                && User.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        // cookie 확인하기
        Cookie[] cookies = request.getCookies();
        if (cookies == null)
            throw new CustomException(CoreDomainErrorType.UNAUTHORIZED, "쿠키를 찾지 못했습니다");
        String accessToken = Arrays.stream(cookies)
                .filter((c) -> "accessToken".equals(c.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(() -> new CustomException(CoreDomainErrorType.UNAUTHORIZED, "쿠키를 찾지 못했습니다"));


        // 쿠키 -> 유저 검색 -> User 반환 받기
        long userId = jwtProvider.verifyToken(accessToken);
        User user = userReader.find(userId);

        return user;
    }
}
