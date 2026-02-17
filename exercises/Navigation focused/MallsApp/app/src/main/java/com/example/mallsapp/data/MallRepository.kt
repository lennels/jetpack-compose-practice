package com.example.mallsapp.data

import com.example.mallsapp.R
import com.example.mallsapp.model.Mall
import com.example.mallsapp.model.Region

object MallRepository {
    val singaporeMalls = listOf(
        Mall(
            id = 1,
            imgRes = R.drawable.ion,
            name = "ION Orchard",
            region = Region.CENTRAL,
            description = "High-end luxury flagship stores and iconic futuristic architecture."
        ),
        Mall(
            id = 2,
            imgRes = R.drawable.simlim,
            name = "Sim Lim Square",
            region = Region.CENTRAL,
            description = "The ultimate hub for IT gadgets, PC parts, and electronics repairs."
        ),
        Mall(
            id = 3,
            imgRes = R.drawable.nex,
            name = "NEX",
            region = Region.NORTH_EAST,
            description = "Major heartland mall with 24-hour groceries and a rooftop dog park."
        ),
        Mall(
            id = 4,
            imgRes = R.drawable.imm,
            name = "IMM",
            region = Region.WEST,
            description = "Singapore's largest outlet mall featuring over 100 designer brands."
        ),
        Mall(
            id = 5,
            imgRes = R.drawable.suntec,
            name = "Suntec City",
            region = Region.CENTRAL,
            description = "Massive complex with the Fountain of Wealth and a world-class convention center."
        )
    )
}

