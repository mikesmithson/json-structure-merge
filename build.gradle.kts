import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {

	id("org.jetbrains.kotlin.jvm") version "1.3.61"
	kotlin("kapt") version "1.3.61"

	`java-library`
}

repositories {
	jcenter()
}

dependencies {

	implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	kapt("com.squareup.moshi:moshi-kotlin-codegen:1.9.2")
	implementation("com.squareup.moshi:moshi-kotlin:1.9.2")
	testImplementation("org.jetbrains.kotlin:kotlin-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
	testImplementation("org.assertj:assertj-core:3.15.0")
}
