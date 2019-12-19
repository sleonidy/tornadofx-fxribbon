plugins {
    kotlin("jvm") version "1.3.61"
    id("com.jfrog.bintray") version "1.8.4"
    `maven-publish`
    id("name.remal.maven-publish-bintray") version "1.0.161"
}

group = "org.github.sleonidy"
version = "0.1.0"
description = "Tornado FX(https://github.com/edvin/tornadofx) binding for FXRibbon(https://github.com/dukke/FXRibbon)"
repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation("no.tornado:tornadofx:1.7.19")
    implementation("com.pixelduke:fxribbon:1.2.1")
    testImplementation("org.kordamp.ikonli:ikonli-javafx:2.4.0")
    testImplementation("org.kordamp.ikonli:ikonli-devicons-pack:2.4.0")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}
tasks {

}
publishing {
    publications{
        create<MavenPublication>("mavenPublication"){

        }

    }
}
bintray{
    user = System.getenv("BINTRAY_USER")
    key = System.getenv("BINTRAY_PASSWORD")
    setPublications("mavenPublication")
    publish = true
    pkg(delegateClosureOf<com.jfrog.bintray.gradle.BintrayExtension.PackageConfig> {
        repo = "TornadoFX-FXRibbon"
        name = "TornadoFX-FXRibbon"
        userOrg = "sleonidy"
        websiteUrl = "https://github.com/sleonidy/tornadofx-fxribbon"
        githubRepo = "sleonidy/tornadofx-fxribbon"
        vcsUrl = "https://github.com/sleonidy/tornadofx-fxribbon"
        setLabels("kotlin","tornadofx","fxribbon","javafx")
//        setLicenses("MIT")
        desc = description
    })
}