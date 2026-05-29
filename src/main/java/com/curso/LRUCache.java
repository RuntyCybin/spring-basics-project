package com.curso;

import java.io.Serial;
import java.util.LinkedHashMap;

public class LRUCache<K, V> extends LinkedHashMap<K, V> {

  @Serial
  private static final long serialVersionUID = -4276040638886380391L;
  private final int capacity;

  public LRUCache(int capacity) {
    super(capacity, 0.75f, true);
    this.capacity = capacity;
  }

  @Override
  protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
    return this.size() > this.capacity;
  }
}
