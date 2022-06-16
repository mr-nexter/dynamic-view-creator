# dynamic-view-creator
Dynamic View Creator library allows to create Android views based on the provided POJO object.

Example of usage:<br>
<code>
  // POJO class<br>
  class User(val firstName: String, val lastName: String)<br>

  // in MainActivity<br>
  val user = User("John", "Smith")<br>
  
  val creator = ViewCreator(this)<br>
  val view = creator.create(user)
</code>
