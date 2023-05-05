/*
 * Copyright (c) 2023 Airbyte, Inc., all rights reserved.
 */

package io.airbyte.commons.server.handlers;

import io.airbyte.api.model.generated.HealthCheckRead;
import io.airbyte.config.persistence.ConfigRepository;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * HealthCheckHandler. Javadocs suppressed because api docs should be used as source of truth.
 */
@SuppressWarnings("MissingJavadocMethod")
@Singleton
public class HealthCheckHandler {

  private final ConfigRepository repository;

  private final AtomicBoolean ready = new AtomicBoolean(false);

  public HealthCheckHandler(@Named("configRepository") final ConfigRepository repository) {
    this.repository = repository;
  }

  public HealthCheckRead health() {
    return new HealthCheckRead().available(repository.healthCheck());
  }

  public boolean isReady() {
    return this.ready.get();
  }

  public void setReady(boolean ready) {
    this.ready.set(ready);
  }

}
