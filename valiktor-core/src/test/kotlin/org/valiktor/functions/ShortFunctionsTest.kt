package org.valiktor.functions

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.valiktor.ConstraintViolationException
import org.valiktor.DefaultConstraintViolation
import org.valiktor.constraints.*
import org.valiktor.functions.ShortFunctionsFixture.Employee
import org.valiktor.validate

private object ShortFunctionsFixture {

    data class Employee(val id: Short? = null)
}

class ShortFunctionsTest {

    @Test
    fun `isNull with null value should be valid`() {
        validate(Employee(), {
            validate(Employee::id).isNull()
        })
    }

    @Test
    fun `isNull with not null value should be invalid`() {
        val exception = assertThrows<ConstraintViolationException> {
            validate(Employee(id = 1.toShort()), {
                validate(Employee::id).isNull()
            })
        }
        assertThat(exception.constraintViolations).containsExactly(
                DefaultConstraintViolation(property = "id", value = 1.toShort(), constraint = Null()))
    }

    @Test
    fun `isNotNull with not null value should be valid`() {
        validate(Employee(id = 1.toShort()), {
            validate(Employee::id).isNotNull()
        })
    }

    @Test
    fun `isNotNull with null value should be invalid`() {
        val exception = assertThrows<ConstraintViolationException> {
            validate(Employee(), {
                validate(Employee::id).isNotNull()
            })
        }
        assertThat(exception.constraintViolations).containsExactly(
                DefaultConstraintViolation(property = "id", constraint = NotNull()))
    }

    @Test
    fun `isEqualTo with null value should be valid`() {
        validate(Employee(), {
            validate(Employee::id).isEqualTo(1.toShort())
        })
    }

    @Test
    fun `isEqualTo with same value should be valid`() {
        validate(Employee(id = 1.toShort()), {
            validate(Employee::id).isEqualTo(1.toShort())
        })
    }

    @Test
    fun `isEqualTo with different value should be invalid`() {
        val exception = assertThrows<ConstraintViolationException> {
            validate(Employee(id = 2.toShort()), {
                validate(Employee::id).isEqualTo(1.toShort())
            })
        }
        assertThat(exception.constraintViolations).containsExactly(
                DefaultConstraintViolation(property = "id", value = 2.toShort(), constraint = Equals(1.toShort())))
    }

    @Test
    fun `isNotEqualTo with null value should be valid`() {
        validate(Employee(), {
            validate(Employee::id).isNotEqualTo(1.toShort())
        })
    }

    @Test
    fun `isNotEqualTo with different value should be valid`() {
        validate(Employee(id = 2.toShort()), {
            validate(Employee::id).isNotEqualTo(1.toShort())
        })
    }

    @Test
    fun `isNotEqualTo with same value should be invalid`() {
        val exception = assertThrows<ConstraintViolationException> {
            validate(Employee(id = 1.toShort()), {
                validate(Employee::id).isNotEqualTo(1.toShort())
            })
        }
        assertThat(exception.constraintViolations).containsExactly(
                DefaultConstraintViolation(property = "id", value = 1.toShort(), constraint = NotEquals(1.toShort())))
    }

    @Test
    fun `isIn vararg with null value should be valid`() {
        validate(Employee(), {
            validate(Employee::id).isIn(1.toShort(), 2.toShort(), 3.toShort())
        })
    }

    @Test
    fun `isIn vararg with same value should be valid`() {
        validate(Employee(id = 2.toShort()), {
            validate(Employee::id).isIn(1.toShort(), 2.toShort(), 3.toShort())
        })
    }

    @Test
    fun `isIn vararg with different value should be invalid`() {
        val exception = assertThrows<ConstraintViolationException> {
            validate(Employee(id = 1.toShort()), {
                validate(Employee::id).isIn(0.toShort(), 2.toShort(), 3.toShort())
            })
        }
        assertThat(exception.constraintViolations).containsExactly(
                DefaultConstraintViolation(property = "id", value = 1.toShort(), constraint = In(setOf(0.toShort(), 2.toShort(), 3.toShort()))))
    }

    @Test
    fun `isIn iterable with null value should be valid`() {
        validate(Employee(), {
            validate(Employee::id).isIn(listOf(1.toShort(), 2.toShort(), 3.toShort()))
        })
    }

    @Test
    fun `isIn iterable with same value should be valid`() {
        validate(Employee(id = 2.toShort()), {
            validate(Employee::id).isIn(listOf(1.toShort(), 2.toShort(), 3.toShort()))
        })
    }

