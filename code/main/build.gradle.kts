plugins {
  id("application")
}

dependencies {
  implementation(project(":foundation"))
  implementation(project(":ordering-internal"))
}

application {
  mainClass = "com.github.immortaleeb.ecommerce.ApplicationKt"
  applicationDefaultJvmArgs = listOf("-Djava.util.logging.manager=org.apache.logging.log4j.jul.LogManager")
}

tasks.processResources {
  filesMatching("log4j2.xml") {
    expand(project.properties)
  }
}
