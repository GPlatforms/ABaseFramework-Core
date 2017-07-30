package android.baseframework.core.ext.webview.handler


class FileParcel: android.os.Parcelable {

    internal var id: Int = 0
    private var contentPath: String
    private var fileBase64: String

    constructor(parcel: android.os.Parcel) {
        id = parcel.readInt()
        contentPath = parcel.readString()
        fileBase64 = parcel.readString()
    }

    constructor(id: Int, contentPath: String, fileBase64: String) {
        this.id = id
        this.contentPath = contentPath
        this.fileBase64 = fileBase64
    }

    val CREATOR: android.os.Parcelable.Creator<FileParcel> = object : android.os.Parcelable.Creator<FileParcel> {
        override fun createFromParcel(parcel: android.os.Parcel): FileParcel {
            return FileParcel(parcel)
        }

        override fun newArray(size: Int): Array<FileParcel?> {
            return arrayOfNulls(size)
        }
    }

    fun getId(): Int {
        return id
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getContentPath(): String? {
        return contentPath
    }

    fun setContentPath(contentPath: String) {
        this.contentPath = contentPath
    }

    fun getFileBase64(): String? {
        return fileBase64
    }

    fun setFileBase64(fileBase64: String) {
        this.fileBase64 = fileBase64
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: android.os.Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeString(contentPath)
        dest.writeString(fileBase64)
    }

    override fun toString(): String {
        return "FileParcel{" +
                "id=" + id +
                ", contentPath='" + contentPath + '\'' +
                ", fileBase64='" + fileBase64 + '\'' +
                '}'
    }
}

