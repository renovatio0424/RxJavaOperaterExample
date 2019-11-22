import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

fun main() {

    //toXXX
    Observable.fromIterable(arrayListOf(1, 2, 3, 4, 5, 6))
            .map {
                "do something at $it"
            }
            .toList()
            .toObservable()
            .subscribe(::println)

    //combineLatest

    val color = PublishSubject.create<String>()
    val shape = PublishSubject.create<String>()

    Observable.combineLatest<String, String, String>(
            color, shape, BiFunction { color, shape -> "$color - $shape" })
            .subscribe(::println)

    color.onNext("purple")
    shape.onNext("diamond")
    color.onNext("orange")
    color.onNext("sky blue")
    shape.onNext("start")
    color.onNext("yellow")
    shape.onNext("pentagon")

    //concat
    val number1 = Observable.fromIterable(arrayListOf(1, 2, 3, 4, 5, 6)).delay(2, TimeUnit.SECONDS)
    val number2 = Observable.fromIterable(arrayListOf(10, 11, 12, 13, 14))

    Observable.concat(number1, number2)
            .subscribe {
                println(it)
            }

    Thread.sleep(3000)

    //zip
    Observable.zip(color, shape, BiFunction<String, String, String> { color, shape ->
        "$color & $shape"
    }).subscribe(::println)

    color.onNext("purple")
    shape.onNext("diamond")
    color.onNext("orange")
    color.onNext("sky blue")
    shape.onNext("start")
    color.onNext("yellow")
    shape.onNext("pentagon")

}