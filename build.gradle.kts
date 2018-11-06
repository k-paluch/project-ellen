plugins {
    java
    application
}

group = "sk.tuke.kpi.oop"
version = "1.0"

val gamelibVersion = "2.3.0"

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_10
}

tasks.withType<JavaCompile>().configureEach {
    options.compilerArgs.plusAssign("-parameters")
}

repositories {
    mavenCentral()
    maven(url=uri("https://repo.kpi.fei.tuke.sk/repository/maven-public"))
}

application {
    mainClassName = "sk.tuke.kpi.gamelib.framework.Main"
    //applicationDefaultJvmArgs = mutableListOf("-XstartOnFirstThread")
}

dependencies {
    implementation("sk.tuke.kpi.gamelib:gamelib-framework:$gamelibVersion")
}
