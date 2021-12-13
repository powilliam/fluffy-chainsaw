package com.powilliam.fluffychainsaw.ui.composables

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Schedule
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.powilliam.fluffychainsaw.ui.theme.FluffyChainsawTheme
import com.powilliam.fluffychainsaw.ui.utils.currency
import com.powilliam.fluffychainsaw.R

@Composable
fun OverviewCard(
    modifier: Modifier = Modifier,
    totalCost: Float,
    onSelectMonthEnding: (Long) -> Unit = {}
) {
    val context = LocalContext.current

    Surface(
        modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 1.dp
    ) {
        Column {
            Column(
                modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 8.dp, top = 16.dp)
            ) {
                Text(text = currency(totalCost), style = MaterialTheme.typography.displaySmall)
                Text(
                    text = stringResource(R.string.expenses_screen_days_until_month_ending, 23),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Divider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.1F))
            Row(
                modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Chip(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    leading = {
                        Icon(
                            imageVector = Icons.Rounded.Schedule,
                            contentDescription = null
                        )
                    },
                    onClick = {
                        (context as AppCompatActivity).let { activity ->
                            val today = MaterialDatePicker.todayInUtcMilliseconds()
                            val calendarConstraints = CalendarConstraints.Builder()
                                .setValidator(DateValidatorPointForward.now())
                                .setStart(today)
                                .build()
                            val picker = MaterialDatePicker.Builder.datePicker()
                                .setTitleText(activity.getString(R.string.expenses_screen_add_month_ending_date))
                                .setCalendarConstraints(calendarConstraints)
                                .setInputMode(MaterialDatePicker.INPUT_MODE_CALENDAR)
                                .build()

                            picker.show(activity.supportFragmentManager, "AddMonthEndingCalendar")
                            picker.addOnPositiveButtonClickListener { selectedDateInUtcMilliseconds ->
                                onSelectMonthEnding(selectedDateInUtcMilliseconds)
                            }
                        }
                    }
                ) {
                    Text(text = stringResource(R.string.expenses_screen_add_month_ending_date))
                }
            }
        }
    }
}

@Preview
@Composable
private fun OverviewCardPreview() {
    FluffyChainsawTheme {
        OverviewCard(totalCost = 89.97F)
    }
}