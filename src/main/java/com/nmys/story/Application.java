package com.nmys.story;

import com.blade.Blade;
import com.blade.security.web.csrf.CsrfMiddleware;
import com.blade.validator.ValidatorMiddleware;
import com.nmys.story.init.TaleLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Tale启动类
 *
 * @author biezhi
 */
@SpringBootApplication
public class Application {

//    public static void main(String[] args) throws Exception {
//        Blade blade = Blade.me();
//        TaleLoader.init(blade);
//        blade.use(new ValidatorMiddleware(), new CsrfMiddleware()).start(Application.class, args);
//    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}