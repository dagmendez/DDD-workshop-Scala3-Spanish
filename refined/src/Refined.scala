package refined

object Refined extends API:

  // These two classes are here so the tests compile.
  // Please, change the implementation.

    class NIE(input: String) extends ID:
      def toUpperCaseWithDash: String = input.toUpperCase()

    class NIF(input: String) extends ID:
      def toUpperCaseWithDash: String = input.toUpperCase()

    override def validateID(input: String): ID | String = ???

end Refined
