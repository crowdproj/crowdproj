plugins {
    kotlin("multiplatform")
    id("com.crowdproj.generator")
    kotlin("plugin.serialization")
}

val apiSpec: Configuration by configurations.creating
dependencies {
    apiSpec(
        group = "com.crowdproj",
        name = "specs-v1",
        version = rootProject.version.toString(),
        classifier = "openapi",
        ext = "yaml"
    )
}

crowdprojGenerate {
    packageName.set("${rootProject.group}.api.v2")
    inputSpec.set("$projectDir/spec-crowdproj-test.yaml")
}

kotlin {
    jvm { withJava() }
    linuxX64 { }
    macosX64 { }

    artifacts {

    }
    sourceSets {
        val serializationVersion: String by project

        @Suppress("UNUSED_VARIABLE")
        val commonMain by getting {

            kotlin.srcDirs("$buildDir/generate-resources/main/src/commonMain/kotlin")
            dependencies {
                implementation(kotlin("stdlib-common"))

                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$serializationVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")
            }
        }

        @Suppress("UNUSED_VARIABLE")
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        @Suppress("UNUSED_VARIABLE")
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }
    }
}

val getSpecs by tasks.creating {
    doFirst {
        copy {
            from(apiSpec.asPath)
            into("$buildDir")
            rename { "base.yaml" }
        }
    }
}

tasks {
    this.openApiGenerate {
        dependsOn(getSpecs)
    }
}

afterEvaluate {
    val openApiGenerate = tasks.getByName("openApiGenerate")
    tasks.filter { it.name.startsWith("compile") }.forEach {
        it.dependsOn(openApiGenerate)
    }
    tasks.filter { it.name.endsWith("Elements") }.forEach {
        it.dependsOn(openApiGenerate)
    }
}
