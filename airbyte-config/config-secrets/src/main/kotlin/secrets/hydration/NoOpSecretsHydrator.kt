/*
 * Copyright (c) 2023 Airbyte, Inc., all rights reserved.
 */

package io.airbyte.config.secrets.hydration

import com.fasterxml.jackson.databind.JsonNode
import io.airbyte.config.secrets.persistence.SecretPersistence
import io.micronaut.context.annotation.Requires
import jakarta.inject.Singleton

/**
 * No-op hydrator. Used if there is no secrets persistence configured for this Airbyte instance.
 */
@Requires(missingBeans = [SecretPersistence::class])
@Singleton
class NoOpSecretsHydrator : SecretsHydrator {
  override fun hydrate(partialConfig: JsonNode): JsonNode {
    return partialConfig
  }

  override fun hydrateSecretCoordinate(secretCoordinate: JsonNode): JsonNode {
    return secretCoordinate
  }
}
