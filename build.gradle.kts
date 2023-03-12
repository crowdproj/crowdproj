plugins {
    id("io.codearte.nexus-staging")
}

nexusStaging {
    serverUrl = "https://s01.oss.sonatype.org/service/local/"
    packageGroup = "com.crowdproj"
//    stagingProfileId = "yourStagingProfileId" //when not defined will be got from server using "packageGroup"
}

group = "com.crowdproj"
version = "0.0.3"

subprojects {
    this.group = group
    this.version = version

    repositories {
        mavenCentral()
        mavenLocal()
    }
}

