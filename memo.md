# Udemy Kotlin Fundamentals Memo

## 定数 & 変数宣言
- 定数 = val , 変数 = varを使用

``` Kotlin
fun main() {
    // 定数
    val firstName = "Donald"
    // 変数
    var lastName = "Trump"
    print("Hello $firstName $lastName")
}
```

## 初期化
``` Kotlin
fun main() {
    val name: String
    val age: Int
    
    // 初期化
    name = "Beast"
    age = 24
    print("Hello my name is $name I'm $age years old.")
}
```

## byte, short, long, float, double
- byte型
-128から127までの整数を格納。
小さな範囲の整数しか扱えないが、メモリの使用量が少ないため、大量のデータを扱う場合に効率的。

- short型
-32768から32767までの整数を格納。
byte型より広い範囲の整数を扱えるが、int型やlong型に比べるとまだ範囲は限られる。

- long型
-9223372036854775808から9223372036854775807までの整数を格納。
非常に広い範囲の整数を扱うことができる。巨大な数値を扱う必要がある場合に便利。

- float型
32ビットの浮動小数点数を格納。
約±3.4×10の38乗までの数値を扱うことができる。
単精度浮動小数点数と呼ばれ、double型に比べて精度は低いが、メモリの使用量が少なくて済む。
例えば、小数点以下の桁数が少ない数値や、それほど精度を必要としない計算などに使用される。

- double型
64ビットの浮動小数点数を格納。
約±1.7×10の308乗までの数値を扱うことができる。
倍精度浮動小数点数と呼ばれ、float型よりも高い精度で数値を扱うことができる。
より正確な計算が必要な場合や、非常に大きな数値、または非常に小さな数値を扱う場合に使用される。

```Kotlin
fun main() {
    val e = 2.7182818284
    val eFloat = 2.7182818284f
    print("e is $e eFloat is $eFloat")
}
```