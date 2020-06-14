package com.flaringapp.sortvisualiztion.data.managers.sort

import com.flaringapp.sortvisualiztion.utils.onComputationThread
import com.flaringapp.sortvisualiztion.utils.swap
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableEmitter
import java.util.*

class SortManagerImpl : SortManager {

    override fun bubbleSortIncrease(
        numbers: IntArray,
        updateFrequency: Int,
        accurateLogging: Boolean
    ): Flowable<IntArray> {
        return createFlowable { emitter ->
            var iteration = 0L

            for (pass in 0 until (numbers.size - 1)) {
                for (index in 0 until (numbers.size - 1)) {
                    if (numbers[index] > numbers[index + 1]) {
                        numbers.swap(index, index + 1)
                    }

                    if (iteration % updateFrequency == 0L) emitter.onDataUpdated(
                        numbers,
                        accurateLogging
                    )
                    iteration++
                }
            }
            emitter.onNext(numbers)
            emitter.onComplete()
        }
    }

    override fun bubbleSortDecrease(
        numbers: IntArray,
        updateFrequency: Int,
        accurateLogging: Boolean
    ): Flowable<IntArray> {
        return createFlowable { emitter ->
            var iteration = 0L

            for (pass in 0 until (numbers.size - 1)) {
                for (index in 0 until (numbers.size - 1)) {
                    if (numbers[index] < numbers[index + 1]) {
                        numbers.swap(index, index + 1)
                    }

                    if (iteration % updateFrequency == 0L) emitter.onDataUpdated(
                        numbers,
                        accurateLogging
                    )
                    iteration++
                }
            }
            emitter.onNext(numbers)
            emitter.onComplete()
        }
    }

    override fun bubbleSortFlaggedIncrease(
        numbers: IntArray,
        updateFrequency: Int,
        accurateLogging: Boolean
    ): Flowable<IntArray> {
        return createFlowable { emitter ->
            var iteration = 0L
            var swapped: Boolean

            for (pass in 0 until (numbers.size - 1)) {
                swapped = false

                for (index in 0 until (numbers.size - 1)) {
                    if (numbers[index] > numbers[index + 1]) {
                        numbers.swap(index, index + 1)
                        swapped = true
                    }

                    if (iteration % updateFrequency == 0L) emitter.onDataUpdated(
                        numbers,
                        accurateLogging
                    )
                    iteration++
                }

                if (!swapped) break
            }
            emitter.onNext(numbers)
            emitter.onComplete()
        }
    }

    override fun bubbleSortFlaggedDecrease(
        numbers: IntArray,
        updateFrequency: Int,
        accurateLogging: Boolean
    ): Flowable<IntArray> {
        return createFlowable { emitter ->
            var iteration = 0L
            var swapped: Boolean

            for (pass in 0 until (numbers.size - 1)) {
                swapped = false

                for (index in 0 until (numbers.size - 1)) {
                    if (numbers[index] < numbers[index + 1]) {
                        numbers.swap(index, index + 1)
                        swapped = true
                    }

                    if (iteration % updateFrequency == 0L) emitter.onDataUpdated(
                        numbers,
                        accurateLogging
                    )
                    iteration++
                }

                if (!swapped) break
            }
            emitter.onNext(numbers)
            emitter.onComplete()
        }
    }

    override fun selectionSortIncrease(
        numbers: IntArray,
        updateFrequency: Int,
        accurateLogging: Boolean
    ): Flowable<IntArray> {
        return createFlowable { emitter ->
            var iteration = 0L

            for (i in numbers.size - 1 downTo 0) {
                var max = i
                for (j in 0 until i) {
                    iteration++
                    if (numbers[j] > numbers[max])
                        max = j
                }
                if (i != max) {
                    numbers.swap(i, max)
                }

                if (iteration % updateFrequency == 0L) emitter.onDataUpdated(
                    numbers,
                    accurateLogging
                )
            }

            emitter.onNext(numbers)
            emitter.onComplete()
        }
    }

    override fun selectionSortDecrease(
        numbers: IntArray,
        updateFrequency: Int,
        accurateLogging: Boolean
    ): Flowable<IntArray> {
        return createFlowable { emitter ->
            var iteration = 0L

            for (i in numbers.size - 1 downTo 0) {
                var min = i
                for (j in 0 until i) {
                    iteration++
                    if (numbers[j] < numbers[min])
                        min = j
                }
                if (i != min) {
                    numbers.swap(i, min)
                }

                if (iteration % updateFrequency == 0L) emitter.onDataUpdated(
                    numbers,
                    accurateLogging
                )
            }

            emitter.onNext(numbers)
            emitter.onComplete()
        }
    }

