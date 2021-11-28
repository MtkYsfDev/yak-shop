package com.xebia.yakshop.utils

import com.xebia.yakshop.models.Herd
import com.xebia.yakshop.models.LabYak
import com.xebia.yakshop.models.Stock
import kotlin.math.round


class Functions {
    companion object {
        fun calculateStock(herd: Herd, days: Int): Pair<Stock, Herd> {
            var liters = 0.0
            var skins = 0
            val calculatedHerd = Herd()
            for (yak: LabYak in herd.labyak) {
                val ageInDays: Int = convertAgeToDays(yak.age)
                val skinsAndLastShave = shavesToDay(ageInDays, days)
                skins += skinsAndLastShave.first
                yak.ageLastShaved = skinsAndLastShave.second
                repeat(days) { i -> liters += getMilk(ageInDays, i) }
                calculatedHerd.labyak.add(yak)
            }
            return Pair(Stock(milk = liters.roundNumber(3), skins = skins), calculatedHerd)
        }

        fun calculateAge(herd: Herd, days: Int): Herd {
            repeat(herd.labyak.size) { index -> herd.labyak[index].age += (days * 0.01) }
            return herd
        }

        fun validateHerd(herd: Herd, days: Int): Herd {
            val validatedHerd = Herd()
            for (yak: LabYak in herd.labyak) {
                if (convertAgeToDays(yak.age) + days < Constants.MAX_DAYS_ALIVE) {
                    validatedHerd.labyak.add(yak)
                }
            }
            return validatedHerd
        }

        fun shavesToDay(age: Int, days: Int): Pair<Int, Double> {
            val ageToDay: Int = age + days
            var skins = 0
            var intervalOfShaving = 0.0
            var ageOfLastShave: Double = age.toDouble()

            if (ageToDay >= 100) {
                skins++
            }
            while (ageToDay > (ageOfLastShave + intervalOfShaving)
                && (ageOfLastShave + intervalOfShaving) < Constants.MAX_DAYS_ALIVE
            ) {
                intervalOfShaving = calculateIntervalOfShaving(ageOfLastShave)
                if ((ageOfLastShave + intervalOfShaving) < ageToDay) {
                    skins++
                    ageOfLastShave += intervalOfShaving
                }
            }
            return Pair(skins, (ageOfLastShave * Constants.SKIN_AS_PER_AGE_FACTOR).roundNumber(2))
        }

        private fun convertAgeToDays(age: Double): Int {
            return (age * Constants.YAK_YEAR).toInt()
        }

        private fun getMilk(age: Int, day: Int): Double {
            val currentDay: Int = age + day
            return if (currentDay < Constants.MAX_DAYS_ALIVE) Constants.MAX_MILK_PRODUCTION - (age + day) * Constants.MILK_AS_PER_AGE_FACTOR else 0.0
        }

        private fun calculateIntervalOfShaving(age: Double): Double {
            return (Constants.MIN_SHAVED_DAY + (age) * Constants.SKIN_AS_PER_AGE_FACTOR) + 1
        }

        private fun Double.roundNumber(decimals: Int): Double {
            var multiplier = 1.0
            repeat(decimals) { multiplier *= 10 }
            return round(this * multiplier) / multiplier
        }
    }
}