    @Test
    fun `isIn iterable with different value should be invalid`() {
        val exception = assertThrows<ConstraintViolationException> {
            validate(Employee(id = 1.toShort()), {
                validate(Employee::id).isIn(listOf(0.toShort(), 2.toShort(), 3.toShort()))
            })
        }
        assertThat(exception.constraintViolations).containsExactly(
                DefaultConstraintViolation(property = "id", value = 1.toShort(), constraint = In(listOf(0.toShort(), 2.toShort(), 3.toShort()))))
    }

    @Test
    fun `isNotIn vararg with null value should be valid`() {
        validate(Employee(), {
            validate(Employee::id).isNotIn(0.toShort(), 2.toShort(), 3.toShort())
        })
    }

    @Test
    fun `isNotIn vararg with different value should be valid`() {
        validate(Employee(id = 1.toShort()), {
            validate(Employee::id).isNotIn(0.toShort(), 2.toShort(), 3.toShort())
        })
    }

    @Test
    fun `isNotIn vararg with same value should be invalid`() {
        val exception = assertThrows<ConstraintViolationException> {
            validate(Employee(id = 1.toShort()), {
                validate(Employee::id).isNotIn(1.toShort(), 2.toShort(), 3.toShort())
            })
        }
        assertThat(exception.constraintViolations).containsExactly(
                DefaultConstraintViolation(property = "id", value = 1.toShort(), constraint = NotIn(setOf(1.toShort(), 2.toShort(), 3.toShort()))))
    }

    @Test
    fun `isNotIn iterable with null value should be valid`() {
        validate(Employee(), {
            validate(Employee::id).isNotIn(listOf(0.toShort(), 2.toShort(), 3.toShort()))
        })
    }

    @Test
    fun `isNotIn iterable with different value should be valid`() {
        validate(Employee(id = 1.toShort()), {
            validate(Employee::id).isNotIn(listOf(0.toShort(), 2.toShort(), 3.toShort()))
        })
    }

    @Test
    fun `isNotIn iterable with same value should be invalid`() {
        val exception = assertThrows<ConstraintViolationException> {
            validate(Employee(id = 1.toShort()), {
                validate(Employee::id).isNotIn(listOf(1.toShort(), 2.toShort(), 3.toShort()))
            })
        }
        assertThat(exception.constraintViolations).containsExactly(
                DefaultConstraintViolation(property = "id", value = 1.toShort(), constraint = NotIn(listOf(1.toShort(), 2.toShort(), 3.toShort()))))
    }

    @Test
    fun `isZero with null value should be valid`() {
        validate(Employee(), {
            validate(Employee::id).isZero()
        })
    }

    @Test
    fun `isZero with zero should be valid`() {
        validate(Employee(id = 0.toShort()), {
            validate(Employee::id).isZero()
        })
    }

    @Test
    fun `isZero with one should be invalid`() {
        val exception = assertThrows<ConstraintViolationException> {
            validate(Employee(id = 1.toShort()), {
                validate(Employee::id).isZero()
            })
        }

        assertThat(exception.constraintViolations).containsExactly(
                DefaultConstraintViolation(
                        property = "id",
                        value = 1.toShort(),
                        constraint = Equals(0.toShort())))
    }

    @Test
    fun `isNotZero with null value should be valid`() {
        validate(Employee(), {
            validate(Employee::id).isNotZero()
        })
    }

    @Test
    fun `isNotZero with one should be valid`() {
        validate(Employee(id = 1.toShort()), {
            validate(Employee::id).isNotZero()
        })
    }

    @Test
    fun `isNotZero with zero should be invalid`() {
        val exception = assertThrows<ConstraintViolationException> {
            validate(Employee(id = 0.toShort()), {
                validate(Employee::id).isNotZero()
            })
        }

        assertThat(exception.constraintViolations).containsExactly(
                DefaultConstraintViolation(
                        property = "id",
                        value = 0.toShort(),
                        constraint = NotEquals(0.toShort())))
    }

    @Test
    fun `isOne with null value should be valid`() {
        validate(Employee(), {
            validate(Employee::id).isOne()
        })
    }

    @Test
    fun `isOne with one should be valid`() {
        validate(Employee(id = 1.toShort()), {
            validate(Employee::id).isOne()
        })
    }

