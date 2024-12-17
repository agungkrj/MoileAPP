import android.os.Parcel
import android.os.Parcelable

data class UserInfo(
    val phoneNumber: String,
    val address: String,
    val deliveryDate: String,
    val deliveryTime: String,
    val deliveryAddress: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "" 
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(phoneNumber)
        parcel.writeString(address)
        parcel.writeString(deliveryDate)
        parcel.writeString(deliveryTime)
        parcel.writeString(deliveryAddress)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<UserInfo> {
        override fun createFromParcel(parcel: Parcel): UserInfo {
            return UserInfo(parcel)
        }

        override fun newArray(size: Int): Array<UserInfo?> {
            return arrayOfNulls(size)
        }
    }
}
