package kz.qwertukg.generator.builders.application

import kz.qwertukg.generator.*
import kz.qwertukg.generator.builders.base.WithContentAndPath

/**
 * Created by Daniil Rakhmatulin.
 */
class ApplicationFile(private val packageName: String) : WithContentAndPath {
    override val fileName = packageName.split(".").last().capitalize() + "Application.kt"
    override val directoryName = packageName.toPath
    override val content = StringBuilder()

    init {
        content.appendln("package $packageName").appendln()
        content.appendln("import org.springframework.boot.SpringApplication")
        content.appendln("import org.springframework.context.annotation.PropertySource")
        content.appendln("import org.springframework.boot.autoconfigure.SpringBootApplication").appendln()
        content.appendln(watermark)
        content.appendln("@SpringBootApplication")
        content.appendln("@PropertySource(\"classpath:application-$packageName.properties\")")
        content.appendln("class ${fileName.dropLast(3)}").appendln()
        content.appendln("fun main(args: Array<String>) {")
        content.appendln("\tSpringApplication.run(${fileName.dropLast(3)}::class.java, *args)")
        content.appendln("}")
    }
}