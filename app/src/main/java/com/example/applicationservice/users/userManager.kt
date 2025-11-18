package com.example.applicationservice.users

object userManager{
    private val userList = mutableListOf<User>()

    fun ajouterUtilisateur(user: User){
        userList.add(user)
    }

    fun seConnecter(user : String, pass : String): Boolean {
        return userList.any{ it.nomUtilisateur == user && it.motDePasse == pass }
    }
}