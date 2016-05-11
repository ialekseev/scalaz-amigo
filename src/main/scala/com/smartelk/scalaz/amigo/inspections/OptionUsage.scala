package com.smartelk.scalaz.amigo.inspections

import com.smartelk.scalaz.amigo.{Inspection, Problem}
import scala.meta._

case object OptionUsage extends Inspection {
  def inspect(mtree: scala.meta.Tree): Seq[Problem] = mtree.collect {
    case t@ q"${name: Term.Name}" if name.toString == "Some" => {
      Problem(t,
        "'Some' usage",
        """Using Scala's standard 'Some'""",
        """Use Scalaz's 'some'. See: 'http://eed3si9n.com/scalaz-cheat-sheet'""",
        """
          |import scalaz._
          |import Scalaz._
          |val a: Option[Int] = 1.some
        """.stripMargin)
    }
    case t@ q"${name: Term.Name}" if name.toString == "None" => {
      Problem(t,
        "'None' usage",
        """Using Scala's standard 'None'""",
        """Use Scalaz's 'none'. See: 'http://eed3si9n.com/scalaz-cheat-sheet'""",
        """
          |import scalaz._
          |import Scalaz._
          |val a: Option[Int] = none[Int]
        """.stripMargin)
    }
  }
}
