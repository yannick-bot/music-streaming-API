package com.example.musicAPI.config;

import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
public class RedisConfig {

    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return builder -> builder
                // Cache pour les données utilisateur (longue durée)
                .withCacheConfiguration("users",
                        createCacheConfiguration(Duration.ofHours(1)))
                // Cache pour les sessions (durée moyenne)
                .withCacheConfiguration("sessions",
                        createCacheConfiguration(Duration.ofMinutes(30)))
                // Cache pour les données temporaires (courte durée)
                .withCacheConfiguration("temp-data",
                        createCacheConfiguration(Duration.ofMinutes(5)))
                // Configuration par défaut pour les autres caches
                .cacheDefaults(createCacheConfiguration(Duration.ofMinutes(15)));
    }

    private RedisCacheConfiguration createCacheConfiguration(Duration ttl) {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(ttl)
                .disableCachingNullValues()
                .serializeKeysWith(RedisSerializationContext.SerializationPair
                        .fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair
                        .fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }
}
