package doubles

import com.example.delivery.enums.UserRole
import domain.entities.User
import infra.interfaces.IUserRepository

class UserRepositoryDouble : IUserRepository {

    var lastIdCalled: String = ""
    var userStored: User = User("","","","", UserRole.PERSON, "")
    var isUserExist = false
    var lastUserIdDeleted: String = ""


    override fun store(user: User) {
        this.userStored = user
    }

    override fun getUserByMail(mail: String) =
        if (userStored.mail == mail)
            userStored
        else
            null

    fun withUser(user: User): UserRepositoryDouble {
        userStored = user
        return this
    }
    override fun deleteUser(userId: String) {
       lastUserIdDeleted = userId
    }
    override fun userExists(userId: String)  = isUserExist

    override fun getStoredUser(): User{
        return userStored
    }

    override fun update(user: User) {
        userStored = user
    }

    override fun getUser(userId: String): User {
        lastIdCalled = userId
        return userStored
    }

    fun withUserExist(isUserExist: Boolean): UserRepositoryDouble {
        this.isUserExist = isUserExist
        return this
    }
}



