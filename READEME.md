### 扩展 javax.validation
* @MultipleString 允许参数为多个String值
```$xslt
@MultipleString(values = {"str1", "str2", "str3"}, message = "参数不合法")
```                     
* @MultipleInt 允许参数为多个int值
```$xslt
@MultipleInt(values = {0, 1, 2}, message = "参数不合法")
``` 
* @ListSize 校验集合的元素个数
```$xslt
@ListSize(min=0, max=500, message = "集合大小超过指定的值0-500")
``` 