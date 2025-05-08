# Kotlin template

## Checking for updates

Run `./gradlew dependencyUpdates` to get a list of all dependencies that can be updated

## Gradle
### Update gradle

1. Lookup the latest gradle version at https://docs.gradle.org/current/release-notes.html
2. Run `gradle wrapper --gradle-version $VERSION`

### Update dependencies

Open `gradle/libs.versions.toml` and update the version of the dependencies you want to update

## Java
### Update java

1. Lookup the latest release of the JDK at https://www.oracle.com/java/technologies/javase/jdk-relnotes-index.html
2. Replace the java version in the toolchain configuration:
```
kotlin {
  jvmToolchain(JDK_VERSION)
}
```

## Kotlin
### Update kotlin

1. Lookup the latest kotlin version compatible with the gradle version at https://kotlinlang.org/docs/gradle-configure-project.html#apply-the-plugin
2. Replace the version of `versions.kotlin` in `gradle/libs.versions.toml`

## Log4j2

### Output format

#### ECS layout

The default log appender logs to the console in [ECS format](https://www.elastic.co/guide/en/ecs-logging/java/current/setup.html) using `log4j2-ecs-layout`:

```xml
<Console name="Console">
    <EcsLayout serviceName="${name}" serviceVersion="${version}" serviceEnvironment="${deployEnvironment}" />
</Console>
```

The ECS layout allows us to do [structured logging in java](https://www.elastic.co/guide/en/ecs-logging/java/current/_structured_logging_with_log4j2.html) via log4j2's `StringMapMessage`:

```java
logger.info(new StringMapMessage()
    .with("message", "Hello World!")
    .with("foo", "bar"))
```

or using slf4j's fluent api:

```java
logger.atInfo()
    .addKeyValue("foo", "bar")
    .log("Hello World!")
```

#### Generic JSON layout

You can change this to another JSON format using [JsonLayout](https://logging.apache.org/log4j/2.x/manual/layouts.html#JSONLayout) or [JsonLayoutTemplate](https://logging.apache.org/log4j/2.x/manual/json-template-layout.html).
Note that both formats do not work well for dynamically adding JSON properties using `MapMessage` (see https://issues.apache.org/jira/browse/LOG4J2-2248).

To set up another JSON format, first remove the `log4j2-ecs-logging` dependency and add `jackson-databind` as a dependency:

```groovy
dependencies {
    // remove: implementation(libs.log4j.ecs.layout)
    implementation "com.fasterxml.jackson.core:jackson-core:${jacksonVersion}"
    implementation "com.fasterxml.jackson.core:jackson-databind:${jacksonVersion}"
}
```

Secondly, switch the layout to `JsonLayout`:

```xml
<Console name="Console">
    <JsonLayout compact="true" eventEol="true" properties="true" stacktraceAsString="true" includeTimeMillis="true">
        <KeyValuePair key="appName" value="${name}" />
        <KeyValuePair key="version" value="${version}" />
        <KeyValuePair key="environment" value="${deploymentEnvironment}" />
    </JsonLayout>
</Console>
```

#### Human-readable layout

To print in a more human-readable format, replace the `Console` appender by:

```xml
<Console name="Console">
     <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
</Console>
```

### Forward java.util.logging to log4j2

This template forwards all `java.util.logging` through log4j2 as explained in https://logging.apache.org/log4j/2.x/log4j-jul/index.html.

Relevant configuration:

```groovy
dependencies {
    // This bundle contains log4j-jul
    implementation(libs.bundles.log4j)
}

application {
    applicationDefaultJvmArgs = ['-Djava.util.logging.manager=org.apache.logging.log4j.jul.LogManager']
}
```

### Update log4j2

1. Lookup the latest version at https://logging.apache.org/log4j/2.x/index.html
2. Change `versions.log4j` in `gradle/libs.versions.toml`
