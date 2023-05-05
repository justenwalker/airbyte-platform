/*
 * Copyright (c) 2023 Airbyte, Inc., all rights reserved.
 */

package io.airbyte.server;

import io.airbyte.commons.server.handlers.HealthCheckHandler;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.event.ApplicationShutdownEvent;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.lang.invoke.MethodHandles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ApplicationShutdownListener sets the ready flag on the HealthCheckHandler to false when it
 * receives a shutdown event.
 */
@Singleton
public class ApplicationShutdownListener implements ApplicationEventListener<ApplicationShutdownEvent> {

  // TODO: log4j is likely unusable during shutdown; so logs will never show up. Need some kind of
  // failsafe logger here
  private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

  @Inject
  private HealthCheckHandler healthCheckHandler;

  @Override
  public void onApplicationEvent(ApplicationShutdownEvent event) {
    log.info("ShutdownEvent received");
    healthCheckHandler.setReady(false);
  }

}
