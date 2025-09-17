package ar.edu.unlam.mobile.scaffolding.data.repositories

interface UserRepository :
    UserCreator,
    UserUpdater,
    UserFetcher

interface UserCreator {
    fun createUser(name: String)
}

interface UserUpdater {
    fun updateUser(
        id: String,
        name: String,
    )

    fun deleteUser(id: String)
}

interface UserFetcher {
    fun fetchUsers(): List<String>

    fun fetchUser(id: String): String
}

class Pepe : UserCreator {
    override fun createUser(name: String) {
        TODO("Not yet implemented")
    }
}