    @Test
    fun `isOne with zero should be invalid`() {
        val exception = assertThrows<ConstraintViolationException> {
            validate(Employee(id = 0.toShort()), {
                validate(Employee::id).isOne()
            })
        }

        assertThat(exception.constraintViolations).containsExactly(
                DefaultConstraintViolation(
                        property = "id",
                        value = 0.toShort(),
                        constraint = Equals(1.toShort())))
    }

    @Test
    fun `isNotOne with null value should be valid`() {
        validate(Employee(), {
            validate(Employee::id).isNotOne()
        })
    }

    @Test
    fun `isNotOne with zero should be valid`() {
        validate(Employee(id = 0.toShort()), {
            validate(Employee::id).isNotOne()
        })
    }

    @Test
    fun `isNotOne with one should be invalid`() {
        val exception = assertThrows<ConstraintViolationException> {
            validate(Employee(id = 1.toShort()), {
                validate(Employee::id).isNotOne()
            })
        }

        assertThat(exception.constraintViolations).containsExactly(
                DefaultConstraintViolation(
                        property = "id",
                        value = 1.toShort(),
                        constraint = NotEquals(1.toShort())))
    }

    @Test
    fun `isPositive with null value should be valid`() {
        validate(Employee(), {
            validate(Employee::id).isPositive()
        })
    }

    @Test
    fun `isPositive with positive value should be valid`() {
        validate(Employee(id = 1.toShort()), {
            validate(Employee::id).isPositive()
        })
    }

    @Test
    fun `isPositive with zero should be invalid`() {
        val exception = assertThrows<ConstraintViolationException> {
            validate(Employee(id = 0.toShort()), {
                validate(Employee::id).isPositive()
            })
        }

        assertThat(exception.constraintViolations).containsExactly(
                DefaultConstraintViolation(
                        property = "id",
                        value = 0.toShort(),
                        constraint = Greater(0.toShort())))
    }

    @Test
    fun `isPositive with negative value should be invalid`() {
        val exception = assertThrows<ConstraintViolationException> {
            validate(Employee(id = 9876.unaryMinus().toShort()), {
                validate(Employee::id).isPositive()
            })
        }

        assertThat(exception.constraintViolations).containsExactly(
                DefaultConstraintViolation(
                        property = "id",
                        value = 9876.unaryMinus().toShort(),
                        constraint = Greater(0.toShort())))
    }

    @Test
    fun `isNotPositive with null value should be valid`() {
        validate(Employee(), {
            validate(Employee::id).isNotPositive()
        })
    }

    @Test
    fun `isNotPositive with zero should be valid`() {
        validate(Employee(id = 0.toShort()), {
            validate(Employee::id).isNotPositive()
        })
    }

    @Test
    fun `isNotPositive with negative value should be valid`() {
        validate(Employee(id = 9876.unaryMinus().toShort()), {
            validate(Employee::id).isNotPositive()
        })
    }

    @Test
    fun `isNotPositive with positive value should be invalid`() {
        val exception = assertThrows<ConstraintViolationException> {
            validate(Employee(id = 1.toShort()), {
                validate(Employee::id).isNotPositive()
            })
        }

        assertThat(exception.constraintViolations).containsExactly(
                DefaultConstraintViolation(
                        property = "id",
                        value = 1.toShort(),
                        constraint = LessOrEqual(0.toShort())))
    }

    @Test
    fun `isNegative with null value should be valid`() {
        validate(Employee(), {
            validate(Employee::id).isNegative()
        })
    }

    @Test
    fun `isNegative with negative value should be valid`() {
        validate(Employee(id = 1.unaryMinus().toShort()), {
            validate(Employee::id).isNegative()
        })
    }

    @Test
    fun `isNegative with zero should be invalid`() {
        val exception = assertThrows<ConstraintViolationException> {
            validate(Employee(id = 0.toShort()), {
                validate(Employee::id).isNegative()
            })
        }

        assertThat(exception.constraintViolations).containsExactly(
                DefaultConstraintViolation(
                        property = "id",
                        value = 0.toShort(),
                        constraint = Less(0.toShort())))
    }

    @Test
    fun `isNegative with positive value should be invalid`() {
        val exception = assertThrows<ConstraintViolationException> {
            validate(Employee(id = 1.toShort()), {
                validate(Employee::id).isNegative()
            })
        }

        assertThat(exception.constraintViolations).containsExactly(
                DefaultConstraintViolation(
                        property = "id",
                        value = 1.toShort(),
                        constraint = Less(0.toShort())))
    }

