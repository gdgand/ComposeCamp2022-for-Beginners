class Song(val title: String, val artist: String, val year: Int, val playCount: Int) {

    val isFamous = {
        playCount >= 1000
    }

    fun p() {
        println("$title, 연주한 $artist, 출시한 $year")
    }
}