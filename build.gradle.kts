plugins {
    kotlin("jvm") version "1.3.70"
    id("com.jfrog.bintray") version "1.8.4"
    `maven-publish`
    id("name.remal.maven-publish-bintray") version "1.0.177"
}

group = "org.github.sleonidy"
version = "0.1.2"
description = "Tornado FX(https://github.com/edvin/tornadofx) binding for FXRibbon(https://github.com/dukke/FXRibbon)"
repositories {
    mavenCentral()
    jcenter()
    maven("https://maven.pkg.github.com/sleonidy/FXRibbon"){
        credentials {
            username = "sleonidy"
            password = project.property("GITHUB_KEY") as String
        }
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation("no.tornado:tornadofx:1.7.20")
    implementation("com.pixelduke:fxribbon:1.2.2")
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
java{
    sourceCompatibility = JavaVersion.VERSION_1_8
    withJavadocJar()
    withSourcesJar()
}
publishing {
    publications{
        create<MavenPublication>("mavenPublication"){
            from(components["java"])
            groupId = project.group as String
            artifactId = project.name
            version = project.version as String
        }

    }
}
bintray{
    user = if(project.hasProperty("bintrayUser")) project.property("bintrayUser") as String else System.getenv("BINTRAY_USER") as String
    key = if(project.hasProperty("bintrayApiKey")) project.property("bintrayApiKey")  as String  else  System.getenv("BINTRAY_API_KEY") as String
    setPublications("mavenPublication")
    pkg(delegateClosureOf<com.jfrog.bintray.gradle.BintrayExtension.PackageConfig> {
        repo = "tornadofx-fxribbon"
        name = "tornadofx-fxribbon"
        websiteUrl = "https://github.com/sleonidy/tornadofx-fxribbon"
        githubRepo = "sleonidy/tornadofx-fxribbon"
        vcsUrl = "https://github.com/sleonidy/tornadofx-fxribbon"
        setLabels("kotlin","tornadofx","fxribbon","javafx")
//        setLicenses("MIT")
        desc = description
    })
}