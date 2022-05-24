package com.autosuggestion.engine.schedulers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * A Cron Job to Clear Cache Based on Configured Time
 */
@Component
public class CacheEvictionScheduler {

    private static final Logger logger = LogManager.getLogger(CacheEvictionScheduler.class);

    @Autowired
    private CacheManager cacheManager;

    @Value("${suggestions.cache.name:suggestions}")
    private String suggestionsCacheName;

    @Value("${cache.evict.scheduler.enabled:false}")
    private boolean cacheEvictSchedulerEnabled;

    @Scheduled(cron = "${cache.evict.scheduler.expression:0 0/15 * * * ?}")
    public void evictCache() {
      if (!cacheEvictSchedulerEnabled) {
          logger.info("Cache Evict Scheduler is Disabled");
          return;
      }
      logger.info("Inside CacheEvictScheduler Cron to evict Cache at {}",new Date());
      this.cacheManager.getCache(suggestionsCacheName).clear();
    }
}
