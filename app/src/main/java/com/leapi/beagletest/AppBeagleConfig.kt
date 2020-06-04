package com.leapi.beagletest

import br.com.zup.beagle.annotation.BeagleComponent
import br.com.zup.beagle.setup.BeagleConfig
import br.com.zup.beagle.setup.Cache
import br.com.zup.beagle.setup.Environment

@BeagleComponent
class AppBeagleConfig : BeagleConfig {

    override val baseUrl: String get() = "https://myapp.server.com/" // return the base url based on your environment
    override val environment: Environment get() = Environment.DEBUG // return the current build state of your app
    override val cache: Cache = Cache(
        enabled = true, // If true, we will cache data on disk and memory.
        maxAge = 300, // Time in seconds that memory cache will live.
        memoryMaximumCapacity = 15 // Memory LRU cache size. It represents number of screens that will be in memory.
    ) // Cache management configuration
}