    @Test
    fun `isNotNegative with null value should be valid`() {
        validate(Employee(), {
            validate(Employee::id).isNotNegative()
        })
    }

    @Test
    fun `isNotNegative with zero should be valid`() {
        validate(Employee(id = 0.toShort()), {
            validate(Employee::id).isNotNegative()
        })
    }

    @Test
    fun `isNotNegative with positive value should be valid`() {
        validate(Employee(id = 1.toShort()), {
            validate(Employee::id).isNotNegative()
        })
    }

    @Test
    fun `isNotNegative with negative value should be invalid`() {
        val exception = assertThrows<ConstraintViolationException> {
            validate(Employee(id = 9876.unaryMinus().toShort()), {
                validate(Employee::id).isNotNegative()
            })
        }

        assertThat(exception.constraintViolations).containsExactly(
                DefaultConstraintViolation(
                        property = "id",
                        value = 9876.unaryMinus().toShort(),
                        constraint = GreaterOrEqual(0.toShort())))
    }

    @Test
    fun `isLessThan with null value should be valid`() {
        validate(Employee(), {
            validate(Employee::id).isLessThan(10.toShort())
        })
    }

    @Test
    fun `isLessThan with less value should be valid`() {
        validate(Employee(id = 999.toShort()), {
            validate(Employee::id).isLessThan(1000.toShort())
        })
    }

    @Test
    fun `isLessThan with negative less value should be valid`() {
        validate(Employee(id = 4.unaryMinus().toShort()), {
            validate(Employee::id).isLessThan(3.unaryMinus().toShort())
        })
    }

    @Test
    fun `isLessThan with greater value should be invalid`() {
        val exception = assertThrows<ConstraintViolationException> {
            validate(Employee(id = 50.toShort()), {
                validate(Employee::id).isLessThan(49.toShort())
            })
        }

        assertThat(exception.constraintViolations).containsExactly(
                DefaultConstraintViolation(
                        property = "id",
                        value = 50.toShort(),
                        constraint = Less(49.toShort())))
    }

    @Test
    fun `isLessThan with negative greater value should be invalid`() {
        val exception = assertThrows<ConstraintViolationException> {
            validate(Employee(id = 50.unaryMinus().toShort()), {
                validate(Employee::id).isLessThan(51.unaryMinus().toShort())
            })
        }

        assertThat(exception.constraintViolations).containsExactly(
                DefaultConstraintViolation(
                        property = "id",
                        value = 50.unaryMinus().toShort(),
                        constraint = Less(51.unaryMinus().toShort())))
    }

    @Test
    fun `isLessThan with equal value should be invalid`() {
        val exception = assertThrows<ConstraintViolationException> {
            validate(Employee(id = 0.toShort()), {
                validate(Employee::id).isLessThan(0.toShort())
            })
        }

        assertThat(exception.constraintViolations).containsExactly(
                DefaultConstraintViolation(
                        property = "id",
                        value = 0.toShort(),
                        constraint = Less(0.toShort())))
    }

    @Test
    fun `isLessThanOrEqualTo with null value should be valid`() {
        validate(Employee(), {
            validate(Employee::id).isLessThanOrEqualTo(10.toShort())
        })
    }

    @Test
    fun `isLessThanOrEqualTo with less value should be valid`() {
        validate(Employee(id = 999.toShort()), {
            validate(Employee::id).isLessThanOrEqualTo(1000.toShort())
        })
    }

    @Test
    fun `isLessThanOrEqualTo with negative less value should be valid`() {
        validate(Employee(id = 4.unaryMinus().toShort()), {
            validate(Employee::id).isLessThanOrEqualTo(3.unaryMinus().toShort())
        })
    }

    @Test
    fun `isLessThanOrEqualTo with equal value should be valid`() {
        validate(Employee(id = 0.toShort()), {
            validate(Employee::id).isLessThanOrEqualTo(0.toShort())
        })
    }

    @Test
    fun `isLessThanOrEqualTo with greater value should be invalid`() {
        val exception = assertThrows<ConstraintViolationException> {
            validate(Employee(id = 5678.toShort()), {
                validate(Employee::id).isLessThanOrEqualTo(57.toShort())
            })
        }

        assertThat(exception.constraintViolations).containsExactly(
                DefaultConstraintViolation(
                        property = "id",
                        value = 5678.toShort(),
                        constraint = LessOrEqual(57.toShort())))
    }

