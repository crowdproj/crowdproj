plugins {
    `maven-publish`
    id("signing")
    id("org.openapi.generator")
}

signing {
    sign(publishing.publications)
}

val apiVersion = project.name.replace("specs-", "")

openApiGenerate {
    val openapiGroup = "${rootProject.group}.api.$apiVersion"
    additionalProperties.set(mapOf(

    ))
    generatorName.set("html") // Это и есть активный генератор
    templateDir.set("$projectDir/templates")
    packageName.set(openapiGroup)
    apiPackage.set("$openapiGroup.api")
    modelPackage.set("$openapiGroup.models")
    invokerPackage.set("$openapiGroup.invoker")
    inputSpec.set("$projectDir/spec-crowdproj-base.yaml")

    /**
     * Настройка дополнительных параметров из документации по генератору
     * https://github.com/OpenAPITools/openapi-generator/blob/master/docs/generators/kotlin.md
     */
    configOptions.set(
        mapOf(
            "appName" to "CrowdProj",
            "infoUrl" to "https://github.com/crowdproj/crowdproj",
            "licenseInfo" to "The Apache License, Version 2.0",
            "licenseUrl" to "http://www.apache.org/licenses/LICENSE-2.0.txt",
            "artifactId" to project.name,
            "groupId" to project.group.toString(),
            "infoEmail" to "sokatov@gmail.com",
            "artifactVersion" to project.version.toString(),
            "legacyDiscriminatorBehavior" to "false",
            "appDescription" to "OpenAPI specification providing basic definitions for all other CrowdProj projects",
        )
    )
}


val archives: Configuration by configurations.getting
val specFile = layout.buildDirectory.file("$projectDir/spec-crowdproj-base.yaml")
val specArtifact = artifacts.add(archives.name, specFile.get().asFile) {
    type = "yaml"
    classifier = "openapi"
//    builtBy("spec")
}

val javadocJar: TaskProvider<Jar> by tasks.registering(Jar::class) {
    dependsOn("openApiGenerate")
    group = "publishing"
    archiveClassifier.set("javadoc")
    from("$buildDir/generate-resources/main")
}

publishing {
    repositories {
        val repoHost: String? = System.getenv("NEXUS_HOST")
        val repoUser: String? = System.getenv("NEXUS_USER") ?: System.getenv("GITHUB_ACTOR")
        val repoPass: String? = System.getenv("NEXUS_PASS") ?: System.getenv("GITHUB_TOKEN")
        if (repoHost != null && repoUser != null && repoPass != null) {
            println("REPO: $repoHost USER: $repoUser")
            maven {
                name = "GitHubPackages"
                url = uri(repoHost)
                credentials {
                    username = repoUser
                    password = repoPass
                }
            }
        }

    }
    publications {
        create("maven", MavenPublication::class.java) {
            groupId = rootProject.group.toString()
            artifactId = project.name
            version = rootProject.version.toString()
            artifact(javadocJar)
            artifact(specArtifact)
            pom {
                name.set("CrowdProj OpenAPI specification")
                description.set("OpenAPI specification providing basic definitions for all other CrowdProj projects")
                url.set("https://crowdproj.com/")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        name.set("Sergey Okatov")
                        email.set("sokatov@gmail.com")
                        id.set("svok")
                        organization.set("CrowdProj")
                        organizationUrl.set("https://crowdproj.com")
                        timezone.set("GMT+5")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/crowdproj/crowdproj.git")
                    developerConnection.set("scm:git:ssh://github.com/crowdproj/crowdproj.git")
                    url.set("https://github.com/crowdproj/crowdproj")
                }
            }
        }
    }
}

tasks {
    publish {
        dependsOn(openApiGenerate)
    }

    create("deploy") {
        group = "build"
        dependsOn(publish)
//        dependsOn(closeAndReleaseRepository)
    }
}
