package ru.danileyko.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by danil on 27.10.2016.
 */
public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses()
    {
        return new Class<?>[]{WebMVCConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses()
    {
        return new Class<?>[]{WebMVCConfig.class};
    }


    @Override
    protected String[] getServletMappings()
    {
        return new String[]{"/"};
    }
}
