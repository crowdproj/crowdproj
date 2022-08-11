plugins {
    kotlin("jvm")
}
val apiSpec: Configuration by configurations.creating

dependencies {
    apiSpec(
        group = "com.crowdproj",
        name = "specs-v0",
        version = rootProject.version.toString(),
        classifier = "openapi",
        ext = "yaml"
    )
}

tasks {
    val getSpecs by creating {
        doFirst {
            copy {
                from(apiSpec.asPath)
                into("$buildDir/generate/")
            }
        }
        apiSpec.asFileTree.forEach {
            println("SPEC: $it")
        }
    }
}
