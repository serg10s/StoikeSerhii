fun bubbleSort(arr: IntArray) {
    val n = arr.size
    for (i in 0 until n-1) {
        for (j in 0 until n-i-1) {
            if (arr[j] > arr[j + 1]) {
                val temp = arr[j]
                arr[j] = arr[j + 1]
                arr[j + 1] = temp
            }
        }
    }
}

fun quickSort(arr: IntArray, low: Int, high: Int) {
    if (low < high) {
        val pi = partition(arr, low, high)
        quickSort(arr, low, pi-1)
        quickSort(arr, pi+1, high)
    }
}

fun partition(arr: IntArray, low: Int, high: Int): Int {
    val pivot = arr[high]
    var i = low - 1
    for (j in low until high) {
        if (arr[j] < pivot) {
            i++
            val temp = arr[i]
            arr[i] = arr[j]
            arr[j] = temp
        }
    }
    val temp = arr[i + 1]
    arr[i + 1] = arr[high]
    arr[high] = temp
    return i + 1
}

fun main() {
    val numbers = IntArray(1000) { it + 1 }
    numbers.shuffle()

    val bubbleSortedArray = numbers.copyOf()
    val quickSortedArray = numbers.copyOf()

    val bubbleSortTime = measureTimeMillis {
        bubbleSort(bubbleSortedArray)
    }
    val quickSortTime = measureTimeMillis {
        quickSort(quickSortedArray, 0, quickSortedArray.size - 1)
    }

    println("Bubble Sort took: $bubbleSortTime ms")
    println("Quick Sort took: $quickSortTime ms")
}
