# dynamic-view-creator
Dynamic View Creator library allows to create Android views based on the provided POJO object.

Example of usage:
```
  // POJO class
  class User(val firstName: String, val lastName: String)

  // in MainActivity
  val user = User("John", "Smith")
  
  val creator = ViewCreator(this)
  val view = creator.create(user)
```
