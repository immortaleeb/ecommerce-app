plugins {
    id("java-test-fixtures")
}

dependencies {
    testFixturesImplementation(platform(rootProject.libs.junit.bom))
    testFixturesImplementation(rootProject.libs.junit.jupiter.api)
}
