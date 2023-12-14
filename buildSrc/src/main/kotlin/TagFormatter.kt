import org.gradle.internal.impldep.org.eclipse.jgit.lib.ObjectChecker.tag

internal object TagFormatter {

    fun formatVersionName(input: String): String {
        return input.split("-").first()
    }

    fun formatVersionCode(input: String): Int {
        val parts = input.split("-")

        val tag = parts.first()
        val commits = parts.getOrNull(1)

        val versionParts = tag.split(".")
        if (versionParts.size != 3 || versionParts.any { it.toIntOrNull() == null }) throw IllegalArgumentException("Invalid version tag format: $tag")

        val (major, minor, patch) = versionParts.map { it.toInt() }

        if (major > 20) throw IllegalArgumentException("Major version too large: $major")

        return major * 1_000_000 + minor * 10_000 + patch * 100 + (commits?.toIntOrNull() ?: 0)
    }

}