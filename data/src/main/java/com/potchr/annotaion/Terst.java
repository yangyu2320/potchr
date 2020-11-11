package com.potchr.annotaion;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;

import java.io.IOException;
import java.lang.annotation.Annotation;

@DistributedBean(name = "test")
public class Terst {

    public static void main(String[] args) throws IOException {
        for (Annotation declaredAnnotation : Terst.class.getDeclaredAnnotations()) {
            System.out.println(declaredAnnotation);
        }
    }
}
