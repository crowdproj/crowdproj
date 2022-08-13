rootProject.name = "crowdproj-root"

pluginManagement {
    plugins {
        val kotlinVersion: String by settings
        val openapiVersion: String by settings

        kotlin("jvm") version kotlinVersion
        kotlin("multiplatform") version kotlinVersion
        kotlin("plugin.serialization") version kotlinVersion apply false

        id("org.openapi.generator") version openapiVersion apply false
    }
}
val runTests: String by settings
val shouldTest = runTests.toBoolean()

include("specs-v0")
if(shouldTest) include("specs-v0-test")
