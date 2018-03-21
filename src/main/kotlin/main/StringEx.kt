package main

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.time.format.ResolverStyle

/**
 * 文字列をLocalDate型に変換する拡張関数
 * @param pattern 日付の書式
 * @return LocalDateに変換した日付
 */
fun String.toDate(pattern: String = "uuuu/MM/dd"): LocalDate? {
    // https://qiita.com/emboss369/items/5a3ddea301cbf79d971a

    // Date and Time API には、LocalDate、LocalTime、LocalDateTime などに分かれています。
    // 日付だけの場合に、LocalDateTimeを使うと例外が発生します。

    // patternの妥当性検証
    val format = try {
        DateTimeFormatter.ofPattern(pattern).withResolverStyle(ResolverStyle.STRICT)
    } catch (e: IllegalArgumentException) {
        null
    }

    return format.let {
        try {
            LocalDate.parse(this, it)
        } catch (e: DateTimeParseException) {
            return null
        }
    }
}