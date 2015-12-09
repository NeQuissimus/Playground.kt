import java.util.ArrayList

fun main(args: Array<String>) {
  println("Hello World")

  val iter = arrayListOf(1, 2, 3, 4, 5, 6)

  println(productIter(iter))
  println(productFun(iter))
  println(productRec(iter))

  println(containsIter(5, iter))
  println(containsFun(5, iter))
  println(containsRec(5, iter))
  println(containsIter(8, iter))
  println(containsFun(8, iter))
  println(containsRec(8, iter))

  val mapping = hashMapOf(
    2 to arrayListOf('A', 'B', 'C'),
    3 to arrayListOf('D', 'E', 'F')
  )

  println(phoneWordsIter(mapping, intArrayOf(2, 2, 3)))
  println(phoneWordsRec(mapping, intArrayOf(2, 2, 3), arrayListOf("")))
  println(phoneWordsFun(mapping, intArrayOf(2, 2, 3)))

  println(palindrome(1))
  println(palindrome(123))
  println(palindrome(121))
  println(palindrome(133331))

  println(duplicates(arrayListOf(1, 1, 2, 3, 1, 4, 2, 5, 6, 6)))
}

fun productIter(xs: Iterable<Int>): Int {
  var i: Int = 1

  for (s in xs) {
    i *= s
  }

  return i
}

fun productFun(xs: Iterable<Int>): Int {
  return xs.fold(1, { acc: Int, x: Int -> acc * x })
}

fun productRec(xs: Iterable<Int>): Int {
  if (xs.none()) {
    return 1
  } else {
    return xs.first() * productRec(xs.drop(1))
  }
}

fun <T> containsIter(elem: T, xs: Iterable<T>): Boolean {
  for (x in xs) {
    if (elem == x) {
      return true
    }
  }

  return false
}

fun <T> containsFun(elem: T, xs: Iterable<T>): Boolean {
  return xs.contains(elem)
}

fun <T> containsRec(elem: T, xs: Iterable<T>): Boolean {
  when (xs.firstOrNull()) {
    null -> return false
    elem -> return true
    else -> return containsRec(elem, xs.drop(1))
  }
}

fun phoneWordsIter(mapping: Map<Int, List<Char>>, number: IntArray): List<String> {
  var l: ArrayList<String> = ArrayList()

  for (n in number) {
    val cs: List<Char> = mapping[n].orEmpty()
    if (l.none()) {
      for (c in cs) {
        l.add(c.toString())
      }
    } else {
      val tmp: ArrayList<String> = ArrayList()
      for (s in l) {
        for (c in cs) {
          tmp.add(s + c)
        }
      }

      l = tmp
    }
  }

  return l
}

fun phoneWordsRec(mapping: Map<Int, List<Char>>, number: IntArray, words: List<String>): List<String> {
  if (number.size > 0) {
    val tmp: ArrayList<String> = ArrayList()

    for (w in words) {
      val cs = mapping[number[0]].orEmpty()
      for (c in cs) {
        tmp.add(w + c)
      }
    }

    return phoneWordsRec(mapping, number.drop(1).toIntArray(), tmp)
  } else {
    return words
  }
}

fun phoneWordsFun(mapping: Map<Int, List<Char>>, number: IntArray): List<String> {
  val chars: List<List<Char>> = number.map({i -> mapping[i].orEmpty()})

  return chars.fold(arrayListOf(""),
    {acc: List<String>, e: List<Char> ->
      acc.flatMap({s: String ->
        e.map({c: Char -> s + c})})})
}

fun palindrome(n: Int): Boolean {
  fun reverse(n: Int): Int {
    var i = 0
    var x = n

    while (x > 0) {
      i = i * 10 + x % 10
      x /= 10
    }

    return i
  }

  return n == reverse(n)
}

fun <T> duplicates(elems: Iterable<T>): List<T> = elems.groupBy({ it }).filter({ it.value.size() > 1 }).flatMap({ it.value.drop(1) })

//.filter { it.size() > 1 }.map({ it.first })
