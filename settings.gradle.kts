import de.fayard.refreshVersions.core.FeatureFlag

plugins{
    id("org.gradle.toolchains.foojay-resolver-convention") version("0.8.0")
    id("de.fayard.refreshVersions") version "0.60.5"
}
rootProject.name = "tornadofx-fxribbon"

refreshVersions {
    featureFlags {
        enable(FeatureFlag.GRADLE_UPDATES)
        enable(FeatureFlag.VERSIONS_CATALOG)
    }
}