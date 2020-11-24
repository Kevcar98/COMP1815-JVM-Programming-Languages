package CriticalPath

import java.util
import java.util.ArrayList

import scala.collection.mutable.{HashMap, HashSet, Set}
import scala.io.Source

class ScalaCP {
  def main(Preq: Array[String], NPreq: Array[String]): Unit = {


    case class DAG[T](root: T) extends HashMap[T, Set[T]] {

      def extend(Parent: Set[T], Child: T) = {

        require(Parent.subsetOf(this.keySet), s"$Parent")
        require(!this.keySet.contains(Child), s" $Child")

        Parent.foreach(key => this (key) += Child)
        this (Child) = new HashSet[T]
        this
      }

      def TheLargestBranch(node: T): (Int, List[T]) = {
        require(keySet.contains(node), s" $node")

        if (this (node).isEmpty) (1, List(node))
        else {
          var M = 0;
          var L: List[T] = Nil;
          this (node).foreach(suc => {
            val (lm, ll) = TheLargestBranch(suc)
            if (lm > M) {
              M = lm;
              L = ll
            }
          }
          )
          (1 + M, node :: L)
        }

      }

      def PrintTree(origin: T, Next: Int = 0): Unit = {
        if (contains(origin)) {
          for (i <- 0 until Next - 1) print("   ")
          if (Next > 0) print("|__")
          println(origin)
          this (origin).foreach(child => PrintTree(child, Next + 1))
        }

      }

      def join(dag: DAG[T]) = this

      this (root) = Set()


      //Project.extend(Set(0),1) //0
      //Project.extend(Set(0),3) //0
      //Project.extend(Set(1),2) //2
      //Project.extend(Set(3,2,0),4) //4
      //Project.extend(Set(4),5) //6

    }

    // start of tree code
    var arrayP: Array[String] = Array.fill[String](Preq.length)("")
    var arrayPL: Array[String] = Array.fill[String](arrayP.length)("")

    val Project = new DAG[Int](0)
    println("Start of tree")
    for (i <- 0 until Preq.size) {
      println(Preq(i))
    }
    for (i <- 0 until NPreq.size) {
      println(NPreq(i))
    }

    for (i <- 0 until NPreq.length) {
      var nprq = NPreq(i).toInt
      Project.extend(Set(0), nprq)
    }
    for (i <- 0 until Preq.length) { // Preq = [123->33,1+2->5] // Preq = [123->33,1+2->5,0+1+2+3+4->6] // Preq = [1->2,3+2->4,4->5]
      arrayP = Preq(i).split("->") // when i = 0, [123,33], when i = 1, [1+2,5] // when i = 2, [0+1+2+3+4,6] // [1,2] [3+2,4] [4,5]
      arrayPL = arrayP(0).split("\\+") // when i = 0, [123], when i = 1, [1,2] // when i = 2, [0,1,2,3,4] // [1] [3,2] [4]

      /*for (j <- 0 until arrayPL.length) {
        arrayPL(j) = arrayPL(j).replace("+", ",")
        println(arrayPL(j))
        println("arrayPL(j)")
      }*/
      var Child = arrayP(1).toInt
      /*if (arrayPL(i).size > 1) { // if more than one prerequisite task
        //
      } else {
        //
      }*/
      var seq: Seq[Int] = Seq(arrayPL.size) // 0, 1, 2
      println(seq)

      Project.extend(Set(seq: _*), Child)
    }

    // var Parents = ""
    // var Child = 0

    /*for (i <- 0 until arrayPL.length) {
      //val pre: util.ArrayList[String] = new util.ArrayList[String]
      //val save: util.ArrayList[String] = new util.ArrayList[String]
      //pre.add(Preq(i))//whole expression
      if (arrayPL(i).size > 1) { // if more than one prerequisite task
        //Parents = arrayPL(i)
        //var Prnts = Array(Parents)

        /*arrayPL(i).split(",")
        for (j <- 0 until arrayPL.length) {

        }*/
      }
      else {
        Child = arrayPL(i).toInt
      }
      var seq: Seq[Int] = Seq(arrayPL(i).toInt)
      println(seq)

      Project.extend(Set(seq: _*), Child)
    }*/

    // child

    Project.TheLargestBranch(0)
    Project.PrintTree(0)








    //tree.extend(Set(CurrentNODE),NewNode)
    //this links the CurrentNODE which can be multiple   to a NewNode


    //var y = Array("1", "2", "3", "4")
    //var z = Array("1+2->4", "3->5", "4+5->6")
    //MakingTree(z, y)

  }

}