    @Test
    fun `isLessThanOrEqualTo with negative greater value should be invalid`() {
        val exception = assertThrows<ConstraintViolationException> {
            validate(Employee(id = 96.unaryMinus().toShort()), {
                validate(Employee::id).isLessThanOrEqualTo(97.unaryMinus().toShort())
            })
        }

        assertThat(exception.constraintViolations).containsExactly(
                DefaultConstraintViolation(
                        property = "id",
                        value = 96.unaryMinus().toShort(),
                        constraint = LessOrEqual(97.unaryMinus().toShort())))
    }

    @Test
    fun `isGreaterThan with null value should be valid`() {
        validate(Employee(), {
            validate(Employee::id).isGreaterThan(10.toShort())
        })
    }

    @Test
    fun `isGreaterThan with greater value should be valid`() {
        validate(Employee(id = 11.toShort()), {
            validate(Employee::id).isGreaterThan(10.toShort())
        })
    }

    @Test
    fun `isGreaterThan with negative greater value should be valid`() {
        validate(Employee(id = 88.unaryMinus().toShort()), {
            validate(Employee::id).isGreaterThan(89.unaryMinus().toShort())
        })
    }

    @Test
    fun `isGreaterThan with less value should be invalid`() {
        val exception = assertThrows<ConstraintViolationException> {
            validate(Employee(id = 10.toShort()), {
                validate(Employee::id).isGreaterThan(11.toShort())
            })
        }

        assertThat(exception.constraintViolations).containsExactly(
                DefaultConstraintViolation(
                        property = "id",
                        value = 10.toShort(),
                        constraint = Greater(11.toShort())))
    }

    @Test
    fun `isGreaterThan with negative less value should be invalid`() {
        val exception = assertThrows<ConstraintViolationException> {
            validate(Employee(id = 189.unaryMinus().toShort()), {
                validate(Employee::id).isGreaterThan(180.unaryMinus().toShort())
            })
        }

        assertThat(exception.constraintViolations).containsExactly(
                DefaultConstraintViolation(
                        property = "id",
                        value = 189.unaryMinus().toShort(),
                        constraint = Greater(180.unaryMinus().toShort())))
    }

    @Test
    fun `isGreaterThan with equal value should be invalid`() {
        val exception = assertThrows<ConstraintViolationException> {
            validate(Employee(id = 0.toShort()), {
                validate(Employee::id).isGreaterThan(0.toShort())
            })
        }

        assertThat(exception.constraintViolations).containsExactly(
                DefaultConstraintViolation(
                        property = "id",
                        value = 0.toShort(),
                        constraint = Greater(0.toShort())))
    }

    @Test
    fun `isGreaterThanOrEqualTo with null value should be valid`() {
        validate(Employee(), {
            validate(Employee::id).isGreaterThanOrEqualTo(10.toShort())
        })
    }

    @Test
    fun `isGreaterThanOrEqualTo with greater value should be valid`() {
        validate(Employee(id = 10000.toShort()), {
            validate(Employee::id).isGreaterThanOrEqualTo(9999.toShort())
        })
    }

    @Test
    fun `isGreaterThanOrEqualTo with negative greater value should be valid`() {
        validate(Employee(id = 3.unaryMinus().toShort()), {
            validate(Employee::id).isGreaterThanOrEqualTo(4.unaryMinus().toShort())
        })
    }

    @Test
    fun `isGreaterThanOrEqualTo with equal value should be valid`() {
        validate(Employee(id = 0.toShort()), {
            validate(Employee::id).isGreaterThanOrEqualTo(0.toShort())
        })
    }

    @Test
    fun `isGreaterThanOrEqualTo with less value should be invalid`() {
        val exception = assertThrows<ConstraintViolationException> {
            validate(Employee(id = 57.toShort()), {
                validate(Employee::id).isGreaterThanOrEqualTo(5678.toShort())
            })
        }

        assertThat(exception.constraintViolations).containsExactly(
                DefaultConstraintViolation(
                        property = "id",
                        value = 57.toShort(),
                        constraint = GreaterOrEqual(5678.toShort())))
    }

    @Test
    fun `isGreaterThanOrEqualTo with negative less value should be invalid`() {
        val exception = assertThrows<ConstraintViolationException> {
            validate(Employee(id = 97.unaryMinus().toShort()), {
                validate(Employee::id).isGreaterThanOrEqualTo(96.unaryMinus().toShort())
            })
        }

        assertThat(exception.constraintViolations).containsExactly(
                DefaultConstraintViolation(
                        property = "id",
                        value = 97.unaryMinus().toShort(),
                        constraint = GreaterOrEqual(96.unaryMinus().toShort())))
    }

