package com.example.rpgapp

import java.io.BufferedReader

class CSVReader() {
    var blockList = mutableListOf<Block>()


    fun readCsvFile(bf: BufferedReader) {
        for(l in bf.readLines()) {
            val blockProp = createMutableList(l.split(";"))
            while(blockProp.count() < 6){
                blockProp.add("-1")
            }
            this.blockList.add(
                Block(
                    blockProp[0].toInt(), blockProp[1], blockProp[2].toInt(),
                    blockProp[3].toInt(), blockProp[4].toInt(), blockProp[5].toInt()
                )
            )
        }
    }

    fun createMutableList(list:List<String>): MutableList<String> {
        val mList = mutableListOf<String>()
        for(l in list){
            mList.add(l)
        }
        return mList
    }

    fun getBlockById(id: Int): Block? {
        return this.blockList.find{it.id == id}
    }

    fun printBlocks() {
        for(b in blockList){
            b.printValues()
        }
    }

}