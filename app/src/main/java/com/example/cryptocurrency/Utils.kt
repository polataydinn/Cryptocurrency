package com.example.cryptocurrency

import android.content.Context
import android.content.LocusId
import android.view.LayoutInflater
import android.view.View
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

fun ChipGroup.addChip(
    label: String,
    layoutInflater: LayoutInflater,
    chipGroup: ChipGroup
) {
    val chip = layoutInflater.inflate(R.layout.custom_tag_item, chipGroup, false) as Chip
    chip.text = label
    chipGroup.addView(chip)
}
