package com.prashant.core.domain.models.enums

import androidx.compose.ui.text.intl.Locale
import kmpcleanarchitecturetemplate.core.generated.resources.Res
import kmpcleanarchitecturetemplate.core.generated.resources.language_english
import kmpcleanarchitecturetemplate.core.generated.resources.language_hindi
import org.jetbrains.compose.resources.StringResource

enum class Language(
    val localeCode: String,
    val languageName: StringResource,
) {
    ENGLISH(
        localeCode = "en",
        languageName = Res.string.language_english,
    ),
    HINDI(
        localeCode = "hi",
        languageName = Res.string.language_hindi,
    ),
    ;

    val locale: Locale
        get() = Locale(localeCode)
}
