/*
 * Copyright 2018-2019 https://www.valiktor.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.valiktor.i18n.formatters

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.entry
import org.valiktor.i18n.Formatters
import org.valiktor.i18n.SupportedLocales
import org.valiktor.i18n.formatAllSupportedLocales
import java.util.Calendar
import kotlin.test.Test

class CalendarFormatterTest {

    @Test
    fun `should format date`() {
        val calendar = Calendar.getInstance()
        calendar.set(2018, Calendar.DECEMBER, 31, 0, 0, 0)

        assertThat(Formatters[Calendar::class].formatAllSupportedLocales(calendar)).contains(
            entry(SupportedLocales.DEFAULT, "Dec 31, 2018"),
            entry(SupportedLocales.DE, "31.12.2018"),
            entry(SupportedLocales.EN, "Dec 31, 2018"),
            entry(SupportedLocales.PT_BR, "31/12/2018"))
    }

    @Test
    fun `should format date and time`() {
        val calendar = Calendar.getInstance()
        calendar.set(2018, Calendar.DECEMBER, 31, 23, 58, 59)

        assertThat(Formatters[Calendar::class].formatAllSupportedLocales(calendar)).contains(
            entry(SupportedLocales.DEFAULT, "Dec 31, 2018 11:58:59 PM"),
            entry(SupportedLocales.DE, "31.12.2018 23:58:59"),
            entry(SupportedLocales.EN, "Dec 31, 2018 11:58:59 PM"),
            entry(SupportedLocales.PT_BR, "31/12/2018 23:58:59"))
    }
}
