package org.ujar.boot.starter.cache;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "ujar.cache", ignoreUnknownFields = false)
public class CacheProperties {
  Ehcache ehcache = new Ehcache();

  public Ehcache getEhcache() {
    return ehcache;
  }

  public static class Ehcache {
    private int timeToLiveSeconds = 3600;  // 1 hour

    private long maxEntries = 100;

    public int getTimeToLiveSeconds() {
      return timeToLiveSeconds;
    }

    public void setTimeToLiveSeconds(int timeToLiveSeconds) {
      this.timeToLiveSeconds = timeToLiveSeconds;
    }

    public long getMaxEntries() {
      return maxEntries;
    }

    public void setMaxEntries(long maxEntries) {
      this.maxEntries = maxEntries;
    }
  }
}
