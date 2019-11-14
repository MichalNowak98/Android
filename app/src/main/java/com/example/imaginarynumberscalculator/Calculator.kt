package com.example.imaginarynumberscalculator

class Calculator {
    private var re: Double = 0.0
    private var im: Double = 0.0

    fun add(aRe: Double, aIm: Double, bRe: Double, bIm: Double)
    {
        re = aRe + bRe
        im = aIm + bIm
    }

    fun sub(aRe: Double, aIm: Double, bRe: Double, bIm: Double)
    {
        re = aRe - bRe
        im = aIm - bIm
    }

    fun mul(aRe: Double, aIm: Double, bRe: Double, bIm: Double)
    {
        re = aRe * bRe - aIm * bIm
        im = aRe * bIm + aIm * bRe
    }

    fun div(aRe: Double, aIm: Double, bRe :Double, bIm: Double)
    {
        re = (aRe * bRe + aIm * bIm) / (bRe * bRe + bIm * bIm)
        im = (aIm * bRe - aRe * bIm) / (bRe * bRe + bIm * bIm)
    }

    fun getRe() = re

    fun getIm() = im
}