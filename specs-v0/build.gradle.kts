plugins {
    `maven-publish`
}

val archives: Configuration by configurations.creating
val specFile = layout.buildDirectory.file("$projectDir/spec-crowdproj-base.yaml")
val specArtifact = artifacts.add(archives.name, specFile.get().asFile) {
    type = "yaml"
    classifier = "openapi"
//    builtBy("spec")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = project.name
            groupId = rootProject.group.toString()
            version = rootProject.version.toString()
            artifact(specArtifact)
            pom {
                name.set("CrowdProj OpenAPI specification")
                description.set("OpenAPI specification providing basic definitions for all other CrowdProj projects")
                url.set("http://crowdproj.com/")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                scm {
                    connection.set("git@github.com:crowdproj/crowdproj.git")
                    developerConnection.set("git@github.com:crowdproj/crowdproj.git")
                    url.set("https://github.com/crowdproj/crowdproj")
                }
            }
        }
    }
}
