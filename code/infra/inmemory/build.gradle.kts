plugins {
    id("org.jetbrains.kotlin.jvm")
}

dependencies {
    implementation(project(":foundation"))
    implementation(project(":vocabulary"))
    
    // Dependencies on the API modules
    implementation(project(":ordering-api"))
    implementation(project(":shipping-api"))
    implementation(project(":catalog-api"))
    
    // Dependencies on the internal modules (for implementing secondary adapters)
    implementation(project(":ordering-internal"))
    implementation(project(":shipping-internal"))
    implementation(project(":catalog-internal"))

    testImplementation(testFixtures(project(":foundation")))
    testImplementation(testFixtures(project(":shipping-internal")))
    testImplementation(testFixtures(project(":ordering-internal")))
}
