package day02

class Day02 {

    fun part1(input: List<Pair<Long, Long>>): Long {
        fun String.base(): Long {
            if (length % 2 == 1) return "1${"0".repeat(length)}".base()
            val left = take(length / 2).toLong()
            val right = drop(length / 2).toLong()
            return if (left < right) left + 1 else left
        }

        fun Long.base(): Long = toString().base()

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
            if (lastNum > end) {
                result -= lastNum
            }
        }
        return result
    }

    fun String.base(n: Int): Pair<Long, Int> {
        val reminder = length % n
        if (reminder != 0) return "1${"0".repeat(length + n - reminder - 1)}".base(n)
        require(length % n == 0) { "Invalid length: $length in String: $this" }
        val list = (0..<length step n).map { substring(it, it + n).toLong() }
        return (if (list.first() == list.max()) list.first() else list.first() + 1) to list.size
    }

    fun Long.base(n: Int): Pair<Long, Int> = toString().base(n)

    fun part2(input: List<Pair<Long, Long>>): Long {
        val used = HashSet<Long>()
        var result = 0L
        for ((start, end) in input) {
            for (i in 1..<start.toString().length) {
                val startStr = start.toString()
                val endStr = end.toString()
                val startTimes = startStr.length / i + (if (startStr.length % i == 0) 0 else 1)
                val endTimes = endStr.length / i + (if (endStr.length % i == 0) 0 else 1)
                for (times in startTimes..endTimes) {
                    var base = "1${"0".repeat(i - 1)}".toLong()
                    do {
                        val current = base.toString().repeat(times).toLong()
                        base++
                        if (current < start) continue
                        if (current > end) break
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
