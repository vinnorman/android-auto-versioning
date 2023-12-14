import org.gradle.api.Project
import org.gradle.process.internal.ExecException
import java.io.ByteArrayOutputStream

fun Project.generateVersionName(): String = try {
    val gitDescribeOutput = gitDescribe()
    TagFormatter.formatVersionName(gitDescribeOutput)
} catch (exception: ExecException) {
    VersionDefaults.DEFAULT_VERSION_NAME
}

fun Project.generateVersionCode(): Int = try {
    val gitDescribeOutput = gitDescribe()
    TagFormatter.formatVersionCode(gitDescribeOutput)
} catch (exception: ExecException) {
    VersionDefaults.DEFAULT_VERSION_CODE
}

private fun Project.gitDescribe(): String {
    val byte = ByteArrayOutputStream()
    project.exec {
        commandLine = "git describe --tags".split(" ")
        standardOutput = byte
    }
    return String(byte.toByteArray()).trim()
}

object VersionDefaults {

    const val DEFAULT_VERSION_NAME = "1"

    const val DEFAULT_VERSION_CODE = 1

}