package com.example.domain.mappers

interface DaoMapper<OLD, NEW> {
    fun fromModel(data: OLD): NEW
    fun toModel(data: NEW): OLD
    fun fromModelList(listData: List<OLD>): List<NEW>
}

interface ApiMapper<OLD, NEW> {
    fun fromModel(data: OLD): NEW
    fun fromModelList(listData: List<OLD>): List<NEW>
}