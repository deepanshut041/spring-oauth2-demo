package io.github.deepanshut041.sod

import io.github.deepanshut041.sod.config.AppProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(AppProperties::class)
class SodApplication

fun main(args: Array<String>) {
	runApplication<SodApplication>(*args)
}
