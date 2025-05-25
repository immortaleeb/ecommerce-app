plugins {
  id("application")
}

dependencies {
  implementation(project(":foundation"))
  implementation(project(":vocabulary"))
  implementation(project(":ordering-internal"))
  implementation(project(":shipping-internal"))
  implementation(project(":catalog-internal"))
  implementation(project(":infra-inmemory"))
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
