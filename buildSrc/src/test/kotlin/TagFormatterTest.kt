import TagFormatter.formatVersionCode
import TagFormatter.formatVersionName
import com.google.common.truth.Truth.assertThat
import org.gradle.internal.impldep.org.junit.Assert.assertThrows
import org.junit.jupiter.api.Test

class TagFormatterTest {

    @Test
    fun validTag_generatesCorrectVersionName() {
        assertThat(formatVersionName("1.2.3-4-ab12cd34")).isEqualTo("1.2.3")
    }

    @Test
    fun validTag_generatesCorrectVersionCode() {
        assertThat(formatVersionCode("1.2.3")).isEqualTo(1020300)
    }

    @Test
    fun validTagWithCommits_generatesCorrectVersionCode() {
        assertThat(formatVersionCode("1.2.3-4-ab12cd34")).isEqualTo(1020304)
    }

    @Test
    fun invalidTag_throwsExpectedException() {
        assertThrows(IllegalArgumentException::class.java) {
            formatVersionCode("1.2")
        }
    }

    @Test
    fun highMajorVersion_throwsExpectedException() {
        assertThrows(IllegalArgumentException::class.java) {
            formatVersionCode("21.1.3")
        }
    }

}