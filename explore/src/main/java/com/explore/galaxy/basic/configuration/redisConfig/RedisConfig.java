package com.explore.galaxy.basic.configuration.redisConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Field;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig;

@Configuration
@PropertySource(value = "classpath:redis.properties", encoding = "utf-8")
public class RedisConfig {
    @Value("${spring.redis.host}")
    private String hostName;
    //    @Value("#{T(java.lang.Integer).parseInt('${spring.redis.port}')}")
    @Value("${spring.redis.port}")
    private int port;


    /**
     * @return: LettuceConnectionFactory
     * @description: 配置redis工厂
     */
    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(hostName, port);
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

    /**
     * @return:
     * @description: 配置自定义redisTemplate的序列化和反序列化, 单独使用
     */
    @Bean
    public RedisTemplate<Object, Object> redisTemplate() {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        RedisSerializer stringSerializer = new StringRedisSerializer();
        template.setConnectionFactory(this.lettuceConnectionFactory());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(stringSerializer);
        template.setHashKeySerializer(stringSerializer);
        template.afterPropertiesSet();
        return template;
    }

    /**
     * @return: redis默认缓存配置
     * @description: 配置redis缓存的默认属性，序列化方法，过期时间等，
     * springboot 2.x后使用如下配置
     */
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        return //创建默认为null的cache配置
                defaultCacheConfig()
                        //使用StringRedisSerializer来序列化key
                        .serializeKeysWith(
                                RedisSerializationContext
                                        .SerializationPair
                                        .fromSerializer(new StringRedisSerializer()))
                        //使用jackson2JsonRedisSerializer来序列化value，否则会出现缓存乱码
                        .serializeValuesWith(
                                RedisSerializationContext
                                        .SerializationPair
                                        .fromSerializer(new GenericJackson2JsonRedisSerializer()))
                        // 不缓存空值,同时需要配合设置@Cacheable（unless="#result == null"）
                        .disableCachingNullValues()
                        //设置过期时间
                        .entryTtl(Duration.ofDays(1));
    }

    /**
     * @params:
     * @return: RedieCacheManager
     * @description: 自定义RedisCacheManager, 顶替Spring的Cache
     */
    @Bean
    public CacheManager cacheManager() throws IllegalAccessException {
        Map<String, RedisCacheConfiguration> configMap = getPerCacheConfig();
        RedisCacheManager redisCacheManager = RedisCacheManager.builder(this.lettuceConnectionFactory())
                //可使用自定义的缓存配置
                .cacheDefaults(defaultCacheConfig())
                .withInitialCacheConfigurations(configMap)
                .build();
        return redisCacheManager;
    }

    /**
     * @return: Map<String, RedisCacheConfiguration>
     * @description: 用作为各个缓存空间自定义配置
     */
    public Map<String, RedisCacheConfiguration> getPerCacheConfig() throws IllegalAccessException {
        // 对每个缓存空间应用不同的配置
        Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
        Class cacheNames = new CacheNames().getClass();
        Field[] fields = cacheNames.getFields();
        for (Field f : fields) {
            configMap.put(f.get(f.getName()).toString(), redisCacheConfiguration().entryTtl(Duration.ofDays(1)));
        }
        return configMap;
    }
}