package functest
import jess.Rete
import jess.Userfunction
import jess.Value
import jess.ValueVector
import jess.Context
import jess.RU
import scala.collection.JavaConversions._


class MyFunc extends Userfunction {
  def getName : String = {
    return "succ"
  }
  
  def call(vv : ValueVector, context : Context) : Value = {
   val n: Int = vv.get(1).intValue(context) + 1
   val value = new Value(n, RU.INTEGER)
   return value
  }
}

object MyApp extends App {
  val engine = new Rete
  val succ = new MyFunc
  
  engine.addUserfunction(succ)
  
  for (i <- 1 to 10) {
    println(s"asserting fact $i")
    engine.assertString(s"(man age (succ $i))") 
  }
  for (fact <- engine.listFacts()) {
    println(fact.toString())
  }
 
}