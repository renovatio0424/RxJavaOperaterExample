import io.reactivex.Observable

fun main() {
    //map
    Observable.fromIterable(arrayListOf(1, 2, 3, 4, 5, 6))
            .map { "side : $it" }
            .subscribe(::println)

    //flatMap
    Observable.fromIterable(arrayListOf("red", "green", "blue"))
            .flatMap { Observable.just("<$it>", "<$it>") }
            .subscribe(::println)

    //concatMap
    Observable.fromIterable(arrayListOf("red", "green", "blue"))
            .concatMap { Observable.just("<$it>", "<$it>") }
            .subscribe(::println)

    //switchMap
    Observable.fromIterable(arrayListOf("red", "green", "blue"))
            .switchMap { Observable.just("<$it>", "<$it>") }
            .subscribe(::println)

    //groupBy
    val circleList = arrayListOf<String>()
    val triangleList = arrayListOf<String>()

    Observable.fromIterable(arrayListOf("●-purple", "●-skyblue", "▲-yellow", "●-yellow", "▲-purple", "▲-skyblue"))
            .groupBy {
                it.contains("●-")
            }
            .subscribe({ group ->
                if (group.key == true)
                    group.subscribe { circleList.add(it) }
                else
                    group.subscribe { triangleList.add(it) }
            }, {
                //do nothing
            }, {
                for (circle in circleList)
                    println(circle)

                for (triangle in triangleList)
                    println(triangle)
            })
}