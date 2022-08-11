rootProject.name = "crowdproj-root"

pluginManagement {
    plugins {
        val kotlinVersion: String by settings
        kotlin("jvm") version kotlinVersion
//        kotlin("multiplatform") version kotlinVersion
    }
}
include("specs-v0")
//include("specs-v0-test")
