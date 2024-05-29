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
      value += 5 // присвоюємо нове значення
      return value
    }

    val original = 5
    val modified = modifyValue(original)

    assert(modified == 10 && original == 5)
  }

  "Heap allocation" should "increase heap size" in {
    class TestClass(var value: Int)

    def createObjectsOnHeap(): Array[TestClass] = {
      Array.fill(100000)(new TestClass(10))
    }

    val heapSizeBefore = getHeapSize()
    val obj = createObjectsOnHeap()
    System.gc() 
    Thread.sleep(1000) 
    val heapSizeAfter = getHeapSize()

    heapSizeAfter should be > heapSizeBefore
  }

  "Stack allocation" should "not increase heap size" in {
    def createObjectOnStack(): Int = {
      val localVariable = 10
      localVariable
    }

    val heapSizeBefore = getHeapSize()
    val stackAllocatedValue = createObjectOnStack()
    val heapSizeAfter = getHeapSize()

    assert(stackAllocatedValue == 10 && heapSizeAfter == heapSizeBefore)
  }

  "Garbage collector" should "collect unreferenced objects" in {
    class TestClass(var value: Int)

    def createObject(): TestClass = {
      new TestClass(10)
    }

    var obj: TestClass = createObject()
    val heapSizeBeforeGC = getHeapSize()
    obj = null

    System.gc() 
    Thread.sleep(1000)  
    val heapSizeAfterGC = getHeapSize()

    heapSizeAfterGC should be < heapSizeBeforeGC
  }

  def getHeapSize(): Long = {
    val memoryMXBean = ManagementFactory.getMemoryMXBean
    val heapMemoryUsage = memoryMXBean.getHeapMemoryUsage
    heapMemoryUsage.getUsed
  }
}