    @Test
    fun `isBetween with null value should be valid`() {
        validate(Employee(), {
            validate(Employee::id).isBetween(start = 1.toShort(), end = 9.toShort())
        })
    }

    @Test
    fun `isBetween with equal start value should be valid`() {
        validate(Employee(id = 0.toShort()), {
            validate(Employee::id).isBetween(start = 0.toShort(), end = 1.toShort())
        })
    }

    @Test
    fun `isBetween with equal end value should be valid`() {
        validate(Employee(id = 1.toShort()), {
            validate(Employee::id).isBetween(start = 0.toShort(), end = 1.toShort())
        })
    }

    @Test
    fun `isBetween with within value should be valid`() {
        validate(Employee(id = 5.toShort()), {
            validate(Employee::id).isBetween(start = 0.toShort(), end = 10.toShort())
        })
    }

    @Test
    fun `isBetween with equal negative start value should be valid`() {
        validate(Employee(id = 2.unaryMinus().toShort()), {
            validate(Employee::id).isBetween(start = 2.unaryMinus().toShort(), end = 1.unaryMinus().toShort())
        })
    }

    @Test
    fun `isBetween with equal negative end value should be valid`() {
        validate(Employee(id = 1.unaryMinus().toShort()), {
            validate(Employee::id).isBetween(start = 2.unaryMinus().toShort(), end = 1.unaryMinus().toShort())
        })
    }

    @Test
    fun `isBetween with within negative value should be valid`() {
        validate(Employee(id = 15.unaryMinus().toShort()), {
            validate(Employee::id).isBetween(start = 20.unaryMinus().toShort(), end = 10.unaryMinus().toShort())
        })
    }

    @Test
    fun `isBetween with less start value should be invalid`() {
        val exception = assertThrows<ConstraintViolationException> {
            validate(Employee(id = 10.toShort()), {
                validate(Employee::id).isBetween(start = 11.toShort(), end = 12.toShort())
            })
        }

        assertThat(exception.constraintViolations).containsExactly(
                DefaultConstraintViolation(
                        property = "id",
                        value = 10.toShort(),
                        constraint = Between(start = 11.toShort(), end = 12.toShort())))
    }

    @Test
    fun `isBetween with greater end value should be invalid`() {
        val exception = assertThrows<ConstraintViolationException> {
            validate(Employee(id = 12.toShort()), {
                validate(Employee::id).isBetween(start = 10.toShort(), end = 11.toShort())
            })
        }

        assertThat(exception.constraintViolations).containsExactly(
                DefaultConstraintViolation(
                        property = "id",
                        value = 12.toShort(),
                        constraint = Between(start = 10.toShort(), end = 11.toShort())))
    }

    @Test
    fun `isBetween with less negative start value should be invalid`() {
        val exception = assertThrows<ConstraintViolationException> {
            validate(Employee(id = 10.unaryMinus().toShort()), {
                validate(Employee::id).isBetween(start = 9.unaryMinus().toShort(), end = 8.unaryMinus().toShort())
            })
        }

        assertThat(exception.constraintViolations).containsExactly(
                DefaultConstraintViolation(
                        property = "id",
                        value = 10.unaryMinus().toShort(),
                        constraint = Between(start = 9.unaryMinus().toShort(), end = 8.unaryMinus().toShort())))
    }

    @Test
    fun `isBetween with greater negative end value should be invalid`() {
        val exception = assertThrows<ConstraintViolationException> {
            validate(Employee(id = 12.unaryMinus().toShort()), {
                validate(Employee::id).isBetween(start = 14.unaryMinus().toShort(), end = 13.unaryMinus().toShort())
            })
        }

        assertThat(exception.constraintViolations).containsExactly(
                DefaultConstraintViolation(
                        property = "id",
                        value = 12.unaryMinus().toShort(),
                        constraint = Between(start = 14.unaryMinus().toShort(), end = 13.unaryMinus().toShort())))
    }

    @Test
    fun `isNotBetween with null value should be valid`() {
        validate(Employee(), {
            validate(Employee::id).isNotBetween(start = 1.toShort(), end = 9.toShort())
        })
    }

    @Test
    fun `isNotBetween with less start value should be valid`() {
        validate(Employee(id = 10.toShort()), {
            validate(Employee::id).isNotBetween(start = 11.toShort(), end = 12.toShort())
        })
    }

