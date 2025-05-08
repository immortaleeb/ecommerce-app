dependencies {
    api(project(":ordering-api"))

    implementation(project(":foundation"))
    testImplementation(testFixtures(project(":foundation")))
}
