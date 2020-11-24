package CriticalPath
import java.util
import java.util.ArrayList

import scala.collection.mutable.{HashMap, HashSet, Set}
import scala.io.Source

class ScalaCP {
  def main(Preq: Array[String], NPreq: Array[String]): Unit ={


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

    var arrayP :Array[String]= Array.fill[String](Preq.length)("0")
    var arrayPL :Array[String]= Array.fill[String](arrayP.length)("0")


    val Project = new DAG[Int](0)
    println("Start of tree")
    var i = 0
    for (i <- 0 until NPreq.length) {
      var nprq = NPreq(i).toInt
      println(NPreq(i))
      Project.extend(Set(0), nprq)
    }
    for (i <- 0 until Preq.length) {
      arrayP= Preq(i).split(",")
      arrayPL= arrayP(i).split("->")
      println(Preq(i))

      for(j <- 0 until arrayPL.length){
        arrayPL(j)=arrayPL(j).replace("+",",")
        println(arrayPL(j))
        println("arrayPL(j)")
      }

      //arrayPL(i)=arrayPL(i).replace("+",",")
      //println(arrayPL(i))
    }

    var Parents = ""
    var Child = 0

    for (i <- 0 until arrayPL.length) {
      //val pre: util.ArrayList[String] = new util.ArrayList[String]
      //val save: util.ArrayList[String] = new util.ArrayList[String]
      //pre.add(Preq(i))//whole expression
      if (i % 2 == 0) {
        //Parents = arrayPL(i)
        //var Prnts = Array(Parents)

        arrayPL(i).split(",")
        for (j <- 0 until arrayPL.length) {

        }
      }
      else {
        var Child = arrayPL(i).toInt
      }
      var seq:Seq[Int] = Seq(arrayPL(i).toInt)
      println(seq)

      Project.extend(Set(seq:_*),Child)
    }

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


