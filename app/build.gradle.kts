plugins {
    application
    checkstyle
    id("org.sonarqube") version "6.0.1.5171"
    id("se.patrikerdes.use-latest-versions") version "0.2.18"
    id("com.github.ben-manes.versions") version "0.52.0"
    jacoco
}

sonar {
    properties {
        property("sonar.projectKey", "thygh0st_java-project-71")
        property("sonar.organization", "thygh0st")
        property("sonar.host.url", "https://sonarcloud.io")
        property ("sonar.coverage.jacoco.xmlReportPaths", "/reports/jacoco/test/jacocoTestReport.xml")
    }
}

application {
    mainClass = "hexlet.code.App"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.13.0-M2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation ("info.picocli:picocli:4.7.7")
    implementation ("com.fasterxml.jackson.core:jackson-databind:2.19.0")
    implementation ("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.14.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}
tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
    reports {
        xml.required = true
        csv.required = false
        html.outputLocation = layout.buildDirectory.dir("jacocoHtml")
    }
}
