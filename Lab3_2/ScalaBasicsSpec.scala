import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ScalaBasicsSpec extends AnyFlatSpec with Matchers {

  "Reference types" should "be passed by reference" in {
    class TestClass(var value: Int)

    def modifyObject(obj: TestClass): Unit = {
      obj.value = 10
    }

    val original = new TestClass(5)
    modifyObject(original)

    original.value should be (10)
  }

  "Value types" should "be passed by value" in {
    def modifyValue(value: Int): Int = {
      value + 5
    }

    val original = 5
    val modified = modifyValue(original)

    original should be (5)
    modified should be (10)
  }

  "Object creation" should "demonstrate heap allocation" in {
    class TestClass(var value: Int)

    def createObjectOnHeap(): TestClass = {
      new TestClass(10)
    }

    val obj = createObjectOnHeap()

    obj.value should be (10)
  }

  "Garbage collector" should "collect unreferenced objects" in {
    class TestClass(var value: Int)

    def createObject(): TestClass = {
      new TestClass(10)
    }

    var obj: TestClass = createObject()
    obj = null
    obj should be (null)
  }
}