    @Test
    fun `isNotBetween with greater end value should be valid`() {
        validate(Employee(id = 12.toShort()), {
            validate(Employee::id).isNotBetween(start = 10.toShort(), end = 11.toShort())
        })
    }

    @Test
    fun `isNotBetween with less negative start value should be valid`() {
        validate(Employee(id = 10.unaryMinus().toShort()), {
            validate(Employee::id).isNotBetween(start = 9.unaryMinus().toShort(), end = 8.unaryMinus().toShort())
        })
    }

    @Test
    fun `isNotBetween with greater negative end value should be valid`() {
        validate(Employee(id = 12.unaryMinus().toShort()), {
            validate(Employee::id).isNotBetween(start = 14.unaryMinus().toShort(), end = 13.unaryMinus().toShort())
        })
    }

    @Test
    fun `isNotBetween with equal start value should be invalid`() {
        val exception = assertThrows<ConstraintViolationException> {
            validate(Employee(id = 0.toShort()), {
                validate(Employee::id).isNotBetween(start = 0.toShort(), end = 1.toShort())
            })
        }

        assertThat(exception.constraintViolations).containsExactly(
                DefaultConstraintViolation(
                        property = "id",
                        value = 0.toShort(),
                        constraint = NotBetween(start = 0.toShort(), end = 1.toShort())))
    }

    @Test
    fun `isNotBetween with equal end value should be invalid`() {
        val exception = assertThrows<ConstraintViolationException> {
            validate(Employee(id = 1.toShort()), {
                validate(Employee::id).isNotBetween(start = 0.toShort(), end = 1.toShort())
            })
        }

        assertThat(exception.constraintViolations).containsExactly(
                DefaultConstraintViolation(
                        property = "id",
                        value = 1.toShort(),
                        constraint = NotBetween(start = 0.toShort(), end = 1.toShort())))
    }

    @Test
    fun `isNotBetween with equal negative start value should be invalid`() {
        val exception = assertThrows<ConstraintViolationException> {
            validate(Employee(id = 2.unaryMinus().toShort()), {
                validate(Employee::id).isNotBetween(start = 2.unaryMinus().toShort(), end = 1.unaryMinus().toShort())
            })
        }

        assertThat(exception.constraintViolations).containsExactly(
                DefaultConstraintViolation(
                        property = "id",
                        value = 2.unaryMinus().toShort(),
                        constraint = NotBetween(start = 2.unaryMinus().toShort(), end = 1.unaryMinus().toShort())))
    }

    @Test
    fun `isNotBetween with equal negative end value should be invalid`() {
        val exception = assertThrows<ConstraintViolationException> {
            validate(Employee(id = 1.unaryMinus().toShort()), {
                validate(Employee::id).isNotBetween(start = 2.unaryMinus().toShort(), end = 1.unaryMinus().toShort())
            })
        }

        assertThat(exception.constraintViolations).containsExactly(
                DefaultConstraintViolation(
                        property = "id",
                        value = 1.unaryMinus().toShort(),
                        constraint = NotBetween(start = 2.unaryMinus().toShort(), end = 1.unaryMinus().toShort())))
    }

    @Test
    fun `isNotBetween with within value should be invalid`() {
        val exception = assertThrows<ConstraintViolationException> {
            validate(Employee(id = 5.toShort()), {
                validate(Employee::id).isNotBetween(start = 0.toShort(), end = 10.toShort())
            })
        }

        assertThat(exception.constraintViolations).containsExactly(
                DefaultConstraintViolation(
                        property = "id",
                        value = 5.toShort(),
                        constraint = NotBetween(start = 0.toShort(), end = 10.toShort())))
    }

    @Test
    fun `isNotBetween with within negative value should be invalid`() {
        val exception = assertThrows<ConstraintViolationException> {
            validate(Employee(id = 15.unaryMinus().toShort()), {
                validate(Employee::id).isNotBetween(start = 20.unaryMinus().toShort(), end = 10.unaryMinus().toShort())
            })
        }

        assertThat(exception.constraintViolations).containsExactly(
                DefaultConstraintViolation(
                        property = "id",
                        value = 15.unaryMinus().toShort(),
                        constraint = NotBetween(start = 20.unaryMinus().toShort(), end = 10.unaryMinus().toShort())))
    }

    @Test
    fun `hasDigits with null value should be valid`() {
        validate(Employee(), {
            validate(Employee::id).hasDigits(min = 1, max = 10)
        })
    }

