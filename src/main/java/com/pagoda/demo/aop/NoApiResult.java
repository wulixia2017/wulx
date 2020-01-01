package com.pagoda.demo.aop;

import java.lang.annotation.*;

/**
 * 不使用{@link com.pagoda.platform.service.ApiResult}对结果进行包装
 * @author PagodaGenerator
 * @since 2019/10/18 19:01
 */
@Inherited
@Target(ElementType.TYPE_PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface NoApiResult {
}
