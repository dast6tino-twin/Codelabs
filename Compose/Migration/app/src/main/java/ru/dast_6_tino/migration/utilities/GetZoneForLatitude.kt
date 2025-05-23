package ru.dast_6_tino.migration.utilities

import kotlin.math.abs

/**
 * A helper function to determine a Plant's growing zone for a given latitude.
 *
 * The numbers listed here are roughly based on the United States Department of Agriculture's
 * Plant Hardiness Zone Map (http://planthardiness.ars.usda.gov/), which helps determine which
 * plants are most likely to thrive at a location.
 *
 * If a given latitude falls on the border between two zone ranges, the larger zone range is chosen
 * (e.g. latitude 14.0 => zone 12).
 *
 * Negative latitude values are converted to positive with [Math.abs].
 *
 * For latitude values greater than max (90.0), zone 1 is returned.
 */
fun getZoneForLatitude(latitude: Double) = when (abs(latitude)) {
    in 0.0..7.0 -> 13
    in 7.0..14.0 -> 12
    in 14.0..21.0 -> 11
    in 21.0..28.0 -> 10
    in 28.0..35.0 -> 9
    in 35.0..42.0 -> 8
    in 42.0..49.0 -> 7
    in 49.0..56.0 -> 6
    in 56.0..63.0 -> 5
    in 63.0..70.0 -> 4
    in 70.0..77.0 -> 3
    in 77.0..84.0 -> 2
    else -> 1 // Remaining latitudes are assigned to zone 1.
}
