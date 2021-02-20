package com.example.rpgapp

data class Block(val id: Int, val msg: String, val left: Int?,
                 val up: Int?, val right: Int?, val down: Int?) {

    fun printValues() {
        println("Id: " + this.id)
        println("Message: " + this.msg)
        println("Up: " + this.up)
        println("Right: " + this.right)
        println("Down: " + this.down)
        println("Left: " + this.left)
    }
}