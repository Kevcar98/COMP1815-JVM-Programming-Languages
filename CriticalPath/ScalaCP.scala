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


      def MakingTree(): Unit = {
        val Project = new DAG[Int](0)
        var i = 0
        for (i <- 0 until NPreq.length) {
          var nprq = NPreq(i).toInt
          Project.extend(Set(0), nprq)
        }
        for (i <- 0 until Preq.length) {
          Preq(i).split("->")
          if (Preq(i).contains("+")) {
            Preq(i).replace("+",",")
          }
        }

        var Parents = ""
        var Child = 0

        for (i <- 0 until Preq.length) {
          //val pre: util.ArrayList[String] = new util.ArrayList[String]
          //val save: util.ArrayList[String] = new util.ArrayList[String]
          //pre.add(Preq(i))//whole expression

          if (i % 2 == 0) {
            Parents = Preq(i)
            var Prnts = Array(Parents)
            Prnts(0).split(",")
            for (j <- 0 until Prnts.length) {


            }
          }
          else {
            var Child = Preq(i).toInt
          }
          Project.extend(Set(Parents),Child)
        }




          // child


          if (Preq(i).contains("->")) {


          }





          //var prq=Preq(i).toInt
          //println(prq)
          //Project.extend(Set(0),prq)
        }




        //Project.extend(Set(0),1) //0
        //Project.extend(Set(0),3) //0
        //Project.extend(Set(1),2) //2
        //Project.extend(Set(3,2,0),4) //4
        //Project.extend(Set(4),5) //6
        Project.TheLargestBranch(0)
        Project.PrintTree(0)
      }


      val ProjectsF = "Projects.txt"
      val TasksF = "Tasks.txt"

      val tree = new DAG[Int](0)


      tree.extend(Set(0), 1) //0
      tree.extend(Set(0), 3) //0
      tree.extend(Set(1), 2) //2
      tree.extend(Set(3, 2, 0), 4) //4
      tree.extend(Set(4), 5) //6
      tree.TheLargestBranch(0)
      tree.PrintTree(0)


      //tree.extend(Set(CurrentNODE),NewNode)
      //this links the CurrentNODE which can be multiple   to a NewNode


      var y = Array("1", "2", "3", "4")
      var z = Array("1+2->4", "3->5", "4+5->6")
      MakingTree(z, y)

    }

  }

}
