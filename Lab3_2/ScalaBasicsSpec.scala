import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import java.lang.management.ManagementFactory


class ScalaBasicsSpec extends AnyFlatSpec with Matchers {

  "Reference types" should "be passed by reference" in {
    class TestClass(var value: Int)

    def modifyObject(obj: TestClass): Unit = {
      obj.value = 10
    }

    val original = new TestClass(5)
    modifyObject(original)

    original.value should be(10)
  }

  "Value types" should "be passed by value" in {
    def modifyValue(value: Int): Int = {
      value + 5
    }

    val original = 5
    val modified = modifyValue(original)

    modified should be(10)

  }
  "Original values" should "be correctly checked" in {
    def modifyValue(value: Int): Int = {
      value + 5}

    val original = 5
    val modified = modifyValue(original)
    assert(modified == 10 && original == 5)

  }


  "MaxHeap" should "change_memory" in {
      val heap = new MaxHeap()
      val heap_before = heap.getSize()
      // Додаємо елементи до купи
      heap.insert(10)
      heap.insert(5)
      heap.insert(15)
      heap.insert(7)
      heap.insert(20)

      // Максимальний елемент з купи
      val heap_after = heap.getSize()
      // Перевіряємо функціональність

      heap_after should be > heap_before
    }

  "Stack" should "change_memory" in {
    val heap = new MaxHeap()

    // Показуємо, що heap пустий
    val heap_before = heap.getSize()
    // Додаємо елементи до heap
    heap.insert(10)
    heap.insert(5)
    heap.insert(12)
    heap.insert(7)
    heap.insert(21)

    val heap_after = heap.getSize()

    heap_after should be > heap_before
  }


  "Garbage collector" should "collect unreferenced objects" in {
      class TestClass(var value: Int)

      def createObject(): TestClass = {
        new TestClass(10)
      }

      var obj: TestClass = createObject()
      val heapSizeBeforeGC = getSize()
      obj = null

      System.gc()  // Request garbage collection
      Thread.sleep(1000)  // Give the GC some time to run

      val heapSizeAfterGC = getSize()

      heapSizeAfterGC should be < heapSizeBeforeGC
    }

    "Stack" should "demonstrate stack allocation" in {
      def createObjectOnStack(): Int = {
        val localVariable = 10
        localVariable
      }

      val heapSizeBefore = getSize()
      val stackAllocatedValue = createObjectOnStack()
      val heapSizeAfter = getSize()


      assert(stackAllocatedValue == 10 && heapSizeAfter == heapSizeBefore)
    }

    def getSize(): Long = {
      val memoryMXBean = ManagementFactory.getMemoryMXBean
      val heapMemoryUsage = memoryMXBean.getHeapMemoryUsage
      heapMemoryUsage.getUsed
    }
}
