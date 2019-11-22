import io.reactivex.Observable


fun main() {
    //just
    Observable.just("1", "2", "3")
            .subscribe(::println)

    //fromIterable
    val numberList = arrayListOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    Observable.fromIterable(numberList)
            .subscribe(::println)

    //defer
    val car = Car()

    val brandObservable = car.getBrandObservable()
    val brandDeferObservable = car.getBrandDeferObservable()

    car.brand = "BMW"

    brandObservable.map {
        "brandObservable $it"
    }.subscribe(::println)

    brandDeferObservable.map {
        "brandDeferObservable $it"
    }.subscribe(::println)


}

class Car {
    var brand: String = "DEFAULT"

    fun getBrandObservable(): Observable<String> = Observable.just(brand)

    fun getBrandDeferObservable(): Observable<String> = Observable.defer {
        Observable.just(brand)
    }
}