    @Test
    fun `hasDigits with valid min value should be valid`() {
        validate(Employee(id = 9999.toShort()), {
            validate(Employee::id).hasDigits(min = 4)
        })
    }

    @Test
    fun `hasDigits with valid max value should be valid`() {
        validate(Employee(id = 9999.toShort()), {
            validate(Employee::id).hasDigits(max = 4)
        })
    }

    @Test
    fun `hasDigits with valid min and max value should be valid`() {
        validate(Employee(id = 9999.toShort()), {
            validate(Employee::id).hasDigits(min = 4, max = 4)
        })
    }

    @Test
    fun `hasDigits with negative valid min value should be valid`() {
        validate(Employee(id = 9999.unaryMinus().toShort()), {
            validate(Employee::id).hasDigits(min = 4)
        })
    }

    @Test
    fun `hasDigits with negative valid max value should be valid`() {
        validate(Employee(id = 9999.unaryMinus().toShort()), {
            validate(Employee::id).hasDigits(max = 4)
        })
    }

    @Test
    fun `hasDigits with negative valid min and max value should be valid`() {
        validate(Employee(id = 9999.unaryMinus().toShort()), {
            validate(Employee::id).hasDigits(min = 4, max = 4)
        })
    }

    @Test
    fun `hasDigits without min and max should be valid`() {
        validate(Employee(id = 9999.toShort()), {
            validate(Employee::id).hasDigits()
        })
    }

    @Test
    fun `hasDigits with less min value should be invalid`() {
        val exception = assertThrows<ConstraintViolationException> {
            validate(Employee(id = 7485.toShort()), {
                validate(Employee::id).hasDigits(min = 5)
            })
        }

        assertThat(exception.constraintViolations).containsExactly(
                DefaultConstraintViolation(
                        property = "id",
                        value = 7485.toShort(),
                        constraint = IntegerDigits(min = 5)))
    }

    @Test
    fun `hasDigits with greater max value should be invalid`() {
        val exception = assertThrows<ConstraintViolationException> {
            validate(Employee(id = 7485.toShort()), {
                validate(Employee::id).hasDigits(max = 3)
            })
        }

        assertThat(exception.constraintViolations).containsExactly(
                DefaultConstraintViolation(
                        property = "id",
                        value = 7485.toShort(),
                        constraint = IntegerDigits(max = 3)))
    }

    @Test
    fun `hasDigits with less value and greater value should be invalid`() {
        val exception = assertThrows<ConstraintViolationException> {
            validate(Employee(id = 7485.toShort()), {
                validate(Employee::id).hasDigits(min = 5, max = 3)
            })
        }

        assertThat(exception.constraintViolations).containsExactly(
                DefaultConstraintViolation(
                        property = "id",
                        value = 7485.toShort(),
                        constraint = IntegerDigits(min = 5, max = 3)))
    }

    @Test
    fun `hasDigits with negative less min value should be invalid`() {
        val exception = assertThrows<ConstraintViolationException> {
            validate(Employee(id = 7485.unaryMinus().toShort()), {
                validate(Employee::id).hasDigits(min = 5)
            })
        }

        assertThat(exception.constraintViolations).containsExactly(
                DefaultConstraintViolation(
                        property = "id",
                        value = 7485.unaryMinus().toShort(),
                        constraint = IntegerDigits(min = 5)))
    }

    @Test
    fun `hasDigits with negative greater max value should be invalid`() {
        val exception = assertThrows<ConstraintViolationException> {
            validate(Employee(id = 7485.unaryMinus().toShort()), {
                validate(Employee::id).hasDigits(max = 3)
            })
        }

        assertThat(exception.constraintViolations).containsExactly(
                DefaultConstraintViolation(
                        property = "id",
                        value = 7485.unaryMinus().toShort(),
                        constraint = IntegerDigits(max = 3)))
    }

    @Test
    fun `hasDigits with negative less value and negative greater value should be invalid`() {
        val exception = assertThrows<ConstraintViolationException> {
            validate(Employee(id = 7485.unaryMinus().toShort()), {
                validate(Employee::id).hasDigits(min = 5, max = 3)
            })
        }

        assertThat(exception.constraintViolations).containsExactly(
                DefaultConstraintViolation(
                        property = "id",
                        value = 7485.unaryMinus().toShort(),
                        constraint = IntegerDigits(min = 5, max = 3)))
    }
}