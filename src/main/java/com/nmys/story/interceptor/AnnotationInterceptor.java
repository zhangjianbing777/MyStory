package com.nmys.story.interceptor;

import com.nmys.story.annotation.OperAction;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Allen 70KG
 * @Title: AnnotationInterceptor
 * @Description: interceptor by annotation
 * @From www.nmyswls.com
 */
public class AnnotationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod action = (HandlerMethod) handler;
            // 获取方法上面的注解
            OperAction operAction = action.getMethodAnnotation(OperAction.class);
            if (operAction != null) {
                String value = operAction.value();
                // 对访问操作进行记录
                // doSomething。。。
                System.out.println(value);
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) {

    }

}
