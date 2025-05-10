dependencies {
    api(project(":shipping-api"))
    implementation(project(":foundation"))

    testImplementation(testFixtures(project(":foundation")))
}
