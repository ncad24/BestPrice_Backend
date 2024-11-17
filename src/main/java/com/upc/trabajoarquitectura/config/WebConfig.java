package com.upc.trabajoarquitectura.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Configura la ruta de imágenes de usuario
        registry.addResourceHandler("/images/user/**")  // URL externa
                .addResourceLocations("file:./images/user/");  // Ruta local en tu servidor

        // Agrega la configuración para las imágenes de productos si no la tienes ya configurada
        registry.addResourceHandler("/images/product/**")
                .addResourceLocations("file:./images/product/");

        registry.addResourceHandler("/images/supermarket/**")
                .addResourceLocations("file:./images/supermarket/");
    }
}