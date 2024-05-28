class MaxHeap {
  private var heapArray: Array[Int] = Array.ofDim(10) // Початковий розмір купи
  private var size: Int = 0 // Поточний розмір купи

  // Функція для обміну елементів у купі
  private def swap(i: Int, j: Int): Unit = {
    val temp = heapArray(i)
    heapArray(i) = heapArray(j)
    heapArray(j) = temp
  }

  // Функція для додавання елемента до купи
  def insert(value: Int): Unit = {
    if (size >= heapArray.length) {
      // Потрібно розширити масив, якщо він заповнений
      val newArray = Array.ofDim[Int](heapArray.length * 2)
      Array.copy(heapArray, 0, newArray, 0, heapArray.length)
      heapArray = newArray
    }
    heapArray(size) = value
    var current = size
    size += 1

    // Відновлення властивості heap вгору
    while (current > 0 && heapArray(current) > heapArray((current - 1) / 2)) {
      swap(current, (current - 1) / 2)
      current = (current - 1) / 2
    }
  }

  // Функція для видалення максимального елемента з купи
  def extractMax(): Option[Int] = {
    if (size == 0) {
      None // Купа порожня
    } else {
      val max = heapArray(0)
      heapArray(0) = heapArray(size - 1)
      size -= 1

      // Відновлення властивості heap вниз
      var current = 0
      while ((2 * current + 1) < size) {
        var child = 2 * current + 1 // Лівий нащадок
        if (child + 1 < size && heapArray(child) < heapArray(child + 1)) {
          child += 1 // Правий нащадок більший
        }
        if (heapArray(current) < heapArray(child)) {
          swap(current, child)
          current = child
        } else {
          return Some(max)
        }
      }
      Some(max)
    }
  }

  // Функція для отримання поточного розміру купи
  def getSize(): Int = size
}


//object Main {
//  def main(args: Array[String]): Unit = {
//    val heap = new MaxHeap()
//    val heap_size = heap.getSize() // 0
//    // 1 Int = 32 b
//    println(heap_size*32L)
////     Додаємо елементи до купи
//    heap.insert(10)
//    heap.insert(5)
//    heap.insert(15)
//    heap.insert(7)
//    heap.insert(20)
//    println(heap)
//    // Максимальний елемент з купи
//    val maxElement = heap.extractMax() // 20
//    println("Максимальний елемент: " + maxElement.getOrElse("Heap порожня"))
//
//    // Виводимо стан купи (змінну в пам'яті)
//    val heap_size_after = heap.getSize()
//    println("Heap_after_insert в пам'яті: " + heap_size_after*32L)    // 120
//  }
//}