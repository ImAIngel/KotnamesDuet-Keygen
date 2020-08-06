package com.example.kotnamesduet

import kotlin.random.Random

class KeyGen {

    private val cols = 5
    private val rows = 5
    private val spies = 9
    private val ass = 3
    lateinit var cardA: ArrayList<Char>
    lateinit var cardB: ArrayList<Char>

    private fun createBoard(): ArrayList<Char> {
        val cards = ArrayList<Char>(cols*rows)
        for (i in 0 until cols * rows) {
            cards.add('W')
        }

        return cards
    }

    private fun fillBoard(board: ArrayList<Char>, ass: Int = this.ass, spies: Int = this.spies) {
        fillAssassins(board, ass)
        fillSpies(board, spies)
    }

    private fun fillAssassins(board: ArrayList<Char>, numb: Int) {
        for (i in 0 until numb) {
            var index: Int
            do {
                index = Random.nextInt(cols*rows)
            } while (board[index] != 'W')

            board[index] = 'A'
        }
    }

    private fun fillSpies(board: ArrayList<Char>, numb: Int) {
        for (i in 0 until numb) {
            var index: Int
            do {
                index = Random.nextInt(cols*rows)
            } while (board[index] != 'W')

            board[index] = 'G'
        }
    }

    private fun getAssassins(board: ArrayList<Char>): ArrayList<Int> {
        val result = ArrayList<Int>(3)
        for ((index, char) in board.withIndex()) {
            if (char == 'A') result.add(index)
        }

        return result
    }

    private fun getSpies(board: ArrayList<Char>): ArrayList<Int> {
        val result = ArrayList<Int>(9)
        for ((index, char) in board.withIndex()) {
            if (char == 'G') result.add(index)
        }

        return result
    }

    private fun couple(board: ArrayList<Char>): ArrayList<Char> {
        val c = createBoard()
        val aux = arrayListOf<Int>()

        val assassins = getAssassins(board)
        assassins.shuffle()
        c[assassins[0]] = 'A'
        c[assassins[1]] = 'G'
        c[assassins[2]] = 'X'
        aux.add(assassins[2])

        val spies = getSpies(board)
        spies.shuffle()
        for (i in 0 until spies.size) {
            when (i) {
                in 0 until 3 -> c[spies[i]] = 'G'
                3 -> c[spies[i]] = 'A'
                else -> { c[spies[i]] = 'X'
                    aux.add(spies[i])
                }
            }
        }
        fillBoard(c,1,5)

        for (card in aux) {
            c[card] = 'W'
        }

        return c
    }

    fun mirror(card: ArrayList<Char>): ArrayList<Char> {
        TODO()
    }

    fun start() {
        cardA = createBoard()
        fillBoard(cardA)
        cardB = couple(cardA)
    }


}