    override fun shellSortIncrease(data: IntArray): Flowable<IntArray> {
        return createFlowable { emitter ->
            val size = data.size
            var gap = size / 2

            while (gap >= 1) {
                for (i in gap until size) {
                    var j = i
                    while (j >= gap && data[j - gap] > data[j]) {
                        data.swap(j, j - gap)
                        j -= gap
                        emitter.onNext(data.clone())
                    }
                }
                gap /= 2
            }

            emitter.onComplete()
        }
    }

    override fun shellSortDecrease(data: IntArray): Flowable<IntArray> {
        return createFlowable { emitter ->
            val size = data.size
            var gap = size / 2

            while (gap >= 1) {
                for (i in gap until size) {
                    var j = i
                    while (j >= gap && data[j - gap] < data[j]) {
                        data.swap(j, j - gap)
                        j -= gap
                        emitter.onNext(data.clone())
                    }
                }
                gap /= 2
            }

            emitter.onComplete()
        }
    }

    override fun mergeSortDecrease(data: IntArray): Flowable<IntArray> {
        fun sort(list: List<Int>, emitter: FlowableEmitter<IntArray>): IntArray {

            fun merge(left: IntArray, right: IntArray): IntArray {
                var indexLeft = 0
                var indexRight = 0
                val newList: MutableList<Int> = mutableListOf()

                while (indexLeft < left.count() && indexRight < right.count()) {
                    if (left[indexLeft] >= right[indexRight]) {
                        newList.add(left[indexLeft])
                        indexLeft++
                    } else {
                        newList.add(right[indexRight])
                        indexRight++
                    }
                }

                while (indexLeft < left.size) {
                    newList.add(left[indexLeft])
                    indexLeft++
                }

                while (indexRight < right.size) {
                    newList.add(right[indexRight])
                    indexRight++
                }

                return newList.toIntArray()
            }

            if (list.size <= 1) {
                return list.toIntArray()
            }

            val middle = list.size / 2
            val left = list.subList(0, middle)
            val right = list.subList(middle, list.size)

            val sortedLeft = sort(left, emitter)
            val sortedRight = sort(right, emitter)

            val merged = merge(sortedLeft, sortedRight)

            return merged
        }

        return createFlowable { emitter ->
            val sortedList = sort(data.toList(), emitter)
            emitter.onNext(sortedList)
            emitter.onComplete()
        }
    }

    override fun mergeSortIncrease(data: IntArray): Flowable<IntArray> {
        fun sort(list: List<Int>, emitter: FlowableEmitter<IntArray>): IntArray {

            fun merge(left: IntArray, right: IntArray): IntArray {
                var indexLeft = 0
                var indexRight = 0
                val newList: MutableList<Int> = mutableListOf()

                while (indexLeft < left.count() && indexRight < right.count()) {
                    if (left[indexLeft] <= right[indexRight]) {
                        newList.add(left[indexLeft])
                        indexLeft++
                    } else {
                        newList.add(right[indexRight])
                        indexRight++
                    }
                }

                while (indexLeft < left.size) {
                    newList.add(left[indexLeft])
                    indexLeft++
                }

                while (indexRight < right.size) {
                    newList.add(right[indexRight])
                    indexRight++
                }

                return newList.toIntArray()
            }

            if (list.size <= 1) {
                return list.toIntArray()
            }

            val middle = list.size / 2
            val left = list.subList(0, middle)
            val right = list.subList(middle, list.size)

            val sortedLeft = sort(left, emitter)
            val sortedRight = sort(right, emitter)

            val merged = merge(sortedLeft, sortedRight)

            return merged
        }

        return createFlowable { emitter ->
            val sortedList = sort(data.toList(), emitter)
            emitter.onNext(sortedList)
            emitter.onComplete()
        }
    }

    override fun quickSortDecrease(data: IntArray): Flowable<IntArray> {
        fun sortPartition(
            data: List<Int>,
            startIndex: Int,
            endIndex: Int,
            emitter: FlowableEmitter<IntArray>
        ): Int {
            val pivot = data[endIndex]
            var i = startIndex
            var j = endIndex - 1
            do {
                while (data[i] <= pivot && i < j) {
                    i++
                }
                while (data[j] >= pivot && i < j) {
                    j--
                }
                when {
                    i < j -> Collections.swap(data, i, j)
                    data[i] > pivot -> Collections.swap(data, i, endIndex)
                    else -> i++
                }
            } while (i < j)
            return i
        }

        fun quickSortRecursion(
            data: List<Int>,
            startIndex: Int,
            endIndex: Int,
            emitter: FlowableEmitter<IntArray>
        ) {
            if (startIndex >= endIndex) {
                return
            }
            val partitionIndex = sortPartition(data, startIndex, endIndex, emitter)
            quickSortRecursion(data, startIndex, partitionIndex - 1, emitter)
            quickSortRecursion(data, partitionIndex + 1, endIndex, emitter)
        }

        fun quickSort(data: List<Int>, emitter: FlowableEmitter<IntArray>): List<Int> {
            quickSortRecursion(data, 0, data.size - 1, emitter)
            return data
        }

        return createFlowable { emitter ->
            val sortedList = quickSort(data.toList(), emitter)
            emitter.onNext(sortedList.reversed().toIntArray())
            emitter.onComplete()
        }
    }

