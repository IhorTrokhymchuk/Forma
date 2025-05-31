package alyona.forma.util

import alyona.forma.model.user.User
import kotlin.math.max
import kotlin.math.min

object TrainingCoefficientsUtil {

    fun calculateResistanceCoefficient(user: User): Double {
        val gender = user.gender
        val age = user.age
        val weight = user.weight

        val genderKoef = getKoefForGender(gender)
        val ageKoef = getAgeKoef(age)
        val weightKoef = getWeightKoef(weight)
        val trainingLevelKoef = user.trainingLevel.koef

        return trainingLevelKoef * genderKoef * ageKoef * weightKoef
    }

    private fun getKoefForGender(gender: User.Gender): Double = when (gender) {
        User.Gender.MALE -> 1.0
        User.Gender.FEMALE -> 0.75
    }

    private fun getAgeKoef(age: Long): Double = when {
        age < 18 -> 0.8
        age < 35 -> 1.0
        age < 50 -> 0.9
        age < 65 -> 0.8
        else -> 0.7
    }

    private fun getWeightKoef(weight: Long): Double {
        val ratio = weight / 70.0
        return max(0.7, min(ratio, 1.3))
    }
}
