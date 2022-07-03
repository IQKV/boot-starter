package org.ujar.boot.starter.logbook;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

/**
 * By default, Logbook uses {@link org.zalando.logbook.json.CompactingJsonBodyFilter}
 * and {@link org.zalando.logbook.json.AccessTokenBodyFilter}
 * But {@link org.zalando.logbook.json.AccessTokenBodyFilter} sometimes throws exceptions
 *
 * <p>This annotation configure body filter only with {@link org.zalando.logbook.json.CompactingJsonBodyFilter}
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({LogbookBodyFilterConfig.class})
public @interface LogbookJsonBodyFilter {
}
