package com.example.ejem16_activities

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

// data class Dato (val dato:String):Serializable El objeto tiene la capacidad de convertirse a bytes
data class Dato (val dato:String?):Parcelable // Igual oero Mas rapido
{
    constructor(parcel: Parcel) : this(parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(dato)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Dato> {
        override fun createFromParcel(parcel: Parcel): Dato {
            return Dato(parcel)
        }

        override fun newArray(size: Int): Array<Dato?> {
            return arrayOfNulls(size)
        }
    }
}