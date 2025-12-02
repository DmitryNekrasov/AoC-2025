package day02

class Day02 {

    fun part1(input: List<Pair<String, String>>): Long {
        fun String.base(): Long {
            if (length % 2 == 1) return "1${"0".repeat(length)}".base()
            val left = take(length / 2).toLong()
            val right = drop(length / 2).toLong()
            return if (left < right) left + 1 else left
        }

        fun Long.multiplier(): Long {
            var x = this
            var result = 1L
            while (x > 0) {
                x /= 10
                result *= 10L
            }
            return result
        }

        fun sum(from: Long, to: Long): Long = (from + to) * (to - from + 1) / 2

        var result = 0L
        for ((start, end) in input) {
            val baseStart = start.base()
            val baseEnd = end.base()
            val baseMiddle = baseEnd - 1

            val multiplierStart = baseStart.multiplier()
            val multiplierEnd = baseEnd.multiplier()

            val firstPart = sum(baseStart, baseMiddle)
            result += firstPart * multiplierStart + firstPart

            val secondPart = sum(baseMiddle + 1, baseEnd)
            result += secondPart * multiplierEnd + secondPart

            val lastNum = baseEnd * multiplierEnd + baseEnd
            if (lastNum > end.toLong()) {
                result -= lastNum
            }
        }
        return result
    }

    fun part2(input: List<Pair<String, String>>): Long {
        var result = 0L
        for ((start, end) in input) {
            val used = HashSet<Long>()
            for (partLen in 1..(end.length / 2)) {
                val startTimes = start.length / partLen + if (start.length % partLen == 0) 0 else 1
                val endTimes = end.length / partLen + if (end.length % partLen == 0) 0 else 1
                for (times in maxOf(startTimes, 2)..endTimes) {
                    var base = "1${"0".repeat(partLen - 1)}".toLong()
                    do {
                        val current = base.toString().repeat(times).toLong()
                        base++
                        if (current < start.toLong()) continue
                        if (current > end.toLong()) break
                        if (current in used) continue
                        used += current
                        result += current
                    } while (true)
                }
            }
        }
        return result
    }
}
