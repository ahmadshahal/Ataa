package com.hero.ataa.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

// *: Still Buggy, be careful.
// TODO: FIX.

class MoneyTransformation(private val currency: String) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {

        val out: StringBuilder = StringBuilder()
        out.append("${currency.reversed()} ")
        for((idx, char) in text.reversed().withIndex()) {
            if(idx % 3 == 0 && idx != 0) {
                out.append(',')
            }
            out.append(char)
        }

        val prefix: MutableList<Int> = mutableListOf()
        prefix.add(if(out.isNotEmpty() && out[0] == ',') 1 else 0)
        for(i in 1 until out.length) {
            prefix.add(prefix[i - 1] + if(out[i] == ',') 1 else 0)
        }

        val numberOffsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if(offset == 0) {
                    return offset
                }
                return offset + prefix[offset - 1]
            }

            override fun transformedToOriginal(offset: Int): Int {
                if(offset == 0) {
                    return offset
                }
                return offset - prefix[offset - 1]
            }
        }

        return TransformedText(AnnotatedString(out.toString().reversed()), numberOffsetTranslator)
    }
}