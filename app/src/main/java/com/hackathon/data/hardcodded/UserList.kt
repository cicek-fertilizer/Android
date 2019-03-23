package com.hackathon.data.hardcodded

enum class UserList(val id: Int, val username: String, val password: String) {
    TEST(1, "test", "test"),
    TEST2(2, "test2", "test")
}

fun getUser(username: String?): UserList? =
        UserList.values().find {
            it.username == username
        }