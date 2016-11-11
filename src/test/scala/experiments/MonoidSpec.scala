package experiments

import org.scalatest.{FlatSpec, Matchers}

import scalaz._
import Scalaz._
import scalaz.Tags.Multiplication
import scalaz.syntax.Syntaxes

/**
  * Created by johnmcgill on 10/24/16.
  */
case class PriceTag(num: Int, txt: String)

object PriceTagMonoid extends Monoid[PriceTag] {
  override def append(a: PriceTag, b: => PriceTag): PriceTag =
    PriceTag(a.num + b.num, a.txt + b.txt)

  override def zero: PriceTag = PriceTag(0, "")
}

object MultiplicationPriceTagMonoid extends Monoid[PriceTag @@ Multiplication] {
  override def append(a: PriceTag @@ Multiplication, b: => PriceTag @@ Multiplication) =
    Multiplication(
      PriceTag(
        Tag.unwrap(a).num * Tag.unwrap(b).num,
        (Tag.unwrap(a).txt + Tag.unwrap(b).txt).sorted)
    )

  override def zero: PriceTag @@ Multiplication = Multiplication(PriceTag(1, ""))
}

class MonoidSpec extends FlatSpec with Matchers with Syntaxes {

  import monoid._

  implicit val bsMonoid = PriceTagMonoid
  implicit val bsMultMonoid = MultiplicationPriceTagMonoid

  "a" should "b" in {

    val result = PriceTag(7, "hey") |+| PriceTag(20, "yoyo")

    println(result)
  }

  "mult price tag monoid" should "append differently" in {
    val result = Tags.Multiplication(PriceTag(7, "hey")) |+| Tags.Multiplication(PriceTag(20, "yoyo"))

    println(result)
  }

  "How about" should "tags?" in {
    val r = Tags.Multiplication(10) |+| Monoid[Int @@ Tags.Multiplication].zero
    println(r)
  }
}
