# akcel

安卓上的excel

## 添加到项目

**Step 1.** Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

```groovy
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

**Step 2.** Add the dependency

```groovy
	dependencies {
	        implementation 'com.github.tosmo5:akcel:0.1.1'
	}
```



## 使用

```kotlin
@Sheet("SimpleData", 1)
data class SimpleData(
    val id: Long, val name: String,
    @DateTimeFormat
    val date: String
)

class DemoWorkbook(inputStream: InputStream, type: ExcelType) : KtWorkbook(inputStream, type) {
    override val sheets: List<KClass<*>>
        get() = listOf(SimpleData::class)
}

val inputStream: InputStream = ...
DemoWorkbook(inputStream, ExcelType.XLSX).read(SimpleData::class)
```



