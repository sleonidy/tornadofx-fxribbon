import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
    alias(libs.plugins.org.openjfx.javafxplugin)
    `maven-publish`
}

group = "org.github.sleonidy"
version = "0.1.2"
description = "Tornado FX(https://github.com/edvin/tornadofx) binding for FXRibbon(https://github.com/dukke/FXRibbon)"
repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("reflect"))
    implementation(libs.tornadofx)
    implementation(libs.fxribbon)
    testImplementation(libs.ikonli.javafx)
    testImplementation(libs.ikonli.devicons.pack)
}
javafx {
    modules("javafx.controls", "javafx.fxml")
}

kotlin{
    compilerOptions{
        jvmTarget = JvmTarget.JVM_1_8
    }
    jvmToolchain(22)
}

java{
    sourceCompatibility = JavaVersion.VERSION_1_8
    withJavadocJar()
    withSourcesJar()
    toolchain {
        languageVersion = JavaLanguageVersion.of(22)
        vendor = JvmVendorSpec.ADOPTIUM
    }
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