    override fun quickSortIncrease(data: IntArray): Flowable<IntArray> {
        fun sortPartition(
            data: List<Int>,
            startIndex: Int,
            endIndex: Int,
            emitter: FlowableEmitter<IntArray>
        ): Int {
            val pivot = data[endIndex]
            var i = startIndex
            var j = endIndex - 1
            do {
                while (data[i] <= pivot && i < j) {
                    i++
                }
                while (data[j] >= pivot && i < j) {
                    j--
                }
                when {
                    i < j -> Collections.swap(data, i, j)
                    data[i] > pivot -> Collections.swap(data, i, endIndex)
                    else -> i++
                }
            } while (i < j)
            return i
        }

        fun quickSortRecursion(
            data: List<Int>,
            startIndex: Int,
            endIndex: Int,
            emitter: FlowableEmitter<IntArray>
        ) {
            if (startIndex >= endIndex) {
                return
            }
            val partitionIndex = sortPartition(data, startIndex, endIndex, emitter)
            quickSortRecursion(data, startIndex, partitionIndex - 1, emitter)
            quickSortRecursion(data, partitionIndex + 1, endIndex, emitter)
        }

        fun quickSort(data: List<Int>, emitter: FlowableEmitter<IntArray>): List<Int> {
            quickSortRecursion(data, 0, data.size - 1, emitter)
            return data
        }

        return createFlowable { emitter ->
            val sortedList = quickSort(data.toList(), emitter)
            emitter.onNext(sortedList.toIntArray())
            emitter.onComplete()
        }
    }

    override fun countSortDecrease(data: IntArray): Flowable<IntArray> {
        fun sort(
            data: MutableList<Int>,
            emitter: FlowableEmitter<IntArray>
        ): List<Int> {
            val min = data.min()!!
            val max = data.max()!!

            val countArray = IntArray(max - min + 1)

            data.forEach {
                ++countArray[it - min]
            }

            for (i in 1 until countArray.size) {
                countArray[i] += countArray[i - 1]
            }

            val output = ArrayList(data)

            for (i in countArray.indices.reversed()) {
                var previousIndex = 0
                if (i != 0)
                    previousIndex = countArray[i - 1]
                while (countArray[i] != previousIndex) {
                    val index = --countArray[i]
                    output[index] = i
                }
            }

            return output.toList().reversed()
        }

        return createFlowable { emitter ->
            if (data.isEmpty()) {
            } else {
                val mutableData = data.map { it }.toMutableList()
                val sorted = sort(mutableData, emitter)
                emitter.onNext(sorted.toIntArray())
            }
            emitter.onComplete()
        }
    }

    override fun countSortIncrease(data: IntArray): Flowable<IntArray> {
        fun sort(
            data: MutableList<Int>,
            emitter: FlowableEmitter<IntArray>
        ): List<Int> {
            val min = data.min()!!
            val max = data.max()!!

            val countArray = IntArray(max - min + 1)

            data.forEach {
                ++countArray[it - min]
            }

            for (i in 1 until countArray.size) {
                countArray[i] += countArray[i - 1]
            }

            val output = ArrayList(data)

            for (i in countArray.indices.reversed()) {
                var previousIndex = 0
                if (i != 0)
                    previousIndex = countArray[i - 1]
                while (countArray[i] != previousIndex) {
                    val index = --countArray[i]
                    output[index] = i
                }
            }

            return output.toList()
        }

        return createFlowable { emitter ->
            if (data.isEmpty()) {
            } else {
                val mutableData = data.map { it }.toMutableList()
                val sorted = sort(mutableData, emitter)
                emitter.onNext(sorted.toIntArray())
            }
            emitter.onComplete()
        }
    }

    private fun <T> createFlowable(action: (FlowableEmitter<T>) -> Unit): Flowable<T> {
        return Flowable.create<T>({ action(it) }, BackpressureStrategy.LATEST)
            .onComputationThread()
    }

    private fun FlowableEmitter<IntArray>.onDataUpdated(
        numbers: IntArray,
        accurateLogging: Boolean
    ) {
        if (accurateLogging) onNext(numbers.clone())
        else onNext(numbers)
    }
}
